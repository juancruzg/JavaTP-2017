package entidades;

import java.sql.Timestamp;

public class DetalleVenta implements EntidadBase {
	private Venta venta;
	private Producto producto;
	private int cantidad;
	private boolean llevaAProbar;
	private Timestamp fechaDevolucion;
	
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isLlevaAProbar() {
		return llevaAProbar;
	}

	public void setLlevaAProbar(boolean llevaAProbar) {
		this.llevaAProbar = llevaAProbar;
	}

	public Timestamp getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Timestamp fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String toJson() {
		return null;
	}

}
