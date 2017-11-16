package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Venta implements EntidadBase {
	private int id;
	private Cliente cliente;
	private Timestamp fecha;
	private ArrayList<DetalleVenta> detalles;
	private ArrayList<Pago> pagos;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public ArrayList<DetalleVenta> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetalleVenta> detalles) {
		this.detalles = detalles;
	}

	public ArrayList<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(ArrayList<Pago> pagos) {
		this.pagos = pagos;
	}

	public String toJson() {
		return null;
	}

}
