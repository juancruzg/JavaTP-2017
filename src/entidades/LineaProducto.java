package entidades;

public class LineaProducto implements EntidadBase {
	private int stock;
	private Producto producto;
	private Talle talle;
	private String color;
	
	/* Constructors */
	
	public LineaProducto() {
		
	}
	
	public LineaProducto(int stock, Producto producto, Talle talle, String color) {
		this.stock = stock;
		this.producto = producto;
		this.talle = talle;
		this.color = color;
	}
	
	/* Getters y Setters */
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Talle getTalle() {
		return talle;
	}

	public void setTalle(Talle talle) {
		this.talle = talle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/* Public Methods */
	
	public String toJson() {
		return "";
	}
}
