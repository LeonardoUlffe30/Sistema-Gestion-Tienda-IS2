package main;

import javax.swing.JOptionPane;

import presentacion.controlador.Controller;
import presentacion.controlador.Evento;

public class Main {
	public static void main(String[] args) {
		try {
			Controller.getInstance().action(Evento.GUI_PRINCIPAL, null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha sucedido un error inesperado: " + e.getMessage(), "ERROR inesperado", JOptionPane.ERROR_MESSAGE);
		}
	}
}
