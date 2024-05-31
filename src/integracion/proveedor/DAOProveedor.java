package integracion.proveedor;
import java.util.List;

import transfers.TProveedor;


public interface DAOProveedor {
	public int alta(TProveedor proveedor);
	public int baja(int id);
	public int update(TProveedor proveedor);
	public TProveedor read(int id);
	public int readByNameAndAddress(TProveedor proveedor);
	public List<TProveedor> readAll();
}
