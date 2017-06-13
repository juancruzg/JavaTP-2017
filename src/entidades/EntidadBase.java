package entidades;

import java.sql.ResultSet;

public interface EntidadBase {
	/**
	 * @author juan
	 * @return Devuelve la entidad como un JSON object
	 */
	public String toJson();
	
	/**
	 * @author juan
	 * @return Llena los campos de la entidad con un ResultSet
	 */
	public void autoFill(ResultSet rs);
}
