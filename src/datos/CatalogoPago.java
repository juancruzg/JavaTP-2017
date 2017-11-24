package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Pago;
import entidades.PagoCuentaCorriente;
import entidades.PagoEfectivo;
import entidades.PagoTarjeta;
import entidades.TipoPago;
import excepciones.RespuestaServidor;

public class CatalogoPago extends CatalogoBase {
	public Pago getPago(int id) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM pago WHERE id = ?");
		
		data.addParameter(id);
		
		return this.getOne(data, rs -> fetchPagoFromDB(rs));
	}
	
	public ArrayList<Pago> getPagos() throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM pago");
		
		return this.getAll(data, rs -> fetchPagoFromDB(rs));
	}
	
	public ArrayList<Pago> getPagos(int idVenta) throws RespuestaServidor {
		DBData data = new DBData("SELECT * FROM pago WHERE idVenta = ?");
		
		data.addParameter(idVenta);
		
		return this.getAll(data, rs -> fetchPagoFromDB(rs));
	}
	
	public int savePago(Pago pago) throws RespuestaServidor {
		DBData data = new DBData("INSERT INTO pago (idVenta, idTipoPago, monto, fechaPago) VALUES (?, ?, ?, ?)");
	
		data.addParameter(pago.getVenta().getId());
		data.addParameter(pago.getTipoPago().getId());
		data.addParameter(pago.getMonto());
		data.addParameter(pago.getFechaPago());
		
		return this.save(data);
	}
	
	private Pago fetchPagoFromDB(ResultSet rs) {
		CatalogoTipoPago ctp = new CatalogoTipoPago();
		//CatalogoVenta cv = new CatalogoVenta(); Por ahora comento esto para no generar bucles
		
		Pago pago = null;
		
		try {
			TipoPago tipoPago = ctp.getTipoPago(rs.getInt("idTipoPago"));
						
			switch (tipoPago.getId()) {
			case 1: // Efectivo
				pago = new PagoEfectivo();
				
				break;
			case 2: // CuentaCorriente
				pago = new PagoCuentaCorriente();
				
				((PagoCuentaCorriente)pago).setFechaVencimiento(rs.getTimestamp("fechaTarjeta"));
				((PagoCuentaCorriente)pago).setNroCuota(rs.getInt("nroCuota"));
				((PagoCuentaCorriente)pago).setPagado(rs.getBoolean("pagado"));

				break;
			case 3: // Tarjeta
				pago = new PagoTarjeta();
				
				((PagoTarjeta)pago).setNroTarjeta(rs.getString("nroTarjeta"));
				((PagoTarjeta)pago).setVencimientoTarjeta(rs.getString("vencimientoTarjeta"));
				((PagoTarjeta)pago).setNombreTitular(rs.getString("nombreTitular"));
				
				break;
			default:
				return null;
			}
			
			pago.setTipoPago(tipoPago);
			pago.setFechaPago(rs.getTimestamp("fechaPago"));
			pago.setId(rs.getInt("id"));
			pago.setMonto(rs.getFloat("monto"));
			//pago.setVenta(cv.getVenta(rs.getInt("idVenta")));
		} catch (SQLException | RespuestaServidor e) {
			e.printStackTrace();
		}
		
		return pago;
	}
}
