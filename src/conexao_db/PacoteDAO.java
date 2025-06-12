package conexao_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.*;

import conexao_db.Conexao;

public class PacoteDAO {
	
	public static void inserirPacote(PacoteViagem pacote) {
    	String sql = "INSERT INTO pacotes (nome, destino, duracao_dias, preco, tipo_pacote) VALUES (?, ?, ?, ?, ?)";
    	Connection conn = null;
        try {
        	conn = Conexao.conectar();
        	PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, pacote.getNome());
            stmt.setString(2, pacote.getDestino());
            stmt.setInt(3, pacote.getDuracao());
            stmt.setDouble(4, pacote.getPreco());

            if (pacote instanceof PacoteAventura) {
                stmt.setString(5, "aventura");
            } else if (pacote instanceof PacoteLuxuoso){
                stmt.setString(5, "luxo"); 
            } else 
                stmt.setString(5, "cultural"); 
            
            
            stmt.executeUpdate();
            System.out.println("Pacote cadastrado com sucesso!");
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pacote.setPacoteId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if(conn != null)
        		Conexao.desconectar(conn);
        }
    }
	
	public static List<PacoteViagem> listarTodos() throws SQLException {
        List<PacoteViagem> pacotes = new ArrayList<>();
        String sql = "SELECT * FROM pacotes";
        Connection conn = null;
        
        try {
        	 conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	PacoteViagem p;
            	String tipo = rs.getString("tipo_pacote");
                String nome = rs.getString("nome");
                String destino = rs.getString("destino");
                int duracao = rs.getInt("duracao_dias");
                double preco = rs.getDouble("preco");
                
                switch(tipo) {
                	case "aventura":
                		p = new PacoteAventura(null, nome, destino, duracao, preco, destino, null);
                		break;
                	case "luxo":
                		p = new PacoteAventura(null, nome, destino, duracao, preco, destino, null);
                		break;
                	case "cultural":
                		p = new PacoteAventura(null, nome, destino, duracao, preco, destino, null);
                		break;
                	default:
                        throw new IllegalArgumentException("Tipo de pacote desconhecido: " + tipo);
                }
                
                p.setPacoteId(rs.getLong("pacote_id"));
                p.setTipo(tipo);
                pacotes.add(p);
            }
        } finally {
        	if(conn != null)
        		Conexao.desconectar(conn);
        }
        return pacotes;
    }
	
	public static PacoteViagem buscarPacote(String nome) throws SQLException{
		String sql = "SELECT * FROM pacotes WHERE nome = ?";
        Connection conn = null;
        try {
        	conn = Conexao.conectar();
        	 PreparedStatement stmt = conn.prepareStatement(sql);
        	 stmt.setString(1, nome);
        	 
        	 try(ResultSet rs = stmt.executeQuery()){
        		 if(rs.next()) {
        			 PacoteViagem pacote;
                     String tipo = rs.getString("tipo_pacote");
                     String destino = rs.getString("destino");
                     int duracao = rs.getInt("duracao_dias");
                     double preco = rs.getDouble("preco");
                     
                     switch(tipo) {
                     case "aventura":
                         pacote = new PacoteAventura(null, nome, destino, duracao, preco, null, null);
                         break;
                     case "luxo":
                         pacote = new PacoteLuxuoso(null, nome, destino, duracao, preco, null, null);
                         break;
                     case "cultural":
                         pacote = new PacoteCultural(null, nome, destino, duracao, preco, null, null);
                         break;
                     default:
                         throw new IllegalArgumentException("Tipo de pacote desconhecido: " + tipo);
                 }
                 
                 pacote.setPacoteId(rs.getLong("pacote_id"));
                 pacote.setTipo(tipo);
                 return pacote;
        		 }
        	 }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if(conn != null) {
        		Conexao.desconectar(conn);
        	}
        }
        
		return null;
	}
	
	public static void deletarPacote (String nome) throws SQLException {
		String sql = "DELETE FROM pacotes WHERE nome = ?";
		Connection conn = null;
		
		try {
			conn = Conexao.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, nome);
            stmt.executeUpdate();
		} finally {
			if(conn != null) {
				Conexao.desconectar(conn);
			}
		}
	}
	
}
