package negocio.factoriaNegocio;

import negocio.lineapedido.SALineaPedido;
import negocio.pedido.SAPedido;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.tienda.SATienda;
import negocio.trabajador.SATrabajador;

public abstract class FactoriaNegocio {
	private static FactoriaNegocio factoryInstance;
	
	public static FactoriaNegocio getInstance() {
		if (factoryInstance == null)
			factoryInstance = new FactoriaNegocioImp();
		return factoryInstance;
	}
	
	public abstract SAProducto generaSAProducto();
	
	public abstract SAPedido generaSAPedido();
	
	public abstract SAProveedor generaSAProveedor();
	
	public abstract SATienda generaSATienda();
	
	public abstract SATrabajador generaSATrabajador();
	
	public abstract SALineaPedido generaSALineaPedido();
}
