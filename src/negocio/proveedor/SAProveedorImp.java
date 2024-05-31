package negocio.proveedor;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.proveedor.DAOProveedor;
import presentacion.controlador.Evento;

import java.util.List;
import transfers.TProveedor;

public class SAProveedorImp implements SAProveedor {
	
	@Override
	public int alta(TProveedor proveedor) {
		int idProveedor = -1;
		
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();

		TProveedor proveedorLeido = daoProveedor.read(proveedor.getId());

		if (proveedorLeido == null) { // No existe un proveedor con el mismo id
			int id = daoProveedor.readByNameAndAddress(proveedor);
			if(id == -1) idProveedor = daoProveedor.alta(proveedor);
			else return Evento.PROVEEDOR_YA_EXISTE;
		} else if(!proveedorLeido.isActivo()) { // Existe un proveedor con el mismo id y esta inactivo, por lo que se activa
			proveedorLeido.setActivo(true);
			idProveedor = daoProveedor.update(proveedorLeido);
		} else { // Existe un proveedor con el mismo id y ya esta activo
			return Evento.PROVEEDOR_YA_ACTIVO;
		}
		
		return idProveedor;
	}

	@Override
	public int baja(int id) {
		int res = -1;
		
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();

		TProveedor proveedorLeido = daoProveedor.read(id);
		
		if (proveedorLeido == null)
			return Evento.PROVEEDOR_NO_EXISTE;
		else if(!proveedorLeido.isActivo())
			return Evento.PROVEEDOR_YA_INACTIVO;
		
		res = daoProveedor.baja(id);

		return res;
	}

	@Override
	public int update(TProveedor proveedor) {
		int res = -1;
		
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();

		TProveedor proveedorLeido = daoProveedor.read(proveedor.getId());

		if (proveedorLeido == null)
			return Evento.PROVEEDOR_NO_EXISTE;
		else if(!proveedorLeido.isActivo())
			return Evento.PROVEEDOR_INACTIVO;
		
		res = daoProveedor.update(proveedor);
		
		return res;
	}

	@Override
	public TProveedor read(int id) {
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();

		TProveedor proveedorLeido = daoProveedor.read(id);

		return proveedorLeido;
	}

	@Override
	public List<TProveedor> readAll() {
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generaDAOProveedor();

		List<TProveedor> proveedores = daoProveedor.readAll();

		return proveedores;
	}
}