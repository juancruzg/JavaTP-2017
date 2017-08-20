package entidades;

public class Modulo implements EntidadBase {
	private int id;
	private String nombre, nombreAMostrar, claseIcono;
	private boolean mostrarEnMenu;
	
	/* Constructores */
	
	public Modulo () {
		
	}
	
	public Modulo (int id, String name) {
		this.id = id;
		this.nombre = name;
	}
	
	/* Getters y Setters */
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getNombreAMostrar() {
		return nombreAMostrar;
	}

	public void setNombreAMostrar(String nombreAMostrar) {
		this.nombreAMostrar = nombreAMostrar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isMostrarEnMenu() {
		return mostrarEnMenu;
	}

	public void setMostrarEnMenu(boolean mostrarEnMenu) {
		this.mostrarEnMenu = mostrarEnMenu;
	}

	public String getClaseIcono() {
		return claseIcono;
	}

	public void setClaseIcono(String claseIcono) {
		this.claseIcono = claseIcono;
	}

	public String toJson() {
		return null;
	}
}
