package integracion.producto;

import java.util.List;

import transfers.TProducto;

public interface DAOProducto {
	public int alta(TProducto producto);
	public int baja(int id);
	public int update(TProducto producto);
	public TProducto read(int id);
	public List<TProducto> readAll();
	public List<TProducto> readByTienda(int idTienda);
	public List<TProducto> readByProveedor(int idProveedor);
	public List<TProducto> readByPedido(int idPedido);
}
