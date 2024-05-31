package presentacion.vista.trabajador;


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
import transfers.TTrabajador;
import utils.CustomGUIUtils;

public class GUIAltaTrabajador extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private static final String title = "Tienda IS2 - Alta Producto";
	
	public GUIAltaTrabajador(){
	}
    
	private void initGUIAltaNuevo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Tienda IS2 - Alta Trabajador");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA TRABAJADOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduzca el id del trabajador que desee dar de alta");
		helpLabel.setFont(new Font("Arial", Font.BOLD, 16));
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(helpLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(5));
		
		// Panel de nombre
		JTextField nombreField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Nombre: ", nombreField));
			
		// Panel de tel
		JTextField telefonoField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Telefono: ", telefonoField));
				
		// Panel de id_tienda
		JTextField idTiendaField = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("Id Tienda: ", idTiendaField));
		
		// Panel de DNI
		JTextField dniText = new JTextField(10);
		mainPanel.add(CustomGUIUtils.createTextFieldPanel("DNI: ", dniText));
		
		// Boton Aceptar
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = nombreField.getText();	
				int telefono = Integer.parseInt(telefonoField.getText());
				int idTienda = Integer.parseInt(idTiendaField.getText());
                String dni = dniText.getText();
				
				TTrabajador trabajador = new TTrabajador( dni,nombre, telefono, idTienda,true);				
				Controller.getInstance().action(Evento.ALTA_TRABAJADOR, trabajador);
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
		setTitle("Tienda IS2 - Alta Trabajador");
		
		// Establece layout principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Titulo
		JLabel titleLabel = new JLabel("ALTA TRABAJADOR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(titleLabel);
		
		// Espacio
		mainPanel.add(Box.createVerticalStrut(10));
		
		// Mensaje de ayuda
		JLabel helpLabel = new JLabel("Introduce el id del trabajador que deseas dar de alta");
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
				TTrabajador trabajador = new TTrabajador(id);
				Controller.getInstance().action(Evento.ALTA_TRABAJADOR, trabajador);
				dispose();
			}
		});
		

		acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton.setPreferredSize(new Dimension(150, 40));
		mainPanel.add(acceptButton);
		//mainPanel.add(cancelButton);
		add(mainPanel);
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
		setResizable(false);
	}
	
	
	//BOTONES
	
	public JButton cancelButton()
	{
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controller.getInstance().action(Evento.GUI_TRABAJADOR, null);
			}
		
		});
		return cancelButton;
	}
	
	

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.GUI_ALTA_TRABAJADOR:
			String options[] = {"Alta nuevo trabajador", "Alta trabajador existente"};
			int res = JOptionPane.showOptionDialog(this, "¿Qué acción deseas realizar?", title, JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			if (res == JOptionPane.YES_OPTION)
				initGUIAltaNuevo();
			else
				initGUIAltaExistente();
			break;

		case Evento.TIENDA_NO_EXISTE:
			JOptionPane.showMessageDialog(this, "La tienda que se desea vincular al producto no existe", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.TIENDA_INACTIVA:
			JOptionPane.showMessageDialog(this, "La tienda que se desea vincular al producto esta inactiva", "Error Alta Producto", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.DNI_INVALIDO:
			JOptionPane.showMessageDialog(this, "El DNI del trabajador que se desea dar de alta no es valido", "Error Alta trabajador", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(this, "El trabajador se dio de alta con ID: " + (Integer) datos, "Alta trabajador",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
			
	}

}
