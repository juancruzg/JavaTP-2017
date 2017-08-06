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
import negocio.ControladorTalle;
import util.Tipos;

@WebServlet("/Talles")
public class Talles extends ServletBase {
	private static final long serialVersionUID = 1L;
       
    public Talles() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorTalle ct = new ControladorTalle();
		
		Respuesta rta = new Respuesta();

		try {
			rta.setData(ct.getTalles());
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
