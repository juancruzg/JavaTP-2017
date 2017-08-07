package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;

import datos.CatalogoUsuario;
import datos.CatalogoPrecio;
import datos.CatalogoProducto;
import entidades.Usuario;
import entidades.Precio;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class ControladorPrecio {
	public Precio getPrecio(int idProducto, Timestamp fecha) throws RespuestaServidor {
		CatalogoPrecio cu = new CatalogoPrecio();
		
		return cu.getPrecio(idProducto, fecha);
	}
	
	public Precio getUltimoPrecio(int idProducto) throws RespuestaServidor {
		CatalogoPrecio cu = new CatalogoPrecio();
		
		return cu.getUltimoPrecio(idProducto);
	}
	
	public ArrayList<Precio> getPrecios(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoPrecio cu = new CatalogoPrecio();
		
		return cu.getPrecios(paginaActual, porPagina);
	}
	
	public int savePrecio(Precio p) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoPrecio cp = new CatalogoPrecio();
		
		Precio precioDB = cp.getUltimoPrecio(p.getProducto().getId());
		
		if (precioDB == null || precioDB.getPrecio() != p.getPrecio()) {
			p.setFecha(new Timestamp(System.currentTimeMillis()));
			
			rs = validarPrecio(p);
			
			if (!rs.getStatus())
				throw rs;
			
			return cp.insertPrecio(p);
		}
		else
			return 0;
	}
	
	public int savePrecio(int idProducto, Timestamp fecha, float precio, String usuario) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoPrecio cp = new CatalogoPrecio();
		CatalogoUsuario cu = new CatalogoUsuario();
		CatalogoProducto cprod = new CatalogoProducto();
			
		// Fetcheo las FKs
		Usuario usuarioAlta = cu.getUsuario(usuario);
		Producto producto = cprod.getProducto(idProducto);
		
		// Creo un nuevo usuario y llamo al método para guardar en la capa de datos
		Precio p = new Precio();
		
		p.setProducto(producto);
		p.setFecha(fecha);
		p.setPrecio(precio);
		p.setUsuarioAlta(usuarioAlta);
		
		return savePrecio(p);
	}
	
	private RespuestaServidor validarPrecio(Precio p) {
		RespuestaServidor res = new RespuestaServidor();
		
		if(p.getFecha() == null )
			res.addError("La fecha es obligatoria");
		
		if(p.getPrecio() <= 0)
			res.addError("El precio no puede ser 0 o negativo");
			
		if(p.getProducto() == null || p.getProducto().getId() <= 0)
			res.addError("El precio debe estar asociado a un producto");
		
		return res;
	}
}
