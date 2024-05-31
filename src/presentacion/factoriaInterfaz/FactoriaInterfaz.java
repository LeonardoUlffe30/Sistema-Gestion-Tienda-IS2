package presentacion.factoriaInterfaz;

import presentacion.vista.IGUI;

public abstract class FactoriaInterfaz {
	private static FactoriaInterfaz factoryInstance;
	
	public static FactoriaInterfaz getInstance() {
		if (factoryInstance == null)
			factoryInstance = new FactoriaInterfazImp();
		return factoryInstance;
	}
	
	public abstract IGUI generaGUI(int evento);
}
