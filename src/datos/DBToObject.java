package datos;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBToObject<T> {
	public T createFromDB(ResultSet rs) throws SQLException;
}
