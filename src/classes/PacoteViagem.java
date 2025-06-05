package classes;

import java.util.ArrayList;

public abstract class PacoteViagem {
	private Long pacote_id;
	private String nome;
	private String destino;
	private int duracao;
	private double preco;
	private String tipo = null;
	private ArrayList<ServicoAdicional> servicosAdicionais = new ArrayList<>();
	private Cliente cliente;
	static 	ArrayList<PacoteViagem> pacotes = new ArrayList<PacoteViagem>();
	
	public PacoteViagem() {
		
	}
	
	// construtor
	public PacoteViagem(Long id, String nome, String destino, int duracao, double preco, Cliente cliente) {
		this.nome = nome;
		this.destino = destino;
		this.duracao = duracao;
		this.preco = preco;
	}
	
	// getters e setters
	
	public Long getPacoteId() {
        return pacote_id;
    }

    public void setPacoteId(Long pacote_id) {
        this.pacote_id = pacote_id;
    }
	
	public ArrayList<ServicoAdicional> getServicosAdicionais() {
		return servicosAdicionais;
	}

	public void setServicosAdicionais(ArrayList<ServicoAdicional> servicosAdicionais) {
		this.servicosAdicionais = servicosAdicionais;
	}

	public static ArrayList<PacoteViagem> getPacotes() {
		return pacotes;
	}

	public static void setPacotes(ArrayList<PacoteViagem> pacotes) {
		PacoteViagem.pacotes = pacotes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String toString() {
		return "Pacote{" + "Nome: " + nome + ", Destino: " + destino + ", Duração: " + duracao + ", Preço: " + preco + ", Tipo: " + tipo;
	}

}
