package integracion.tienda;

import java.util.List;

import transfers.TTienda;

public interface DAOTienda {
	public int alta(TTienda tienda);
	public int baja(int id);
	public int update(TTienda tienda);
	public TTienda read(int id);
	public List<TTienda> readAll();
}
