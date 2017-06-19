package tests;

import excepciones.RespuestaServidor;
import negocio.ControladorUsuario;

public class testControladores {
	public static void main(String[] args) {
		ControladorUsuario cu = new ControladorUsuario();
		
		try {
			cu.insertUsuario("Test", "Test", "test", "", 0, null);
		} catch (RespuestaServidor e) {
			System.out.println(e.toString());
		}
	}
}
