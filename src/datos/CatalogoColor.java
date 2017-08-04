package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Color;
import excepciones.RespuestaServidor;

public class CatalogoColor extends CatalogoBase {
	public Color getColor(int idColor) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM color WHERE id = ?");
		
		data.addParameter(idColor);
		
		return this.getOne(data, rs -> fetchColorFromDB(rs));
	}
	
	public ArrayList<Color> getColores() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM color");
		
		return this.getAll(data, rs -> fetchColorFromDB(rs));
	}
	
	public ArrayList<Color> getColores(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM color LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return this.getAll(data, rs -> fetchColorFromDB(rs));
	}
	
	public int insertColor(Color color) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO color (color, codigoColor) VALUES (?, ?)");
		
		data.addParameter(color.getColor());
		data.addParameter(color.getCodigoColor());
		
		return this.save(data);
	}
	
	public int updateColor(Color color) throws RespuestaServidor {
		DBData data = new DBData("UPDATE color SET color = ?, codigoColor = ? WHERE id = ?");
		
		data.addParameter(color.getColor());
		data.addParameter(color.getCodigoColor());
		data.addParameter(color.getId());
		
		return this.save(data);
	}
	
	private Color fetchColorFromDB(ResultSet rs) {
		Color c = new Color();
		
		try {
			//c.setCodigoColor(rs.getString("codigoColor"));
			c.setColor(rs.getString("color"));
			c.setId(rs.getInt("id"));
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return c;
	}
}
