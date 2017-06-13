package entidades;

import java.util.ArrayList;

public class Producto implements EntidadBase {
	private int id;
	private String descripcion;
	private ArrayList<LineaProducto> lineas;
	private Precio precio;
	private ArrayList<Precio> historicoPrecios;
	
	/* Constructors */
	
	public Producto() {
		
	}
	
	public Producto(int id, String descripcion, ArrayList<LineaProducto> lineas, Precio precio, ArrayList<Precio> historicoPrecios) {
		this.id = id;
		this.descripcion = descripcion;
		this.lineas = lineas;
		this.precio = precio;
		this.historicoPrecios = historicoPrecios;
	}

	/* Getters y Setters */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<LineaProducto> getLineas() {
		return lineas;
	}

	public void setLineas(ArrayList<LineaProducto> lineas) {
		this.lineas = lineas;
	}
	
	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public ArrayList<Precio> getHistoricoPrecios() {
		return historicoPrecios;
	}

	public void setHistoricoPrecios(ArrayList<Precio> historicoPrecios) {
		this.historicoPrecios = historicoPrecios;
	}

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
