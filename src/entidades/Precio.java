package entidades;

import java.sql.Timestamp;

public class Precio implements EntidadBase {
	private Timestamp fecha;
	private Producto producto;
	private float precio;
	private Usuario usuarioAlta;
	
	/* Constructors */
	
	public Precio() {
		
	}
	
	public Precio(Timestamp fecha, Producto producto, float precio, Usuario usuarioAlta) {
		this.fecha = fecha;
		this.producto = producto;
		this.precio = precio;
		this.usuarioAlta = usuarioAlta;
	}
	
	/* Getters y Setters */

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
