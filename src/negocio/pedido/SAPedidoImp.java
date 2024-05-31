package negocio.pedido;

import java.util.List;

import transfers.TLineaPedido;
import transfers.TOADetallesPedido;
import transfers.TPedido;
import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.lineapedido.DAOLineaPedido;
import integracion.pedido.DAOPedido;
import integracion.producto.DAOProducto;
import integracion.trabajador.DAOTrabajador;
import presentacion.controlador.Evento;
import transfers.TTrabajador;

public class SAPedidoImp implements SAPedido {

	@Override
	public int alta(TPedido pedido) {
		int idPedido = -1;
		
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();

		TTrabajador trabajadorLeido = daoTrabajador.read(pedido.getIdTrabajador());
		
		
		if (trabajadorLeido == null) // ERROR No existe el trabajador al que se quiere relacionar el pedido
			return Evento.TRABAJADOR_NO_EXISTE;
		else if (!trabajadorLeido.isActivo())
			return Evento.TRABAJADOR_INACTIVO;
		
		idPedido = daoPedido.alta(pedido);
		
		return idPedido;
	}

	@Override
	public TPedido read(int id) {
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		
		TPedido pedidoLeido = daoPedido.read(id);
		
		return pedidoLeido;
	}

	@Override
	public List<TPedido> readAll() {
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		
		List<TPedido> pedidos = daoPedido.readAll();
		
		return pedidos;
	}

	@Override
	public List<TPedido> readByTrabajador(int idTrabajador) {
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();
		
		if (daoTrabajador.read(idTrabajador) == null)
			return null;
		
		List<TPedido> pedidos = daoPedido.readByTrabajador(idTrabajador);
		
		return pedidos;
	}

	@Override
	public List<TPedido> readByProducto(int idProducto) {
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		
		if (daoProducto.read(idProducto) == null)
			return null;
		
		List<TPedido> pedidos = daoPedido.readByProducto(idProducto);
		
		return pedidos;
	}

	@Override
	public TOADetallesPedido readDetallesPedido(int idPedido) {
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		DAOLineaPedido daoLineaPedido = FactoriaIntegracion.getInstance().generaDAOLineaPedido();
		
		TPedido pedido = daoPedido.read(idPedido);
		List<TLineaPedido> lineasPedido = daoLineaPedido.readByPedido(idPedido);
		
		TOADetallesPedido toaDetallesPedido = new TOADetallesPedido(pedido, lineasPedido);
		
		return toaDetallesPedido;
	}

	
}
