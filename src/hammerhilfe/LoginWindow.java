package hammerhilfe;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.b100.swing.JGridPanel;

public class LoginWindow {
	
	private JFrame frame;
	private JGridPanel panel;
	
	private JTextField emailField;
	private JTextField passwordField;
	
	private JButton loginButton;
	
	public LoginWindow(){
		frame = new JFrame();
		panel = new JGridPanel(10);
		
		emailField = new JTextField();
		passwordField = new JTextField();
		loginButton = new JButton("Login!");
		loginButton.addActionListener((e) -> {
			
		});

		panel.add(new JLabel("Email: "), 0, 0, 1, 1, 1, 1);
		panel.add(new JLabel("Passwort: "), 0, 1, 1, 1, 1, 1);
		
		panel.add(emailField, 1, 0, 1, 1, 1, 1);
		panel.add(passwordField, 1, 1, 1, 1, 1, 1);
		
		panel.add(loginButton, 0, 2, 2, 1, 1, 1);
		
		frame.add(panel);
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	
}
