package conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	private Properties prop = new Properties();
	private InputStream input = null;
	
	private static Conexion instancia;
	public static Conexion getInstancia() {
		if(instancia == null)
			instancia = new Conexion();
		
		return instancia;
	}
	
	private static String dbUrl;
	private static String dbUser;
	private static String dbPassword;
	
	private Connection conn;
	static int cantCon=0;
	
	//Construtor Default
	private Conexion() {	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=null;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		try {
			input = new FileInputStream("resources/config.properties");
			
			prop.load(input);
			
			dbUrl = prop.getProperty("db-url");
			dbUser = prop.getProperty("db-user");
			dbPassword = prop.getProperty("db-password");
			
			if(conn==null || !conn.isValid(3)) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			}		
			
			cantCon++;
			System.out.println("Nueva conexión abierta -> cantidad de conexiones abiertas: " + cantCon);
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//Cerrar conexión y manejo de errores
	public void CloseConn() {
		try {
			cantCon--;
			System.out.println("Se cerró una conexión -> cantidad de conexiones abiertas: " + cantCon);
			
			if(cantCon==0)
				conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void startTransaction() throws SQLException {
		conn.setAutoCommit(true);
	}
	
	public void commit() throws SQLException {
		conn.commit();
		conn.setAutoCommit(false);
	}
	
	public void rollback() throws SQLException {
		conn.rollback();
		conn.setAutoCommit(false);
	}
}
