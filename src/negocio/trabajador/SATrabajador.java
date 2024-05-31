package negocio.trabajador;

import java.util.List;

import transfers.TTrabajador;

public interface SATrabajador {
	public int alta(TTrabajador trabajador);
	public int baja(int id) throws Exception;
	public int update(TTrabajador trabajador);
	public TTrabajador read(int id);
	public List<TTrabajador> readAll();
	public List<TTrabajador> readByTienda(int idTienda);
}
