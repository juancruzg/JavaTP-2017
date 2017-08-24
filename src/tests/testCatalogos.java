package tests;

import java.util.ArrayList;

import com.google.gson.Gson;

import datos.CatalogoUsuario;
import entidades.Usuario;
import excepciones.RespuestaServidor;
import negocio.ControladorUsuario;

public class testCatalogos {

	public static void main(String[] args) {
		ControladorUsuario cu = new ControladorUsuario();
		Gson gson = new Gson();

			System.out.println(cu.encriptarContrasena("facafaca"));
	}

}
