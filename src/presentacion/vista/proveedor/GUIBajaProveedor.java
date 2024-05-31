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

public class GUIBajaProveedor extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	public GUIBajaProveedor() {
		super("Tienda IS2 - Baja Proveedor");
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Baja Proveedor");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("BAJA PROVEEDOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce el id del proveedor que deseas dar de baja");
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
				Controller.getInstance().action(Evento.BAJA_PROVEEDOR, id);
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
		switch(evento) {
		case Evento.GUI_BAJA_PROVEEDOR:
			initGUI();
			break;
		case Evento.PROVEEDOR_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El proveedor que se desea dar de baja no existe", "Error Baja Proveedor", JOptionPane.ERROR_MESSAGE);
			break;		
		case Evento.PROVEEDOR_YA_INACTIVO:
			JOptionPane.showMessageDialog(this, "El proveedor que se desea dar de baja ya se encuentra dado de baja", "Baja proveedor",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case Evento.BAJA_PROVEEDOR:
			JOptionPane.showMessageDialog(this, "El proveedor se dio se dio de baja con éxito", "Baja proveedor",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(this, "Error inesperado en baja proveedor", "Error Baja Proveedor", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
