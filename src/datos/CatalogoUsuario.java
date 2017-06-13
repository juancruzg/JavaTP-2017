package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import entidades.Usuario;

public class CatalogoUsuario extends CatalogoBase {
	public Usuario obtenerUsuario(String usuario) {
		PreparedStatement sentencia = null;
		Usuario u = new Usuario();
		String sql = "SELECT * FROM usuario WHERE usuario = ?";

		try {
			sentencia = Conexion.getInstancia().getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, usuario);
		} 
		catch (SQLException e) {
			return u;
		}
		
		query(sentencia, u);
		
		return u;
	}
}
