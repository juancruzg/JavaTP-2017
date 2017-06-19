package entidades;

public class Color implements EntidadBase {
	private int id;
	private String color, codigoColor;
	
	public Color() {
		
	}
	
	public Color(int id, String color, String codigoColor) {
		this.id = id;
		this.color = color;
		this.codigoColor = codigoColor;
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
