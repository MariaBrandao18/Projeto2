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
	
	public Cliente(String nome, String telefone, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
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

