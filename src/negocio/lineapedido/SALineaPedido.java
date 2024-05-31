package negocio.lineapedido;

import java.util.List;

import transfers.TLineaPedido;

public interface SALineaPedido {
	public int addProductoPedido(TLineaPedido lineaPedido);
	public int deleteProductoPedido(TLineaPedido lineaPedido);
	public TLineaPedido read(int idPedido, int idProducto);
	public List<TLineaPedido> readByPedido(int idPedido);
}
