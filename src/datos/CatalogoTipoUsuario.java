package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoUsuario;
import excepciones.RespuestaServidor;

public class CatalogoTipoUsuario extends CatalogoBase {
	public TipoUsuario getTipoUsuario(int tipoUsuario) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM tipoUsuario WHERE id = ?");
		
		data.addParameter(tipoUsuario);
		
		return getOne(data, rs -> fetchFromDB(rs));
	}
	
	public ArrayList<TipoUsuario> getTiposUsuario() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM tipoUsuario");
				
		return getAll(data, rs -> fetchFromDB(rs));
	}
	
	private TipoUsuario fetchFromDB(ResultSet rs) {
		CatalogoModulo cm = new CatalogoModulo();
		TipoUsuario tu = new TipoUsuario();
		
		try {
			int id = rs.getInt("id");
			
			tu.setId(id);
			tu.setDescripcion(rs.getString("descripcion"));
			
			tu.setModulos(cm.getModulos(id));
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (RespuestaServidor e) {
			e.printStackTrace();
		}
		
		return tu;
	}
}
