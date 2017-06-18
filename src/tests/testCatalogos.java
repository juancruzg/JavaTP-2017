package tests;

import java.util.ArrayList;

import datos.CatalogoUsuario;
import entidades.Usuario;

public class testCatalogos {

	public static void main(String[] args) {
		CatalogoUsuario cu = new CatalogoUsuario();
		
		ArrayList<Usuario> usuarios = cu.getUsuarios(0, 3);
		
		System.out.println(usuarios.size());
		System.out.println(usuarios.get(0).getNombre());
	}

}
