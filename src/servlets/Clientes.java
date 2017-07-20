package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Cliente;
import entidades.Usuario;
import excepciones.RespuestaServidor;
import negocio.ControladorCliente;
import util.Tipos;

@WebServlet("/Clientes")
public class Clientes extends ServletBase {
	private static final long serialVersionUID = 1L;
	
    public Clientes() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCliente cc = new ControladorCliente();
		
		String json;
		
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		int idCliente = Tipos.toInt(request.getParameter("idCliente"));

		if (idCliente == 0) {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			try {
				clientes = cc.getClientes(paginaActual, porPagina);
			}
			catch(RespuestaServidor rs) {
				
			}
			
			Gson gson = new Gson();
			json = gson.toJson(clientes);
		}
		else {
			Cliente cliente = new Cliente();
			
			try {
				cliente = cc.getCliente(idCliente);
			}
			catch(RespuestaServidor rs) {
				
			}
			
			Gson gson = new Gson();
			json = gson.toJson(cliente);
		}
		
		enviarJSON(request, response, json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCliente cc = new ControladorCliente();
		
		Cliente c = (Cliente) procesarRequest(request, Cliente.class);
		int rta = 0;
		
		try
		{
			rta = cc.saveCliente(c);
		} 
		catch (RespuestaServidor e)
		{
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(rta).toString());
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCliente cc = new ControladorCliente();
		
		Cliente c = (Cliente) procesarRequest(request, Cliente.class);
		int rta = 0;
		
		Usuario u = new Usuario();
		
		u.setUsuario("juan");
		
		c.setUsuarioAlta(new Usuario());
		
		try
		{
			rta = cc.saveCliente(c);
		} 
		catch (RespuestaServidor e)
		{
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(rta).toString());
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
