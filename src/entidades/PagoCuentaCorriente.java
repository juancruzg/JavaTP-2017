package entidades;

import java.sql.Timestamp;

public class PagoCuentaCorriente extends Pago {
	private int nroCuota;
	private Timestamp fechaVencimiento;
	private boolean pagado;
	
	public int getNroCuota() {
		return nroCuota;
	}
	
	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}
	
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public boolean isPagado() {
		return pagado;
	}
	
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	
}
