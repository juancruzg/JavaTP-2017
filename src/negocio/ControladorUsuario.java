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
	
	public int saveUsuario(String nombre, String apellido, String usuario, String password, int idSucursal, String usuarioUsuarioAlta) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoUsuario cu = new CatalogoUsuario();
		CatalogoSucursal cs = new CatalogoSucursal();
		
		Usuario usuarioDB = cu.getUsuario(usuario);
		
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
		
		// Corren las validaciones
		rs = validarUsuario(u);

		// Si falla alguna, lanzar el error
		if (!rs.getStatus())
			throw rs;
		
		if (usuarioDB == null)
			return cu.insertUsuario(u);
		else
			return cu.updateUsuario(u);
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
