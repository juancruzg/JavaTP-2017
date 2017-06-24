package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;

import datos.CatalogoDetalleVenta;
import datos.CatalogoProducto;
import datos.CatalogoVenta;
import entidades.DetalleVenta;
import excepciones.RespuestaServidor;

public class ControladorDetalleVenta {
	public DetalleVenta getDetalleVenta(int idProducto, int idVenta) throws RespuestaServidor {
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		
		return cdv.getDetalleVenta(idVenta, idProducto);
	}
	
	public ArrayList<DetalleVenta> getDetallesVenta(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		
		return cdv.getDetallesVenta(paginaActual, porPagina);
	}
	
	public int saveDetalleVenta(int cantidad, Timestamp fechaDevolucion, boolean llevaAProbar, int idProducto, int idVenta) throws RespuestaServidor { 
		RespuestaServidor res = new RespuestaServidor();
		CatalogoDetalleVenta cdv = new CatalogoDetalleVenta();
		CatalogoVenta cv = new CatalogoVenta();
		CatalogoProducto cp = new CatalogoProducto();
		
		DetalleVenta dv = new DetalleVenta();
		
		dv.setCantidad(cantidad);
		dv.setFechaDevolucion(fechaDevolucion);
		dv.setLlevaAProbar(llevaAProbar);
		dv.setProducto(cp.getProducto(idProducto));
		dv.setVenta(cv.getVenta(idVenta));
		
		res = validarDetalleVenta(dv);
		
		if (!res.getStatus())
			throw res;
		
		if (cdv.getDetalleVenta(idVenta, idProducto) == null)
			return cdv.insertDetalleVenta(dv);
		else
			return cdv.updateDetalleVenta(dv);
	}
	
	private RespuestaServidor validarDetalleVenta(DetalleVenta dv) {
		RespuestaServidor res = new RespuestaServidor();
		
		return res;
	}
}
