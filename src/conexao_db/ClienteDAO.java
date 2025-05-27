package conexao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import classes.Cliente;
import classes.ClienteEstrangeiro;
import classes.ClienteNacional;
import classes.PacoteAventura;
import classes.PacoteLuxuoso;
import classes.PacoteViagem;


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

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String passaporte = rs.getString("passaporte");

                Cliente cliente;
                if (cpf != null) {
                    cliente = new ClienteNacional(nome, telefone, email, cpf);
                } else {
                    cliente = new ClienteEstrangeiro(nome, telefone, email, passaporte);
                }

                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<Cliente> getClientePacote(String nome) throws SQLException {
        List<PacoteViagem> disciplinas = new ArrayList<>();
        String sql = "SELECT d.* FROM disciplina d " +
                    "JOIN aluno_disciplina ad ON d.id = ad.disciplina_id " +
                    "WHERE ad.aluno_id = ?";
        
        Connection conn = null;
        
        try {
        	conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, alunoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getLong("id"));
                    disciplina.setNome(rs.getString("nome"));
                    disciplina.setCodigo(rs.getString("codigo"));
                    disciplinas.add(disciplina);
                }
            }
        } finally {
        	if(conn != null)
        		DatabaseConnection.desconectar(conn);
        }
        return disciplinas;
    }
}
