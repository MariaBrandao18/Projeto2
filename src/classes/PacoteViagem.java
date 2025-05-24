package classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.Cliente;
import conexao_db.ClienteDAO;
import classes.CadastroPacote;

public abstract class PacoteViagem {
	
	private ArrayList<ServicoAdicional> servicosAdicionais = new ArrayList<>();
	static 	ArrayList<PacoteViagem> pacotes = new ArrayList<PacoteViagem>();
	
	//variaveis
	String nome;
	String destino;
	int duracao;
	double preco;
	String tipo = null;
	Cliente cliente;
	
	// construtor
	public PacoteViagem(String nome, String destino, int duracao, double preco, Cliente cliente) {
		this.nome = nome;
		this.destino = destino;
		this.duracao = duracao;
		this.preco = preco;
		this.cliente = cliente;
		cliente.adicionarPacote(this);
		pacotes.add(this);
		ClienteDAO conectivo = new ClienteDAO();
		conectivo.inserirPacote(this);
	}
	
	// getters
	public String getNome() {
		return nome;
	}
	public String getDestino() {
		return destino;
	}
	public int getDuracao() {
		return duracao;
	}
	public double getPreco() {
		return preco;
	}
	public String getTipo() {
		return tipo;
	}
 	public String getCliente() {
		return cliente.nome;
	}
	
	// funcoes relacionadas aos pacotes
	public void listarPacotes() {
		for (PacoteViagem p : pacotes) {
		    System.out.println("Nome: " + p.getNome() +
		                       " | Destino: " + p.getDestino() +
		                       " | Duração: " + p.getDuracao() +
		                       " | Preço: " + p.getPreco() +
		                       " | Tipo: " + p.getTipo() +
		                       "----------------------------");
		}
		
	}
	
	public void pesquisarPacotes(String nomeProcura) {
		for (PacoteViagem p : pacotes) {
			if (p.nome.equals(nomeProcura)) {
				System.out.println(p.nome + "encontrado!");
			}
		}
	}
	
	public void excluirPacote(String nomeExcluir) {
		for (int i = 0; i < pacotes.size(); i++) {
	        if (pacotes.get(i).nome.equals(nomeExcluir)) {
	            System.out.println(pacotes.get(i).nome + " excluído!");
	            pacotes.remove(i);
	            return;
	        }
		}
	}
	
	// funcoes relacionadas aos servicos
	
	 public void adicionarServico(ServicoAdicional servico) {
	        servicosAdicionais.add(servico);
	    }
	public void listarServicos() {
        if (servicosAdicionais.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Nenhum serviço adicional para o pacote " + nome);
            return;
        }

        StringBuilder mensagem = new StringBuilder("Serviços adicionais para " + nome + ":\n");
        for (ServicoAdicional s : servicosAdicionais) {
            mensagem.append(" - ")
                    .append(s.getNome())
                    .append(" (R$").append(s.getPreco()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }
}
