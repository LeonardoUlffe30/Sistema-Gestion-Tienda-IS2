package presentacion.factoriaInterfaz;

import presentacion.controlador.Evento;
import presentacion.vista.GUIPrincipal;
import presentacion.vista.IGUI;
import presentacion.vista.producto.GUIAltaProducto;
import presentacion.vista.producto.GUIBajaProducto;
import presentacion.vista.producto.GUIProducto;
import presentacion.vista.producto.GUIUpdateProducto;
import presentacion.vista.pedido.GUIAddProductoPedido;
import presentacion.vista.pedido.GUIAltaPedido;
import presentacion.vista.pedido.GUIDeleteProductoPedido;
import presentacion.vista.pedido.GUIDetallesPedido;
import presentacion.vista.pedido.GUIPedido;
import presentacion.vista.tienda.GUIAltaTienda;
import presentacion.vista.tienda.GUIBajaTienda;
import presentacion.vista.tienda.GUITienda;
import presentacion.vista.tienda.GUIUpdateTienda;
import presentacion.vista.proveedor.GUIAltaProveedor;
import presentacion.vista.proveedor.GUIBajaProveedor;
import presentacion.vista.proveedor.GUIProveedor;
import presentacion.vista.proveedor.GUIUpdateProveedor;
import presentacion.vista.trabajador.GUIAltaTrabajador;
import presentacion.vista.trabajador.GUIBajaTrabajador;
import presentacion.vista.trabajador.GUITrabajador;
import presentacion.vista.trabajador.GUIUpdateTrabajador;

public class FactoriaInterfazImp extends FactoriaInterfaz {

	@Override
	public IGUI generaGUI(int evento) {
		switch (evento) {
		case Evento.GUI_PRINCIPAL:
			return new GUIPrincipal();
			
		// PRODUCTO
		case Evento.GUI_PRODUCTO:
			return new GUIProducto();
		case Evento.GUI_ALTA_PRODUCTO:
			return new GUIAltaProducto();
		case Evento.GUI_UPDATE_PRODUCTO:
			return new GUIUpdateProducto();
		case Evento.GUI_BAJA_PRODUCTO:
			return new GUIBajaProducto();
			
		// TIENDA
		case Evento.GUI_TIENDA:
			return new GUITienda();
		case Evento.GUI_ALTA_TIENDA:
			return new GUIAltaTienda();
		case Evento.GUI_BAJA_TIENDA:
			return new GUIBajaTienda();
		case Evento.GUI_UPDATE_TIENDA:
			return new GUIUpdateTienda();
			
		// PEDIDO
		case Evento.GUI_PEDIDO:
			return new GUIPedido();
		case Evento.GUI_ALTA_PEDIDO:
			return new GUIAltaPedido();
		case Evento.GUI_ADD_PRODUCTO_PEDIDO:
			return new GUIAddProductoPedido();
		case Evento.GUI_DETALLES_PEDIDO:
			return new GUIDetallesPedido();
		case Evento.GUI_DELETE_PRODUCTO_PEDIDO:
			return new GUIDeleteProductoPedido();
			
		// PROVEEDOR
		case Evento.GUI_PROVEEDOR:
			return new GUIProveedor();
		case Evento.GUI_ALTA_PROVEEDOR:
			return new GUIAltaProveedor();
		case Evento.GUI_BAJA_PROVEEDOR:
			return new GUIBajaProveedor();
		case Evento.GUI_UPDATE_PROVEEDOR:
			return new GUIUpdateProveedor();
			
		// TRABAJADOR
		case Evento.GUI_TRABAJADOR:
			return new GUITrabajador();
		case Evento.GUI_ALTA_TRABAJADOR:
			return new GUIAltaTrabajador();
		case Evento.GUI_BAJA_TRABAJADOR:
			return new GUIBajaTrabajador();
		case Evento.GUI_UPDATE_TRABAJADOR:
			return new GUIUpdateTrabajador();
		default:
			return null;
		}
	}
}
