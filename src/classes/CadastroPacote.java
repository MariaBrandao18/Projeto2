
package classes;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import classes.PacoteViagem;
import classes.Cliente;

public class CadastroPacote {
	
	// declarando listas & servicos
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	static 	ArrayList<PacoteViagem> pacotes = new ArrayList<PacoteViagem>();
	
	private ServicoAdicional Translado;
	private ServicoAdicional Passeios;
	private ServicoAdicional MotoristaParticular;
	private ServicoAdicional AluguelCarro;
	
	
	// construindo objetos para testes
	public CadastroPacote() {
		Cliente cliente1 = new ClienteNacional("Maria", 45612387, "maria@gmail.com", 4567845);
		clientes.add(cliente1);
		PacoteViagem pacote1 = new PacoteAventura("Familia Radical", "Cancun", 7, 15000.00, "Luxuoso", cliente1);
		pacotes.add(pacote1);
		Translado = new Translado("Translado",1000);
		Passeios = new Passeios("Passeios",400);
		MotoristaParticular = new MotoristaParticular("Motorista particular",800);
		AluguelCarro = new AluguelCarro("Aluguel de carro",200);
	}
	
	
	// funcoes relacionadas aos pacotes
	public void listarPacotes() {
		JOptionPane.showMessageDialog(null, "Pacote Listado.");
		for (PacoteViagem p : pacotes) {
			JOptionPane.showMessageDialog(null, "\nNome: " + p.getNome() +
		                       "\n | Destino: " + p.getDestino() +
		                       "\n | Duração: " + p.getDuracao() +
		                       "\n | Preço: " + p.getPreco() +
		                       "\n | Tipo: " + p.getTipo());
		}
		
	}
	
	public void pesquisarPacotes() {
		String nome = JOptionPane.showInputDialog("Digite o nome ou o destino:");
		for (PacoteViagem p : pacotes) {
			if (p.nome.equals(nome) || p.destino.equals(nome)) {
				JOptionPane.showMessageDialog(null, "Pacote Encontrado! "
						+  "\n | Nome: " + p.getNome() +
	                       "\n | Destino: " + p.getDestino() +
	                       "\n | Duração: " + p.getDuracao() +
	                       "\n | Preço: " + p.getPreco() +
	                       "\n | Tipo: " + p.getTipo() +
	                       "\n | Cliente Relacionado: " + p.getCliente());
			}
		}
	}
	
	public void excluirPacote() {
		boolean removido = false;
		String excluirPacote = JOptionPane.showInputDialog("Digite o Pacote para remover:");
		for (int i = 0; i < pacotes.size(); i++) {
	        if (pacotes.get(i).nome.equals(excluirPacote)) {
	        	pacotes.remove(i);
				JOptionPane.showMessageDialog(null, "Pacote " + excluirPacote + " removido!");
				removido = true;
				break;
	        }
		}
		if(!removido) {
			JOptionPane.showMessageDialog(null, "Pacote não encontrado ou já removido!");
		}
	}
	
	// funcoes relacionadas aos servicos
	public void incluirServico() {
		String nome = JOptionPane.showInputDialog("Digite o nome ou destino do pacote:");
		PacoteViagem pacoteEncontrado = null;
		for (PacoteViagem p : pacotes) {
			if (p.nome.equals(nome) || p.destino.equals(nome)) {
				pacoteEncontrado = p;
				break;
			}
		}
		String[] opcoesServico = {"Translado", "Passeios", "Motorista Particular", "Aluguel de carro", "Cancelar"};
	       int opcao;
	       do {
	           opcao = JOptionPane.showOptionDialog(
	               null,
	               "Servicos Adicionais",
	               "Menu",
	               JOptionPane.DEFAULT_OPTION,
	               JOptionPane.INFORMATION_MESSAGE,
	               null,
	               opcoesServico,
	               opcoesServico[0]
	           );
	          
	           switch (opcao) {
	               case 0:
	            	   pacoteEncontrado.adicionarServico(Translado);
	            	   JOptionPane.showMessageDialog(null, "Translado incluso no pacote!");
	            	   pacoteEncontrado.listarServicos();
	                   break;
	               case 1:
	            	   pacoteEncontrado.adicionarServico(Passeios);
	            	   JOptionPane.showMessageDialog(null, "Passeios adicionais inclusos no pacote!");
	            	   pacoteEncontrado.listarServicos();
	                   break;
	               case 2:
	            	   pacoteEncontrado.adicionarServico(MotoristaParticular);
	            	   JOptionPane.showMessageDialog(null, "Motorista particular incluso no pacote!");
	            	   pacoteEncontrado.listarServicos();
	            	   break;
	               case 3:
	            	   pacoteEncontrado.adicionarServico(AluguelCarro);
	            	   JOptionPane.showMessageDialog(null, "Aluguel de carro incluso no pacote!");
	            	   pacoteEncontrado.listarServicos();
	                   break;
	               case 4:
	            	   JOptionPane.showMessageDialog(null, "Saindo...");
	            	   break;
	               default:
	                   break;
	           }
	       } while (opcao != 4);
	}
	

	public static void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
}
