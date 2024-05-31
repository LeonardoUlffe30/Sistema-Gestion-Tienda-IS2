package negocio.proveedor;

import java.util.List;

import transfers.TProveedor;

public interface SAProveedor {
	int alta(TProveedor proveedor);
    int baja(int id);
    int update(TProveedor proveedor);
    TProveedor read(int id);
    List<TProveedor> readAll();
}