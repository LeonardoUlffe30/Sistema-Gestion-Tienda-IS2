package presentacion.vista.tienda;

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
import transfers.TTienda;
import utils.CustomGUIUtils;

public class GUIUpdateTienda extends JFrame implements IGUI {
private static final long serialVersionUID = 1L;
	
	private JTextField idTextField;
	private JTextField paisTextField;
	private JTextField ciudadTextField;
	private JTextField calleTextField;
	
//	private static GUIUpdateTienda instance;
//	
//	public static GUIUpdateTienda getInstance() {
//		if (instance == null)
//			instance = new GUIUpdateTienda();
//		
//		return instance;
//	}
	
	
	
	public GUIUpdateTienda() {
		super("Tienda IS2 - Update Tienda");
	}
	
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Update Tienda");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("UPADATE TIENDA");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos para actualizar una tienda");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de id
		idTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("id: ", idTextField));
		
		// Obtener datos producto
		JButton obtenerProductoButton = new JButton("OBTENER DATOS TIENDA");
		obtenerProductoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				
				Controller.getInstance().action(Evento.OBTENER_DATOS_TIENDA, id);
				dispose();
			}
		});
		obtenerProductoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        obtenerProductoButton.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(obtenerProductoButton);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de nombre
		paisTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Pais: ", paisTextField));
		
		// Panel de stock
		ciudadTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Ciudad: ", ciudadTextField));
		
		// Panel de precio
		calleTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Calle: ", calleTextField));
				
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				
				String pais = paisTextField.getText();
				String ciudad = ciudadTextField.getText();
				String calle = calleTextField.getText();
				
				TTienda tienda = new TTienda(id, ciudad, calle, pais, true);
				
				Controller.getInstance().action(Evento.UPDATE_TIENDA, tienda);
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
		case Evento.GUI_UPDATE_TIENDA:
			initGUI();
			break;
		case Evento.OBTENER_DATOS_TIENDA:
			initGUI();
			TTienda tienda = (TTienda) datos;
			idTextField.setText(Integer.toString(tienda.getId()));
			paisTextField.setText(tienda.getPais());
			ciudadTextField.setText(tienda.getCiudad());
			calleTextField.setText(tienda.getCalle());
			
//			if (tienda == null)
//				JOptionPane.showMessageDialog(new Frame(), "La tienda no existe", "Error", JOptionPane.ERROR_MESSAGE);

			break;
		case Evento.UPDATE_TIENDA:
			JOptionPane.showMessageDialog(this, "La tienda se ha actualizado correctamente", "Update tienda",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case Evento.TIENDA_INACTIVA:
			JOptionPane.showMessageDialog(this, "La tienda de la que se desea actualizar datos esta inactiva", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "La tienda de la que se desea actualizar datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}



}
