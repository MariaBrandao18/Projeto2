package classes;

public class ClienteNacional extends Cliente {
	protected String cpf;
	
	public ClienteNacional(String nome,String telefone, String email, String cpf) {
		super(nome,telefone,email);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}
}
