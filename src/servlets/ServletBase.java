package servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.EntidadBase;
import excepciones.RespuestaServidor;

public class ServletBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected <T extends EntidadBase> EntidadBase procesarRequest(HttpServletRequest request, Class<T> T) {
		StringBuffer jb = new StringBuffer();
		String line = null;
 
		try {
			BufferedReader reader = request.getReader();
	 
			while ((line = reader.readLine()) != null)
				jb.append(line);
		}
		catch (Exception e) { 
			System.out.println("woops...");
		}
		
		Gson gson = new Gson();
		return gson.fromJson(jb.toString(), T);
	}
	
	protected void enviarJSON(HttpServletRequest request, HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	}
}
