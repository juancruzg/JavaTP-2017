	package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.EntidadBase;
import excepciones.RespuestaServidor;

public abstract class CatalogoBase {	   
	/**
	 * @author Juan Grasso
	 * @return Devuelve una lista de la entidad (EntidadBase) especificada, basada en el query que se le manda
	 */
    protected <T extends EntidadBase> ArrayList<T> getAll(DBData data, DBToObject<T> converter) throws RespuestaServidor {
        ArrayList<T> ResList = new ArrayList<>();
    	Statement st = null;
    	ResultSet rs = null;
    	RespuestaServidor res = new RespuestaServidor();
    	
        try {
        	// Dependiendo si tengo parámetros para la query abro un Statement o un PreparedStatement...
        	if (data.getParameters() == null || data.getParameters().isEmpty()) {
        		st = Conexion.getInstancia().getConn().createStatement();
        		
        		// ... y corre el query
                rs = st.executeQuery(data.getQuery()); 
        	}
        	else {
        		st = Conexion.getInstancia().getConn().prepareStatement(data.getQuery(), Statement.RETURN_GENERATED_KEYS);
        		
        		int i = 0;
        		
        		for (Object parameter : data.getParameters()) {
        			i++;

        			((PreparedStatement)st).setObject(i, parameter);
        		}

        		// ... y corre el query
                rs = ((PreparedStatement)st).executeQuery();
        	}
            
            while (rs.next()) {
                ResList.add((T) converter.createFromDB(rs));
            }
        }
        catch (SQLException ex) {
        	res.addError(ex);
        	throw res;
        }
		finally {
			Conexion.getInstancia().CloseConn();
		}
        
        return ResList;
    }
		
    /**
	 * @author Juan Grasso
	 * @return Devuelve una entidad (Base), basada en el query que se le manda
	 */
    protected <T extends EntidadBase> T getOne(DBData data, DBToObject<T> converter) throws RespuestaServidor {
    	T result = null;
    	Statement st = null;
    	ResultSet rs = null;
    	RespuestaServidor res = new RespuestaServidor();
    	
        try {
        	if (data.getParameters() == null || data.getParameters().isEmpty()) {
        		st = Conexion.getInstancia().getConn().createStatement();
        		
                rs = st.executeQuery(data.getQuery()); 
        	}
        	else {
        		st = Conexion.getInstancia().getConn().prepareStatement(data.getQuery(), Statement.RETURN_GENERATED_KEYS);
        		
        		int i = 0;
        		
        		for (Object parameter : data.getParameters()) {
        			i++;

        			((PreparedStatement)st).setObject(i, parameter);
        		}

                rs = ((PreparedStatement)st).executeQuery();
        	}
        	
            
            if (rs.next())
                result = (T) converter.createFromDB(rs);
        }
        catch (SQLException ex) {
        	res.addError(ex);
        	throw res;
        }
		finally {
			Conexion.getInstancia().CloseConn();
		}
        
        return result;
    }	
    
    /**
	 * @author Juan Grasso
	 * @return Devuelve un entero. Devuelve la cantidad de registros que se editaron o 0 si ninguno se editó.
	 */
    protected <T extends CatalogoBase> int save(DBData data) throws RespuestaServidor {
    	int result = 0;
    	PreparedStatement st = null;
    	RespuestaServidor res = new RespuestaServidor();
    	
    	try {
    		st = Conexion.getInstancia().getConn().prepareStatement(data.getQuery(), Statement.RETURN_GENERATED_KEYS);
    		
    		int i = 0;
    		
    		for (Object parameter : data.getParameters()) {
    			i++;

    			((PreparedStatement)st).setObject(i, parameter);
    		}

            result = ((PreparedStatement)st).executeUpdate();
    	}
    	catch (SQLException ex) {
        	res.addError(ex);
        	throw res;
        }
    	finally {
			Conexion.getInstancia().CloseConn();
		}
    	
    	return result;
    }
}
