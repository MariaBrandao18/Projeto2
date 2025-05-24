package classes;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import classes.Cliente;
import classes.PacoteViagem;

public class CadastroCliente {
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public CadastroCliente(){
		Cliente cliente1 = new ClienteNacional("Maria", 45612387, "maria@gmail.com", 4567845);
		clientes.add(cliente1);
	}
	
	
	public void Listar() {
		JOptionPane.showMessageDialog(null, "Clientes Listados.");
		for (int i = 0; i < clientes.size(); i++) {
			JOptionPane.showMessageDialog(null, clientes.get(i).nome);
		}
	}
	
	public void Procurar(){
		String nome = JOptionPane.showInputDialog("Nome procurado:");
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).nome.equals(nome)) {
				JOptionPane.showMessageDialog(null, "Cliente encontrado! Nome: " + clientes.get(i).getNome() + " Telefone: " + clientes.get(i).getTelefone() + " Email: " + clientes.get(i).getEmail());
				}
			}
		}
	
	public void Eliminar() {
		boolean removido = false;
		String nomeRemove = JOptionPane.showInputDialog("Nome procurado:");
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).nome.equals(nomeRemove)) {
				clientes.remove(i);
				JOptionPane.showMessageDialog(null, "Nome " + nomeRemove + " removido!");
				removido = true;
				break;
			}
		}
		if(!removido) {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado ou já removido!");
		}
	}
	public void InserirDAO(Cliente cliente) {
		CadastroPacote.adicionarCliente(cliente);
		ClienteDAO conectivo = new ClienteDAO();
		conectivo.inserirCliente(cliente);
		}
	
	public void clientePacote() {
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

	}
}
