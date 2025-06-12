package classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import conexao_db.ClienteDAO;

public class CadastroCliente {
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static final ClienteDAO clienteDAO = new ClienteDAO();
	
	public static void incluirCliente() throws SQLException {
	    String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
	    if (nome == null || nome.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Nome obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String telefone = JOptionPane.showInputDialog("Digite o telefone do cliente:");
	    if (telefone == null || telefone.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Telefone obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String email = JOptionPane.showInputDialog("Digite o email do cliente:");
	    if (email == null || email.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Email obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String[] options = {"Nacional", "Estrangeira"};
	    int tipo = JOptionPane.showOptionDialog(null, 
	            "Selecione o tipo de cliente:", 
	            "Tipo de Cliente",
	            JOptionPane.DEFAULT_OPTION, 
	            JOptionPane.QUESTION_MESSAGE,
	            null, 
	            options, 
	            options[0]);

	    if (tipo == JOptionPane.CLOSED_OPTION) {
	        JOptionPane.showMessageDialog(null, "Tipo de cliente obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String tipoCliente = (tipo == 0) ? "N" : "E";

	    Cliente cliente;
	    if (tipoCliente.equals("N")) {
	        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
	        if (cpf == null || cpf.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "CPF obrigatório para nacional!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        cliente = new ClienteNacional(null, nome, telefone, email, cpf);
	    } else {
	        String passaporte = JOptionPane.showInputDialog("Digite o numero do passaporte do cliente:");
	        if (passaporte == null || passaporte.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Passaporte obrigatório para estrangeiro!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        cliente = new ClienteEstrangeiro(null, nome, telefone, email, passaporte);
	    }

	    StringBuilder confirmacao = new StringBuilder();
	    confirmacao.append("Confirme os dados do cliente:\n\n");
	    confirmacao.append("Nome: ").append(nome).append("\n");
	    confirmacao.append("Telefone: ").append(telefone).append("\n");
	    confirmacao.append("Email: ").append(email).append("\n");
	    confirmacao.append("Tipo: ").append(tipoCliente.equals("N") ? "Nacional" : "Estrangeiro").append("\n");

	    int resposta = JOptionPane.showConfirmDialog(null, 
	            confirmacao.toString(), 
	            "Confirmar Cadastro", 
	            JOptionPane.YES_NO_OPTION, 
	            JOptionPane.QUESTION_MESSAGE);

	    if (resposta == JOptionPane.YES_OPTION) {
	        clienteDAO.inserirCliente(cliente);
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Cadastro cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	
	public static void ListarCliente() throws SQLException{
		List<Cliente> clientes = clienteDAO.listarClientes();
    	if(clientes == null || clientes.size() == 0) {
    		JOptionPane.showMessageDialog(null, "Não há clientes", "Erro", JOptionPane.ERROR_MESSAGE);    		
    		return;
    	}
    	
    	StringBuilder dadosCliente = new StringBuilder();
    	for(Cliente c: clientes) {
    		dadosCliente.append("Nome: " + c.getNome() + "\n");
    		dadosCliente.append("Telefone: " + c.getTelefone() + "\n");
    		dadosCliente.append("Email: " + c.getEmail() + "\n");
    		if(c.getCpf() != null) {
    			dadosCliente.append("CPF: " + c.getCpf());
    		}
    		else if(c.getPassaporte() == null) {
    			dadosCliente.append("Passaporte: " + c.getPassaporte());
    		}
    		dadosCliente.append("\n----------------\n");
    		dadosCliente.append("Pacotes: \n");
    		List<PacoteViagem> pacotes = clienteDAO.getClientePacote(c.getClienteId());
    		
    		for(PacoteViagem d: pacotes) {
    			dadosCliente.append(d.getNome() + "\n");
    		}
    		dadosCliente.append("\n<<<<<<<<<<<<<<<\n");
    		
    		dadosCliente.append("\n-----------------------\n");
    	}
    	JOptionPane.showMessageDialog(null, dadosCliente.toString(), "Clientes:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void ProcurarCliente() throws SQLException{
		String documento = JOptionPane.showInputDialog("Digite o documento(CPF / Passaporte) do cliente:");
        if (documento == null || documento.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Documento obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Cliente cliente = ClienteDAO.buscarCliente(documento);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Dados do cliente:\n");
        mensagem.append("Nome: ").append(cliente.getNome()).append("\n");
        mensagem.append("Telefone: ").append(cliente.getTelefone()).append("\n");
        if(cliente.getCpf() != null) {
			mensagem.append("CPF: " + cliente.getCpf() + "\n");
		}
		else if(cliente.getPassaporte() == null) {
			mensagem.append("Passaporte: " + cliente.getPassaporte() + "\n");
		}
        List<PacoteViagem> pacotes = clienteDAO.getClientePacote(cliente.getClienteId());
        mensagem.append("Pacotes cadastrados:\n");
        if (pacotes.isEmpty()) {
            mensagem.append("Nenhum pacote associado.");
        } else {
            for (PacoteViagem p : pacotes) {
                mensagem.append("- ").append(p.getNome()).append(" (").append(p.getPacoteId()).append(")\n");
            }
        }
        
        JOptionPane.showMessageDialog(null, mensagem.toString(), "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
		}
	
	public static void EliminarCliente() throws SQLException{
		String documento = JOptionPane.showInputDialog("Digite o documento do Cliente:");
        if (documento == null || documento.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Cliente cliente = ClienteDAO.buscarCliente(documento);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        StringBuilder dadosCliente = new StringBuilder();
        dadosCliente.append("Nome: " + cliente.getNome() + "\n");
		dadosCliente.append("Telefone: " + cliente.getTelefone() + "\n");
		dadosCliente.append("\n----------------\n");
		dadosCliente.append("Pacotes: \n");
		
        int confirmacao = JOptionPane.showConfirmDialog(null, 
        		dadosCliente.toString() + "\n\nTem certeza que deseja remover este cliente?", 
                "Confirmar Remoção", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            ClienteDAO.deletarCliente(cliente.getNome());
            JOptionPane.showMessageDialog(null, "Cliente removido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}
