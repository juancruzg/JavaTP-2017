package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.RespuestaServidor;
import negocio.ControladorCliente;
import negocio.ControladorColor;
import negocio.ControladorProducto;
import negocio.ControladorTipoUsuario;
import util.Tipos;

@WebServlet("/TiposUsuario")
public class TiposUsuario extends ServletBase {
	private static final long serialVersionUID = 1L;
       
    public TiposUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorTipoUsuario cc = new ControladorTipoUsuario();
		
		Respuesta rta = new Respuesta();

		try {
			rta.setData(cc.getTiposUsuario());
		}
		catch(RespuestaServidor rs) {
			rta.setErrores(rs.getErrores());
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
