package entidades;

public class Sucursal {
	private int id;
	private String domicilio, telefono;

	/* Constructors */
	
	public Sucursal() {
		
	}
	
	public Sucursal(int id, String domicilio, String telefono) {
		this.id = id;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}
	
	/* Getters y setters */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
