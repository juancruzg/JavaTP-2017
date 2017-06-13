package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Usuario implements EntidadBase {
	private String usuario, password, nombre, apellido;
	private Usuario usuarioAlta;
	private Timestamp fechaAlta;
	private Sucursal sucursal;
	
	/* Constructors */
	public Usuario () {
		
	}
	
	public Usuario(String usuario, String password, String nombre, String apellido, Usuario usuarioAlta, Timestamp fechaAlta, Sucursal sucursal) {
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuarioAlta = usuarioAlta;
		this.fechaAlta = fechaAlta;
		this.sucursal = sucursal;
	}
	
	/* Getters y Setters */
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	/* Public Methods */
	
	public String toJson() {
		return "";
	}
	
	public void autoFill(ResultSet rs) {
		try {
			usuario = rs.getString("usuario");
			password = rs.getString("password");
			nombre = rs.getString("nombre");
			apellido = rs.getString("apellido");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
