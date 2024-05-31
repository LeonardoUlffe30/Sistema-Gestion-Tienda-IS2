package presentacion.vista.pedido;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentacion.controlador.Controller;
import presentacion.controlador.Evento;
import presentacion.vista.IGUI;
import transfers.TLineaPedido;
import transfers.TOADetallesPedido;
import utils.CustomGUIUtils;

public class GUIDetallesPedido extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private static final String title = "Tienda IS2 - Detalles Pedido";
	private CarroPedidoTableModel carroPedidoTableModel;
	private PedidoTableModel pedidoTableModel;
	
	public GUIDetallesPedido() {
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
		JLabel titleLabel = new JLabel("DETALLES PEDIDO");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce el id del pedido sobre el que quieres conocer sus detalles");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		JPanel idPanel = new JPanel(new FlowLayout());
		
		JTextField idField = new JTextField(10);
		idPanel.add(CustomGUIUtils.createTextFieldPanel("Id Pedido: ", idField));
		
		JButton verPedidoButton = new JButton("Ver");
		verPedidoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(idField.getText());
					Controller.getInstance().action(Evento.DETALLES_PEDIDO, id);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new Frame(), "El id del pedido debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		idPanel.add(verPedidoButton);
		
		
		mainPanel.add(idPanel);
				
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		// Mensaje de información
		JLabel infoLabel = new JLabel("Datos del pedido");
		infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(infoLabel);
		
		// Tabla de pedido
		JPanel tablePedidoPanel = buildPedidoPanel();
		mainPanel.add(tablePedidoPanel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		JLabel infoLabel2 = new JLabel("Productos asociados al pedido");
		infoLabel2.setFont(new Font("Arial", Font.BOLD, 16));
		infoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(infoLabel2);
		
		// TABLA DE CARRITOPEDIDO
		JPanel tableProductosPanel = buildProductosPanel();
		mainPanel.add(tableProductosPanel);
		
		add(mainPanel);
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.GUI_DETALLES_PEDIDO:
			initGUI();
			break;
		case Evento.DETALLES_PEDIDO:
			initGUI();
			TOADetallesPedido toaDetallePedido = (TOADetallesPedido) datos;
			
			if (toaDetallePedido.getPedido() != null) {
				carroPedidoTableModel.clearTable();
				for (TLineaPedido lineaPedido : toaDetallePedido.getLineaPedidos()) {
					carroPedidoTableModel.addLineaPedido(lineaPedido);
				}
				
				pedidoTableModel.clearTable();
				pedidoTableModel.addPedido(toaDetallePedido.getPedido());
				
			} else {
				JOptionPane.showMessageDialog(this, "El pedido no existe", "ERROR - Detalles Pedido",
						JOptionPane.ERROR_MESSAGE);
			}
			
			break;
		}
	}
	
	private JPanel buildPedidoPanel() {
		JPanel panel = new JPanel();
		
		CustomGUIUtils.changeComponentSize(panel, new Dimension(500, 100));
		
		pedidoTableModel = new PedidoTableModel();
		
		JTable table = new JTable(pedidoTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setPreferredSize(new Dimension(500, 90));
		scrollPane.setMaximumSize(new Dimension(500, 90));
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	private JPanel buildProductosPanel() {
		JPanel panel = new JPanel();
		
		CustomGUIUtils.changeComponentSize(panel, new Dimension(700, 450));
		
		carroPedidoTableModel = new CarroPedidoTableModel();
		
		JTable table = new JTable(carroPedidoTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel.add(scrollPane);
		
		return panel;
	}

}
