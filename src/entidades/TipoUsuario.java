package entidades;

import java.util.ArrayList;

public class TipoUsuario implements EntidadBase {
	private int id;
	private String descripcion;	
	private ArrayList<Modulo> modulos;
	
	/* Constructors */

	public TipoUsuario() {
		
	}
	
	public TipoUsuario(int id, String descripcion, ArrayList<Modulo> modulos) {
		this.id = id; 
		this.descripcion = descripcion;
		this.setModulos(modulos);
	}
	
	/* Getters y Setters */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(ArrayList<Modulo> modulos) {
		this.modulos = modulos;
	}

	public String toJson() {
		return null;
	}
}
