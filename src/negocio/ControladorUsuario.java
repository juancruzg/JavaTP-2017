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
	
	public Usuario getUsuario(String usuario, String password) throws RespuestaServidor {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		return cu.getUsuario(usuario, password);
	}
	
	public ArrayList<Usuario> getUsuarios(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		return cu.getUsuarios(paginaActual, porPagina);
	}
	
	public ArrayList<Usuario> getUsuarios(int paginaActual, int porPagina, boolean mostrarInactivos) throws RespuestaServidor {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		return cu.getUsuarios(paginaActual, porPagina, mostrarInactivos);
	}
	
	public int saveUsuario(Usuario usuario) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoUsuario cu = new CatalogoUsuario();
		
		Usuario usuarioDB = cu.getUsuario(usuario.getUsuario());

		if (usuarioDB == null) {
			usuario.setPassword("nuevoUsuario");
		}
		else {
			if (usuario.getPassword().equals(usuarioDB.getPassword())) {
				usuario.setPassword(null);
			}
		}
		
		// Corren las validaciones
		rs = validarUsuario(usuario);

		// Si falla alguna, lanzar el error
		if (!rs.getStatus())
			throw rs;
		
		if (usuarioDB == null)
			return cu.insertUsuario(usuario);
		else
			return cu.updateUsuario(usuario);
	}
	
	public int saveUsuario(String nombre, String apellido, String usuario, String password, int idSucursal, String usuarioUsuarioAlta) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoUsuario cu = new CatalogoUsuario();
		CatalogoSucursal cs = new CatalogoSucursal();
		
		// Fetcheo las FKs
		Usuario usuarioAlta = cu.getUsuario(usuarioUsuarioAlta);
		Sucursal sucursal = cs.getSucursal(idSucursal);
		
		// Construyo el usuario y valido que no exista en la DB para crearlo. De lo contrario se edita
		Usuario u = new Usuario();
		
		u.setApellido(apellido);
		u.setNombre(nombre);
		u.setPassword(password);
		u.setSucursal(sucursal);
		u.setUsuarioAlta(usuarioAlta);
		u.setUsuario(usuario);
		
		return saveUsuario(u);
	}
	
	private RespuestaServidor validarUsuario(Usuario u) {
		RespuestaServidor res = new RespuestaServidor();
		
		String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d]{6,20}$";
		
		if (u.getUsuario() == null || u.getUsuario().isEmpty())
			res.addError("El nombre de usuario no puede estar en blanco.");
		
		if (u.getUsuario().length() < 5)
			res.addError("El nombre de usuario debe contener al menos 5 caracteres.");
				
		if (u.getPassword() != null) {
			if (u.getPassword().isEmpty())
				res.addError("La contraseña no puede estar en blanco.");
			
			if (u.getPassword().matches(passwordPattern))
				res.addError("La contraseña ingresada no es aceptable. Asegúrese de tener una minúscula, una mayúscula, un caracter especial y un número al menos");
		}
		
		return res;
	}
}
