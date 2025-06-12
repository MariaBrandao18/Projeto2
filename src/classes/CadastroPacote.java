package classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao_db.PacoteDAO;

public class CadastroPacote {
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	static 	ArrayList<PacoteViagem> pacotes = new ArrayList<PacoteViagem>();	
	
	public static void inserirPacote() {
		String nome = JOptionPane.showInputDialog("Digite o nome do pacote:");
	    if (nome == null || nome.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Nome obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String destino = JOptionPane.showInputDialog("Digite o destino do pacote:");
	    if (destino == null || destino.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Destino obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int duracao = 0;
	    try {
	        String duracaoStr = JOptionPane.showInputDialog("Digite a duração do pacote (em dias):");
	        if (duracaoStr == null || duracaoStr.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Duração obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        duracao = Integer.parseInt(duracaoStr);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Duração deve ser um número inteiro!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    double preco = 0;
	    try {
	        String precoStr = JOptionPane.showInputDialog("Digite o preço do pacote:");
	        if (precoStr == null || precoStr.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Preço obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        preco = Double.parseDouble(precoStr);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Preço deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    String[] options = {"Aventura", "Cultural", "Luxuoso"};
	    int tipo = JOptionPane.showOptionDialog(null, 
	            "Selecione o tipo de pacote:", 
	            "Tipo de Pacote",
	            JOptionPane.DEFAULT_OPTION, 
	            JOptionPane.QUESTION_MESSAGE,
	            null, 
	            options, 
	            options[0]);

	    if (tipo == JOptionPane.CLOSED_OPTION) {
	        JOptionPane.showMessageDialog(null, "Tipo de pacote obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String[] tipoPacote = {"A", "C", "L"};
	    String tipoSelecionado = tipoPacote[tipo];

	    PacoteViagem pacoteViagem;
	    if (tipoSelecionado.equals("A")) {
	        pacoteViagem = new PacoteAventura(null, nome, destino, duracao, preco);
	    } else if (tipoSelecionado.equals("C")) {
	        pacoteViagem = new PacoteCultural(null, nome, destino, duracao, preco);
	    } else {
	        pacoteViagem = new PacoteLuxuoso(null, nome, destino, duracao, preco);
	    }
	    pacoteViagem.setTipo(tipoSelecionado);

	    StringBuilder confirmacao = new StringBuilder();
	    confirmacao.append("Confirme os dados do pacote:\n\n");
	    confirmacao.append("Nome: ").append(nome).append("\n");
	    confirmacao.append("Destino: ").append(destino).append("\n");
	    confirmacao.append("Duracao: ").append(duracao).append("\n");
	    confirmacao.append("Preço: ").append(preco).append("\n");
	    confirmacao.append("Tipo: ").append(options[tipo]).append("\n");

	    int resposta = JOptionPane.showConfirmDialog(null, 
	            confirmacao.toString(), 
	            "Confirmar Cadastro", 
	            JOptionPane.YES_NO_OPTION, 
	            JOptionPane.QUESTION_MESSAGE);

	    if (resposta == JOptionPane.YES_OPTION) {
	        PacoteDAO.inserirPacote(pacoteViagem);
			JOptionPane.showMessageDialog(null, "Pacote cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Cadastro cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	
	// funcoes relacionadas aos pacotes
	public static void listarPacotes() throws SQLException{
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
    	
    	JOptionPane.showMessageDialog(null, listaPacote.toString(), "Pacotes:", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void pesquisarPacotes() throws SQLException {
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
	
	public static  void excluirPacote() throws SQLException{
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
	public static void incluirServico() throws SQLException {
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
