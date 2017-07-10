package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entidades.Cliente;

@WebServlet("/Clientes")
public class Clientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Clientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*LLEGA BIEN LA DATA DEL REQUEST*/
		String data = request.getParameter("idCliente");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*NOOOOO LLEGA BIEN LA DATA DEL REQUEST*/
		String data = request.getParameter("data");
		Cliente clie = new Cliente();
		clie.setNombre("Leonardo");
		clie.setApellido("Peretti");
		String json = null;
	    response.setContentType("application/json");
	    /*HABRIA QUE VALIDAR QUE EL RESPUESTA SERVIDOR NO CONTENGA ERROR*/
	    if(json != null)
	    {
	    	response.getWriter().write(json);
	    }
	    else
	    {
	    	/*ESTO SIRVE PARA MANDAR UN 404, NOt found, EN EL APISERVICE LO CAPTURA EL CATCH*/
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
	    
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
