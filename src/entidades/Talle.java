package entidades;

public class Talle implements EntidadBase {
	private int id;
	private String talle;
	private Usuario usuarioAlta;
	
	/* Constructors */
	
	public Talle() {
		
	}
	
	public Talle(int id, String talle, Usuario usuario) {
		this.id = id;
		this.talle = talle;
		this.usuarioAlta = usuario;
	}

	/* Getters y Setters */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}
	
	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}
	
	public void setUsuarioAlta(Usuario usuario) {
		this.usuarioAlta = usuario;
	}

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
