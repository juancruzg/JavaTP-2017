package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.DetalleVenta;
import excepciones.RespuestaServidor;

public class CatalogoDetalleVenta extends CatalogoBase {
	public DetalleVenta getDetalleVenta(int idVenta, int idProducto) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM detalleVenta WHERE idVenta = ? AND idProducto = ?");
		
		data.addParameter(idVenta);
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
	
	public int insertDetalleVenta(DetalleVenta dv) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO detalleVenta (idVenta, idProducto, cantidad, llevaAProbar, fechaDevolucion) VALUES (?, ?, ?, ?, ?)");
		
		data.addParameter(dv.getVenta().getId());
		data.addParameter(dv.getProducto().getId());
		data.addParameter(dv.getCantidad());
		data.addParameter(dv.isLlevaAProbar());
		data.addParameter(dv.getFechaDevolucion());
		
		return super.save(data);
	}
	
	public int updateDetalleVenta(DetalleVenta dv) throws RespuestaServidor {
		DBData data = new DBData("UPDATE detalleVenta cantidad = ?, llevaAProbar = ?, fechaDevolucion = ? WHERE idVenta = ? AND idProducto = ?");
		
		data.addParameter(dv.getCantidad());
		data.addParameter(dv.isLlevaAProbar());
		data.addParameter(dv.getFechaDevolucion());
		data.addParameter(dv.getVenta().getId());
		data.addParameter(dv.getProducto().getId());
		
		return super.save(data);
	}
	
	private DetalleVenta fetchDetalleVentaFromDB(ResultSet rs) {
		CatalogoProducto cp = new CatalogoProducto();
		CatalogoVenta cv = new CatalogoVenta();
		DetalleVenta dv = new DetalleVenta();
		
		try {
			dv.setCantidad(rs.getInt("cantidad"));
			dv.setFechaDevolucion(rs.getTimestamp("fechaDevolucion"));
			dv.setLlevaAProbar(rs.getBoolean("llevaAProbar"));
			dv.setProducto(cp.getProducto(rs.getInt("idProducto")));
			dv.setVenta(cv.getVenta(rs.getInt("idVenta")));
		} 
		catch (SQLException | RespuestaServidor e) {
			e.printStackTrace();
		}
		
		return dv;
	}
}
