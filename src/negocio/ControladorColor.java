package negocio;

import java.util.ArrayList;

import datos.CatalogoColor;
import entidades.Color;
import excepciones.RespuestaServidor;

public class ControladorColor {
	public Color getColor(int idColor) throws RespuestaServidor {
		CatalogoColor cc = new CatalogoColor();
		
		return cc.getColor(idColor);
	}

	public ArrayList<Color> getColores() throws RespuestaServidor {
		CatalogoColor cc = new CatalogoColor();
		
		return cc.getColores();
	}
	
	public ArrayList<Color> getColores(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoColor cc = new CatalogoColor();
		
		return cc.getColores(paginaActual, porPagina);
	}
	
	public int saveColor(String color, String codigoColor, int idColor) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoColor cc = new CatalogoColor();
		
		// Instancio y construyo el color
		Color c = new Color();
		
		c.setCodigoColor(codigoColor);
		c.setColor(color);
		c.setId(idColor);
		
		// Corren las validaciones
		sr = validarColor(c);

		// Si falla alguna, lanzar el error
		if (!sr.getStatus())
			throw sr;
		
		if (idColor == 0)
			return cc.insertColor(c);
		else
			return cc.updateColor(c);
	}
	
	private RespuestaServidor validarColor(Color color) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoColor cc = new CatalogoColor();
		
		if (color.getId() == 0) {
			if (cc.getColor(color.getId()) == null)
				sr.addError("Está intentando editar un color inexistente...");
		}
		
		if (color.getColor() == null || color.getColor().isEmpty())
			sr.addError("El nombre del color no puede quedar en blanco.");
		
		if (color.getCodigoColor() == null || color.getCodigoColor().isEmpty())
			sr.addError("Debe elegir un color.");
		
		return sr;
	}
}
