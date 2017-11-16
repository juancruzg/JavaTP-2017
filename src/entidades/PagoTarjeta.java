package entidades;

public class PagoTarjeta extends Pago {
	private String nroTarjeta, nombreTitular, vencimientoTarjeta; // Pongo el vencimiento como String de momento porque es mm/yyyy

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
}
