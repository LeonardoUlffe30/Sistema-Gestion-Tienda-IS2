package presentacion.vista.proveedor;

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
import presentacion.vista.proveedor.GUIUpdateProveedor;
import transfers.TProveedor;
import utils.CustomGUIUtils;

public class GUIUpdateProveedor extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField direccionTextField;	
	
	public GUIUpdateProveedor() {
		super("Tienda IS2 - Update Proveedor");
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Update Proveedor");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("UPDATE PROVEEDOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos para actualizar un proveedor");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de id
		idTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("id: ", idTextField));
		
		// Obtener datos producto
		JButton obtenerProveedorButton = new JButton("OBTENER DATOS PROVEEDOR");
		obtenerProveedorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				
				Controller.getInstance().action(Evento.OBTENER_DATOS_PROVEEDOR, id);
				dispose();
			}
		});
		obtenerProveedorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		obtenerProveedorButton.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(obtenerProveedorButton);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de nombre
		nombreTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Nombre: ", nombreTextField));
		
		// Panel de stock
		direccionTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Direccion: ", direccionTextField));
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				String nombre = nombreTextField.getText(); 
				String direccion = direccionTextField.getText(); 
				
				TProveedor proveedor = new TProveedor(id, nombre, direccion); 
				 
				Controller.getInstance().action(Evento.UPDATE_PROVEEDOR, proveedor); 
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
		case Evento.GUI_UPDATE_PROVEEDOR:
			initGUI();
			break;
		case Evento.OBTENER_DATOS_PROVEEDOR:
			initGUI();
			TProveedor proveedor = (TProveedor) datos;
			
			idTextField.setText(Integer.toString(proveedor.getId()));
			nombreTextField.setText(proveedor.getNombre());
			direccionTextField.setText(proveedor.getDireccion());
			break;
		case Evento.PROVEEDOR_OBTENER_DATOS_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El proveedor del que se desea obtener datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El proveedor del que se desea actualizar datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_INACTIVO:
			JOptionPane.showMessageDialog(this, "El proveedor del que se desea actualizar datos esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.UPDATE_PROVEEDOR:
			JOptionPane.showMessageDialog(this, "El proveedor se ha actualizado correctamente", "Alta proveedor", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
