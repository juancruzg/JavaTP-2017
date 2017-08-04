package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.RespuestaServidor;
import negocio.ControladorCliente;
import negocio.ControladorProducto;
import util.Tipos;

@WebServlet("/Productos")
public class Productos extends ServletBase {
	private static final long serialVersionUID = 1L;
       
    public Productos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorProducto cp = new ControladorProducto();
		
		Respuesta rta = new Respuesta();
		
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		int idProducto = Tipos.toInt(request.getParameter("idProducto"));
		String query = request.getParameter("query");

		if (idProducto == 0) {
			try {
				if (query != null)
					rta.setData(cp.getProductos(paginaActual, porPagina, query));
				else
					rta.setData(cp.getProductos(paginaActual, porPagina));
			}
			catch(RespuestaServidor rs) {
				rta.setErrores(rs.getErrores());
			}
		}
		else {
			try {
				rta.setData(cp.getProducto(idProducto));
			}
			catch(RespuestaServidor rs) {
				rta.setErrores(rs.getErrores());
			}
		}
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
