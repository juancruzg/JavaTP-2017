package negocio;

import java.util.ArrayList;

import datos.CatalogoTipoPago;
import entidades.TipoPago;
import excepciones.RespuestaServidor;

public class ControladorTipoPago {
	public TipoPago getTipoPago(int idTipoPago) throws RespuestaServidor {
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		
		return ctp.getTipoPago(idTipoPago);
	}

	public ArrayList<TipoPago> getTiposPago() throws RespuestaServidor {
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		
		return ctp.getTiposPago();
	}
	
	public int saveColor(String tipoPago, int idTipoPago) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		
		// Instancio y construyo el color
		TipoPago tp = new TipoPago();
		
		tp.setId(idTipoPago);
		tp.setTipoPago(tipoPago);
		
		// Corren las validaciones
		sr = validarTipoPago(tp);

		// Si falla alguna, lanzar el error
		if (!sr.getStatus())
			throw sr;
		
		if (idTipoPago == 0)
			return ctp.insertTipoPago(tp);
		else
			return ctp.updateTipoPago(tp);
	}
	
	private RespuestaServidor validarTipoPago(TipoPago tipoPago) throws RespuestaServidor {
		RespuestaServidor sr = new RespuestaServidor();
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		
		if (tipoPago.getId() == 0) {
			if (ctp.getTipoPago(tipoPago.getId()) == null)
				sr.addError("Está intentando editar un tipo de pago inexistente...");
		}
		
		if (tipoPago.getTipoPago() == null || tipoPago.getTipoPago().isEmpty())
			sr.addError("El tipo de pago no puede quedar en blanco.");
		
		return sr;
	}
}
