package utils;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import presentacion.controlador.Controller;
import presentacion.controlador.Evento;

public class CustomGUIUtils {
	public static void changeComponentSize(Component c, Dimension d) {
		c.setPreferredSize(d);
		c.setMaximumSize(d);
		c.setMinimumSize(d);
	}
	
	public static Dimension calculateDimension(int width) {
        int height = width * 9 / 16;
        return new Dimension(width, height);
    }
	
	public static JButton createButton1(String text) {
		JButton button = new JButton(text);
		
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(Color.decode("#75aadb"));
		button.setBorder(BorderFactory.createLineBorder(Color.decode("#6C7A89")));
		button.setPreferredSize(new Dimension(250, 35));
		
		return button;
		
	}
	
	public static JButton createButton2(String text) {
		JButton button = new JButton(text);
		
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(Color.decode("#75aadb"));
		button.setBorder(BorderFactory.createLineBorder(Color.decode("#6C7A89")));
		button.setPreferredSize(new Dimension(200, 35));
		
		return button;
		
	}
	
	public static JPanel createTextFieldPanel(String text, JTextField textField) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel(text);
		panel.add(label);
		panel.add(textField);
		
		return panel;
	}
	
	public static JPanel buildEntidadesButtonPanel(JFrame frame) {
		JButton pedidoButton = CustomGUIUtils.createButton1("PEDIDO");
		ActionListener pedidoListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUI_PEDIDO, null);
				frame.dispose();
			}
			
		};
		pedidoButton.addActionListener(pedidoListener);
		
		JButton productoButton = CustomGUIUtils.createButton1("PRODUCTO");
		ActionListener productoListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUI_PRODUCTO, null);
				frame.dispose();
				
			}
		};
		productoButton.addActionListener(productoListener);
		
		JButton proveedorButton = CustomGUIUtils.createButton1("PROVEEDOR");
		ActionListener proveedorListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUI_PROVEEDOR, null);
				frame.dispose();
			}
			
		};
		proveedorButton.addActionListener(proveedorListener);
		
		JButton tiendaButton = CustomGUIUtils.createButton1("TIENDA");
		ActionListener tiendaListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUI_TIENDA, null);
				frame.dispose();
			}
		};
		tiendaButton.addActionListener(tiendaListener);
		
		JButton trabajadorButton = CustomGUIUtils.createButton1("TRABAJADOR");
		ActionListener trabajadorListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUI_TRABAJADOR, null);
				frame.dispose();
			}
		};
		trabajadorButton.addActionListener(trabajadorListener);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(pedidoButton);
		buttonPanel.add(productoButton);
		buttonPanel.add(proveedorButton);
		buttonPanel.add(tiendaButton);
		buttonPanel.add(trabajadorButton);
		buttonPanel.setBackground(Color.decode("#1a5276"));
		
		return buttonPanel;
	}
	
}
