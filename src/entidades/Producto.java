package entidades;

import java.util.ArrayList;

public class Producto implements EntidadBase {
	private int id;
	private String descripcion, marca;
	private Precio precio;
	private Usuario usuarioAlta;
	private boolean activo;
	private ArrayList<Precio> historicoPrecios;
	private ArrayList<LineaProducto> lineas;
	
	/* Constructors */
	
	public Producto() {
		
	}
	
	public Producto(int id, String descripcion, Precio precio, Usuario usuario) {
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.usuarioAlta = usuario;
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
	
	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Usuario getUsuarioAlta(){
		return usuarioAlta;
	}
	
	public void setUsuarioAlta(Usuario usuario){
		this.usuarioAlta = usuario;
	}
	
	public ArrayList<LineaProducto> getLineas() {
		return lineas;
	}

	public void setLineas(ArrayList<LineaProducto> lineas) {
		this.lineas = lineas;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	/* Public Methods */
	
	public ArrayList<Precio> getHistoricoPrecios() {
		return historicoPrecios;
	}

	public void setHistoricoPrecios(ArrayList<Precio> historicoPrecios) {
		this.historicoPrecios = historicoPrecios;
	}

	public String toJson() {
		return "";
	}

	
}
