package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoPago;
import excepciones.RespuestaServidor;

public class CatalogoTipoPago extends CatalogoBase {
	public TipoPago getTipoPago(int idTipoPago) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM tipoPago WHERE id = ?");
		
		data.addParameter(idTipoPago);
		
		return super.getOne(data, rs -> fetchTipoPagoFromDB(rs));
	}
	
	public ArrayList<TipoPago> getTiposPago() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM tipoPago");
		
		return super.getAll(data, rs -> fetchTipoPagoFromDB(rs));
	}
	
	public ArrayList<TipoPago> getTiposPago(int paginaActual, int porPagina) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM tipoPago LIMIT ?, ?");
		
		data.addParameter(paginaActual);
		data.addParameter(porPagina);
		
		return super.getAll(data, rs -> fetchTipoPagoFromDB(rs));
	}
	
	public int insertTipoPago(TipoPago tp) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO tipoPago (tipoPago) VALUES (?)");
		
		data.addParameter(tp.getTipoPago());
		
		return super.save(data);
	}
	
	public int updateTipoPago(TipoPago tp) throws RespuestaServidor {
		DBData data = new DBData("UPDATE tipoPago SET tipoPago = ? WHERE id = ?");
		
		data.addParameter(tp.getTipoPago());
		data.addParameter(tp.getId());
		
		return super.save(data);
	}
	
	private TipoPago fetchTipoPagoFromDB(ResultSet rs) {
		TipoPago tp = new TipoPago();
		
		try {
			tp.setId(rs.getInt("id"));
			tp.setTipoPago(rs.getString("tipoPago"));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tp;
	}
}
