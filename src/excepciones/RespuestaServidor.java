package excepciones;

import java.util.ArrayList;

public class RespuestaServidor extends Exception {
	private static final long serialVersionUID = 1L;
	
	ArrayList<ErrorServidor> errores;
	
	public void addError(Exception ex) {
		errores.add(new ErrorServidor(ex));
	}

	public void addError(String error) {
		errores.add(new ErrorServidor(error));
	}
	
	public boolean getStatus() {
		return errores.isEmpty();
	}
	
	public ArrayList<ErrorServidor> getErrores() {
		return this.errores;
	}
}
