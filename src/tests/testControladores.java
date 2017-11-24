package tests;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.DetalleVenta;
import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorUsuario;
import negocio.ControladorVenta;

public class testControladores {
	public static void main(String[] args) {
		ControladorVenta cv = new ControladorVenta();
		
		try {
			Venta v = new Venta();
			
			Cliente cliente = new Cliente();
			
			ArrayList<E>
			DetalleVenta dv = new DetalleVenta();
			
			
			
			v.setCliente(cliente);
			v.setDetalles(detalles);
			
			cv.saveVenta(v);
		} catch (RespuestaServidor e) {
			System.out.println(e.toString());
		}
	}
}
