package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Cliente;

@WebServlet("/Clientes")
public class Clientes extends ServletBase {
	private static final long serialVersionUID = 1L;
	
    public Clientes() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*LLEGA BIEN LA DATA DEL REQUEST*/
		String data = request.getParameter("idCliente");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente rc = (Cliente) procesarRequest(request, Cliente.class);
		 
		Gson gson = new Gson();
		System.out.println(gson.toJson(rc).toString());
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente rc = (Cliente) procesarRequest(request, Cliente.class);
		
		System.out.println(rc.isActivo());
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente rc = (Cliente) procesarRequest(request, Cliente.class);
		
		System.out.println("delete " + rc.isActivo());
	}
}
