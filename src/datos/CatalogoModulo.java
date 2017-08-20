package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Modulo;
import excepciones.RespuestaServidor;

public class CatalogoModulo extends CatalogoBase {
	public ArrayList<Modulo> getModulos(int idTipoUsuario) throws RespuestaServidor {
		DBData data = new DBData("SELECT m.* FROM modulo m INNER JOIN tipoUsuarioModulo tum ON m.id = tum.idModulo WHERE tum.idTipoUsuario = ? ORDER BY m.id");
		
		data.addParameter(idTipoUsuario);
		
		return getAll(data, rs -> fetchModuloFromDB(rs));
	}
	
	private Modulo fetchModuloFromDB(ResultSet rs) {
		Modulo m = new Modulo();
		
		try {
			m.setId(rs.getInt("id"));
			m.setNombre(rs.getString("nombre"));
			m.setNombreAMostrar(rs.getString("nombreAMostrar"));
			m.setMostrarEnMenu(rs.getBoolean("mostrarEnMenu"));
			m.setClaseIcono(rs.getString("claseIcono"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
}
