package integracion.pedido;

import java.util.List;
import transfers.TPedido;

public interface DAOPedido {
	public int alta(TPedido pedido);
//	public int baja(int id);
	public int update(TPedido pedido);
	public TPedido read(int id);
	public List<TPedido> readAll();
	public List<TPedido> readByTrabajador(int idTrabajador);
	public List<TPedido> readByProducto(int idProducto);
}
