package presentacion.vista.trabajador;

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
import transfers.TTrabajador;
import utils.CustomGUIUtils;

public class GUITrabajador extends JFrame implements IGUI {
	
private static final long serialVersionUID = 1L;
	
	private JPanel northButtonPanel;
	private JPanel centerPanel;
	private TrabajadorTableModel trabajadorTableModel;
	
	public GUITrabajador() {
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

	private JPanel buildCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.decode("#cce6ff"));
		centerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.decode("#cce6ff"));
		
		
		//BOTON ALTA TRABAJADOR
		JButton altaButton = CustomGUIUtils.createButton2("ALTA");
        ActionListener altaListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try { //EVENTO CAMBIAR ALTA_TRABAJADOR
					Controller.getInstance().action(Evento.GUI_ALTA_TRABAJADOR, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		};
		
		altaButton.addActionListener(altaListener);
		buttonPanel.add(altaButton);
		
		
		//BOTON BAJA TRABAJADOR 
		JButton bajaButton = CustomGUIUtils.createButton2("BAJA");
	    bajaButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
					Controller.getInstance().action(Evento.GUI_BAJA_TRABAJADOR, null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
	        }
	    });
	    buttonPanel.add(bajaButton);
	    
	    //BOTON ACTUALIZAR
	    JButton updateButton = CustomGUIUtils.createButton2("UPDATE");
	    updateButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
					Controller.getInstance().action(Evento.GUI_UPDATE_TRABAJADOR, null);
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
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.MOSTRAR_TRABAJADOR:
			TTrabajador trabajadorLeido = (TTrabajador) datos;
			
			if (trabajadorLeido == null)
				JOptionPane.showMessageDialog(new Frame(), "El trabajador no existe", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				trabajadorTableModel.clearTable();
				trabajadorTableModel.addTrabajador((TTrabajador) datos);
			}
			break;
		case Evento.LISTAR_TRABAJADORES: //POSIBLE ERROR SI DATOS ES NULL
			List<TTrabajador> trabajadores = (List<TTrabajador>) datos;
			
			if (trabajadores.isEmpty())
				JOptionPane.showMessageDialog(new Frame(), "No hay trabajadores", "Listar trabajadores", JOptionPane.INFORMATION_MESSAGE);
			
			trabajadorTableModel.clearTable();
			for (TTrabajador trabajador : trabajadores) {
				trabajadorTableModel.addTrabajador(trabajador);
			}
			break;
		case Evento.READ_BY_TIENDA_TRABAJADOR:
			List<TTrabajador> trabajadoresTienda = (List<TTrabajador>) datos;
			
			if (trabajadoresTienda == null) {
				JOptionPane.showMessageDialog(new Frame(), "No existe la tienda", "Read By Tienda",
						JOptionPane.ERROR_MESSAGE);
			} else if (trabajadoresTienda.isEmpty()) {
				JOptionPane.showMessageDialog(new Frame(), "No hay trabajadores vinculados a esa tienda", "Read By Tienda",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				trabajadorTableModel.clearTable();
				for (TTrabajador trabajador : trabajadoresTienda) {
					trabajadorTableModel.addTrabajador(trabajador);
				}
			}
			break;
		}
		
	}
	
	public JPanel buildMostrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.decode("#cce6ff"));
		panel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		CustomGUIUtils.changeComponentSize(panel, new Dimension(900, 450));
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel("MOSTRAR TRABAJADOR/ES");
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
					
					int id  = Integer.parseInt(idTextField.getText());
					Controller.getInstance().action(Evento.MOSTRAR_TRABAJADOR, id);
					dispose();
				} catch (NumberFormatException ex) {			            
		            JOptionPane.showMessageDialog(null, "El id debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topPanel.add(verButton);
		
		JButton verPorTiendaButton = new JButton("Ver por idTienda");
		verPorTiendaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						if (idTextField.getText().equals(""))
							throw new Exception("Debes de introducir un id");
						
			            int id = Integer.parseInt(idTextField.getText());
			            Controller.getInstance().action(Evento.READ_BY_TIENDA_TRABAJADOR, id);
			            dispose();
			        } catch (NumberFormatException ex) {			            
			            JOptionPane.showMessageDialog(null, "Error: El Id debe ser un numero ", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		topPanel.add(verPorTiendaButton);
		
		JButton verTodosButton = new JButton("Ver todos");
		verTodosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.LISTAR_TRABAJADORES, null);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topPanel.add(verTodosButton);
		
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		trabajadorTableModel = new TrabajadorTableModel();
		
		JTable table = new JTable(trabajadorTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		panel.add(tablePanel, BorderLayout.CENTER);
		
		return panel;
	}


}
