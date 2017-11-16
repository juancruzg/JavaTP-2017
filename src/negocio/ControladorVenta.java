package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import conexion.Conexion;
import datos.CatalogoCliente;
import datos.CatalogoPago;
import datos.CatalogoTipoPago;
import datos.CatalogoVenta;
import entidades.DetalleVenta;
import entidades.LineaProducto;
import entidades.Pago;
import entidades.Producto;
import entidades.Venta;
import excepciones.RespuestaServidor;

public class ControladorVenta {
	public Venta getVenta(int idVenta) throws RespuestaServidor {
		CatalogoVenta cv = new CatalogoVenta();
		
		return cv.getVenta(idVenta);
	}
	
	public ArrayList<Venta> getVentas(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoVenta cv = new CatalogoVenta();
		
		return cv.getVentas(paginaActual, porPagina);
	}
	
	public int saveVenta(Venta v) throws RespuestaServidor {
		RespuestaServidor res = new RespuestaServidor();
		ControladorDetalleVenta cdv = new ControladorDetalleVenta();
		ControladorLineaProducto clp = new ControladorLineaProducto();
		CatalogoVenta cv = new CatalogoVenta();
		CatalogoPago cp = new CatalogoPago();
		
		Connection conn = Conexion.getInstancia().getConn();
		int retorno = 0;

		try {
			// Esto a este nivel es muy raro, pero no lo pude resolver de otra manera.
			// Por lo menos funciona. Esto es un BEGIN TRAN, y como tengo que hacer todo el proceso entre controladores, no pude de otra forma...
			conn.setAutoCommit(false);
			
			res = validarVenta(v);
			
			if (!res.getStatus())
				throw res;
			
			if (v.getId() == 0) {
				retorno = cv.insertVenta(v);
				v.setId(retorno);
			}
			else
				retorno = cv.updateVenta(v);
						
			for(DetalleVenta detalle : v.getDetalles()) {
				if (detalle.getVenta() == null)
					detalle.setVenta(v);
				
				cdv.saveDetalleVenta(detalle);
				
				LineaProducto lp = detalle.getLineaProducto();
				
				lp.setStock(lp.getStock() - detalle.getCantidad());
				
				clp.saveLineaProducto(lp);
			}
			
			for (Pago pago : v.getPagos()) {
				if (pago.getVenta() == null)
					pago.setVenta(v);
				
				cp.savePago(pago);
			}
			
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(RespuestaServidor sr) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			throw sr;
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		finally {
			Conexion.getInstancia().CloseConn();
		}
		
		return retorno;
	}
	
	private RespuestaServidor validarVenta(Venta venta) {
		RespuestaServidor rs = new RespuestaServidor();
		
		if (venta.getCliente() == null)
			rs.addError("Debe seleccionar un cliente");
		
		if (venta.getDetalles() == null || venta.getDetalles().isEmpty())
			rs.addError("Debe seleccionar al menos un producto");
		
		if (venta.getFecha() == null)
			rs.addError("Debe seleccionar una fecha");
		
		return rs;
	}
}
