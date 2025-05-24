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
    
    public void inserirPacote(PacoteViagem pacote) {
    	String sql = "INSERT INTO pacotes (nome, destino, duracao_dias, preco, tipo_pacote) VALUES (?, ?, ?, ?, ?)"; 
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
}
