package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;

import datos.CatalogoDetalleVenta;
import datos.CatalogoProducto;
import datos.CatalogoVenta;
import entidades.DetalleVenta;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class ControladorDetalleVenta {
	public DetalleVenta getDetalleVenta(int idVenta, int idProducto, int idTalle, int idColor, int idSucursal) throws RespuestaServidor {
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		
		return cdv.getDetalleVenta(idVenta, idProducto, idTalle, idColor, idSucursal);
	}
	
	public ArrayList<DetalleVenta> getDetallesVenta(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		
		return cdv.getDetallesVenta(paginaActual, porPagina);
	}
	
	public int saveDetalleVenta(DetalleVenta dv) throws RespuestaServidor { 
		RespuestaServidor res = new RespuestaServidor();
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		
		res = validarDetalleVenta(dv);
		
		if (!res.getStatus())
			throw res;
		
		DetalleVenta detalleDB = cdv.getDetalleVenta(dv.getVenta().getId(), dv.getLineaProducto().getProducto().getId(), dv.getLineaProducto().getTalle().getId(), dv.getLineaProducto().getColor().getId(), dv.getLineaProducto().getSucursal().getId());
		
		if (detalleDB == null)
			return cdv.insertDetalleVenta(dv);
		else
			return cdv.updateDetalleVenta(dv);
	}
	
	private RespuestaServidor validarDetalleVenta(DetalleVenta dv) {
		RespuestaServidor rs = new RespuestaServidor();
		
		if (dv.getCantidad() > dv.getLineaProducto().getStock()) {
			Producto producto = dv.getLineaProducto().getProducto();
			
			rs.addError("No hay suficiente stock para el siguiente producto: " + producto.getDescripcion() + " " + producto.getMarca());
		}
		
		return rs;
	}
}
