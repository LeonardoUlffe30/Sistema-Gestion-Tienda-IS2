package negocio.producto;

import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.pedido.DAOPedido;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.tienda.DAOTienda;
import presentacion.controlador.Evento;
import transfers.TProducto;
import transfers.TProveedor;
import transfers.TTienda;

public class SAProductoImp implements SAProducto {

	@Override
	public int alta(TProducto producto) {
		int idProducto = -1;
		
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		
		
		TProducto productoLeido = daoProducto.read(producto.getId());
		
		if (productoLeido == null) { // No existe un producto con el mismo id
			DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();
			DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
			
			TTienda tiendaLeida = daoTienda.read(producto.getIdTienda());
			TProveedor proveedorLeido = daoProveedor.read(producto.getIdProveedor());
			
			if (producto.getStock() < 0)
				return Evento.STOCK_INVALIDO_NEGATIVO;
			
			if (producto.getPrecio() < 0)
				return Evento.PRECIO_INVALIDO_NEGATIVO;
			
			if (tiendaLeida == null) // ERROR No existe la tienda al que se que se quiere relacionar el producto
				return Evento.TIENDA_NO_EXISTE;
			else if (!tiendaLeida.isActivo())
				return Evento.TIENDA_INACTIVA;
			else if (proveedorLeido == null) // ERROR No existe el proveedor al que se quiere relacionar el producto
				return Evento.PROVEEDOR_NO_EXISTE;
			else if (!proveedorLeido.isActivo())
				return Evento.PROVEEDOR_INACTIVO;
			idProducto = daoProducto.alta(producto);
		} else if (!productoLeido.isActivo()){ // Existe un producto con el mismo id y esta inactivo, por lo que se activa
			productoLeido.setActivo(true);
			idProducto = daoProducto.update(productoLeido);
		} else { // Existe un producto con el mismo y id y ya esta activo
			return Evento.PRODUCTO_YA_ACTIVO;
		}
		
		return idProducto;
	}

	@Override
	public int baja(int id){
		int res = -1;
		
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
	
		TProducto productoLeido = daoProducto.read(id);
		
		if (productoLeido == null)
			return Evento.PRODUCTO_NO_EXISTE;
		else if (!productoLeido.isActivo())
			return Evento.PRODUCTO_YA_INACTIVO;
		
		res = daoProducto.baja(id);
		
		return res;
	}

	@Override
	public int update(TProducto producto){
		int res = -1;
		
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		
		if (producto.getStock() < 0)
			return Evento.STOCK_INVALIDO_NEGATIVO;
		
		if (producto.getPrecio() < 0)
			return Evento.PRECIO_INVALIDO_NEGATIVO;
		
		TProducto productoLeido = daoProducto.read(producto.getId());
		
		if (productoLeido == null)
			return Evento.PRODUCTO_NO_EXISTE;
		else if (!productoLeido.isActivo())
			return Evento.PRODUCTO_INACTIVO;
		
		TTienda tiendaLeida = daoTienda.read(producto.getIdTienda());
		
		if (tiendaLeida == null)
			return Evento.TIENDA_NO_EXISTE;
		else if (!tiendaLeida.isActivo())
			return Evento.TIENDA_INACTIVA;
		
		TProveedor proveedorLeido = daoProveedor.read(producto.getIdProveedor());
		
		if (proveedorLeido == null)
			return Evento.PROVEEDOR_NO_EXISTE;
		else if (!proveedorLeido.isActivo())
			return Evento.PROVEEDOR_INACTIVO;
		
		
		res = daoProducto.update(producto);
		
		return res;
	}

	@Override
	public TProducto read(int id) {
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();		
		TProducto productoLeido = daoProducto.read(id);
		
		return productoLeido;
	}

	@Override
	public List<TProducto> readAll() {
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		
		List<TProducto> productos = daoProducto.readAll();
		
		return productos;
	}

	@Override
	public List<TProducto> readByTienda(int idTienda) {
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();
		
		if (daoTienda.read(idTienda) == null)
			return null;
		
		List<TProducto> productos = daoProducto.readByTienda(idTienda);
		
		return productos;
	}

	@Override
	public List<TProducto> readByProveedor(int idProveedor) {
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();
		
		if (daoProveedor.read(idProveedor) == null)
			return null;
		
		List<TProducto> productos = daoProducto.readByProveedor(idProveedor);
		
		return productos;
	}

	@Override
	public List<TProducto> readByPedido(int idPedido) {
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generaDAOProducto();
		DAOPedido daoPedido = FactoriaIntegracion.getInstance().generaDAOPedido();
		
		if (daoPedido.read(idPedido) == null)
			return null;
		
		List<TProducto> productos = daoProducto.readByPedido(idPedido);
		
		return productos;
	}
	
}
