package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Talle;
import excepciones.RespuestaServidor;

public class CatalogoTalle extends CatalogoBase {
	// Métodos públicos
	public ArrayList<Talle> getTalles() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM talle");
		
		return super.getAll(data, rs -> fetchTalleFromDB(rs));
	}
	
	public ArrayList<Talle> getTalles(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM talle LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchTalleFromDB(rs));
	}
	
	public Talle getTalle(int id) throws RespuestaServidor { 
		DBData data = new DBData("SELECT * FROM talle WHERE id = ?");
		
		data.addParameter(id);
		
		return super.getOne(data, rs -> fetchTalleFromDB(rs));
	}
	
	public int insertTalle(Talle producto) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO talle (id, talle,usuarioAlta) VALUES (?, ?, ?)");
		
		data.addParameter(producto.getId());
		data.addParameter(producto.getTalle());
		data.addParameter(producto.getUsuarioAlta());
		
		return super.save(data);
	}
	
	public int updateTalle(Talle producto) throws RespuestaServidor {
		DBData data = new DBData("UPDATE talle SET talle = ? WHERE id = ?");
		
		data.addParameter(producto.getTalle());
		data.addParameter(producto.getId());
		
		return super.save(data);
	}
	
	// Métodos privados
	private Talle fetchTalleFromDB(ResultSet rs) {
		Talle talle = new Talle();
		CatalogoUsuario cu = new CatalogoUsuario();
	    
	    try {
	    	
	    	talle.setId(rs.getInt("id"));
	    	talle.setTalle(rs.getString("talle"));
	    	talle.setUsuarioAlta(cu.getUsuario(rs.getString("usuarioAlta")));
	    	
	    }
	    catch (SQLException | RespuestaServidor ex) {
	    	ex.printStackTrace();
	    }
	    
	    return talle;
	}
}
