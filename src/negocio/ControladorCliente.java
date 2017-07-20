package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;

import datos.CatalogoCliente;
import datos.CatalogoUsuario;
import entidades.Cliente;
import excepciones.RespuestaServidor;

public class ControladorCliente {
	public Cliente getCliente(int idCliente) throws RespuestaServidor {
		CatalogoCliente cc = new CatalogoCliente();
		
		return cc.getCliente(idCliente);
	}
	
	public ArrayList<Cliente> getClientes(int paginaActual, int porPagina) throws RespuestaServidor {
		CatalogoCliente cc = new CatalogoCliente();
		
		return cc.getClientes(paginaActual, porPagina);
	}
	
	public int saveCliente(String nombre, String apellido, int idCliente, String telefono, String domicilio, boolean activo, String usuarioAlta) throws RespuestaServidor {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		// Instancio y construyo el cliente, busco las FK en la DB.
		Cliente c = new Cliente();
		
		c.setActivo(activo);
		c.setApellido(apellido);
		c.setDomicilio(domicilio);
		c.setId(idCliente);
		c.setNombre(nombre);
		c.setTelefono(telefono);
		c.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		c.setUsuarioAlta(cu.getUsuario(usuarioAlta));
		
		return saveCliente(c);
	}
	
	public int saveCliente(Cliente c) throws RespuestaServidor {
		RespuestaServidor rs = new RespuestaServidor();
		CatalogoCliente cc = new CatalogoCliente();

		// Corren las validaciones
		rs = validarCliente(c);
		
		// Si falla alguna, lanzar el error
		if (!rs.getStatus())
			throw rs;
		
		if (c.getId() == 0)
			return cc.insertCliente(c);
		else
			return cc.updateCliente(c);
	}
	
	private RespuestaServidor validarCliente (Cliente cliente) throws RespuestaServidor {
		CatalogoCliente cc = new CatalogoCliente();
		RespuestaServidor sr = new RespuestaServidor();
		
		if (cliente.getId() != 0) {
			if (cc.getCliente(cliente.getId()) == null)
				sr.addError("El cliente que intenta editar no existe...");
		}
		
		if (cliente.getNombre() == null || cliente.getNombre().isEmpty())
			sr.addError("El nombre del cliente no puede estar en blanco.");
		
		if (cliente.getApellido() == null || cliente.getApellido().isEmpty())
			sr.addError("El nombre del cliente no puede estar en blanco.");
		
		return sr;
	}
}
