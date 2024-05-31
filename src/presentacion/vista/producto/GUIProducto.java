package presentacion.vista.producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.List;

import presentacion.controlador.Controller;
import presentacion.controlador.Evento;
import presentacion.vista.IGUI;
import transfers.TProducto;
import utils.CustomGUIUtils;

public class GUIProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private JPanel northButtonPanel;
	private JPanel centerPanel;
	private ProductoTableModel productoTableModel;
	

	public GUIProducto() {
		super("Tienda IS2 - Producto");
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tienda IS2 - Producto");
		
		setLayout(new BorderLayout());
		
		northButtonPanel = CustomGUIUtils.buildEntidadesButtonPanel(this);
		
		centerPanel = buildCenterPanel();
		
		add(northButtonPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		setPreferredSize(CustomGUIUtils.calculateDimension(1480));
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.MOSTRAR_PRODUCTO:
			TProducto productoLeido = (TProducto) datos;
			
			if (productoLeido == null)
				JOptionPane.showMessageDialog(new Frame(), "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				productoTableModel.clearTable();
				productoTableModel.addProducto((TProducto) datos);
			}
			break;
		case Evento.LISTAR_PRODUCTOS:
			List<TProducto> productos = (List<TProducto>) datos;
			
			if (productos.isEmpty())
				JOptionPane.showMessageDialog(new Frame(), "No hay productos", "Listar Productos",
						JOptionPane.INFORMATION_MESSAGE);
			
			productoTableModel.clearTable();
			for (TProducto producto : productos) {
				productoTableModel.addProducto(producto);
			}
			break;
		case Evento.READ_PRODUCTO_BY_PROVEEDOR:
			List<TProducto> productosPorProveedor = (List<TProducto>) datos;
			
			if (productosPorProveedor == null) {
				JOptionPane.showMessageDialog(new Frame(), "El proveedor no existe", "Read By Proveedor",
						JOptionPane.ERROR_MESSAGE);
			} else if (productosPorProveedor.isEmpty()) {
				JOptionPane.showMessageDialog(new Frame(), "No hay productos vinculados a ese proveedor", "Read By Proveedor",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				productoTableModel.clearTable();
				for (TProducto producto : productosPorProveedor) {
					productoTableModel.addProducto(producto);
				}
			}
			break;
		case Evento.READ_PRODUCTO_BY_TIENDA:
			List<TProducto> productosPorTienda = (List<TProducto>) datos;
			 
			if (productosPorTienda == null) {
				JOptionPane.showMessageDialog(new Frame(), "La tienda no existe", "Read By Tienda",
						JOptionPane.ERROR_MESSAGE);
			} else if (productosPorTienda.isEmpty()) {
				JOptionPane.showMessageDialog(new Frame(), "No hay productos vinculados a esa tienda", "Read By Tienda",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				productoTableModel.clearTable();
				for (TProducto producto : productosPorTienda) {
					productoTableModel.addProducto(producto);
				}
			}
			break;
		case Evento.READ_PRODUCTO_BY_PEDIDO:
			List<TProducto> productosPorPedido = (List<TProducto>) datos;
			 
			if (productosPorPedido == null) {
				JOptionPane.showMessageDialog(new Frame(), "El pedido no existe", "Read By Pedido",
						JOptionPane.ERROR_MESSAGE);
			} else if (productosPorPedido.isEmpty()) {
				JOptionPane.showMessageDialog(new Frame(), "No hay productos vinculados a ese pedido", "Read By Pedido",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				productoTableModel.clearTable();
				for (TProducto producto : productosPorPedido) {
					productoTableModel.addProducto(producto);
				}
			}
			break;
		case Evento.PRODUCTO_YA_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "El producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
		case Evento.PRODUCTO_YA_INACTIVO:
			JOptionPane.showMessageDialog(new Frame(), "El producto ya esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
		case Evento.PRODUCTO_NO_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
		
		}
	}

	
	private JPanel buildCenterPanel()  {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.decode("#cce6ff"));
		centerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.decode("#cce6ff"));
		
		JButton altaButton = CustomGUIUtils.createButton2("ALTA");
		ActionListener altaListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.GUI_ALTA_PRODUCTO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		};
		altaButton.addActionListener(altaListener);
		buttonPanel.add(altaButton);
		
		JButton bajaButton = CustomGUIUtils.createButton2("BAJA");
	    bajaButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
					Controller.getInstance().action(Evento.GUI_BAJA_PRODUCTO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
	        }
	    });
	    buttonPanel.add(bajaButton);
	    
	    JButton updateButton = CustomGUIUtils.createButton2("UPDATE");
	    updateButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
					Controller.getInstance().action(Evento.GUI_UPDATE_PRODUCTO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
	        }
	    });
	    buttonPanel.add(updateButton);
	    
		centerPanel.add(buttonPanel);
		
		centerPanel.add(buildMostrarPanel());

		// Lo he añadido porque si no se ponen las tablas al final en otras entidades si existe este problema ajustarlo
		centerPanel.add(Box.createVerticalStrut(200));
		
		return centerPanel;
	}
	
	public JPanel buildMostrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.decode("#cce6ff"));
		panel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		CustomGUIUtils.changeComponentSize(panel, new Dimension(900, 450));
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel("MOSTRAR PRODUCTO/S");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		topPanel.add(titleLabel);
		
		
		JTextField idTextField = new JTextField(5);
		topPanel.add(idTextField);
		
		JButton verButton = new JButton("Ver por id");
		verButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (idTextField.getText().equals(""))
						throw new Exception("Debes de introducir un id");
					
					int id = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.MOSTRAR_PRODUCTO, id);
					dispose();
				} catch (NumberFormatException ex) {			            
		            JOptionPane.showMessageDialog(null, "El id debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topPanel.add(verButton);
		
		JButton readByTiendaButton = new JButton("Read by Tienda");
	    readByTiendaButton.addActionListener(new ActionListener() {
	    	
	    	@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (idTextField.getText().equals(""))
						throw new Exception("Debes de introducir un id");
					
					int id = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.READ_PRODUCTO_BY_TIENDA, id);
					dispose();
				} catch (NumberFormatException ex) {			            
		            JOptionPane.showMessageDialog(null, "El id debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
	    });
	    topPanel.add(readByTiendaButton);
	    
	    JButton readByProveedorButton = new JButton("Read by Proveedor");
	    readByProveedorButton.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (idTextField.getText().equals(""))
						throw new Exception("Debes de introducir un id");
					
					int id = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.READ_PRODUCTO_BY_PROVEEDOR, id);
					dispose();
				} catch (NumberFormatException ex) {			            
		            JOptionPane.showMessageDialog(null, "El id debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
	    });
	    topPanel.add(readByProveedorButton);
		
	    JButton readByPedidoButton = new JButton("Read by Pedido");
	    readByPedidoButton.addActionListener(new ActionListener() {
	    	
	    	@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (idTextField.getText().equals(""))
						throw new Exception("Debes de introducir un id");
					
					int id = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.READ_PRODUCTO_BY_PEDIDO, id);
					dispose();
				} catch (NumberFormatException ex) {			            
		            JOptionPane.showMessageDialog(null, "El id debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
	    });
	    topPanel.add(readByPedidoButton);
		
		JButton verTodosButton = new JButton("Ver todos");
		verTodosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.LISTAR_PRODUCTOS, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		topPanel.add(verTodosButton);
		
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		productoTableModel = new ProductoTableModel();
		
		JTable table = new JTable(productoTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		panel.add(tablePanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	
}
