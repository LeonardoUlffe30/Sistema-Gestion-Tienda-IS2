package presentacion.controlador;

/*
 * EN ESTA CLASE SE A�ADIRAN LOS EVENTOS QUE DEBERA TRATAR EL CONTROLADOR
 * INTENTAD MANTENER ORDEN AGRUPANDO LAS CONSTANTES RELACIONADAS
*/

public class Evento {
	// EVENTOS DE GUI
	public static final int GUI_PRINCIPAL = 0;
	public static final int GUI_PEDIDO = 1;
	public static final int GUI_PRODUCTO = 2;
	public static final int GUI_PROVEEDOR = 3;
	public static final int GUI_TIENDA = 4;
	public static final int GUI_TRABAJADOR = 5;

	// EVENTOS DE PRODUCTO
	public static final int GUI_ALTA_PRODUCTO = 100;
	public static final int GUI_BAJA_PRODUCTO = 101;
	public static final int GUI_UPDATE_PRODUCTO = 102;

	public static final int ALTA_PRODUCTO = 150;
	public static final int MOSTRAR_PRODUCTO = 151;
	public static final int LISTAR_PRODUCTOS = 152;
	public static final int BAJA_PRODUCTO = 153;
	public static final int UPDATE_PRODUCTO = 154;
	public static final int READ_PRODUCTO_BY_TIENDA = 155;
	public static final int READ_PRODUCTO_BY_PROVEEDOR = 156;
	public static final int READ_PRODUCTO_BY_PEDIDO = 157;
	public static final int OBTENER_DATOS_PRODUCTO = 158;

	// EVENTOS ERRORES DE PRODUCTO
	public static final int PRODUCTO_YA_EXISTE = -100;
	public static final int PRODUCTO_YA_INACTIVO = -101;
	public static final int PRODUCTO_NO_EXISTE = -102;
	public static final int PRODUCTO_YA_ACTIVO = -103;
	public static final int PRODUCTO_INACTIVO = -104;
	public static final int PRODUCTO_OBTENER_DATOS_NO_EXISTE = -105;
	public static final int STOCK_INVALIDO_NEGATIVO = -106;
	public static final int PRECIO_INVALIDO_NEGATIVO = -107;
	

	// EVENTOS DE PEDIDO
	public static final int GUI_ALTA_PEDIDO = 200;
	public static final int GUI_DETALLES_PEDIDO = 201;
	public static final int GUI_ADD_PRODUCTO_PEDIDO = 204;
	public static final int GUI_DELETE_PRODUCTO_PEDIDO = 205;

	public static final int ALTA_PEDIDO = 250;
	public static final int DETALLES_PEDIDO = 251;
	public static final int MOSTRAR_PEDIDO = 252;
	public static final int LISTAR_PEDIDOS = 253;
	public static final int READ_PEDIDO_BY_TRABAJADOR = 254;
	public static final int READ_PEDIDO_BY_PRODUCTO = 255;
	public static final int ADD_PRODUCTO_PEDIDO = 256;
	public static final int DELETE_PRODUCTO_PEDIDO = 257;
	public static final int PRODUCTO_PEDIDO_UPDATE = 258;

	// EVENTOS ERRORES DE PEDIDO
	public static final int PEDIDO_YA_EXISTE = -200;
	public static final int PEDIDO_YA_INACTIVO = -201;
	public static final int PEDIDO_NO_EXISTE = -202;
	public static final int PEDIDO_YA_ACTIVO = -203;
	
	// EVENTOS DE TIENDA
	public static final int GUI_ALTA_TIENDA = 500;
	public static final int GUI_BAJA_TIENDA = 501;
	public static final int GUI_UPDATE_TIENDA = 502;
	public static final int OBTENER_DATOS_TIENDA = 506;

	public static final int ALTA_TIENDA = 550;
	public static final int MOSTRAR_TIENDA =  551;
	public static final int LISTAR_TIENDAS = 552;
	public static final int BAJA_TIENDA = 553;
	public static final int UPDATE_TIENDA = 554;
	

	// EVENTOS ERRORES DE TIENDA
	public static final int TIENDA_YA_EXISTE = -501;
	public static final int TIENDA_NO_EXISTE = -502;
	public static final int FALLO_BAJA_TIENDA = -503;
	public static final int ERROR_ACTUALIZAR_TIENDA = -504;
	public static final int TIENDA_INACTIVA = -505;
	public static final int TIENDA_YA_INACTIVA = -506;

	
	//EVENTOS DE PROVEEDOR 
	public static final int GUI_ALTA_PROVEEDOR = 401;
	public static final int GUI_BAJA_PROVEEDOR = 402;
	public static final int GUI_UPDATE_PROVEEDOR = 403;
	
	public static final int MOSTRAR_PROVEEDOR = 450;
	public static final int LISTAR_PROVEEDORES = 451;
	public static final int BAJA_PROVEEDOR = 453;
	public static final int ALTA_PROVEEDOR = 454;
	public static final int UPDATE_PROVEEDOR = 455;
	public static final int OBTENER_DATOS_PROVEEDOR = 456;
	
	// EVENTOS ERROR DE PROVEEDOR	
	public static final int PROVEEDOR_YA_EXISTE = -450;
	public static final int PROVEEDOR_NO_EXISTE = -451;
	public static final int PROVEEDOR_YA_INACTIVO = -452;
	public static final int PROVEEDOR_YA_ACTIVO = -455;
	public static final int PROVEEDOR_INACTIVO = -456;
	public static final int PROVEEDOR_OBTENER_DATOS_NO_EXISTE = -457;

	//EVENTOS TRABAJADOR
	
	//vistas trabajador
	public static final int GUI_ALTA_TRABAJADOR = 600;
	public static final int GUI_BAJA_TRABAJADOR = 601;
	public static final int GUI_UPDATE_TRABAJADOR = 602;
	public static final int GUI_READ_ALL_TRABAJADOR = 604;
	public static final int GUI_READ_TRABAJADOR_BY_NAME= 605;
	
	//acciones trabajador
	public static final int ALTA_TRABAJADOR = 606;
	public static final int BAJA_TRABAJADOR = 607;
	public static final int UPDATE_TRABAJADOR = 608;
	public static final int READ_BY_TIENDA_TRABAJADOR = 609;
	public static final int READ_BY_NAME_TRABAJADOR = 610;
	public static final int MOSTRAR_TRABAJADOR = 611;
	public static final int LISTAR_TRABAJADORES = 612;
	public static final int OBTENER_DATOS_TRABAJADOR = 613;
	
	// ERRORRES TRABAJADOR
	public static final int TRABAJADOR_INACTIVO = -600;
	public static final int TRABAJADOR_NO_EXISTE = -601;
	public static final int TRABAJADOR_YA_EXISTE = -602;
	public static final int TRABAJADOR_YA_INACTIVO = -603;
	public static final int TRABAJADOR_YA_ACTIVO = -604;
	public static final int TRABAJADOR_OBTENER_DATOS_NO_EXISTE = -605;
	public static final int NO_HAY_TRABAJADORES_EN_BASE = -606;
	public static final int DNI_INVALIDO = -607;
	
	// EVENTOS LINEAPEDIDO
	public static final int STOCK_INSUFICIENTE = -700;
	public static final int CANTIDAD_INVALIDA_NEGATIVA = -701;
	public static final int LINEAPEDIDO_NO_EXISTE = -702;
}
