package classes;

import java.util.ArrayList;
import java.util.List;
import classes.PacoteViagem;
import classes.CadastroPacote;
import conexao_db.ClienteDAO;

public abstract class Cliente {
	String nome;
	String telefone;
	String email;
	
	ArrayList<PacoteViagem> pacoteRelacionado = new ArrayList<PacoteViagem>();
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public Cliente(String nome, String telefone, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		}
	
	public void adicionarPacote(PacoteViagem pacote) {
		pacoteRelacionado.add(pacote);
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}
}

