package classes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao_db.ClienteDAO;
import conexao_db.PacoteDAO;

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
		
	}
	
	
	// funcoes relacionadas aos pacotes
	public void listarPacotes() {
		JOptionPane.showMessageDialog(null, "Pacote Listado.");
		List<PacoteViagem> pacotes = PacoteDAO.listarTodos();
    	if(pacotes == null || pacotes.size() == 0) {
    		JOptionPane.showMessageDialog(null, "Não possui pacotes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);    		
    		return;
    	}
    	
    	StringBuilder listaPacote = new StringBuilder();
    	for(PacoteViagem p: pacotes) {
    		listaPacote.append("Nome: " + p.getNome() + "\n");
    		listaPacote.append("Destino: " + p.getDestino() + "\n");
    		listaPacote.append("Duração: " + p.getDuracao() + "\n");
    		listaPacote.append("Preco: " + p.getPreco() +"\n");
    		listaPacote.append("Tipo: " + p.getTipo() +"\n");
    		listaPacote.append("\n<<<<<<<<<<<<<<<\n");
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
		String nome = JOptionPane.showInputDialog("Digite o nome do Pacote: ");        
        PacoteViagem pacote = PacoteDAO.buscarPacote(nome);

        StringBuilder dadosPacote = new StringBuilder();
        dadosPacote.append("Dados do aluno a ser removido:\n");
        dadosPacote.append("Nome: ").append(pacote.getNome()).append("\n");
        dadosPacote.append("Destino: ").append(pacote.getDestino()).append("\n");
        dadosPacote.append("Duracao: ").append(pacote.getDuracao()).append("\n");
        dadosPacote.append("Preco: ").append(pacote.getPreco()).append("\n");
        dadosPacote.append("Tipo: ").append(pacote.getTipo());
        
        int confirmacao = JOptionPane.showConfirmDialog(null, 
                dadosPacote.toString() + "\n\nTem certeza de que quer deletar esse pacote?", 
                "Confirmar Remoção", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            PacoteDAO.deletarPacote(pacote.getNome());
            JOptionPane.showMessageDialog(null, "Pacote removido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
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
