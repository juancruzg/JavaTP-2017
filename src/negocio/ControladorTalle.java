package negocio;

import java.util.ArrayList;

import datos.CatalogoTalle;
import entidades.Talle;
import excepciones.RespuestaServidor;

public class ControladorTalle {
	public Talle getTalle(int idTalle) throws RespuestaServidor {
		CatalogoTalle cc = new CatalogoTalle();
		
		return cc.getTalle(idTalle);
	}

	public ArrayList<Talle> getTalles() throws RespuestaServidor {
		CatalogoTalle cc = new CatalogoTalle();
		
		return cc.getTalles();
	}
	
	public ArrayList<Talle> getTalles(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoTalle cc = new CatalogoTalle();
		
		return cc.getTalles(paginaActual, porPagina);
	}
	
	public int saveTalle(String talle, String codigoTalle, int idTalle) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoTalle cc = new CatalogoTalle();
		
		// Instancio y construyo el talle
		Talle c = new Talle();
		
		c.setTalle(talle);
		c.setId(idTalle);
		
		// Corren las validaciones
		sr = validarTalle(c);

		// Si falla alguna, lanzar el error
		if (!sr.getStatus())
			throw sr;
		
		if (idTalle == 0)
			return cc.insertTalle(c);
		else
			return cc.updateTalle(c);
	}
	
	private RespuestaServidor validarTalle(Talle talle) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoTalle cc = new CatalogoTalle();
		
		if (talle.getId() == 0) {
			if (cc.getTalle(talle.getId()) == null)
				sr.addError("Está intentando editar un talle inexistente...");
		}
		
		if (talle.getTalle() == null || talle.getTalle().isEmpty())
			sr.addError("El nombre del talle no puede quedar en blanco.");

		return sr;
	}
}
