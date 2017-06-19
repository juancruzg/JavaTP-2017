package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import excepciones.RespuestaServidor;

public class CatalogoCliente extends CatalogoBase {
	public Cliente getCliente(int idCliente) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM cliente WHERE id = ?");
		
		data.addParameter(idCliente);
		
		return super.getOne(data, rs -> fetchClienteFromDB(rs));
	}
	
	public ArrayList<Cliente> getClientes() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM cliente");
		
		return super.getAll(data, rs -> fetchClienteFromDB(rs));
	}
	
	public ArrayList<Cliente> getClientes(int paginaAtual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM cliente LIMIT ?, ?");
		
		data.addParameter(paginaAtual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchClienteFromDB(rs));
	}
	
	public int insertCliente(Cliente cliente) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO cliente (nombre, apellido, telefono, domicilio, activo, usuarioAlta) VALUES (?, ?, ?, ?, ?, ?)");
		
		data.addParameter(cliente.getNombre());
		data.addParameter(cliente.getApellido());
		data.addParameter(cliente.getTelefono());
		data.addParameter(cliente.getDomicilio());
		data.addParameter(cliente.isActivo());
		data.addParameter(cliente.getUsuarioAlta().getUsuario());
		
		return super.save(data);
	}

	private Cliente fetchClienteFromDB(ResultSet rs) {
		CatalogoUsuario cu = new CatalogoUsuario();
		Cliente cliente = new Cliente();
		
		try {
			cliente.setActivo(rs.getBoolean("activo"));
			cliente.setApellido(rs.getString("apellido"));
			cliente.setDomicilio(rs.getString("domicilio"));
			cliente.setFechaAlta(rs.getTimestamp("fechaAlta"));
			cliente.setId(rs.getInt("id"));
			cliente.setNombre(rs.getString("nombre"));
			cliente.setTelefono(rs.getString("telefono"));
			
			cliente.setUsuarioAlta(cu.getUsuario(rs.getString("usuarioAlta")));
		}
		catch(SQLException | RespuestaServidor ex) {
			ex.printStackTrace();
		}
		
		return cliente;
	}
}