package classes;

public class PacoteAventura extends PacoteViagem {
	
	String tipo;
	
	public PacoteAventura(Long pacote_id, String nome, String destino, int duracao, double preco, String tipo, Cliente cliente) {
		super(pacote_id, nome, destino, duracao, preco, cliente);
		this.tipo = "Aventura";
		//String detalhes = "Inclui trilhas e passeios de jipe";
		pacotes.add(this);
	}
}
