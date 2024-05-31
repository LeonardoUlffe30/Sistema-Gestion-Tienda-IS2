package presentacion.vista.producto;

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
import transfers.TProducto;
import utils.CustomGUIUtils;

public class GUIUpdateProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField stockTextField;
	private JTextField precioTextField;
	private JTextField idTiendaTextField;
	private JTextField idProveedorTextField;
	
	
	public GUIUpdateProducto() {
		super("Tienda IS2 - Update Producto");
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Update Producto");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("UPDATE PRODUCTO");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce los datos para actualizar un producto");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de id
		idTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("id: ", idTextField));
		
		// Obtener datos producto
		JButton obtenerProductoButton = new JButton("OBTENER DATOS PRODUCTO");
		obtenerProductoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				
				Controller.getInstance().action(Evento.OBTENER_DATOS_PRODUCTO, id);
				dispose();
			}
		});
		obtenerProductoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        obtenerProductoButton.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(obtenerProductoButton);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Panel de nombre
		nombreTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Nombre: ", nombreTextField));
		
		// Panel de stock
		stockTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Stock: ", stockTextField));
		
		// Panel de precio
		precioTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Precio: ", precioTextField));
				
		// Panel de id_tienda
		idTiendaTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Id Tienda: ", idTiendaTextField));
		
		// Panel de id_proveedor
		idProveedorTextField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Id Proveedor: ", idProveedorTextField));
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idTextField.getText());
				String nombre = nombreTextField.getText();
				int stock = Integer.parseInt(stockTextField.getText());
				double precio = Double.parseDouble(precioTextField.getText());
				int idTienda = Integer.parseInt(idTiendaTextField.getText());
				int idProveedor = Integer.parseInt(idProveedorTextField.getText());
				
				TProducto producto = new TProducto(id, stock, nombre, precio, idTienda, idProveedor);
				
				Controller.getInstance().action(Evento.UPDATE_PRODUCTO, producto);
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
		case Evento.GUI_UPDATE_PRODUCTO:
			initGUI();
			break;
		case Evento.OBTENER_DATOS_PRODUCTO:
			initGUI();
			TProducto producto = (TProducto) datos;
			idTextField.setText(Integer.toString(producto.getId()));
			nombreTextField.setText(producto.getNombre());
			stockTextField.setText(Integer.toString(producto.getStock()));
			precioTextField.setText(Double.toString(producto.getPrecio()));
			idTiendaTextField.setText(Integer.toString(producto.getIdTienda()));
			idProveedorTextField.setText(Integer.toString(producto.getIdProveedor()));
			break;
		case Evento.PRODUCTO_OBTENER_DATOS_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El producto del que se desea obtener datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			initGUI();
			break;
		case Evento.PRODUCTO_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El producto del que se desea actualizar datos no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PRODUCTO_INACTIVO:
			JOptionPane.showMessageDialog(this, "El producto del que se desea actualizar datos esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "La tienda a la que se desea vincular no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_INACTIVA:
			JOptionPane.showMessageDialog(this, "La tienda a la que se desea vincular esta inactiva", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "El proveedor al que se desea vincular no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_INACTIVO:
			JOptionPane.showMessageDialog(this, "El proveedor al que se desea vincular esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.STOCK_INVALIDO_NEGATIVO:
			JOptionPane.showMessageDialog(this, "El stock debe ser positivo", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PRECIO_INVALIDO_NEGATIVO:
			JOptionPane.showMessageDialog(this, "El precio debe ser positivo", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.UPDATE_PRODUCTO:
			JOptionPane.showMessageDialog(this, "El producto se ha actualizado correctamente", "Alta producto",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
			
	}

}
