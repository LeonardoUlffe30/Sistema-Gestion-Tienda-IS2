package presentacion.vista.pedido;

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
import transfers.TPedido;
import utils.CustomGUIUtils;

public class GUIPedido extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private JPanel northButtonPanel;
	private JPanel centerPanel;
	private PedidoTableModel pedidoTableModel;
	
	public GUIPedido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tienda IS2");
		
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
		case Evento.MOSTRAR_PEDIDO:
			TPedido pedidoLeido = (TPedido) datos;
			
			if (pedidoLeido == null)
				JOptionPane.showMessageDialog(new Frame(), "El pedido no existe", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				pedidoTableModel.clearTable();
			pedidoTableModel.addPedido((TPedido) datos);
			}
			break;
		case Evento.LISTAR_PEDIDOS:
			List<TPedido> pedidos = (List<TPedido>) datos;
			
			if (pedidos.isEmpty())
				JOptionPane.showMessageDialog(new Frame(), "No hay pedidos ", "Listar Pedidos",
					JOptionPane.INFORMATION_MESSAGE);
			
			pedidoTableModel.clearTable();
			for (TPedido pedido : pedidos) {
				pedidoTableModel.addPedido(pedido);
			}
			break;
		case Evento.READ_PEDIDO_BY_TRABAJADOR:
			List<TPedido> pedidosByTrabajador = (List<TPedido>) datos;
			
			if (pedidosByTrabajador == null) {
				JOptionPane.showMessageDialog(new Frame(), "El trabajador no existe", "Read By Producto",
						JOptionPane.ERROR_MESSAGE);
			} else if (pedidosByTrabajador.isEmpty()) {
				JOptionPane.showMessageDialog(new Frame(), "No hay pedidos vinculados a esa trabajador", "Read By Trabajador",
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				pedidoTableModel.clearTable();
				for (TPedido pedido : pedidosByTrabajador) {
					pedidoTableModel.addPedido(pedido);
				}
			}
			break;
		case Evento.READ_PEDIDO_BY_PRODUCTO:
			List<TPedido> pedidosByProducto = (List<TPedido>) datos;
			
			if (pedidosByProducto == null) {
				JOptionPane.showMessageDialog(new Frame(), "El producto no existe", "Read By Producto",
						JOptionPane.ERROR_MESSAGE);
			} else if (pedidosByProducto.isEmpty()) {
					JOptionPane.showMessageDialog(new Frame(), "No hay pedidos vinculados a ese producto", "Read By Producto",
							JOptionPane.INFORMATION_MESSAGE);
			} else {
				pedidoTableModel.clearTable();
				for (TPedido pedido : pedidosByProducto) {
					pedidoTableModel.addPedido(pedido);
				}
			}
			break;
			
		case Evento.PEDIDO_YA_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "El pedido ya existe", "Error", JOptionPane.ERROR_MESSAGE);
		case Evento.PEDIDO_YA_INACTIVO:
			JOptionPane.showMessageDialog(new Frame(), "El pedido ya esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
		case Evento.PEDIDO_NO_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "El pedido no existe", "Error", JOptionPane.ERROR_MESSAGE);
		
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
					Controller.getInstance().action(Evento.GUI_ALTA_PEDIDO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		altaButton.addActionListener(altaListener);
		buttonPanel.add(altaButton);
		
		JButton addProductoPedidoButton = CustomGUIUtils.createButton2("AÑADIR PRODUCTO");
		addProductoPedidoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.GUI_ADD_PRODUCTO_PEDIDO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		buttonPanel.add(addProductoPedidoButton);
		
		JButton deleteProductoPedidoButton = CustomGUIUtils.createButton2("ELIMINAR PRODUCTO");
		deleteProductoPedidoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.GUI_DELETE_PRODUCTO_PEDIDO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		buttonPanel.add(deleteProductoPedidoButton);
		
		JButton verDetallesPedidoButton = CustomGUIUtils.createButton2("DETALLES PEDIDO");
		verDetallesPedidoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.GUI_DETALLES_PEDIDO, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPanel.add(verDetallesPedidoButton);
		
		
		centerPanel.add(buttonPanel);
		
		
		pedidoTableModel = new PedidoTableModel();
		
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
		JLabel titleLabel = new JLabel("MOSTRAR PEDIDO/S");
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
					Controller.getInstance().action(Evento.MOSTRAR_PEDIDO, id);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new Frame(), "El id tiene que ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topPanel.add(verButton);
		
		JButton readByTrabajadorButton = new JButton("Ver por Trabajador");
		readByTrabajadorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (idTextField.getText().equals(""))
						throw new Exception("Debes de introducir un id");
					
					int id = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.READ_PEDIDO_BY_TRABAJADOR, id);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new Frame(), "El id tiene que ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		topPanel.add(readByTrabajadorButton);
		
		
		JButton readByProductoButton = new JButton("Ver por Producto");
		readByProductoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (idTextField.getText().equals(""))
						throw new Exception("Debes de introducir un id");
					
					int id = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.READ_PEDIDO_BY_PRODUCTO, id);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new Frame(), "El id tiene que ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		topPanel.add(readByProductoButton);
		
		JButton verTodosButton = new JButton("Ver todos");
		verTodosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.LISTAR_PEDIDOS, null);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		topPanel.add(verTodosButton);
		
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		pedidoTableModel = new PedidoTableModel();
		
		JTable table = new JTable(pedidoTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		panel.add(tablePanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	
}
