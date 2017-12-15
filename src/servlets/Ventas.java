package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Venta;
import excepciones.RespuestaServidor;
import modelos.DtoModeloPagoVenta;
import modelos.ModeloPago;
import negocio.ControladorCliente;
import negocio.ControladorColor;
import negocio.ControladorProducto;
import negocio.ControladorTipoUsuario;
import negocio.ControladorVenta;
import util.Tipos;

@WebServlet("/Ventas")
public class Ventas extends ServletBase {
	private static final long serialVersionUID = 1L;
       
    public Ventas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorVenta cv = new ControladorVenta();
		Respuesta rta = new Respuesta();
		int porPagina = Tipos.toInt(request.getParameter("porPagina"));
		int paginaActual = Tipos.toInt(request.getParameter("paginaActual"));
		
		try	{
			rta.setData(cv.getVentas(paginaActual, porPagina));
		} 
		catch (RespuestaServidor e) {
			rta.setErrores(e.getErrores());
		}
		
		enviarJSON(request, response, rta.toJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorVenta cv = new ControladorVenta();
		Respuesta rta = new Respuesta();
		
		DtoModeloPagoVenta dto = (DtoModeloPagoVenta)procesarRequest(request, DtoModeloPagoVenta.class);

		try {
			rta.setData(cv.saveVenta(dto));
		}
		catch(RespuestaServidor sr) {
			rta.setErrores(sr.getErrores());
		}
		
		enviarJSON(request, response, rta.toJson());		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
