package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Venta;
import excepciones.RespuestaServidor;

public class CatalogoVenta extends CatalogoBase{
	public Venta getVenta(int idVenta) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM venta WHERE id = ?");
		
		data.addParameter(idVenta);
		
		return super.getOne(data, rs -> fetchVentaFromDB(rs));
	}
	
	public ArrayList<Venta> getVentas() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM venta");
		
		return super.getAll(data, rs -> fetchVentaFromDB(rs));
	}
	
	public ArrayList<Venta> getVentas(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM venta LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchVentaFromDB(rs));
	}
	
	public int insertVenta(Venta venta) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO venta (idCliente, idTipoPago, fecha) VALUES (?, ?, ?)");
		
		data.addParameter(venta.getCliente().getId());
		data.addParameter(venta.getTipoPago().getId());
		data.addParameter(venta.getFecha());
		
		return super.save(data);
	}
	
	public int updateVenta(Venta venta) throws RespuestaServidor {
		DBData data = new DBData("UPDATE venta SET idCliente = ?, idTipoPago = ?, fecha = ? WHERE id = ?");
		
		data.addParameter(venta.getCliente().getId());
		data.addParameter(venta.getTipoPago().getId());
		data.addParameter(venta.getFecha());
		
		return super.save(data);
	}
	
	private Venta fetchVentaFromDB(ResultSet rs) {
		CatalogoCliente cc = new CatalogoCliente();
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		Venta venta = new Venta();
		
		try {
			venta.setId(rs.getInt("id"));
			venta.setFecha(rs.getTimestamp("fecha"));
			venta.setCliente(cc.getCliente(rs.getInt("idCliente"), false));
			venta.setTipoPago(ctp.getTipoPago(rs.getInt("idTipoPago")));
			venta.setDetalles(cdv.getDetallesVenta(rs.getInt("id")));
		} 
		catch (SQLException | RespuestaServidor e) {
			e.printStackTrace();
		}
		
		return venta;
	}
}
