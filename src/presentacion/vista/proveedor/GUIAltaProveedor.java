package presentacion.vista.proveedor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import transfers.TProveedor;
import utils.CustomGUIUtils;

public class GUIAltaProveedor extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private static final String title = "Tienda IS2 - Alta Proveedor";
	
	public GUIAltaProveedor() {
	}
	
			
	private void initGUIAltaNuevo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Alta Proveedor");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA PROVEEDOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce el id del proveedor que deseas dar de alta");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		// Panel de nombre
		JTextField nombreField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Nombre: ", nombreField));
		
		// Panel de stock
		JTextField direccionField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Direccion: ", direccionField));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = nombreField.getText();
				String direccion = direccionField.getText();
				
				TProveedor proveedor = new TProveedor(nombre, direccion);
				
				Controller.getInstance().action(Evento.ALTA_PROVEEDOR, proveedor);
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
	
	private void initGUIAltaExistente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Alta Proveedor");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA PROVEEDOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos del proveedor que deseas dar de alta");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		// Panel de id
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel idLabel = new JLabel("id: ");
		JTextField idField = new JTextField(10);
		idPanel.add(idLabel);
		idPanel.add(idField);
		
		mainPanel.add(idPanel);
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				TProveedor proveedor = new TProveedor(id);
				Controller.getInstance().action(Evento.ALTA_PROVEEDOR, proveedor);
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
		case Evento.GUI_ALTA_PROVEEDOR:
			String options[] = { "Alta nuevo proveedor", "Alta proveedor existente" };
			int res = JOptionPane.showOptionDialog(this, "¿Qué acción deseas realizar?", title,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (res == JOptionPane.YES_OPTION)
				initGUIAltaNuevo();
			else
				initGUIAltaExistente();
			break;
		case Evento.PROVEEDOR_YA_EXISTE:
			JOptionPane.showMessageDialog(this, "El proveedor que se desea dar de alta ya existe", "Alta proveedor",
					JOptionPane.INFORMATION_MESSAGE);
			break;			
		case Evento.PROVEEDOR_YA_ACTIVO:
			JOptionPane.showMessageDialog(this, "El proveedor que se desea dar de alta ya esta activo", "Alta proveedor",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(this, "El proveedor se dio de alta con ID: " + (Integer) datos,
					"Alta proveedor", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
