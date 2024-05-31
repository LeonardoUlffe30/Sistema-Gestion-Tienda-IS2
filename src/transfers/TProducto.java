package transfers;

public class TProducto {
	private int id;
	private int stock;
	private String nombre;
	private Double precio;
	private int idTienda;
	private int idProveedor;
	private boolean activo;
	
	public TProducto(int id) {
		this.id = id;
	}
	
	public TProducto(int id, int stock, String nombre, Double precio, int idTienda, int idProveedor) {
		this.id = id;
		this.stock = stock;
		this.nombre = nombre;
		this.precio = precio;
		this.idTienda = idTienda;
		this.idProveedor = idProveedor;
		this.activo = true;
	}
	
	public TProducto(int stock, String nombre, Double precio, int idTienda, int idProveedor) {
		this.stock = stock;
		this.nombre = nombre;
		this.precio = precio;
		this.idTienda = idTienda;
		this.idProveedor = idProveedor;
	}
	
	public TProducto(int stock, String nombre, Double precio, int idTienda, int idProveedor, boolean activo) {
		this.stock = stock;
		this.nombre = nombre;
		this.precio = precio;
		this.idTienda = idTienda;
		this.idProveedor = idProveedor;
		this.activo = activo;
	}
	
	public TProducto(int id, int stock, String nombre, Double precio, int idTienda, int idProveedor, boolean activo) {
		this.id = id;
		this.stock = stock;
		this.nombre = nombre;
		this.precio = precio;
		this.idTienda = idTienda;
		this.idProveedor = idProveedor;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public int getIdTienda() {
		return idTienda;
	}
	
	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}
	
	public int getIdProveedor() {
		return idProveedor;
	}
	
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public boolean isActivo() {
		return activo;
		
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
