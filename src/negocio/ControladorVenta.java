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
import entidades.PagoCuentaCorriente;
import entidades.PagoEfectivo;
import entidades.PagoTarjeta;
import entidades.Producto;
import entidades.Venta;
import excepciones.RespuestaServidor;
import modelos.DtoModeloPagoVenta;
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
	
	public int saveVenta(DtoModeloPagoVenta dto) throws RespuestaServidor {
		RespuestaServidor res = new RespuestaServidor();
		ControladorDetalleVenta cdv = new ControladorDetalleVenta();
		ControladorLineaProducto clp = new ControladorLineaProducto();
		CatalogoVenta cv = new CatalogoVenta();
		CatalogoPago cp = new CatalogoPago();
		
		int retorno = 0;
		
		try {
			Conexion.getInstancia().beginTransaction();
			
			res = validarVenta(dto.getVenta(), dto.getModeloPago());
			
			if (!res.getStatus())
				throw res;
			
			if (dto.getVenta().getId() == 0) {
				retorno = cv.insertVenta(dto.getVenta());
				dto.getVenta().setId(retorno);
			}
			else
				retorno = cv.updateVenta(dto.getVenta());
						
			for(DetalleVenta detalle : dto.getVenta().getDetalles()) {
				if (detalle.getVenta() == null)
					detalle.setVenta(dto.getVenta());
				
				cdv.saveDetalleVenta(detalle);
				
				LineaProducto lp = detalle.getLineaProducto();
				
				lp.setStock(lp.getStock() - detalle.getCantidad());
				
				clp.saveLineaProducto(lp);
			}
			
//			for (Pago p: v.getPagos()) {
//				// TODO: Acá meteríamos lógica dependiendo del tipo de pago para calcular la cantidad de pagos en caso de que sea en cuotas.
//				
//				Pago pago = null; 
//				
//				if (pago.getTipoPago().getId() == 1) {// efectivo
//					pago = new PagoEfectivo(p);
//				}
//				
//				if (pago.getVenta() == null)
//					pago.setVenta(v);
//				
//				cp.savePago(pago);
//			}
			
			Pago pago = new Pago();
			pago.setFechaPago(dto.getModeloPago().getFechaPago());
			pago.setMonto(dto.getModeloPago().getMonto());
			pago.setVenta(dto.getVenta());
			pago.setTipoPago(dto.getModeloPago().getTipoPago());
			
			switch(dto.getModeloPago().getTipoPago().getId()) {
				case 1: pago = new PagoEfectivo(pago);
						break;
						
				case 2: pago = new PagoCuentaCorriente();
						((PagoCuentaCorriente)pago).setPagado(false);
						break;
						
				case 3: pago = new PagoTarjeta();
						((PagoTarjeta)pago).setNroTarjeta(dto.getModeloPago().getNroTarjeta());
						((PagoTarjeta)pago).setNombreTitular(dto.getModeloPago().getNombreTitular());
						((PagoTarjeta)pago).setVencimientoTarjeta(dto.getModeloPago().getVencimientoTarjeta());
						break;
						
				default: 
						break;
			}
			
			cp.savePago(pago);
		
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
	
	private RespuestaServidor validarVenta(Venta venta, ModeloPago modeloPago) {
		RespuestaServidor rs = new RespuestaServidor();
		
		if (venta.getCliente() == null)
			rs.addError("Debe seleccionar un cliente");
		
		if (venta.getDetalles() == null || venta.getDetalles().isEmpty())
			rs.addError("Debe seleccionar al menos un producto");
		
		if (venta.getFecha() == null)
			rs.addError("Debe seleccionar una fecha");
		
		if(modeloPago.getFechaPago() == null)
			rs.addError("No se definió la fecha del pago");
		
		if(modeloPago.getMonto() == 0)
			rs.addError("No se definió el monto del pago");
		
		if(modeloPago.getTipoPago().getId() == 2 && modeloPago.getNroTarjeta() == null)
			rs.addError("No se definió el número de la tarjeta");
		
		if(modeloPago.getTipoPago().getId() == 2 && modeloPago.getNombreTitular() == null)
			rs.addError("No se definió el nombre del titular");
		
		if(modeloPago.getTipoPago().getId() == 2 && modeloPago.getVencimientoTarjeta() == null)
			rs.addError("No se definió el vencimiento de la tarjeta");
		
		return rs;
	}
}
