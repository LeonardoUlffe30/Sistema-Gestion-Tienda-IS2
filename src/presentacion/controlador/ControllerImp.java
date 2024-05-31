package presentacion.controlador;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.lineapedido.SALineaPedido;
import negocio.pedido.SAPedido;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.tienda.SATienda;
import negocio.trabajador.SATrabajador;
import presentacion.factoriaInterfaz.FactoriaInterfaz;
import presentacion.vista.IGUI;
import transfers.*;
import transfers.TTrabajador;

public class ControllerImp extends Controller {


	@Override
	public void action(int evento, Object transfer) {
		IGUI gui;
		int res;
		int id;
		SAProducto saProducto = null;
		SAProveedor saProveedor = null;
		SATrabajador saTrabajador = null;
		SATienda saTienda = null;
		SAPedido saPedido = null;
		SALineaPedido saLineaPedido = null;

		switch (evento) {
		case Evento.GUI_PRINCIPAL:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			break;

		// EVENTOS PEDIDO
		case Evento.GUI_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			break;
		case Evento.GUI_ALTA_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_ADD_PRODUCTO_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_DELETE_PRODUCTO_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_DETALLES_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
			
		case Evento.ALTA_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_ALTA_PEDIDO);
			saPedido = FactoriaNegocio.getInstance().generaSAPedido();

			TPedido pedidoAlta = (TPedido) transfer;

			res = saPedido.alta(pedidoAlta);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saPedido = null;

			break;
		case Evento.DETALLES_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_DETALLES_PEDIDO);
			saPedido = FactoriaNegocio.getInstance().generaSAPedido();
			
			int idPedidoDetalles = (int) transfer;
			
			TOADetallesPedido toaDetallePedido = saPedido.readDetallesPedido(idPedidoDetalles);
			
			gui.actualizar(evento, toaDetallePedido);
			
