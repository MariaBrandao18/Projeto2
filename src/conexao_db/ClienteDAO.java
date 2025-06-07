package conexao_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.*;


public class ClienteDAO {
	
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, telefone, email, cpf, passaporte) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());

            if (cliente instanceof ClienteNacional) {
                stmt.setString(4, ((ClienteNacional) cliente).getCpf());
                stmt.setNull(5, Types.VARCHAR);
            } else {
                stmt.setNull(4, Types.VARCHAR);
                stmt.setString(5, ((ClienteEstrangeiro) cliente).getPassaporte());
            }

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        Connection conn = null;
        
        try {
        	conn = Conexao.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String passaporte = rs.getString("passaporte");

                Cliente c;
                if (cpf != null) {
                    c = new ClienteNacional(null , nome, telefone, email, cpf);
                } else {
                    c = new ClienteEstrangeiro(null , nome, telefone, email, passaporte);
                }
                c.setClienteId(rs.getLong("id"));
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
    
    public List<PacoteViagem> getClientePacote(Long cliente_id) throws SQLException {
        List<PacoteViagem> pacotesRelacionados = new ArrayList<>();
        String sql = "SELECT p.* FROM pacotes p " +
                    "JOIN cliente_pacote cp ON p.pacote_id = cp.pacote_id " +
                    "WHERE cp.cliente_id = ?";
        
        Connection conn = null;
        
        try {
        	conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, cliente_id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String tipo = rs.getString("tipo_pacote");
                    String nome = rs.getString("nome");
                    String destino = rs.getString("destino");
                    int duracao = rs.getInt("duracao_dias");
                    double preco = rs.getDouble("preco");
                    
                    PacoteViagem pacote;
                    switch (tipo != null ? tipo.toUpperCase() : "") {
                    case "CULTURAL":
                        pacote = new PacoteCultural(null , nome, destino, duracao, preco, tipo, null);
                        break;
                    case "LUXO":
                        pacote = new PacoteLuxuoso(null, nome, destino, duracao, preco, tipo, null);
                        break;
                    case "AVENTURA":
                        pacote = new PacoteAventura(null, nome, destino, duracao, preco, tipo, null);
                        break;
                    default:
                    	throw new IllegalArgumentException("Tipo de pacote desconhecido: " + tipo);
                }   
                    pacotesRelacionados.add(pacote);
                }
                
            }
        } finally {
        	if(conn != null)
        		Conexao.desconectar(conn);
        }
        return pacotesRelacionados;
    }
    
    public static void deletarCliente (String nome) throws SQLException {
		String sql = "DELETE FROM clientes WHERE nome = ?";
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
    
    public static Cliente buscarCliente(String nome) throws SQLException{
		String sql = "SELECT * FROM clientes WHERE nome = ?";
        Connection conn = null;
        try {
        	conn = Conexao.conectar();
        	 PreparedStatement stmt = conn.prepareStatement(sql);
        	 stmt.setString(1, nome);
        	 
        	 try(ResultSet rs = stmt.executeQuery()){
        		 if(rs.next()) {
        			 Cliente c;
                     String telefone = rs.getString("telefone");
                     String email = rs.getString("email");
                     String tipo_cliente = rs.getString("tipo_cliente");
                     String cpf = rs.getString("cpf");
                     String passaporte = rs.getString("passaporte");
                     
                     switch(tipo_cliente) {
                     case "Aventura":
                         c = new ClienteNacional(null, nome, telefone, email, cpf);
                         break;
                     case "Luxuoso":
                         c = new ClienteEstrangeiro(null, nome, telefone, email, passaporte);
                         break;
                     default:
                         throw new IllegalArgumentException("Cliente desconhecido: " + tipo_cliente);
                 }
                 
                 c.setClienteId(rs.getLong("id"));
                 c.setTipo_cliente(tipo_cliente);
                 return c;
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
}
