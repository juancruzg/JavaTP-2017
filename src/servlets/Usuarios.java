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
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		boolean mostrarInactivos = Tipos.toBoolean(request.getParameter("mostrarInactivos"));

		if (usuario == null || usuario.isEmpty()) {
			try {
				rta.setData(cu.getUsuarios(paginaActual, porPagina, mostrarInactivos));
			} 
			catch (RespuestaServidor e) {
				rta.setErrores(e.getErrores());
			}
		}
		else {
			try {
				rta.setData(cu.getUsuario(usuario));
			}
			catch (RespuestaServidor e) {
				rta.setErrores(e.getErrores());
			}
		}
		
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorUsuario cu = new ControladorUsuario();
		
		Usuario u = (Usuario) procesarRequest(request, Usuario.class);
		RespuestaServidor sr = new RespuestaServidor();
		int nro = 0;
		
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		u.setSucursal(usuarioSesion.getSucursal());
		u.setUsuarioAlta(usuarioSesion);
		
		try {
			nro = cu.saveUsuario(u);
		} 
		catch (RespuestaServidor e) {
			sr = e;
		}
		
		Respuesta rta = new Respuesta();
		
		rta.setData(nro);
		rta.setErrores(sr.getErrores());
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorUsuario cu = new ControladorUsuario();
		
		Usuario u = (Usuario) procesarRequest(request, Usuario.class);
		RespuestaServidor sr = new RespuestaServidor();
		int nro = 0;
		
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		u.setSucursal(usuarioSesion.getSucursal());
		u.setUsuarioAlta(usuarioSesion);
		
		try
		{
			nro = cu.saveUsuario(u);
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
