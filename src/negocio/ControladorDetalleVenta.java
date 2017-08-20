package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;

import datos.CatalogoDetalleVenta;
import datos.CatalogoProducto;
import datos.CatalogoVenta;
import entidades.DetalleVenta;
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
		CatalogoVenta cv = new CatalogoVenta();
		CatalogoProducto cp = new CatalogoProducto();
		
		res = validarDetalleVenta(dv);
		
		if (!res.getStatus())
			throw res;
		
		if (cdv.getDetalleVenta(dv.getVenta().getId(), dv.getLineaProducto().getProducto().getId(), dv.getLineaProducto().getTalle().getId(), dv.getLineaProducto().getColor().getId(), dv.getLineaProducto().getSucursal().getId()) == null)
			return cdv.insertDetalleVenta(dv);
		else
			return cdv.updateDetalleVenta(dv);
	}
	
	private RespuestaServidor validarDetalleVenta(DetalleVenta dv) {
		RespuestaServidor res = new RespuestaServidor();
		
		return res;
	}
}
