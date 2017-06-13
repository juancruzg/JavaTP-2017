package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import entidades.EntidadBase;

public class CatalogoBase {
		
	public void query(PreparedStatement sentencia, EntidadBase entidad) {
		ResultSet rs = null;
		
		try {
			rs = sentencia.executeQuery();
			
			if (rs.next())
				entidad.autoFill(rs);
		}
		catch(SQLException e) {
			e.printStackTrace();
		} 
		finally {
			Conexion.getInstancia().CloseConn();
		}
	}
}
