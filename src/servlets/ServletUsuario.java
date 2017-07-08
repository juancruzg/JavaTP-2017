package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Usuario;
import excepciones.RespuestaServidor;
import negocio.ControladorUsuario;
import util.Tipos;

@WebServlet("/ServletCliente")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletUsuario() {
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
		case "getUsuarios": 
			getUsuarios(request, response);
			break;
		}
	}
	
	private void getUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorUsuario cu = new ControladorUsuario();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Gson gson = new Gson();

		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		
		try {
			usuarios = cu.getUsuarios(paginaActual, porPagina);
		} catch (RespuestaServidor e) {
			e.printStackTrace();
		}
		
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(gson.toJson(usuarios));
	}

}
