package presentacion.vista.tienda;

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
import presentacion.controlador.Controller;
import presentacion.controlador.Evento;
import presentacion.vista.IGUI;
import transfers.TTienda;
import utils.CustomGUIUtils;

public class GUITienda extends JFrame implements IGUI {
private static final long serialVersionUID = 1L;
	private JPanel northButtonPanel;
	private JPanel centerPanel;
	private TiendaTableModel tiendaTableModel;

	public GUITienda() {
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
		switch(evento){
		case Evento.TIENDA_YA_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "La tienda ya existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_NO_EXISTE:
			JOptionPane.showMessageDialog(new Frame(), "La tienda no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.FALLO_BAJA_TIENDA:
			JOptionPane.showMessageDialog(new Frame(), "Error al dar de baja a la tienda.", "Error", JOptionPane.ERROR_MESSAGE);
	    	break;
		case Evento.ERROR_ACTUALIZAR_TIENDA:
			JOptionPane.showMessageDialog(new Frame(), "Error al actualizar la tienda.", "Error", JOptionPane.ERROR_MESSAGE);
	    	break;
		
		
		case Evento.MOSTRAR_TIENDA:
			TTienda tiendaLeida = (TTienda) datos;
			
			if(tiendaLeida == null)
				JOptionPane.showMessageDialog(new Frame(), "La tienda no existe", "Error", JOptionPane.ERROR_MESSAGE);

			tiendaTableModel.clearTable();
			
			tiendaTableModel.addTienda((TTienda) datos);		
			break;
		case Evento.LISTAR_TIENDAS:
			List<TTienda>tiendas = (List<TTienda>) datos;
			
			if (tiendas.isEmpty())
				JOptionPane.showMessageDialog(new Frame(), "No hay tiendas", "Listar Tiendas",
						JOptionPane.INFORMATION_MESSAGE);
			
			tiendaTableModel.clearTable();
			for(TTienda tienda:tiendas){
				tiendaTableModel.addTienda(tienda);
			}
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
					Controller.getInstance().action(Evento.GUI_ALTA_TIENDA, null);
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
					Controller.getInstance().action(Evento.GUI_BAJA_TIENDA, null);
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
					Controller.getInstance().action(Evento.GUI_UPDATE_TIENDA, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
	        }
	    });
	    buttonPanel.add(updateButton);
	    
		
		centerPanel.add(buttonPanel);
		
		tiendaTableModel = new TiendaTableModel();
		
		centerPanel.add(buildMostrarPanel());


		// Lo he a�adido porque si no se ponen las tablas al final en otras entidades si existe este problema ajustarlo
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
		JLabel titleLabel = new JLabel("MOSTRAR TIENDA/S");
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
					Controller.getInstance().action(Evento.MOSTRAR_TIENDA, id);
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
					Controller.getInstance().action(Evento.LISTAR_TIENDAS, null);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		topPanel.add(verTodosButton);
		
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		tiendaTableModel = new TiendaTableModel();
		
		JTable table = new JTable(tiendaTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		panel.add(tablePanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	
}
