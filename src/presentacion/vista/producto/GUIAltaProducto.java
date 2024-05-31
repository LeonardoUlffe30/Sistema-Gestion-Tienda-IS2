package presentacion.vista.producto;

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
import transfers.TProducto;
import utils.CustomGUIUtils;

public class GUIAltaProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private static final String title = "Tienda IS2 - Alta Producto";
	
	public GUIAltaProducto() {
	}
	
			
	private void initGUIAltaNuevo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Alta Producto");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA PRODUCTO");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduzca el id del producto que desee dar de alta");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		// Panel de nombre
		JTextField nombreField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Nombre: ", nombreField));
		
		// Panel de stock
		JTextField stockField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Stock: ", stockField));
		
		// Panel de precio
		JTextField precioField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Precio: ", precioField));
				
		// Panel de id_tienda
		JTextField idTiendaField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Id Tienda: ", idTiendaField));
		
		// Panel de id_proveedor
		JTextField idProveedorField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Id Proveedor: ", idProveedorField));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = nombreField.getText();
				int stock = Integer.parseInt(stockField.getText());
				double precio = Double.parseDouble(precioField.getText());
				int idTienda = Integer.parseInt(idTiendaField.getText());
				int idProveedor = Integer.parseInt(idProveedorField.getText());
				
				TProducto producto = new TProducto(stock, nombre, precio, idTienda, idProveedor);
				
				Controller.getInstance().action(Evento.ALTA_PRODUCTO, producto);
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
		setTitle("Tienda IS2 - Baja Producto");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA PRODUCTO");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce el id del producto que deseas dar de alta");
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
				TProducto producto = new TProducto(id);
				Controller.getInstance().action(Evento.ALTA_PRODUCTO, producto);
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
		case Evento.GUI_ALTA_PRODUCTO:
			String options[] = {"Alta nuevo producto", "Alta producto existente"};
			int res = JOptionPane.showOptionDialog(this, "¿Qué acción deseas realizar?", title, JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			if (res == JOptionPane.YES_OPTION)
				initGUIAltaNuevo();
			else
				initGUIAltaExistente();
			break;
		case Evento.PROVEEDOR_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El proveedor que se desea vincular al producto no existe", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_INACTIVO:
			JOptionPane.showMessageDialog(this, "El proveedor que se desea vincular al producto esta inactivo", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "La tienda que se desea vincular al producto no existe", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_INACTIVA:
			JOptionPane.showMessageDialog(this, "La tienda que se desea vincular al producto esta inactiva", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.STOCK_INVALIDO_NEGATIVO:
			JOptionPane.showMessageDialog(this, "El stock debe ser positivo", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PRECIO_INVALIDO_NEGATIVO:
			JOptionPane.showMessageDialog(this, "El precio debe ser positivo", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(this, "El producto se dio de alta con ID: " + (Integer) datos, "Alta producto",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
			
	}
		
}
