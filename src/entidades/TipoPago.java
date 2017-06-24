package entidades;

public class TipoPago implements EntidadBase {
	private int id;
	private String tipoPago;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	public String toJson() {
		return null;
	}
}
