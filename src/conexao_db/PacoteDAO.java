package conexao_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.PacoteAventura;
import classes.PacoteLuxuoso;
import classes.PacoteViagem;

import conexao_db.Conexao;

public class PacoteDAO {
	
	public void inserirPacote(PacoteViagem pacote) {
    	String sql = "INSERT INTO pacotes (nome, destino, duracao_dias, preco, tipo_pacote) VALUES (?, ?, ?, ?, ?)";
    	Connection conn = null;
        try {
        	conn = Conexao.conectar();
        	PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, pacote.getNome());
            stmt.setString(2, pacote.getDestino());
            stmt.setInt(3, pacote.getDuracao());
            stmt.setDouble(4, pacote.getPreco());

            if (pacote instanceof PacoteAventura) {
                stmt.setString(5, "Aventura");
            } else if (pacote instanceof PacoteLuxuoso){
                stmt.setString(5, "Luxo"); 
            } else 
                stmt.setString(5, "Cultural"); 
            
            
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
	
	public List<PacoteViagem> listarTodos() throws SQLException {
        List<PacoteViagem> pacotes = new ArrayList<>();
        String sql = "SELECT * FROM pacotes";
        Connection conn = null;
        
        try {
        	 conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                PacoteViagem p = new PacoteViagem();
                p.setNome(rs.getString("nome"));
                p.setDuracao(rs.getInt("duracao"));
                p.setDestino(rs.getString("destino"));
                p.setPreco(rs.getDouble("preco"));
                p.setTipo(rs.getString("tipo"));
                p.add(pacotes);
            }
        } finally {
        	if(conn != null)
        		Conexao.desconectar(conn);
        }
        return pacotes;
    }
	
	public PacoteViagem buscarPacote(String nome) throws SQLException{
		String sql = "SELECT * FROM pacotes WHERE nome = ?";
        Connection conn = null;
        try {
        	conn = Conexao.conectar();
        	 PreparedStatement stmt = conn.prepareStatement(sql);
        	 stmt.setString(1, nome);
        	 
        	 try(ResultSet rs = stmt.executeQuery()){
        		 if(rs.next()) {
        			 PacoteViagem pacote = new PacoteViagem();
        			 pacote.setPacoteId(rs.getLong("id"));
        			 pacote.setNome(rs.getString("nome"));
        			 pacote.setDestino(rs.getString("destino"));
        			 pacote.setDuracao(rs.getInt("duracao"));
        			 pacote.setPreco(rs.getDouble("preco"));
        			 pacote.setTipo(rs.getString("tipo"));
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
	
	public void deletarPacote (String nome) throws SQLException {
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
