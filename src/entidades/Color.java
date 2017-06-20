package entidades;


public class Color implements EntidadBase {
	private int id;
	private String color, codigoColor;
	private Usuario usuarioAlta;
	
	public Color() {
		
	}
	
	public Color(int id, String color, String codigoColor, Usuario usuarioAlta) {
		this.id = id;
		this.color = color;
		this.codigoColor = codigoColor;
		this.usuarioAlta = usuarioAlta;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}


	public String getCodigoColor() {
		return codigoColor;
	}

	public void setCodigoColor(String codigoColor) {
		this.codigoColor = codigoColor;
	}
	
	public String toJson() {
		return "";
	}
}
