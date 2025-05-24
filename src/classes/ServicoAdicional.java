
package classes;

import java.util.ArrayList;

public abstract class ServicoAdicional {
	// variaveis
	String nome;
	double preco;
	static ArrayList<ServicoAdicional> servicos = new ArrayList<ServicoAdicional>();
	
	// construtor
	public ServicoAdicional(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
		servicos.add(this);
	}
	
	//funcoes
	
	 public void adicionarServico(ServicoAdicional servico) {
	        servicos.add(servico);
	    }
	
	public void listarServicos() {
		for (ServicoAdicional s : servicos) {
			System.out.println(" Nome: " + s.nome +
							   "Preco: " + s.preco);
		}
	}
	
	public void buscarServico(String servicoBuscado) {
		for (ServicoAdicional s : servicos) {
			if (s.nome.equals(servicoBuscado)) {
				System.out.println(servicoBuscado + " foi encontrado!");
				return;
			}
		}
	}
	
	public void removerServico(String servicoRemovido) {
		for (ServicoAdicional s : servicos) {
			if (s.nome.equals(servicoRemovido)) {
				System.out.println(servicoRemovido + " foi removido!");
				servicos.remove(s);
				return;
			}
		}
	}
}

// Servicos adicionais
class Translado extends ServicoAdicional{
	public Translado(String nome, double preco) {
		super(nome,preco);
		servicos.add(this);
	}
}

class Passeios extends ServicoAdicional{
	public Passeios(String nome, double preco) {
		super(nome,preco);
		servicos.add(this);
	}
}

class MotoristaParticular extends ServicoAdicional{
	public MotoristaParticular(String nome, double preco) {
		super(nome,preco);
		servicos.add(this);
	}
}

class AluguelCarro extends ServicoAdicional{
	public AluguelCarro(String nome, double preco) {
		super(nome,preco);
		servicos.add(this);
	}
}
