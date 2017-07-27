package servlets;

import java.util.ArrayList;

import com.google.gson.Gson;

import excepciones.ErrorServidor;

public class Respuesta {
	private Object data;
	private ArrayList<ErrorServidor> errores;
	
	public String toJson() {
		Gson gson = new Gson();
		
		return gson.toJson(this);
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public ArrayList<ErrorServidor> getErrores() {
		return errores;
	}
	
	public void setErrores(ArrayList<ErrorServidor> errores) {
		this.errores = errores;
	}
	
	
}
