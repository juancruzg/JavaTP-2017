package entidades;

import java.sql.Timestamp;

public class Cliente implements EntidadBase {
	private int id;
	private String nombre, apellido, telefono, domicilio;
	private Usuario usuarioAlta;
	private boolean activo;
	private Timestamp fechaAlta;
	
	/* Constructors */
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String nombre, String apellido, String telefono, String domicilio, Usuario usuarioAlta, boolean activo, Timestamp fechaAlta) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.usuarioAlta = usuarioAlta;
		this.activo = activo;
		this.fechaAlta = fechaAlta;
	}
	
	/* Getters y Setters */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	/* Public methods */

	public String toJson() {
		return "";
	}
}
