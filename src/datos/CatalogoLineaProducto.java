package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.LineaProducto;
import excepciones.RespuestaServidor;

public class CatalogoLineaProducto extends CatalogoBase {
	// Métodos públicos
	public ArrayList<LineaProducto> getLineaProductos() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM lineaproducto");
		
		return super.getAll(data, rs -> fetchLineaProductoFromDB(rs));
	}
	
	public ArrayList<LineaProducto> getLineaProductos(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM lineaproducto LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchLineaProductoFromDB(rs));
	}
	
	public LineaProducto getLineaProducto(int idProducto, int idTalle, int idColor,int idSucursal) throws RespuestaServidor { 
		DBData data = new DBData("SELECT * FROM lineaproducto WHERE idProducto = ? and idTalle = ? and idColor = ? and idSucursal = ?");
		
		data.addParameter(idProducto);
		data.addParameter(idTalle);
		data.addParameter(idColor);
		data.addParameter(idSucursal);
		
		return super.getOne(data, rs -> fetchLineaProductoFromDB(rs));
	}
	
	public int insertLineaProducto(LineaProducto lineaProducto) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO lineaproducto (idProducto,idTalle,idColor,idSucursal,stock,usuarioAlta) VALUES (?, ?, ?, ?, ?, ?)");
		
		data.addParameter(lineaProducto.getProducto().getId());
		data.addParameter(lineaProducto.getTalle().getId());
		data.addParameter(lineaProducto.getColor().getId());
		data.addParameter(lineaProducto.getSucursal().getId());
		data.addParameter(lineaProducto.getStock());
		data.addParameter(lineaProducto.getUsuarioAlta());
		
		return super.save(data);
	}
	
	public int updateLineaProducto(LineaProducto usuario) throws RespuestaServidor {
		DBData data = new DBData("UPDATE lineaproducto SET stock = ? WHERE idProducto = ? and idTalle = ? and idColor = ? and idSucursal = ?");
		
		data.addParameter(usuario.getStock());
		data.addParameter(usuario.getProducto().getId());
		data.addParameter(usuario.getTalle().getId());
		data.addParameter(usuario.getColor().getId());
		data.addParameter(usuario.getSucursal().getId());
		
		return super.save(data);
	}
	
	// Métodos privados
	private LineaProducto fetchLineaProductoFromDB(ResultSet rs) {
		LineaProducto lp = new LineaProducto();
		
		CatalogoProducto cp = new CatalogoProducto();
		CatalogoTalle ct = new CatalogoTalle();
		CatalogoColor cc = new CatalogoColor();
		CatalogoSucursal cs = new CatalogoSucursal();
		CatalogoUsuario cu = new CatalogoUsuario();
	    
	    try {
	    	
	    	lp.setStock(rs.getInt("stock"));
	    	
	    	lp.setProducto(cp.getProducto(rs.getInt("idProducto")));
	    	lp.setTalle(ct.getTalle(rs.getInt("idTalle")));
	    	lp.setColor(cc.getColor(rs.getInt("color")));
	    	lp.setSucursal(cs.getSucursal(rs.getInt("idSucursal")));
	    	lp.setUsuarioAlta(cu.getUsuario(rs.getString("usuarioAlta")));
	    	
	    }
	    catch (SQLException | RespuestaServidor ex) {
	    	ex.printStackTrace();
	    }
	    
	    return lp;
	}
}
