package entidades;

import java.sql.Timestamp;

public class Pago implements EntidadBase {
	private int id;
	private Timestamp fechaPago;
	private Venta venta;
	private TipoPago tipoPago;
	private float monto;
	
	public Pago() {
		
	}
	
	public Pago(Pago p) {
		this.id = p.getId();
		this.fechaPago = p.getFechaPago();
		this.venta = p.getVenta();
		this.tipoPago = p.getTipoPago();
		this.monto = p.getMonto();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Timestamp getFechaPago() {
		return fechaPago;
	}
	
	public void setFechaPago(Timestamp fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public Venta getVenta() {
		return venta;
	}
	
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public TipoPago getTipoPago() {
		return tipoPago;
	}
	
	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}
	

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
}
