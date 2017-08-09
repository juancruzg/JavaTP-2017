package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import conexion.Conexion;
import datos.CatalogoLineaProducto;
import datos.CatalogoPrecio;
import datos.CatalogoProducto;
import datos.CatalogoUsuario;
import entidades.LineaProducto;
import entidades.Precio;
import entidades.Producto;
import entidades.Sucursal;
import excepciones.RespuestaServidor;

public class ControladorProducto {
	public Producto getProducto(int idProducto) throws RespuestaServidor {
		CatalogoProducto cc = new CatalogoProducto();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		Producto producto = cc.getProducto(idProducto);
		
		if (producto != null)
			producto.setLineas(clp.getLineaProductos(idProducto));
		
		return producto;
	}
	
	public ArrayList<Producto> getProductos(int paginaActual, int porPagina, boolean mostrarInactivos) throws RespuestaServidor {
		CatalogoProducto cc = new CatalogoProducto();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		ArrayList<Producto> productos = cc.getProductos(paginaActual, porPagina, mostrarInactivos);
		
		for (Producto producto : productos) {
			producto.setLineas(clp.getLineaProductos(producto.getId()));
		}
		
		return productos;
	}
	
	public ArrayList<Producto> getProductos(int paginaActual, int porPagina, boolean mostrarInactivos, String query) throws RespuestaServidor {
		CatalogoProducto cc = new CatalogoProducto();
		CatalogoLineaProducto clp = new CatalogoLineaProducto();
		
		ArrayList<Producto> productos = cc.getProductos(paginaActual, porPagina, mostrarInactivos, query);
		
		for (Producto producto : productos) {
			producto.setLineas(clp.getLineaProductos(producto.getId()));
		}
		
		return productos;
	}
	
	public int saveProducto(Producto p) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoProducto cp = new CatalogoProducto();
		ControladorPrecio ctrp = new ControladorPrecio();
		ControladorLineaProducto ctrlp = new ControladorLineaProducto();

		Connection conn = Conexion.getInstancia().getConn();
		int retorno = 0;

		try {
			conn.setAutoCommit(false);
			
			// Primero guardo o edito el producto
			
			if (p.getId() == 0) {
				retorno = cp.insertProducto(p);
				p.setId(retorno);
			}
			else
				retorno = cp.updateProducto(p);
			
			// Después guardo o edito el precio
			p.getPrecio().setProducto(p);
			ctrp.savePrecio(p.getPrecio());
			
			// Después guardo o edito las líneas
			for (LineaProducto linea : p.getLineas()) {
				if (linea.getProducto() == null)
					linea.setProducto(p);
				
				// TODO: Hardcodeo por ahora
				linea.setSucursal(new Sucursal(1, "", ""));
				
				ctrlp.saveLineaProducto(linea);
			}
			
			// Corren las validaciones
			rs = validarProducto(p);
			
			// Si falla alguna, lanzar el error
			if (!rs.getStatus()) {
				conn.rollback();
				throw rs;
			}
			
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(RespuestaServidor sr) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			throw sr;
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		finally {
			Conexion.getInstancia().CloseConn();
		}
		
		return retorno;
	}
	
	/*public int saveProducto(int id, String descripcion, float precio, String usuario, boolean activo) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoProducto cp = new CatalogoProducto();
		CatalogoPrecio cpr = new CatalogoPrecio();
		CatalogoUsuario cu = new CatalogoUsuario();
		
		// Instancio y construyo el cliente, busco las FK en la DB.
		Producto p = new Producto();
		Precio precioDB = null;
		
		//cp.beginTransaction();
		
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
				rs.addError("Producto a modificar no contenï¿½a un precio");
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
	
		//cp.commitTransaction();

		return retorno;	
	}*/
	
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
