package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Sucursal;
import excepciones.RespuestaServidor;

public class CatalogoSucursal extends CatalogoBase {	
	public Sucursal getSucursal(int idSucursal) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM sucursal WHERE id = ?");
		
		data.addParameter(idSucursal);
		
		return super.getOne(data, rs -> fetchSucursalFromDB(rs));
	}
	
	public ArrayList<Sucursal> getSucursales() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM sucursal");
		
		return super.getAll(data, rs -> fetchSucursalFromDB(rs));
	}
	
	public ArrayList<Sucursal> getSucursales(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM sucursal LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchSucursalFromDB(rs));
	}
	
	public int insertSucursal(Sucursal sucursal) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO sucursal(domicilio, telefono) VALUES (?, ?)");
		
		data.addParameter(sucursal.getDomicilio());
		data.addParameter(sucursal.getTelefono());
		
		return super.save(data);
	}
	
	private Sucursal fetchSucursalFromDB(ResultSet rs) {
		Sucursal sucursal = new Sucursal();
		
		try {
			sucursal.setDomicilio(rs.getString("domicilio"));
			sucursal.setTelefono(rs.getString("telefono"));
			sucursal.setId(rs.getInt("id"));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sucursal;
	}
}
