package negocio.factoriaNegocio;

import negocio.lineapedido.SALineaPedido;
import negocio.lineapedido.SALineaPedidoImp;
import negocio.pedido.SAPedido;
import negocio.pedido.SAPedidoImp;
import negocio.producto.SAProducto;
import negocio.producto.SAProductoImp;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.SAProveedorImp;
import negocio.tienda.SATienda;
import negocio.tienda.SATiendaImp;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.SATrabajadorImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SAProducto generaSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SAPedido generaSAPedido() {
		return new SAPedidoImp();
	}

	@Override
	public SAProveedor generaSAProveedor() {
		return new SAProveedorImp();
	}

	@Override
	public SATienda generaSATienda() {
		return new SATiendaImp();
	}

	@Override
	public SATrabajador generaSATrabajador() {
		return new SATrabajadorImp();
	}
	
	@Override
	public SALineaPedido generaSALineaPedido() {
		return new SALineaPedidoImp();
	}
	
}
