package presentacion.vista.pedido;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import transfers.TPedido;
import utils.CustomGUIUtils;

public class GUIAltaPedido extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private static final String title = "Tienda IS2 - Iniciar Pedido";
	
	public GUIAltaPedido() {
		super(title);
	}
	
			
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(title);
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA PEDIDO");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos del pedido que deseas crear");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		JPanel panelDatosPedido = new JPanel(new FlowLayout());
		
		JTextField idTrabajadorField = new JTextField(10);
		panelDatosPedido.add(CustomGUIUtils.createTextFieldPanel("Id Trabajador: ", idTrabajadorField));
		
		JTextField dateField = new JTextField(10);
		Date actualDate = new Date(System.currentTimeMillis());
		dateField.setText(actualDate.toString());
		panelDatosPedido.add(CustomGUIUtils.createTextFieldPanel("Fecha: ", dateField));
		
		mainPanel.add(panelDatosPedido);
		
		// Espacio
		//mainPanel.add(Box.createVerticalStrut(10));
	
		// BOTON ACEPTAR
		JPanel cerrarPedidoButtonPanel = new JPanel();
		cerrarPedidoButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton cerrarPedidoButton = new JButton("Aceptar");
		cerrarPedidoButton.setPreferredSize(new Dimension(100, 30));
		cerrarPedidoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idTrabajador = Integer.parseInt(idTrabajadorField.getText());
					Date date = Date.valueOf(dateField.getText());
					
					TPedido pedido = new TPedido(date, idTrabajador);
					
					Controller.getInstance().action(Evento.ALTA_PEDIDO, pedido);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cerrarPedidoButtonPanel.add(cerrarPedidoButton);
		
		
		mainPanel.add(cerrarPedidoButtonPanel);
		add(mainPanel);
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.GUI_ALTA_PEDIDO:
			initGUI();
			break;
		case Evento.TRABAJADOR_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El trabajador que gestiona el pedido no existe", "ERROR - Alta Pedido",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TRABAJADOR_INACTIVO:
			JOptionPane.showMessageDialog(this, "El trabajador que gestiona el pedido esta inactivo", "ERROR - Alta Pedido",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			int id = (int) datos;
			JOptionPane.showMessageDialog(this, "El pedido se ha dado de alta con id: " + id, "Alta Pedido",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
