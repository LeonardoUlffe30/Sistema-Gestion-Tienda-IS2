package negocio.trabajador;

import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.tienda.DAOTienda;
import integracion.trabajador.DAOTrabajador;
import presentacion.controlador.Evento;
import transfers.TTienda;
import transfers.TTrabajador;

public class SATrabajadorImp implements SATrabajador {
	 @Override
	public int alta(TTrabajador trabajador) {
		int idTrabajador = -1;
		
		DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();		
		TTrabajador trabajadorLeido = daoTrabajador.read(trabajador.getId());
		
		
		if (trabajadorLeido == null) { // No existe un trabajador con el mismo id
			
			DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();	
			TTienda tiendaLeida = daoTienda.read(trabajador.getIdTienda());
			
			if (tiendaLeida == null) // ERROR No existe la tienda al que se que se quiere relacionar el trabajador
				return Evento.TIENDA_NO_EXISTE;
			else if (!tiendaLeida.isActivo())
				return Evento.TIENDA_INACTIVA;
			
			if (!dniValido(trabajador.getDni()))
				return Evento.DNI_INVALIDO;
			
			trabajador.setDni(trabajador.getDni().toUpperCase());
			
			idTrabajador = daoTrabajador.alta(trabajador);
			
		} else if (!trabajadorLeido.isActivo()){ // Existe un trabajador con el mismo id y esta inactivo, por lo que se activa
			trabajadorLeido.setActivo(true);
			idTrabajador = daoTrabajador.update(trabajadorLeido);
		} else { // Existe un trabajador con el mismo y id y ya esta activo
			return Evento.TRABAJADOR_YA_ACTIVO;
		}
		
		return idTrabajador;
	}

    @Override
    public int baja(int id) throws Exception {
		int res = -1;
		
		DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();
	
		TTrabajador trabajadorLeido = daoTrabajador.read(id);
		
		if (trabajadorLeido == null)
			return Evento.TRABAJADOR_NO_EXISTE;
		else if (!trabajadorLeido.isActivo())
			return Evento.TRABAJADOR_YA_INACTIVO;
		
		res = daoTrabajador.baja(id);
		
		return res;
	}

    @Override
    public int update(TTrabajador trabajador)  {
    	
    	int res = -1;
    	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();   
    	DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();
    	
        TTrabajador trabajadorExistente = daoTrabajador.read(trabajador.getId());
        
        if (trabajadorExistente == null)
			return Evento.TRABAJADOR_NO_EXISTE;
		else if (!trabajadorExistente.isActivo())
			return Evento.TRABAJADOR_YA_INACTIVO;
        if (!dniValido(trabajador.getDni()))
			return Evento.DNI_INVALIDO;
		
		trabajador.setDni(trabajador.getDni().toUpperCase());
        
        
       TTienda tiendaLeida = daoTienda.read(trabajadorExistente.getIdTienda());
		
		if (tiendaLeida == null)
			return Evento.TIENDA_NO_EXISTE;
		else if (!tiendaLeida.isActivo())
			return Evento.TIENDA_INACTIVA;
        
        res = daoTrabajador.update(trabajador);
		return res;
        
    }

    @Override
    public TTrabajador read(int id)  {
    	
    	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();
        TTrabajador trabajador = daoTrabajador.read(id);
        
        return trabajador;
    }

    @Override
    public List<TTrabajador> readAll()  {
    	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();
        List<TTrabajador> trabajadores = daoTrabajador.readAll();
        if (trabajadores.isEmpty()) { //Si no ha guardado nada, es que no ha leido ningún dato, entonces no hay proveedores en la bd.
            //return Evento.NO_HAY_TRABAJADORES_EN_BASE;
        }
        return trabajadores;
    }
    
    @Override
    public List<TTrabajador> readByTienda(int idTienda){
    	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generaDAOTrabajador();
    	DAOTienda daoTienda = FactoriaIntegracion.getInstance().generaDAOTienda();
    	
    	if (daoTienda.read(idTienda) == null)
    		return null;
    	
    	List<TTrabajador> trabajadores = daoTrabajador.readByTienda(idTienda);
    	
        return trabajadores;
    }
    
    private boolean dniValido(String dni) {
    	boolean valid = true;
    	
    	if(dni.length() != 9)
			valid = false;
		else {
			for(int i = 0; i < dni.length() -1; i++) {
				if(!Character.isDigit(dni.charAt(i)))
					valid = false;
			}
			if(!Character.isLetter(dni.charAt(dni.length() - 1)))
				valid = false;
		}
    	
    	return valid;
    }
}