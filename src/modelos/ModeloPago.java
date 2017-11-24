package modelos;

import java.sql.Timestamp;

import entidades.EntidadBase;
import entidades.Pago;
import entidades.TipoPago;
import entidades.Venta;

public class ModeloPago implements EntidadBase{
	private String nroTarjeta, nombreTitular, vencimientoTarjeta; // Pongo el vencimiento como String de momento porque es mm/yyyy
	private int id, nroCuota;
	private Timestamp fechaPago;
	private Timestamp fechaVencimiento;
	private Venta venta;
	private TipoPago tipoPago;
	private float monto;
	private boolean pagado;
	
	
	
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
	
	
	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getVencimientoTarjeta() {
		return vencimientoTarjeta;
	}

	public void setVencimientoTarjeta(String vencimiento) {
		this.vencimientoTarjeta = vencimiento;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
}
