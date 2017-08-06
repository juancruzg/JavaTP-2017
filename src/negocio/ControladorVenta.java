package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;

import datos.CatalogoCliente;
import datos.CatalogoTipoPago;
import datos.CatalogoVenta;
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
	
	public int saveVenta(int idCliente, Timestamp fecha, int idTipoPago, int id) throws RespuestaServidor {
		RespuestaServidor res = new RespuestaServidor();
		CatalogoVenta cv = new CatalogoVenta();
		CatalogoCliente cc = new CatalogoCliente();
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		
		Venta v = new Venta();
		
		v.setCliente(cc.getCliente(idCliente, false));
		v.setFecha(fecha);
		v.setId(id);
		v.setTipoPago(ctp.getTipoPago(idTipoPago));
		
		res = validarVenta(v);
		
		if (!res.getStatus())
			throw res;
		
		if (id == 0)
			return cv.insertVenta(v);
		else
			return cv.updateVenta(v);
	}
	
	private RespuestaServidor validarVenta(Venta venta) {
		RespuestaServidor res = new RespuestaServidor();
		
		return res;
	}
}
