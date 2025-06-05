package classes;

public class PacoteLuxuoso extends PacoteViagem {
	
	String tipo;
	
	public PacoteLuxuoso(Long pacote_id, String nome, String destino, int duracao, double preco, String tipo, Cliente cliente) {
		super(pacote_id, nome, destino, duracao, preco, cliente);
		this.tipo = "Luxo";
		//String detalhes = "Mais comodidade, viagem de primeira classe e motoristas inclusos";
		pacotes.add(this);
	}
}
