package transfers;

public class TTrabajador {
	private int id;
	private String nombre;
	private String dni;
	private int telefono;
	private int idTienda;
	private boolean activo;
	
	public TTrabajador(int id) {
		this.id = id;
	}
	
	
	public TTrabajador(int id, String dni, String nombre, int telefono, int idTienda, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.idTienda = idTienda;
		this.activo = activo;
	}
	
	public TTrabajador(String dni, String nombre, int telefono, int idTienda, boolean activo) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.idTienda = idTienda;
		this.activo = activo;
	}
	public TTrabajador(String dni, String nombre, int telefono, int idTienda) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.idTienda = idTienda;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	

	public int getIdTienda() {
		return idTienda;
	}


	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
