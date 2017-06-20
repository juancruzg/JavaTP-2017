package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Usuario;
import excepciones.RespuestaServidor;

public class CatalogoUsuario extends CatalogoBase {
	// Métodos públicos
	public ArrayList<Usuario> getUsuarios() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM usuario");
		
		return super.getAll(data, rs -> fetchUsuarioFromDB(rs));
	}
	
	public ArrayList<Usuario> getUsuarios(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM usuario LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchUsuarioFromDB(rs));
	}
	
	public Usuario getUsuario(String usuario) throws RespuestaServidor { 
		DBData data = new DBData("SELECT * FROM usuario WHERE usuario = ?");
		
		data.addParameter(usuario);
		
		return super.getOne(data, rs -> fetchUsuarioFromDB(rs));
	}
	
	public int insertUsuario(Usuario usuario) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO usuario (usuario, password, nombre, apellido, idSucursal, usuarioAlta) VALUES (?, ?, ?, ?, ?, ?)");
		
		data.addParameter(usuario.getUsuario());
		data.addParameter(usuario.getPassword());
		data.addParameter(usuario.getNombre());
		data.addParameter(usuario.getApellido());
		data.addParameter(usuario.getSucursal().getId());
		data.addParameter(usuario.getUsuarioAlta().getUsuario());
		
		return super.save(data);
	}
	
	public int updateUsuario(Usuario usuario) throws RespuestaServidor {
		DBData data = new DBData("UPDATE usuario SET password = ?, nombre = ?, apellido = ?, idSucursal = ? WHERE usuario = ?");
		
		data.addParameter(usuario.getPassword());
		data.addParameter(usuario.getNombre());
		data.addParameter(usuario.getApellido());
		data.addParameter(usuario.getSucursal().getId());
		data.addParameter(usuario.getUsuario());
		
		return super.save(data);
	}
	
	// Métodos privados
	private Usuario fetchUsuarioFromDB(ResultSet rs) {
		CatalogoSucursal cs = new CatalogoSucursal();
	    Usuario user = new Usuario();
	    
	    try {
	    	user.setUsuario(rs.getString("usuario"));
	    	user.setNombre(rs.getString("nombre"));
	    	user.setApellido(rs.getString("apellido"));
	    	user.setFechaAlta(rs.getTimestamp("fechaAlta"));
	    	user.setPassword(rs.getString("password"));
	    	
	    	user.setUsuarioAlta(getUsuario(rs.getString("usuarioAlta")));
	    	user.setSucursal(cs.getSucursal(rs.getInt("idSucursal")));
	    }
	    catch (SQLException | RespuestaServidor ex) {
	    	ex.printStackTrace();
	    }
	    
	    return user;
	}
}
