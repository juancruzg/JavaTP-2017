package negocio;

import java.util.ArrayList;

import datos.CatalogoSucursal;
import entidades.Sucursal;
import excepciones.RespuestaServidor;

public class ControladorSucursal {
	public Sucursal getSucursal(int idSucursal) throws RespuestaServidor {
		CatalogoSucursal cs = new CatalogoSucursal();
		
		return cs.getSucursal(idSucursal);
	}
	
	public ArrayList<Sucursal> getSucursales(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoSucursal cs = new CatalogoSucursal();
		
		return cs.getSucursales(paginaActual, porPagina);
	}
	
	public int saveSucursal(String telefono, String domicilio, int idSucursal) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoSucursal cs = new CatalogoSucursal();
		
		Sucursal s = new Sucursal();
		
		s.setDomicilio(domicilio);
		s.setTelefono(telefono);
		s.setId(idSucursal);
		
		sr = validarSucursal(s);
		
		if (!sr.getStatus())
			throw sr;
		
		if (idSucursal == 0)
			return cs.insertSucursal(s);
		else
			return cs.updateSucursal(s);
	}
	
	private RespuestaServidor validarSucursal(Sucursal sucursal) throws RespuestaServidor {
		CatalogoSucursal cs = new CatalogoSucursal();
		RespuestaServidor sr = new RespuestaServidor();
		
		if (sucursal.getId() != 0) {
			if (cs.getSucursal(sucursal.getId()) == null)
				sr.addError("La sucursal que intenta editar no existe...");
		}
		
		return sr;
	}
}
