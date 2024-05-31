package transfers;

public class TLineaPedido {
	private int idPedido;
	private int idProducto;
	private int cantidad;
	private double precio;
	

	public TLineaPedido(int idPedido, int idProducto, int cantidad, double precio) {
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public TLineaPedido(int idPedido, int idProducto, int cantidad) {
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	
	public TLineaPedido(int idPedido, int idProducto) {
		this.idPedido = idPedido;
		this.idProducto = idProducto;
	}
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}