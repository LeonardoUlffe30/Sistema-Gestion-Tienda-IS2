package presentacion.controlador;

public abstract class Controller {
	private static Controller controllerInstance;
	
	public static Controller getInstance() {
		if (controllerInstance == null)
			controllerInstance = new ControllerImp();
		return controllerInstance;
	}
	
	public abstract void action(int evento, Object transfer);
}
