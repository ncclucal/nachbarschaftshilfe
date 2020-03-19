package hammerhilfe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.b100.swing.JGridPanel;
import de.b100.swing.JPlaceholderTextField;

public class LoginWindow implements ActionListener{
	
	private JFrame frame;
	private JGridPanel panel;
	
	private JPlaceholderTextField emailField;
	private JPlaceholderTextField passwordField;
	
	private JButton loginButton;
	
	public LoginWindow(){
		frame = new JFrame();
		panel = new JGridPanel(10);
		panel.setFillMode(GridBagConstraints.HORIZONTAL);
		
		emailField = new JPlaceholderTextField("Email");
		passwordField = new JPlaceholderTextField("Password");
		loginButton = new JButton("Login!");
		loginButton.addActionListener(this);

		Dimension dim1 = new Dimension(0,28);
		emailField.setPreferredSize(dim1);
		passwordField.setPreferredSize(dim1);
		
		panel.setFillMode(0);
		panel.add(new JLabel("Login"), 0, 0, 1, 1, 1, 0.5);

		panel.setFillMode(2);
		panel.add(emailField, 0, 1, 1, 1, 1, 1);
		panel.add(passwordField, 0, 2, 1, 1, 1, 1);

		panel.setFillMode(0);
		panel.add(loginButton, 0, 3, 2, 1, 1, 1);
		
		frame.add(panel);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			System.out.println("Login");
		}
	}
	
	
}
