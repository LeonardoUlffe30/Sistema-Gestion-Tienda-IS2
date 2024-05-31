package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.CustomGUIUtils;

public class GUIPrincipal extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private JPanel northButtonPanel;
	private JPanel centerPanel;
	
	//TODO Faltan los action listeners de los botones
	
	public GUIPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tienda IS2");
		
		setLayout(new BorderLayout());
		
		northButtonPanel = CustomGUIUtils.buildEntidadesButtonPanel(this);

		centerPanel = buildHomeCenterPanel();
		
		add(northButtonPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		setPreferredSize(CustomGUIUtils.calculateDimension(1480));
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// Empty
	}
	
	private JPanel buildHomeCenterPanel()  {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.decode("#cce6ff"));
		centerPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		JLabel titleLabel = new JLabel("TIENDA IS2", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 54));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.add(titleLabel, BorderLayout.CENTER);
		
		JLabel textLabel1 = new JLabel("Aplicación de gestión de tienda", SwingConstants.CENTER);
		textLabel1.setFont(new Font("Arial", Font.PLAIN, 32));
		textLabel1.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.add(textLabel1, BorderLayout.CENTER);
		
		JLabel textLabel2 = new JLabel("Pulse uno de los botones para empezar la gestión", SwingConstants.CENTER);
		textLabel2.setFont(new Font("Arial", Font.PLAIN, 24));
		textLabel2.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.add(textLabel2, BorderLayout.CENTER);
		
		return centerPanel;
	}
}