package modelos;

import entidades.EntidadBase;
import entidades.Venta;

public class DtoModeloPagoVenta implements EntidadBase {

	public ModeloPago modeloPago;
	
	public Venta venta;

	public ModeloPago getModeloPago() {
		return modeloPago;
	}

	public void setModeloPago(ModeloPago modeloPago) {
		this.modeloPago = modeloPago;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
