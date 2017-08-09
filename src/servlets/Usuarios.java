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
import negocio.ControladorUsuario;
import util.Tipos;

@WebServlet("/Usuarios")
public class Usuarios extends ServletBase {
	private static final long serialVersionUID = 1L;
	
    public Usuarios() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorUsuario cu = new ControladorUsuario();
		
		Respuesta rta = new Respuesta();
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");

		if ((usuario == null || usuario.isEmpty()) && (password == null || password.isEmpty())) {
			if (request.getSession().getAttribute("usuario") != null)
				rta.setData((Usuario) request.getSession().getAttribute("usuario"));
			else
				rta.setData(null);
		}
		else if (!usuario.isEmpty() && !password.isEmpty()) {
			try {
				Usuario u = cu.getUsuario(usuario, password);
				
				if (u != null)
					request.getSession().setAttribute("usuario", u);
				
				rta.setData(u);
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
