package transfers;

public class TProveedor {
	private int id;
	private String nombre;
	private String direccion;
	private Boolean activo;
	
	public TProveedor(int id) {
		this.id = id;
	}
	
	public TProveedor(String nombre, String direccion){
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public TProveedor(int id, String nombre, String direccion){
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.activo = true;
	}
	
	public TProveedor(int id, String nombre, String direccion, boolean activo){
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.activo = activo;
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
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
