package negocio;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import conexion.Conexion;
import datos.CatalogoLineaProducto;
import datos.CatalogoPrecio;
import datos.CatalogoProducto;
import datos.CatalogoUsuario;
import entidades.Precio;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class ControladorProducto {
	public Producto getProducto(int idProducto) throws RespuestaServidor {
		CatalogoProducto cc = new CatalogoProducto();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		Producto producto = cc.getProducto(idProducto);
		
		producto.setLineas(clp.getLineaProductos(idProducto));
		
		return producto;
	}
	
	public ArrayList<Producto> getProductos(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoProducto cc = new CatalogoProducto();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		ArrayList<Producto> productos = cc.getProductos(paginaActual, porPagina);
		
		for (Producto producto : productos) {
			producto.setLineas(clp.getLineaProductos(producto.getId()));
		}
		
		return productos;
	}
	
	public ArrayList<Producto> getProductos(int paginaActual, int porPagina, String query) throws RespuestaServidor {
		CatalogoProducto cc = new CatalogoProducto();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		ArrayList<Producto> productos = cc.getProductos(paginaActual, porPagina, query);
		
		for (Producto producto : productos) {
			producto.setLineas(clp.getLineaProductos(producto.getId()));
		}
		
		return productos;
	}
	
	public int saveProducto(int id, String descripcion, float precio, String usuario, boolean activo) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoProducto cp = new CatalogoProducto();
		CatalogoPrecio cpr = new CatalogoPrecio();
		CatalogoUsuario cu = new CatalogoUsuario();
		
		// Instancio y construyo el cliente, busco las FK en la DB.
		Producto p = new Producto();
		Precio precioDB = null;
		
		try {
		
			Conexion.getInstancia().startTransaction();
			
			if(id == 0)	{
				precioDB = new Precio();
				precioDB.setFecha(new Timestamp(System.currentTimeMillis()));
				precioDB.setPrecio(precio);
				precioDB.setUsuarioAlta(cu.getUsuario(usuario));
				precioDB.setProducto(new Producto(id, null, null, null));
				cpr.insertPrecio(precioDB);
			}
			else {
				precioDB = cpr.getUltimoPrecio(id);
				
				if(precioDB == null){
					rs.addError("Producto a modificar no conten�a un precio");
					throw rs;
				}
				
				if(precioDB.getPrecio() != precio){
					precioDB.setFecha(new Timestamp(System.currentTimeMillis()));
					precioDB.setPrecio(precio);
					precioDB.setUsuarioAlta(cu.getUsuario(usuario));
					precioDB.setProducto(new Producto(id, null, null, null));
					cpr.insertPrecio(precioDB);
				}
			}
			
			p.setId(id);
			p.setDescripcion(descripcion);
			p.setPrecio(precioDB);
			p.setActivo(activo);
			p.setUsuarioAlta(cu.getUsuario(usuario));
			
			// Corren las validaciones
			rs = validarProducto(p);
			
			// Si falla alguna, lanzar el error
			if (!rs.getStatus())
				throw rs;
			
			int retorno;
			
			if (id == 0)
				retorno = cp.insertProducto(p);
			else
				retorno = cp.updateProducto(p);
		
			Conexion.getInstancia().commit();

			return retorno;		
		}
		catch(SQLException e) {
			rs.addError(e);
			throw rs;
		}
	}
	
	private RespuestaServidor validarProducto (Producto producto) throws RespuestaServidor {
		CatalogoProducto cp = new CatalogoProducto();
		RespuestaServidor sr = new RespuestaServidor();
		
		if (producto.getId() != 0) {
			if (cp.getProducto(producto.getId()) == null)
				sr.addError("El producto que intenta editar no existe...");
		}
		
		if (producto.getDescripcion() == null)
			sr.addError("El nombre del cliente no puede estar en blanco.");
		
		return sr;
	}
}