			saPedido = null;	
			break;
		case Evento.ADD_PRODUCTO_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_ADD_PRODUCTO_PEDIDO);
			saLineaPedido = FactoriaNegocio.getInstance().generaSALineaPedido();
			
			TLineaPedido lineaPedidoAdd = (TLineaPedido) transfer;
			
			res = saLineaPedido.addProductoPedido(lineaPedidoAdd);
			
			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);
			
			saLineaPedido = null;
			break;
		case Evento.DELETE_PRODUCTO_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_DELETE_PRODUCTO_PEDIDO);
			saLineaPedido = FactoriaNegocio.getInstance().generaSALineaPedido();
			
			TLineaPedido lineaPedidoDelete = (TLineaPedido) transfer;
			
			res = saLineaPedido.deleteProductoPedido(lineaPedidoDelete);
			
			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);
			
			saLineaPedido = null;
			break;
		case Evento.READ_PEDIDO_BY_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PEDIDO);
			saPedido = FactoriaNegocio.getInstance().generaSAPedido();

			id = (int) transfer;

			List<TPedido> pedidosPorTrabajador = saPedido.readByTrabajador(id);
			
			gui.actualizar(evento, pedidosPorTrabajador);

			saPedido = null;
			break;
		case Evento.MOSTRAR_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PEDIDO);
			saPedido = FactoriaNegocio.getInstance().generaSAPedido();
			id = (int) transfer;

			TPedido pedido = null;

			pedido = saPedido.read(id);

			gui.actualizar(evento, pedido);

			saPedido = null;
			break;
		case Evento.LISTAR_PEDIDOS:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PEDIDO);
			saPedido = FactoriaNegocio.getInstance().generaSAPedido();

			List<TPedido> pedidos = saPedido.readAll();
			gui.actualizar(evento, pedidos);

			saPedido = null;
			break;
			
		case Evento.READ_PEDIDO_BY_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PEDIDO);
			saPedido = FactoriaNegocio.getInstance().generaSAPedido();

			id = (int) transfer;

			List<TPedido> pedidosPorProducto = saPedido.readByProducto(id);
			
			gui.actualizar(evento, pedidosPorProducto);

			saPedido = null;
			break;

		// EVENTOS PRODUCTO
		case Evento.GUI_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			break;
		case Evento.GUI_ALTA_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_BAJA_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_UPDATE_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;

		case Evento.ALTA_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_ALTA_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			TProducto productoAlta = (TProducto) transfer;

			res = saProducto.alta(productoAlta);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProducto = null;

			break;

		case Evento.BAJA_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_BAJA_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			id = (int) transfer;
			res = saProducto.baja(id);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProducto = null;

			break;
		case Evento.UPDATE_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			TProducto productoUpdate = (TProducto) transfer;
			res = saProducto.update(productoUpdate);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProducto = null;

			break;

		case Evento.READ_PRODUCTO_BY_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			id = (int) transfer;

			List<TProducto> productosPorProveedor = saProducto.readByProveedor(id);
			gui.actualizar(evento, productosPorProveedor);

			saProducto = null;
			break;

		case Evento.READ_PRODUCTO_BY_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			id = (int) transfer;

			List<TProducto> productosPorTienda = saProducto.readByTienda(id);
			gui.actualizar(evento, productosPorTienda);

			saProducto = null;
			break;
			
		case Evento.READ_PRODUCTO_BY_PEDIDO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			id = (int) transfer;

			List<TProducto> productosPorPedido = saProducto.readByPedido(id);
			gui.actualizar(evento, productosPorPedido);

			saProducto = null;
			break;

		case Evento.MOSTRAR_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();
			id = (int) transfer;

			TProducto producto = null;

			producto = saProducto.read(id);

			gui.actualizar(evento, producto);

			saProducto = null;
			break;

		case Evento.LISTAR_PRODUCTOS:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			List<TProducto> productos = saProducto.readAll();
			gui.actualizar(evento, productos);

			saProducto = null;
			break;

		case Evento.OBTENER_DATOS_PRODUCTO:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_PRODUCTO);
			saProducto = FactoriaNegocio.getInstance().generaSAProducto();

			id = (int) transfer;

			TProducto productoDatos = null;

			productoDatos = saProducto.read(id);

			if (productoDatos != null)
				gui.actualizar(evento, productoDatos);
			else
				gui.actualizar(Evento.PRODUCTO_NO_EXISTE, null);

			saProducto = null;
			break;

		// EVENTOS PROVEEDOR
		case Evento.GUI_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			break;

		case Evento.GUI_ALTA_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;

		case Evento.GUI_BAJA_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;

		case Evento.GUI_UPDATE_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;

		case Evento.ALTA_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_ALTA_PROVEEDOR);
			saProveedor = FactoriaNegocio.getInstance().generaSAProveedor();

			TProveedor proveedorAlta = (TProveedor) transfer;

			res = saProveedor.alta(proveedorAlta);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProveedor = null;

			break;

		case Evento.BAJA_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_BAJA_PROVEEDOR);
			saProveedor = FactoriaNegocio.getInstance().generaSAProveedor();

			id = (int) transfer;
			res = saProveedor.baja(id);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProveedor = null;

			break;
			
		case Evento.UPDATE_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_PROVEEDOR);
			saProveedor = FactoriaNegocio.getInstance().generaSAProveedor();

			TProveedor proveedorUpdate = (TProveedor) transfer;
			res = saProveedor.update(proveedorUpdate);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProducto = null;

			break;
			
		case Evento.MOSTRAR_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PROVEEDOR);
			saProveedor = FactoriaNegocio.getInstance().generaSAProveedor();
			id = (int) transfer;

			TProveedor proveedor = null;

			proveedor = saProveedor.read(id);

			gui.actualizar(evento, proveedor);

			saProveedor = null;
			break;

		case Evento.LISTAR_PROVEEDORES:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_PROVEEDOR);
			saProveedor = FactoriaNegocio.getInstance().generaSAProveedor();

			List<TProveedor> proveedores = saProveedor.readAll();
			gui.actualizar(evento, proveedores);

			saProveedor = null;
			break;
			
		case Evento.OBTENER_DATOS_PROVEEDOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_PROVEEDOR);
			saProveedor = FactoriaNegocio.getInstance().generaSAProveedor();

			id = (int) transfer;

			TProveedor proveedorDatos = null;

			proveedorDatos = saProveedor.read(id);

			if (proveedorDatos != null)
				gui.actualizar(evento, proveedorDatos);
			else
				gui.actualizar(Evento.PROVEEDOR_NO_EXISTE, null);

			saProveedor = null;
			break;

		// EVENTOS TIENDA
		case Evento.GUI_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			break;
		case Evento.GUI_ALTA_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_BAJA_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_UPDATE_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.ALTA_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_ALTA_TIENDA);
			saTienda = FactoriaNegocio.getInstance().generaSATienda();

			TTienda tiendaAlta = (TTienda) transfer;

			res = saTienda.alta(tiendaAlta);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saTienda = null;
			break;
		case Evento.MOSTRAR_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_TIENDA);
			saTienda = FactoriaNegocio.getInstance().generaSATienda();
			id = (int) transfer;

			TTienda tienda = null;

			tienda = saTienda.read(id);

			gui.actualizar(evento, tienda);

			saTienda = null;
			break;
		case Evento.BAJA_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_BAJA_TIENDA);
			saTienda = FactoriaNegocio.getInstance().generaSATienda();

			id = (int) transfer;
			res = saTienda.baja(id);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saTienda = null;

			break;
			
		case Evento.UPDATE_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_TIENDA);
			saTienda = FactoriaNegocio.getInstance().generaSATienda();

			TTienda tiendaUpdate = (TTienda) transfer;
			res = saTienda.update(tiendaUpdate);

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saProducto = null;

			break;
			
		case Evento.OBTENER_DATOS_TIENDA:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_TIENDA);
			saTienda = FactoriaNegocio.getInstance().generaSATienda();

			id = (int) transfer;

			TTienda tiendaDatos = null;

			tiendaDatos = saTienda.read(id);

			if (tiendaDatos != null)
				gui.actualizar(evento, tiendaDatos);
			else
				gui.actualizar(Evento.TIENDA_NO_EXISTE, null);

			saTienda = null;
			break;
			
		case Evento.LISTAR_TIENDAS:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_TIENDA);
			saTienda = FactoriaNegocio.getInstance().generaSATienda();

			List<TTienda> tiendas = saTienda.readAll();
			gui.actualizar(evento, tiendas);

			saTienda = null;
			break;

		// EVENTOS TRABAJADOR
		case Evento.GUI_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			break;
		case Evento.GUI_ALTA_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_BAJA_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
		case Evento.GUI_UPDATE_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(evento);
			gui.actualizar(evento, null);
			break;
			
		case Evento.ALTA_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_ALTA_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();

			TTrabajador trabajadorAlta = (TTrabajador) transfer;
			res = 0;
			try {
				res = saTrabajador.alta(trabajadorAlta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saTrabajador = null;

			break;
		case Evento.BAJA_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_BAJA_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();

			id = (int) transfer;
			res = 0;
			try {
				res = saTrabajador.baja(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);

			saTrabajador = null;

			break;
			
		case Evento.OBTENER_DATOS_TRABAJADOR:
			
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();

			TTrabajador Trabajadordatos = null;
			id = (int) transfer;

			Trabajadordatos = saTrabajador.read(id);
		
			if (Trabajadordatos != null)
				gui.actualizar(evento, Trabajadordatos);	
			else
				gui.actualizar(Evento.TRABAJADOR_NO_EXISTE, null);

			saTrabajador = null;
			break;
			
		case Evento.UPDATE_TRABAJADOR:
			
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_UPDATE_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();
	
			TTrabajador trabajadorUpdate = (TTrabajador) transfer;
			res = saTrabajador.update(trabajadorUpdate);
			
			if (res > 0)
				gui.actualizar(evento, res);
			else
				gui.actualizar(res, null);
	
			saTrabajador = null;
	
			break;
			
		case Evento.MOSTRAR_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();
			id = (int) transfer;

			TTrabajador trabajador = null;

			trabajador = saTrabajador.read(id);

			gui.actualizar(evento, trabajador);

			saTrabajador = null;
			
			break;

		case Evento.LISTAR_TRABAJADORES:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();

			List<TTrabajador> trabajadores = saTrabajador.readAll();
			gui.actualizar(evento, trabajadores);

			saTrabajador = null;
			break;
			
			
		case Evento.READ_BY_TIENDA_TRABAJADOR:
			gui = FactoriaInterfaz.getInstance().generaGUI(Evento.GUI_TRABAJADOR);
			saTrabajador = FactoriaNegocio.getInstance().generaSATrabajador();

			List<TTrabajador> trabajadoresTienda = null;
			int idTienda = (int) transfer;
			trabajadoresTienda = saTrabajador.readByTienda(idTienda);
			
			gui.actualizar(evento, trabajadoresTienda);

			saProducto = null;
			break;
		}
				
		
	}
}
