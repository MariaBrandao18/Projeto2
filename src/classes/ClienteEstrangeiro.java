package classes;

public class ClienteEstrangeiro extends Cliente {
	protected String passaporte;
	
	public ClienteEstrangeiro(String nome,String telefone, String email, String passaporte) {
		super(nome,telefone,email);
		this.passaporte = passaporte;
	}

	public String getPassaporte() {
		return passaporte;
	}
	
}
