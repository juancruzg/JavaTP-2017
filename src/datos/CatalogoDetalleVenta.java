package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.DetalleVenta;
import excepciones.RespuestaServidor;

public class CatalogoDetalleVenta extends CatalogoBase {
	public DetalleVenta getDetalleVenta(int idVenta, int idProducto, int idTalle, int idColor, int idSucursal) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM detalleVenta WHERE idVenta = ? AND idProducto = ? AND idColor = ? AND idTalle = ? AND idSucursal = ?");
		
		data.addParameter(idVenta);
		data.addParameter(idColor);
		data.addParameter(idTalle);
		data.addParameter(idSucursal);
		data.addParameter(idProducto);
		
		return super.getOne(data, rs -> fetchDetalleVentaFromDB(rs));
	}
	
	public ArrayList<DetalleVenta> getDetallesVenta() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM detalleVenta");
		
		return super.getAll(data, rs -> fetchDetalleVentaFromDB(rs));
	}
	
	public ArrayList<DetalleVenta> getDetallesVenta(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM detalleVenta LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchDetalleVentaFromDB(rs));
	}
	
	// TODO:Revisar este método...
	public int insertDetalleVenta(DetalleVenta dv) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO detalleVenta (idVenta, idProducto, idColor, idTalle, idSucursal, cantidad, llevaAProbar, fechaDevolucion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
		data.addParameter(dv.getVenta().getId());
		data.addParameter(dv.getLineaProducto().getProducto().getId());
		data.addParameter(dv.getLineaProducto().getColor().getId());
		data.addParameter(dv.getLineaProducto().getTalle().getId());
		data.addParameter(dv.getLineaProducto().getSucursal().getId());
		data.addParameter(dv.getCantidad());
		data.addParameter(dv.isLlevaAProbar());
		data.addParameter(dv.getFechaDevolucion());
		
		return super.save(data);
	}
	
	public int updateDetalleVenta(DetalleVenta dv) throws RespuestaServidor {
		DBData data = new DBData("UPDATE detalleVenta cantidad = ?, llevaAProbar = ?, fechaDevolucion = ? WHERE idVenta = ? AND (idProducto = ? AND idColor = ? AND idTalle = ? AND idSucursal = ?)");
		
		data.addParameter(dv.getCantidad());
		data.addParameter(dv.isLlevaAProbar());
		data.addParameter(dv.getFechaDevolucion());
		data.addParameter(dv.getVenta().getId());
		data.addParameter(dv.getLineaProducto().getProducto().getId());
		data.addParameter(dv.getLineaProducto().getColor().getId());
		data.addParameter(dv.getLineaProducto().getTalle().getId());
		data.addParameter(dv.getLineaProducto().getSucursal().getId());

		
		return super.save(data);
	}
	
	public ArrayList<DetalleVenta> getDetallesVenta(int idVenta) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM detalleVenta WHERE idVenta = ?");
		
		data.addParameter(idVenta);
		
		return super.getAll(data, rs -> fetchDetalleVentaFromDB(rs));
	}
	
	private DetalleVenta fetchDetalleVentaFromDB(ResultSet rs) {
		CatalogoLineaProducto cp = new CatalogoLineaProducto();
		CatalogoVenta cv = new CatalogoVenta();
		DetalleVenta dv = new DetalleVenta();
		
		try {
			dv.setCantidad(rs.getInt("cantidad"));
			dv.setFechaDevolucion(rs.getTimestamp("fechaDevolucion"));
			dv.setLlevaAProbar(rs.getBoolean("llevaAProbar"));
			dv.setLineaProducto(cp.getLineaProducto(rs.getInt("idProducto"), rs.getInt("idTalle"), rs.getInt("idColor"), rs.getInt("idSucursal")));
			//dv.setVenta(cv.getVenta(rs.getInt("idVenta")));
		} 
		catch (SQLException | RespuestaServidor e) {
			e.printStackTrace();
		}
		
		return dv;
	}
}
