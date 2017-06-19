package entidades;

import java.sql.ResultSet;

public class Color implements EntidadBase{
	private int id;
	private String color;
	private Usuario usuarioAlta;
	
	/* Getters & Setters */
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

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void autoFill(ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

}
