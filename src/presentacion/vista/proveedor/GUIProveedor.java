package presentacion.vista.proveedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

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
import transfers.TProveedor;

import presentacion.controlador.Controller;
import presentacion.controlador.Evento;
import presentacion.vista.IGUI;
import utils.CustomGUIUtils;

public class GUIProveedor extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private JPanel northButtonPanel;
	private JPanel centerPanel;
	private ProveedorTableModel proveedorTableModel;
	
	public GUIProveedor() {
		super("Tienda IS2 - Proveedor");
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Proveedor");
		
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
		case Evento.MOSTRAR_PROVEEDOR:
			TProveedor productoLeido = (TProveedor) datos;
			
			if (productoLeido == null)
				JOptionPane.showMessageDialog(new Frame(), "El proveedor no existe", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				proveedorTableModel.clearTable();
				proveedorTableModel.addProveedor((TProveedor) datos);
			}
			break;
		case Evento.LISTAR_PROVEEDORES:
			List<TProveedor> proveedores = (List<TProveedor>) datos;
			
			if (proveedores.isEmpty())
				JOptionPane.showMessageDialog(new Frame(), "No hay proveedores", "Listar Proveedores",
						JOptionPane.INFORMATION_MESSAGE);
			
			proveedorTableModel.clearTable();
			for (TProveedor proveedor : proveedores) {
				proveedorTableModel.addProveedor(proveedor);
			}
			break;
		case Evento.PROVEEDOR_YA_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "El proveedor ya existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_YA_INACTIVO:
			JOptionPane.showMessageDialog(new Frame(), "El proveedor ya esta inactivo", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.PROVEEDOR_NO_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "El proveedor no existe", "Error", JOptionPane.ERROR_MESSAGE);
			break;
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
					Controller.getInstance().action(Evento.GUI_ALTA_PROVEEDOR, null);
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
					Controller.getInstance().action(Evento.GUI_BAJA_PROVEEDOR, null);
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
					Controller.getInstance().action(Evento.GUI_UPDATE_PROVEEDOR, null);
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
		JLabel titleLabel = new JLabel("MOSTRAR PROVEEDOR/ES");
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
					Controller.getInstance().action(Evento.MOSTRAR_PROVEEDOR, id);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new Frame(), "El id tiene que ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topPanel.add(verButton);
		
		JButton verTodosButton = new JButton("Ver todos");
		verTodosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.LISTAR_PROVEEDORES, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
				dispose();
			}
		});
		topPanel.add(verTodosButton);
		
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		proveedorTableModel = new ProveedorTableModel();
		
		JTable table = new JTable(proveedorTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		panel.add(tablePanel, BorderLayout.CENTER);
		
		return panel;
	}
}
