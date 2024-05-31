package presentacion.vista.pedido;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import transfers.TLineaPedido;
import utils.CustomGUIUtils;

public class GUIDeleteProductoPedido extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private static final String title = "Tienda IS2 - Eliminar Producto a Pedido";
	
	public GUIDeleteProductoPedido() {
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
		JLabel titleLabel = new JLabel("ELIMINAR PRODUCTO DE PEDIDO");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos del producto que deseas eliminar");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		JPanel panelDatosProductoPedido = new JPanel();
		panelDatosProductoPedido.setLayout(new BoxLayout(panelDatosProductoPedido, BoxLayout.Y_AXIS));
		
		JTextField idPedidoField = new JTextField(10);
		panelDatosProductoPedido.add(CustomGUIUtils.createTextFieldPanel("Id Pedido: ", idPedidoField));
		
		JTextField idProductoField = new JTextField(10);
		panelDatosProductoPedido.add(CustomGUIUtils.createTextFieldPanel("Id Producto: ", idProductoField));
		
		
		mainPanel.add(panelDatosProductoPedido);
	
		// BOTON ACEPTAR
		JPanel cerrarPedidoButtonPanel = new JPanel();
		cerrarPedidoButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton cerrarPedidoButton = new JButton("Aceptar");
		cerrarPedidoButton.setPreferredSize(new Dimension(100, 30));
		cerrarPedidoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idPedido = Integer.parseInt(idPedidoField.getText());
					int idProducto = Integer.parseInt(idProductoField.getText());
					
					TLineaPedido lineaPedido = new TLineaPedido(idPedido, idProducto);
					
					Controller.getInstance().action(Evento.DELETE_PRODUCTO_PEDIDO, lineaPedido);
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
		case Evento.GUI_DELETE_PRODUCTO_PEDIDO:
			initGUI();
			break;
		case Evento.PEDIDO_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El pedido del que se desea eliminar productos no existe", "Eliminar Producto Pedido",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PRODUCTO_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El producto que se desea eliminar del pedido no existe", "Eliminar Producto Pedido",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.LINEAPEDIDO_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El producto que se desea eliminar del pedido no se encuentra en el pedido", "Eliminar Producto Pedido",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(this, "El producto se ha eliminado del pedido con éxito", "Eliminar Producto Pedido",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
