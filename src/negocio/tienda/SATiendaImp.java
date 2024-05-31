package negocio.tienda;

import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.tienda.DAOTienda;
import integracion.tienda.DAOTiendaImp;
import presentacion.controlador.Evento;
import transfers.TTienda;

public class SATiendaImp implements SATienda {
	private DAOTienda daoTienda;

	public SATiendaImp() {
		this.daoTienda = new DAOTiendaImp();
	}

	@Override
	public int alta(TTienda tienda) {
		TTienda tiendaExistente = daoTienda.read(tienda.getId());

		if (tiendaExistente == null) // no existe en la bbdd
			return daoTienda.alta(tienda);
		else if (!tiendaExistente.isActivo()) {// existe pero no esta activa
			tiendaExistente.setActivo(true);
			return daoTienda.update(tiendaExistente);
		} else// ya existe en la bbdd
			return Evento.TIENDA_YA_EXISTE;
	}

	@Override
	public int baja(int id) {
		TTienda tiendaExistente = daoTienda.read(id);
		if (tiendaExistente == null) {
			return Evento.TIENDA_NO_EXISTE;
		} else if (!tiendaExistente.isActivo())
			return Evento.TIENDA_YA_INACTIVA;

		int res = daoTienda.baja(id);// devuelve -1 si falla
		if (res == -1) {
			return Evento.FALLO_BAJA_TIENDA;
		}
		return res;
	}

	@Override
	public int update(TTienda tienda) {
		int res = -1;

		DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();

		TTienda tiendaExistente = daoTienda.read(tienda.getId());

		if (tiendaExistente == null)
			return Evento.TIENDA_NO_EXISTE;
		else if (!tiendaExistente.isActivo())
			return Evento.TIENDA_INACTIVA;
		
		res = daoTienda.update(tienda);
		
		return res;
	}

	@Override
	public TTienda read(int id) {
		TTienda tienda = daoTienda.read(id);

		return tienda;
	}

	@Override
	public List<TTienda> readAll() {
		List<TTienda> tiendas = daoTienda.readAll();

		return tiendas;
	}

}
