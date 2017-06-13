package entidades;

import java.sql.Timestamp;

import datos.CatalogoUsuario;

public class Prueba {

	public static void main(String[] args) {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		Usuario u = cu.obtenerUsuario("juan");
		
		System.out.println(u.getApellido());
		System.out.println(u.getNombre());
	}
}
