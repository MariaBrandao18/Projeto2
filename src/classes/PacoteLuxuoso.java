package classes;

public class PacoteLuxuoso extends PacoteViagem {
	
	String tipo;
	
	public PacoteLuxuoso(String nome, String destino, int duracao, double preco, String tipo, Cliente cliente) {
		super(nome, destino, duracao, preco, cliente);
		this.tipo = "Luxo";
		String detalhes = "Mais comodidade, viagem de primeira classe e motoristas inclusos";
		pacotes.add(this);
	}
}
