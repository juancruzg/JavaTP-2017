package negocio;

import datos.CatalogoLineaProducto;
import entidades.LineaProducto;
import excepciones.RespuestaServidor;

public class ControladorLineaProducto {
	public int saveLineaProducto(LineaProducto lp) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		LineaProducto lineaDB = clp.getLineaProducto(lp.getProducto().getId(), lp.getTalle().getId(), lp.getColor().getId(), lp.getSucursal().getId());
		
		rs = validarLineaProducto(lp);
		
		if (!rs.getStatus())
			throw rs;
		
		if (lineaDB != null)
			return clp.updateLineaProducto(lp);
		else
			return clp.insertLineaProducto(lp);
	}
	
	private RespuestaServidor validarLineaProducto(LineaProducto lp) {
		RespuestaServidor rs = new RespuestaServidor();
		
		return rs;
	}
}
