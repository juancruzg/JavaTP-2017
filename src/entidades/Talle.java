package entidades;

public class Talle implements EntidadBase {
	private int id;
	private String talle;
	
	/* Constructors */
	
	public Talle() {
		
	}
	
	public Talle(int id, String talle) {
		this.id = id;
		this.talle = talle;
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

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
