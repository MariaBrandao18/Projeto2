package classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import conexao_db.ClienteDAO;

public class CadastroCliente {
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static final ClienteDAO clienteDAO = new ClienteDAO();
	
	public CadastroCliente(){
		
		//clientes.add(cliente1);
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
		String nome = JOptionPane.showInputDialog("Digite o CPF do aluno:");
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Cliente cliente = clienteDAO.buscarCliente(nome);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Dados do aluno:\n");
        mensagem.append("Nome: ").append(cliente.getNome()).append("\n");
        mensagem.append("Telefone: ").append(cliente.getTelefone()).append("\n");
        //mensagem.append("CPF: ").append(cliente.getCpf()).append("\n");
        
        // Adicionar disciplinas
        List<PacoteViagem> pacotes = clienteDAO.getClientePacote(cliente.getClienteId());
        mensagem.append("Pacotes cadastrados:\n");
        if (pacotes.isEmpty()) {
            mensagem.append("Nenhuma disciplina matriculada.");
        } else {
            for (PacoteViagem p : pacotes) {
                mensagem.append("- ").append(p.getNome()).append(" (").append(p.getPacoteId()).append(")\n");
            }
        }
        
        JOptionPane.showMessageDialog(null, mensagem.toString(), "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
		}
	
	public void EliminarCliente() throws SQLException{
		String nome = JOptionPane.showInputDialog("Digite o nome do Cliente:");
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Cliente cliente = clienteDAO.buscarCliente(nome);
        if (nome == null) {
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
            clienteDAO.deletarCliente(cliente.getNome());
            JOptionPane.showMessageDialog(null, "Cliente removido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
	}
	public void InserirDAO(Cliente cliente) {
		CadastroPacote.adicionarCliente(cliente);
		ClienteDAO conectivo = new ClienteDAO();
		conectivo.inserirCliente(cliente);
		}
	
	/*public void clientePacote() {
		String nome = JOptionPane.showInputDialog("Nome do cliente:");
		boolean clienteEncontrado = false;
		for(Cliente c: clientes) {
			if(c.getNome().equals(nome)) {
				clienteEncontrado = true;
				if(c.pacoteRelacionado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O cliente não possui pacotes cadastrados.");
				} else {
					StringBuilder info = new StringBuilder("Pacotes do cliente " + c.getNome() + ":\n");
	                for (PacoteViagem p : c.pacoteRelacionado) {
	                    info.append("- ")
	                        .append(p.getNome()).append(" | ");
	                }
	                JOptionPane.showMessageDialog(null, info.toString());
				}
				break;
			} 
		}
		if (!clienteEncontrado) {
	        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
	    }

	}*/
}
