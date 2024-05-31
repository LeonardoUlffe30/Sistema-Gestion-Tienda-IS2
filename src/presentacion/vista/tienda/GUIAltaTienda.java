package presentacion.vista.tienda;

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
import transfers.TTienda;
import utils.CustomGUIUtils;

public class GUIAltaTienda 	extends JFrame implements IGUI {
		private static final long serialVersionUID = 1L;
		private static final String title = "Tienda IS2 - Alta Tienda";
		
		public GUIAltaTienda() {
		}
		
		private void initGUIAltaNuevo() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setTitle("Tienda IS2 - Alta Tienda");
			
			// Establece layout principal
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			
			// Titulo
			JLabel titleLabel = new JLabel("ALTA TIENDA");
			titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
			titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(titleLabel);
			
			// Espacio
			mainPanel.add(Box.createVerticalStrut(10));
			
			// Mensaje de ayuda
			JLabel helpLabel = new JLabel("Introduce el id de la tienda que deseas dar de alta");
			helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
			helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(helpLabel);
			
			// Espacio
			mainPanel.add(Box.createVerticalStrut(5));			
			
			// Panel de calle
			JTextField paisField = new JTextField(10);
			mainPanel.add(CustomGUIUtils.createTextFieldPanel("Pais: ",paisField));	
			
			// Panel de precio
			JTextField ciudadField = new JTextField(10);
			mainPanel.add(CustomGUIUtils.createTextFieldPanel("Ciudad: ", ciudadField));
			
			// Panel de calle
			JTextField calleField = new JTextField(10);
			mainPanel.add(CustomGUIUtils.createTextFieldPanel("Calle: ",calleField));	
			
			// Boton Aceptar
			JButton acceptButton = new JButton("ACEPTAR");
			acceptButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String pais = paisField.getText();
					String ciudad = ciudadField.getText();
					String calle = calleField.getText();
					
					TTienda tienda = new TTienda(ciudad, calle, pais, true);
					
					Controller.getInstance().action(Evento.ALTA_TIENDA, tienda);
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
			setTitle("Tienda IS2 - Alta Tienda");
			
			// Establece layout principal
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			
			// Titulo
			JLabel titleLabel = new JLabel("ALTA TIENDA");
			titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
			titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(titleLabel);
			
			// Espacio
			mainPanel.add(Box.createVerticalStrut(10));
			
			// Mensaje de ayuda
			JLabel helpLabel = new JLabel("Introduce los datos de la tienda que deseas dar de alta");
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
					TTienda tienda = new TTienda(id);
					Controller.getInstance().action(Evento.ALTA_TIENDA, tienda);
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
			case Evento.GUI_ALTA_TIENDA:
				String options[] = {"Alta nueva tienda", "Alta tienda existente"};
				int res = JOptionPane.showOptionDialog(this, "¿Qué acción deseas realizar?", title, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				
				if (res == JOptionPane.YES_OPTION)
					initGUIAltaNuevo();
				else
					initGUIAltaExistente();
				
				break;
			default:
				JOptionPane.showMessageDialog(this, "La tienda se dio de alta con ID: " + (Integer) datos,
						"Alta tienda", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			
				
		}
			
}
