package negocio;

import java.util.ArrayList;

import datos.CatalogoTipoPago;
import datos.CatalogoTipoUsuario;
import entidades.TipoPago;
import entidades.TipoUsuario;
import excepciones.RespuestaServidor;

public class ControladorTipoUsuario {
	public TipoUsuario getTipoPago(int idTipoUsuario) throws RespuestaServidor {
		CatalogoTipoUsuario ctu = new CatalogoTipoUsuario();
		
		return ctu.getTipoUsuario(idTipoUsuario);
	}

	public ArrayList<TipoUsuario> getTiposUsuario() throws RespuestaServidor {
		CatalogoTipoUsuario ctu = new CatalogoTipoUsuario();
		
		return ctu.getTiposUsuario();
	}
}
