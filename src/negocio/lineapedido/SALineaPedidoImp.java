package negocio.lineapedido;

import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.lineapedido.DAOLineaPedido;
import integracion.pedido.DAOPedido;
import integracion.producto.DAOProducto;
import presentacion.controlador.Evento;
import transfers.TLineaPedido;
import transfers.TPedido;
import transfers.TProducto;

public class SALineaPedidoImp implements SALineaPedido {

	@Override
	public int addProductoPedido(TLineaPedido lineaPedido) {
		int res = -1;
		
		DAOLineaPedido daoLineaPedido = FactoriaIntegracion.getInstance().generaDAOLineaPedido();
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		
		TLineaPedido lineaPedidoLeida = daoLineaPedido.read(lineaPedido.getIdPedido(), lineaPedido.getIdProducto());
		TPedido pedidoLeido = daoPedido.read(lineaPedido.getIdPedido());
		TProducto productoLeido = daoProducto.read(lineaPedido.getIdProducto());
		
		if (lineaPedido.getCantidad() < 0)
			return Evento.CANTIDAD_INVALIDA_NEGATIVA;
		
		if (pedidoLeido == null)
			return Evento.PEDIDO_NO_EXISTE;
		else if (productoLeido == null)
			return Evento.PRODUCTO_NO_EXISTE;
		else if (!productoLeido.isActivo())
			return Evento.PRODUCTO_INACTIVO;
		
		if (lineaPedido.getCantidad() > productoLeido.getStock())
			return Evento.STOCK_INSUFICIENTE;
		
		if (lineaPedidoLeida != null) { // En caso de que ya estuviese ese producto se le actualiza la cantidad y el precio al nuevo
			
			if (productoLeido.getStock() + lineaPedidoLeida.getCantidad() - lineaPedido.getCantidad() < 0)
				return Evento.STOCK_INSUFICIENTE;
			
			// Actualiza el stock del producto
			productoLeido.setStock(productoLeido.getStock() + lineaPedidoLeida.getCantidad() - lineaPedido.getCantidad());
			daoProducto.update(productoLeido);
			
			// Calcula el nuevo precio
			lineaPedido.setPrecio(productoLeido.getPrecio() * lineaPedido.getCantidad());
			
			daoLineaPedido.update(lineaPedido);
			
			// Calcula el nuevo total de pedido
			pedidoLeido.setTotal(pedidoLeido.getTotal() - lineaPedidoLeida.getCantidad() * productoLeido.getPrecio() 
					+ lineaPedido.getCantidad() * productoLeido.getPrecio());
			
			daoPedido.update(pedidoLeido);
			
			res = Evento.PRODUCTO_PEDIDO_UPDATE;
		} else { // En caso de que en el pedido no este en el producto
			
			if (productoLeido.getStock() - lineaPedido.getCantidad() < 0)
				return Evento.STOCK_INSUFICIENTE;
			
			// Actualiza el stock del producto
			productoLeido.setStock(productoLeido.getStock() - lineaPedido.getCantidad());
			daoProducto.update(productoLeido);
			
			// Calcula el precio
			lineaPedido.setPrecio(productoLeido.getPrecio() * lineaPedido.getCantidad());
			
			daoLineaPedido.addProductoPedido(lineaPedido);
			
			pedidoLeido.setTotal(pedidoLeido.getTotal() + lineaPedido.getCantidad() * productoLeido.getPrecio());
			
			daoPedido.update(pedidoLeido);
			
			res = Evento.ADD_PRODUCTO_PEDIDO;
		}
		
		return res;
	}

	@Override
	public int deleteProductoPedido(TLineaPedido lineaPedido) {
		int res = -1;
		
		DAOLineaPedido daoLineaPedido = FactoriaIntegracion.getInstance().generaDAOLineaPedido();
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		
		TLineaPedido lineaPedidoLeida = daoLineaPedido.read(lineaPedido.getIdPedido(), lineaPedido.getIdProducto());
		TPedido pedidoLeido = daoPedido.read(lineaPedido.getIdPedido());
		TProducto productoLeido = daoProducto.read(lineaPedido.getIdProducto()); 
		
		
		if (pedidoLeido == null)
			return Evento.PEDIDO_NO_EXISTE;
		else if (productoLeido == null)
			return Evento.PRODUCTO_NO_EXISTE;
		else if (lineaPedidoLeida == null)
			return Evento.LINEAPEDIDO_NO_EXISTE;
		
		// Actualiza el stock del producto
		productoLeido.setStock(productoLeido.getStock() + lineaPedidoLeida.getCantidad());
		daoProducto.update(productoLeido);
		
		// Actualiza el total del pedido
		pedidoLeido.setTotal(pedidoLeido.getTotal() - lineaPedidoLeida.getPrecio());
		daoPedido.update(pedidoLeido);
		
		// Elimina la relacion entre producto y pedido
		res = daoLineaPedido.deleteProductoPedido(lineaPedidoLeida);
		
		return res;
	}
	
	@Override
	public TLineaPedido read(int idPedido, int idProducto) {
		DAOLineaPedido daoLineaPedido = FactoriaIntegracion.getInstance().generaDAOLineaPedido();		
		TLineaPedido lineaPedidoLeido = daoLineaPedido.read(idPedido, idProducto);
		
		return lineaPedidoLeido;
	}

	@Override
	public List<TLineaPedido> readByPedido(int idPedido) {
		DAOLineaPedido daoLineaPedido = FactoriaIntegracion.getInstance().generaDAOLineaPedido();
		
		List<TLineaPedido> lineasPedido = daoLineaPedido.readByPedido(idPedido);
		
		return lineasPedido;
	}

}
