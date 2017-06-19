package excepciones;

import java.util.ArrayList;

public class RespuestaServidor extends Exception {
	private static final long serialVersionUID = 1L;
	
	ArrayList<ErrorServidor> errores;
	
	public String toString() {
		String ret;
		
		ret = "Errores: \n";
		
		for (ErrorServidor er : this.errores) {
			ret += er.getMensajeError() + "\n";
		}
		
		return ret;
	}
	
	public void addError(Exception ex) {
		if (errores == null)
			errores = new ArrayList<ErrorServidor>();
		
		errores.add(new ErrorServidor(ex));
	}

	public void addError(String error) {
		if (errores == null)
			errores = new ArrayList<ErrorServidor>();
		
		errores.add(new ErrorServidor(error));
	}
	
	public boolean getStatus() {
		return errores == null || errores.isEmpty();
	}
	
	public ArrayList<ErrorServidor> getErrores() {
		return this.errores;
	}
}
