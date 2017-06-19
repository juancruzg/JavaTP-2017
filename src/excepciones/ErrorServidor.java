package excepciones;

public class ErrorServidor {
	private String mensajeError, detalleError;
	
	public ErrorServidor() {
		
	}
	
	public ErrorServidor(Exception ex) {
		this.mensajeError = ex.getMessage();
		this.detalleError = ex.toString();
	}
	
	public ErrorServidor(String errorMessage) {
		this.mensajeError = errorMessage;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getDetalleError() {
		return detalleError;
	}

	public void setDetalleError(String detalleError) {
		this.detalleError = detalleError;
	}
	
	
}
