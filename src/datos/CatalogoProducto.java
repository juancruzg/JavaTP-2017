package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Producto;
import excepciones.RespuestaServidor;

public class CatalogoProducto extends CatalogoBase {
	// Métodos públicos
	public ArrayList<Producto> getProductos() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM producto");
		
		return super.getAll(data, rs -> fetchProductoFromDB(rs));
	}
	
	public ArrayList<Producto> getProductos(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM producto LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchProductoFromDB(rs));
	}
	
	public Producto getProducto(int id) throws RespuestaServidor { 
		DBData data = new DBData("SELECT * FROM producto WHERE id = ?");
		
		data.addParameter(id);
		
		return super.getOne(data, rs -> fetchProductoFromDB(rs));
	}
	
	public int insertProducto(Producto producto) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO producto (id, descripcion,usuarioAlta) VALUES (?, ?, ?)");
		
		data.addParameter(producto.getId());
		data.addParameter(producto.getDescripcion());
		data.addParameter(producto.getUsuarioAlta());
		
		return super.save(data);
	}
	
	public int updateProducto(Producto producto) throws RespuestaServidor {
		DBData data = new DBData("UPDATE producto SET descripcion = ? WHERE id = ?");
		
		data.addParameter(producto.getDescripcion());
		data.addParameter(producto.getId());
		
		return super.save(data);
	}
	
	// Métodos privados
	private Producto fetchProductoFromDB(ResultSet rs) {
		Producto prod = new Producto();
		CatalogoUsuario cu = new CatalogoUsuario();
		CatalogoPrecio cp = new CatalogoPrecio();
	    
	    try {
	    	
	    	prod.setId(rs.getInt("id"));
	    	prod.setDescripcion(rs.getString("denominacion"));
	    	prod.setPrecio(cp.getUltimoPrecio(prod.getId()));
	    	prod.setUsuarioAlta(cu.getUsuario(rs.getString("usuarioAlta")));
	    	
	    }
	    catch (SQLException | RespuestaServidor ex) {
	    	ex.printStackTrace();
	    }
	    
	    return prod;
	}
}
