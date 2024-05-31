package negocio.pedido;

import java.util.List;

import transfers.TOADetallesPedido;
import transfers.TPedido;

public interface SAPedido {
	public int alta(TPedido pedido);
	public TPedido read(int id);
	public List<TPedido> readAll();
	public List<TPedido> readByTrabajador(int idTrabajador);
	public List<TPedido> readByProducto(int idProducto);
	public TOADetallesPedido readDetallesPedido(int idPedido);
}
