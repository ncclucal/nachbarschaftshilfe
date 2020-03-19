package hammerhilfe;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.b100.swing.SwingUtils;
import de.b100.swing.TextInputWindow;

public class LoginWindow extends TextInputWindow{
	
	public static final String invalidCharacters = "?&=";
	
	private JTextField emailField;
	private JTextField passwordField;
	
	private JButton loginButton;
	private JButton registerButton;
	
	public LoginWindow(){
		
		emailField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = SwingUtils.newJButton("Login", this);
		registerButton = SwingUtils.newJButton("Registrieren", this);
		
		addButton(loginButton);
		addButton(registerButton);
		addTextField("Email: ", emailField);
		addTextField("Passwort: ", passwordField);
		
		create();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			if(emailField.getText().length() < 1) {
				message(getFrame(), "Ungültige Email");
				return;
			}
			if(passwordField.getText().length() < 1) {
				message(getFrame(), "Ungültiges Passwort");
				return;
			}
			if(Utils.stringContainsAnyChar(emailField.getText(), invalidCharacters)) {
				message(getFrame(), "Die Email-Adresse enthält ungültige Zeichen!");
				return;
			}
			if(Utils.stringContainsAnyChar(passwordField.getText(), invalidCharacters)) {
				message(getFrame(), "Das Passwort enthält ungültige Zeichen!");
				return;
			}
			String t = ConnectionUtils.getWebpageContent("login.php?email="+emailField.getText()+"&password="+passwordField.getText());
			if(t == null) {
				message(getFrame(), "Verbindung konnte nicht hergestellt werden.");
			}
			if(t.startsWith("ERROR:")) {
				message(getFrame(), t.substring("error:".length()));
			}
			if(t.startsWith("TOKEN:")) {
				String token = t.substring("TOKEN:".length());
				
				System.out.println("Token: "+token);
			}
		}
		if(e.getSource() == registerButton) {
			close();
			new RegisterWindow();
		}
	}
	
	public static void message(JFrame frame, String s) {
		JOptionPane.showMessageDialog(frame, s);
	}
	
}