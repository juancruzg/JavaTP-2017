package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Cliente;
import excepciones.RespuestaServidor;
import negocio.ControladorCliente;
import util.Tipos;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if (accion == null)
			return;
		
		switch (accion) {
		case "getClientes": 
			getClientes(request, response);
			break;
		}
	}
	
	private void getClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCliente cc = new ControladorCliente();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Gson gson = new Gson();

		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		
		try {
			clientes = cc.getClientes(paginaActual, porPagina);
		} catch (RespuestaServidor e) {
			e.printStackTrace();
		}
		
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(gson.toJson(clientes));
	}

}
