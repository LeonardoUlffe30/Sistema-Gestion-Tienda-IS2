package integracion.factoriaIntegracion;

import integracion.lineapedido.DAOLineaPedido;
import integracion.lineapedido.DAOLineaPedidoImp;
import integracion.pedido.DAOPedido;
import integracion.pedido.DAOPedidoImp;
import integracion.producto.DAOProducto;
import integracion.producto.DAOProductoImp;
import integracion.proveedor.DAOProveedor;
import integracion.proveedor.DAOProveedorImp;
import integracion.tienda.DAOTienda;
import integracion.tienda.DAOTiendaImp;
import integracion.trabajador.DAOTrabajador;
import integracion.trabajador.DAOTrabajadorImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	@Override
	public DAOProducto generaDAOProducto() {
		return new DAOProductoImp();
	}

	@Override
	public DAOPedido generaDAOPedido() {
		return new DAOPedidoImp();
	}

	@Override
	public DAOProveedor generaDAOProveedor() {
		return new DAOProveedorImp();
	}

	@Override
	public DAOTienda generaDAOTienda() {
		return new DAOTiendaImp();
	}

	@Override
	public DAOTrabajador generaDAOTrabajador() {
		return new DAOTrabajadorImp();
	}
	
	@Override
	public DAOLineaPedido generaDAOLineaPedido() {
		return new DAOLineaPedidoImp();
	}
}
