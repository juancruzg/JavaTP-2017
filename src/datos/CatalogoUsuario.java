package datos;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
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
	
	public ArrayList<Usuario> getUsuarios(int paginaActual, int porPagina, boolean mostrarInactivos) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM usuario WHERE activo = ? OR activo = 1 LIMIT ?, ?");
		
		data.addParameter(!mostrarInactivos);
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
		DBData data = new DBData("INSERT INTO usuario (usuario, password, nombre, apellido, idSucursal, usuarioAlta, idTipoUsuario, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		data.addParameter(usuario.getUsuario());
		data.addParameter(usuario.getPassword());
		data.addParameter(usuario.getNombre());
		data.addParameter(usuario.getApellido());
		data.addParameter(usuario.getSucursal().getId());
		data.addParameter(usuario.getUsuarioAlta().getUsuario());
		data.addParameter(usuario.getTipoUsuario().getId());
		data.addParameter(usuario.isActivo());
		
		return super.save(data);
	}
	
	public int updateUsuario(Usuario usuario) throws RespuestaServidor {
		DBData data;
		
		if (usuario.getPassword() != null) {
			data = new DBData("UPDATE usuario SET password = ?, nombre = ?, apellido = ?, idSucursal = ?, idTipoUsuario = ?, activo = ? WHERE usuario = ?");
			data.addParameter(usuario.getPassword());
		}
		else {
			data = new DBData("UPDATE usuario SET nombre = ?, apellido = ?, idSucursal = ?, idTipoUsuario = ?, activo = ? WHERE usuario = ?");
		}
		data.addParameter(usuario.getNombre());
		data.addParameter(usuario.getApellido());
		data.addParameter(usuario.getSucursal().getId());
		data.addParameter(usuario.getTipoUsuario().getId());
		data.addParameter(usuario.isActivo());
		data.addParameter(usuario.getUsuario());
		
		return super.save(data);
	}
	
	// Métodos privados
	private Usuario fetchUsuarioFromDB(ResultSet rs) {
		CatalogoSucursal cs = new CatalogoSucursal();
		CatalogoTipoUsuario ctu = new CatalogoTipoUsuario();
	    Usuario user = new Usuario();
	    
	    try {
	    	user.setUsuario(rs.getString("usuario"));
	    	user.setNombre(rs.getString("nombre"));
	    	user.setApellido(rs.getString("apellido"));
	    	user.setFechaAlta(rs.getTimestamp("fechaAlta"));
	    	user.setPassword(rs.getString("password"));
	    	user.setActivo(rs.getBoolean("activo"));
	    	
	    	user.setUsuarioAlta(getUsuario(rs.getString("usuarioAlta")));
	    	user.setSucursal(cs.getSucursal(rs.getInt("idSucursal")));
	    	user.setTipoUsuario(ctu.getTipoUsuario(rs.getInt("idTipoUsuario")));
	    }
	    catch (SQLException | RespuestaServidor ex) {
	    	ex.printStackTrace();
	    }
	    
	    return user;
	}
	
}
