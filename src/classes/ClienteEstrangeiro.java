package classes;

public class ClienteEstrangeiro extends Cliente {
	protected String passaporte;
	
	public ClienteEstrangeiro(){
		
	}

	public ClienteEstrangeiro(Long clienteId, String nome, String telefone, String email, String passaporte) {
		super(clienteId, nome, telefone,email);
		this.passaporte = passaporte;
	}
	
	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public String getPassaporte() {
		return passaporte;
	}
	
}
