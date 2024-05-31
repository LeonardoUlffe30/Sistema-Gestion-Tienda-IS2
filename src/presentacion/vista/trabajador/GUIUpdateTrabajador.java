package presentacion.vista.trabajador;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.controlador.Controller;
import presentacion.controlador.Evento;
import presentacion.vista.IGUI;
import transfers.TTrabajador;
import utils.CustomGUIUtils;

public class GUIUpdateTrabajador extends JFrame implements IGUI{
	
private static final long serialVersionUID = 1L;
	
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField telefonoTextField;
	private JTextField idTiendaTextField;
	private JTextField dniTextField;
	
	
	public GUIUpdateTrabajador() {
		super("Tienda IS2 - Update Trabajador");
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Update Trabajador");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("UPDATE TRABAJADOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos para actualizar un trabajador");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de id
		idTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("id: ", idTextField));
		
		// Obtener datos producto
		JButton obtenerTrabajadorButton = new JButton("OBTENER DATOS TRABAJADOR");
		obtenerTrabajadorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				
				Controller.getInstance().action(Evento.OBTENER_DATOS_TRABAJADOR, id);
				dispose();
			}
		});
		obtenerTrabajadorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        obtenerTrabajadorButton.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(obtenerTrabajadorButton);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de nombre
		nombreTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Nombre: ", nombreTextField));
		
		// Panel de stock
		telefonoTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Telefono: ", telefonoTextField));
				
		// Panel de id_tienda
		idTiendaTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Id Tienda: ", idTiendaTextField));
		
		// Panel de id_proveedor
		dniTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("DNI: ", dniTextField));
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				String nombre = nombreTextField.getText();
				int telefono = Integer.parseInt(telefonoTextField.getText());
				int idTienda = Integer.parseInt(idTiendaTextField.getText());
				String dni = dniTextField.getText();
				
				TTrabajador trabajador = new TTrabajador( id, dni, nombre,  telefono,idTienda, true);
				
				Controller.getInstance().action(Evento.UPDATE_TRABAJADOR, trabajador);
				dispose();
			}
		});
		acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton.setPreferredSize(new Dimension(150, 40));
		mainPanel.add(acceptButton);
		
		add(mainPanel);
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.GUI_UPDATE_TRABAJADOR:
			initGUI();
			break;
		case Evento.OBTENER_DATOS_TRABAJADOR:
			initGUI();
			TTrabajador trabajador = (TTrabajador) datos;
			idTextField.setText(Integer.toString(trabajador.getId()));
			nombreTextField.setText(trabajador.getNombre());
			telefonoTextField.setText(Integer.toString(trabajador.getTelefono()));
			idTiendaTextField.setText(Integer.toString(trabajador.getIdTienda()));
			dniTextField.setText((trabajador.getDni()));
			break;
		case Evento.TRABAJADOR_OBTENER_DATOS_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El trabajador del que se desea obtener datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			initGUI();
			break;
		case Evento.TRABAJADOR_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El trabajador del que se desea actualizar datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TRABAJADOR_INACTIVO:
			JOptionPane.showMessageDialog(this, "El trbajador del que se desea actualizar datos esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "La tienda a la que se desea vincular no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_INACTIVA:
			JOptionPane.showMessageDialog(this, "La tienda a la que se desea vincular esta inactiva", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.DNI_INVALIDO:
			JOptionPane.showMessageDialog(this, "El DNI del trabajador que se deseas actualizar no es valido", "Error Update trabajador", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.UPDATE_TRABAJADOR:
			JOptionPane.showMessageDialog(this, "El trabajador se ha actualizado correctamente", "Update trabajador",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
			
	}


}
