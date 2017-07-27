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
		
		Respuesta rta = new Respuesta();
		
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		int idCliente = Tipos.toInt(request.getParameter("idCliente"));
		String query = request.getParameter("query");

		if (idCliente == 0) {
			try {
				if (query != null)
					rta.setData(cc.getClientes(paginaActual, porPagina, query));
				else
					rta.setData(cc.getClientes(paginaActual, porPagina));
			}
			catch(RespuestaServidor rs) {
				rta.setErrores(rs.getErrores());
			}
		}
		else {
			try {
				rta.setData(cc.getCliente(idCliente));
			}
			catch(RespuestaServidor rs) {
				rta.setErrores(rs.getErrores());
			}
		}
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCliente cc = new ControladorCliente();
		
		Cliente c = (Cliente) procesarRequest(request, Cliente.class);
		RespuestaServidor sr = new RespuestaServidor();
		int nro = 0;
		
		try
		{
			nro = cc.saveCliente(c);
		} 
		catch (RespuestaServidor e)
		{
			sr = e;
		}
		
		Respuesta rta = new Respuesta();
		
		rta.setData(nro);
		rta.setErrores(sr.getErrores());
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCliente cc = new ControladorCliente();
		
		Cliente c = (Cliente) procesarRequest(request, Cliente.class);
		RespuestaServidor sr = new RespuestaServidor();
		int nro = 0;
		
		Usuario u = new Usuario();
		u.setUsuario("juan");
		c.setUsuarioAlta(new Usuario());
		
		try
		{
			nro = cc.saveCliente(c);
		} 
		catch (RespuestaServidor e)
		{
			sr = e;
		}
		
		Respuesta rta = new Respuesta();
		
		rta.setData(nro);
		rta.setErrores(sr.getErrores());
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
