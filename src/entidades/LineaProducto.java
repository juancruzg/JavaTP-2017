package entidades;

public class LineaProducto implements EntidadBase {
	private int stock;
	private Producto producto;
	private Talle talle;
	private Color color;
	private Sucursal sucursal;
	private Usuario usuarioAlta;
	
	/* Constructors */
	
	public LineaProducto() {
		
	}
	
	public LineaProducto(int stock, Producto producto, Talle talle, Color color, Sucursal sucursal, Usuario usuario) {
		this.stock = stock;
		this.producto = producto;
		this.talle = talle;
		this.color = color;
		this.sucursal = sucursal;
		this.usuarioAlta = usuario;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/* Public Methods */
	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public String toJson() {
		return "";
	}
}
