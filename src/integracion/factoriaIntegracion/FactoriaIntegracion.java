package integracion.factoriaIntegracion;

import integracion.lineapedido.DAOLineaPedido;
import integracion.pedido.DAOPedido;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.tienda.DAOTienda;
import integracion.trabajador.DAOTrabajador;

public abstract class FactoriaIntegracion {
	private static FactoriaIntegracion factoryInstance;
	
	public static FactoriaIntegracion getInstance() {
		if (factoryInstance == null)
			factoryInstance = new FactoriaIntegracionImp();
		return factoryInstance;
	}
	
	public abstract DAOProducto generaDAOProducto();
	
	public abstract DAOPedido generaDAOPedido();
	
	public abstract DAOProveedor generaDAOProveedor();
	
	public abstract DAOTienda generaDAOTienda();
	
	public abstract DAOTrabajador generaDAOTrabajador();
	
	public abstract DAOLineaPedido generaDAOLineaPedido();
}
