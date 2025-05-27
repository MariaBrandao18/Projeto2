package classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	private String nome;
	private String telefone;
	private String email;
	private List<PacoteViagem> pacoteRelacionado;
	
	public Cliente() {
		
	}

	public Cliente(String nome, String telefone, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PacoteViagem> getPacoteRelacionado() {
		return pacoteRelacionado;
	}

	public void setPacoteRelacionado(ArrayList<PacoteViagem> pacoteRelacionado) {
		this.pacoteRelacionado = pacoteRelacionado;
	}

	
}

