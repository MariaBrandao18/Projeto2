package classes;

public class ClienteNacional extends Cliente {
	protected String cpf;
	
	public ClienteNacional() {
	}
	
	public ClienteNacional(Long clienteId, String nome, String telefone, String email, String cpf) {
		super(clienteId,nome,telefone,email);
		this.cpf = cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}
}
