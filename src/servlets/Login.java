package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Sucursal;
import entidades.Usuario;
import excepciones.RespuestaServidor;
import negocio.ControladorCliente;
import negocio.ControladorUsuario;
import util.Tipos;

@WebServlet("/Login")
public class Login extends ServletBase {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorUsuario cu = new ControladorUsuario();
		
		Respuesta rta = new Respuesta();
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String accion = request.getParameter("accion");

		if(accion != null && !accion.isEmpty()) {
			if (accion.equals("estaLoggeado")) {
				HttpSession session = request.getSession(false);
				
				if (session != null && session.getAttribute("usuario") != null)
					rta.setData(request.getSession().getAttribute("usuario"));
				else
					rta.setData(null);
			}
			else if (accion.equals("login")) {
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
			else if (accion.equals("logout")) {
				HttpSession session = request.getSession(false);
				
				if (session != null && session.getAttribute("usuario") != null)
					session.removeAttribute("usuario");
				
				rta.setData(null);
			}
		}
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
