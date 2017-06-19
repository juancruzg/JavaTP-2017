package negocio;

import java.util.ArrayList;

import datos.CatalogoSucursal;
import datos.CatalogoUsuario;
import entidades.Sucursal;
import entidades.Usuario;
import excepciones.RespuestaServidor;

public class ControladorUsuario {
	public Usuario getUsuario(String usuario) throws RespuestaServidor {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		return cu.getUsuario(usuario);
	}
	
	public ArrayList<Usuario> getUsuarios(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		return cu.getUsuarios(paginaActual, porPagina);
	}
	
	public int insertUsuario(String nombre, String apellido, String usuario, String password, int idSucursal, String usuarioUsuarioAlta) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoUsuario cu = new CatalogoUsuario();
		CatalogoSucursal cs = new CatalogoSucursal();
		
		// Fetcheo las FKs
		Usuario usuarioAlta = cu.getUsuario(usuarioUsuarioAlta);
		Sucursal sucursal = cs.getSucursal(idSucursal);
		
		// Creo un nuevo usuario y llamo al método para guardar en la capa de datos
		Usuario u = new Usuario();
		
		u.setApellido(apellido);
		u.setNombre(nombre);
		u.setPassword(password);
		u.setSucursal(sucursal);
		u.setUsuarioAlta(usuarioAlta);
		u.setUsuario(usuario);
		
		rs = validarUsuario(u);
		
		if (!rs.getStatus())
			throw rs;
		
		return cu.insertUsuario(u);
	}
	
	private RespuestaServidor validarUsuario(Usuario u) {
		RespuestaServidor res = new RespuestaServidor();
		
		String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d]{6,20}$";
		
		if (u.getUsuario() == null || u.getUsuario().isEmpty())
			res.addError("El nombre de usuario no puede estar en blanco.");
		
		if (u.getUsuario().length() < 5)
			res.addError("El nombre de usuario debe contener al menos 5 caracteres.");
		
		if (u.getPassword() == null || u.getPassword().isEmpty())
			res.addError("La contraseña no puede estar en blanco.");
		
		if (u.getPassword().matches(passwordPattern))
			res.addError("La contraseña ingresada no es aceptable. Asegúrese de tener una minúscula, una mayúscula, un caracter especial y un número al menos");
		
		return res;
	}
}
