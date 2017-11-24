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
import entidades.PagoEfectivo;
import entidades.Producto;
import entidades.Venta;
import excepciones.RespuestaServidor;
import modelos.ModeloPago;

public class ControladorVenta {
	public Venta getVenta(int idVenta) throws RespuestaServidor {
		CatalogoVenta cv = new CatalogoVenta();
		
		return cv.getVenta(idVenta);
	}
	
	public ArrayList<Venta> getVentas(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoVenta cv = new CatalogoVenta();
		
		return cv.getVentas(paginaActual, porPagina);
	}
	
	public int saveVenta(Venta v,ModeloPago mp) throws RespuestaServidor {
		RespuestaServidor res = new RespuestaServidor();
		ControladorDetalleVenta cdv = new ControladorDetalleVenta();
		ControladorLineaProducto clp = new ControladorLineaProducto();
		CatalogoVenta cv = new CatalogoVenta();
		CatalogoPago cp = new CatalogoPago();
		
		int retorno = 0;
		
		try {
			Conexion.getInstancia().beginTransaction();
			
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
			
			for (Pago p: v.getPagos()) {
				// TODO: Acá meteríamos lógica dependiendo del tipo de pago para calcular la cantidad de pagos en caso de que sea en cuotas.
				
				Pago pago = null; 
				
				if (pago.getTipoPago().getId() == 1) {// efectivo
					pago = new PagoEfectivo(p);
				}
				
				if (pago.getVenta() == null)
					pago.setVenta(v);
				
				cp.savePago(pago);
			}
			
			Conexion.getInstancia().commitTransaction();
		} 
		catch (SQLException e) {
			Conexion.getInstancia().rollbackTransaction();
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
