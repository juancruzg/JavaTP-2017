package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.RespuestaServidor;
import negocio.ControladorTipoPago;
import util.Tipos;

@WebServlet("/TiposPago")
public class TiposPago extends ServletBase {
	private static final long serialVersionUID = 1L;
       
    public TiposPago() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorTipoPago controlador = new ControladorTipoPago();
		
		Respuesta rta = new Respuesta();

		try {
			rta.setData(controlador.getTiposPago());
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
