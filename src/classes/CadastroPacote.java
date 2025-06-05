package classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao_db.ClienteDAO;
import conexao_db.PacoteDAO;

public class CadastroPacote {
	
	// declarando listas & servicos
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	static 	ArrayList<PacoteViagem> pacotes = new ArrayList<PacoteViagem>();	
	
	// construindo objetos para testes
	public CadastroPacote() {
		
	}
	
	
	// funcoes relacionadas aos pacotes
	public static void listarPacotes() throws SQLException{
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
    		listaPacote.append("\n<<<<<<<<<<<<<<<\n");}
	}
	
	public void pesquisarPacotes() throws SQLException {
		String nome = JOptionPane.showInputDialog("Digite o nome ou o destino:");
		PacoteViagem p = PacoteDAO.buscarPacote(nome);
		
		if (p == null) {
	        List<PacoteViagem> todosPacotes = PacoteDAO.listarTodos();
	        for (PacoteViagem pacote : todosPacotes) {
	            if (pacote.getDestino().equalsIgnoreCase(nome)) {
	                p = pacote;
	                break;
	            }
	        }
	    }
	    
	    if (p != null) {
	        JOptionPane.showMessageDialog(null, "Pacote Encontrado! "
	                + "\n | Nome: " + p.getNome() +
	                "\n | Destino: " + p.getDestino() +
	                "\n | Duração: " + p.getDuracao() +
	                "\n | Preço: " + p.getPreco() +
	                "\n | Tipo: " + p.getTipo() +
	                "\n | Cliente Relacionado: " + (p.getCliente() != null ? p.getCliente() : "Nenhum"));
	    } else {
	        JOptionPane.showMessageDialog(null, "Nenhum pacote encontrado com nome ou destino: " + nome, 
	                                   "Não encontrado", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	public  void excluirPacote() throws SQLException{
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
	public void incluirServico() throws SQLException {
	    String nome = JOptionPane.showInputDialog("Digite o nome ou destino do pacote:");
	    
	    PacoteViagem pacoteEncontrado = null;
	    List<PacoteViagem> todosPacotes = PacoteDAO.listarTodos();
	    for (PacoteViagem p : todosPacotes) {
	        if (p.getNome().equalsIgnoreCase(nome) || p.getDestino().equalsIgnoreCase(nome)) {
	            pacoteEncontrado = p;
	            break;
	        }
	    }
	    
	    if (pacoteEncontrado == null) {
	        JOptionPane.showMessageDialog(null, "Pacote não encontrado!");
	        return;
	    }
	    
	    ServicoAdicional translado = new Translado("Translado Aeroporto-Hotel", 150.0);
	    ServicoAdicional passeios = new Passeios("Passeios Turísticos", 200.0);
	    ServicoAdicional motorista = new MotoristaParticular("Motorista Particular", 300.0);
	    ServicoAdicional carro = new AluguelCarro("Aluguel de Carro", 250.0);
	    
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
	                pacoteEncontrado.getServicosAdicionais().add(translado);
	                JOptionPane.showMessageDialog(null, 
	                    "Translado incluso no pacote! Preço: R$" + translado.getPreco());
	                break;
	            case 1:
	                pacoteEncontrado.getServicosAdicionais().add(passeios);
	                JOptionPane.showMessageDialog(null, 
	                    "Passeios adicionais inclusos no pacote! Preço: R$" + passeios.getPreco());
	                break;
	            case 2:
	                pacoteEncontrado.getServicosAdicionais().add(motorista);
	                JOptionPane.showMessageDialog(null, 
	                    "Motorista particular incluso no pacote! Preço: R$" + motorista.getPreco());
	                break;
	            case 3:
	                pacoteEncontrado.getServicosAdicionais().add(carro);
	                JOptionPane.showMessageDialog(null, 
	                    "Aluguel de carro incluso no pacote! Preço: R$" + carro.getPreco());
	                break;
	            case 4:
	                JOptionPane.showMessageDialog(null, "Saindo...");
	                break;
	            default:
	                break;
	        }
	        
	        StringBuilder servicosAtuais = new StringBuilder("Serviços atuais:\n");
	        for (ServicoAdicional s : pacoteEncontrado.getServicosAdicionais()) {
	            servicosAtuais.append("- ").append(s.getNome())
	                         .append(" (R$").append(s.getPreco()).append(")\n");
	        }
	        JOptionPane.showMessageDialog(null, servicosAtuais.toString());
	        
	    } while (opcao != 4);
	}
	

	public static void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
}
