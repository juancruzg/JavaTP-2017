package entidades;

import java.util.ArrayList;

public class Producto implements EntidadBase {
	private int id;
	private String descripcion;
	private Precio precio;
	private Usuario usuarioAlta;
	
	/* Constructors */
	
	public Producto() {
		
	}
	
	public Producto(int id, String descripcion, Precio precio, Usuario usuario) {
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.usuarioAlta = usuario;
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
	
	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public Usuario getUsuarioAlta(){
		return usuarioAlta;
	}
	
	public void setUsuarioAlta(Usuario usuario){
		this.usuarioAlta = usuario;
	}

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
