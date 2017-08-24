package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import entidades.Precio;
import excepciones.RespuestaServidor;

public class CatalogoPrecio extends CatalogoBase {
	// Métodos públicos
	public ArrayList<Precio> getPrecios() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM precio");
		
		return super.getAll(data, rs -> fetchPrecioFromDB(rs));
	}
	
	public ArrayList<Precio> getPrecios(int idProducto) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM precio WHERE idProducto = ? ORDER BY fecha");
		
		data.addParameter(idProducto);
		
		return super.getAll(data, rs -> fetchPrecioFromDB(rs));
	}
	
	public ArrayList<Precio> getPrecios(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM precio LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchPrecioFromDB(rs));
	}
	
	public Precio getPrecio(int idProducto,Timestamp fecha ) throws RespuestaServidor { 
		DBData data = new DBData("SELECT * FROM precio WHERE idProducto = ? and fecha = ?");
		
		data.addParameter(idProducto);
		data.addParameter(fecha);
		
		return super.getOne(data, rs -> fetchPrecioFromDB(rs));
	}
	
	public Precio getUltimoPrecio(int idProducto) throws RespuestaServidor { 
		DBData data = new DBData("SELECT * FROM precio WHERE idProducto = ? and fecha < NOW() ORDER BY fecha DESC LIMIT 1");
		
		data.addParameter(idProducto);
		
		return super.getOne(data, rs -> fetchPrecioFromDB(rs));
	}
	
	public int insertPrecio(Precio precio) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO precio (idProducto,fecha,precio,usuarioAlta) VALUES (?, ?, ?, ?)");
		
		data.addParameter(precio.getProducto().getId());
		data.addParameter(precio.getFecha());
		data.addParameter(precio.getPrecio());
		data.addParameter(precio.getUsuarioAlta());
		
		return super.save(data);
	}
	
	public int updatePrecio(Precio precio) throws RespuestaServidor {
		DBData data = new DBData("UPDATE precio SET precio =? WHERE idProducto =? and fecha =?");
		
		data.addParameter(precio.getPrecio());
		data.addParameter(precio.getProducto().getId());
		data.addParameter(precio.getFecha());
		
		return super.save(data);
	}
	
	// Métodos privados
	private Precio fetchPrecioFromDB(ResultSet rs) {
		CatalogoProducto cp = new CatalogoProducto();
	    Precio user = new Precio();
	    
	    try {
	    	user.setFecha(rs.getTimestamp("fecha"));
	    	user.setPrecio(rs.getFloat("precio"));
	    	
	    	//user.setProducto(cp.getProducto(rs.getInt("idProducto")));
	    }
	    catch (SQLException ex) {
	    	ex.printStackTrace();
	    }
	    
	    return user;
	}
}
