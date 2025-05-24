package classes;

public class PacoteCultural extends PacoteViagem {
	
	String tipo;
	
	public PacoteCultural(String nome, String destino, int duracao, double preco, String tipo, Cliente cliente) {
		super(nome, destino, duracao, preco, cliente);
		this.tipo = "Cultural";
		String detalhes = "Inclui visitas a museus, centros históricos e experiências gastronômicas";
		pacotes.add(this);
	}
}
