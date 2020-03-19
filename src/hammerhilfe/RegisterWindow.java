package hammerhilfe;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.b100.swing.SwingUtils;
import de.b100.swing.TextInputWindow;

import static hammerhilfe.LoginWindow.message;

public class RegisterWindow extends TextInputWindow{

	private JTextField emailField;
	private JPasswordField passwordConfirmField;
	private JPasswordField passwordField;
	
	private JButton registerButton;
	private JButton loginButton;
	
	public RegisterWindow() {
		registerButton = SwingUtils.newJButton("Registrieren!", this);
		loginButton = SwingUtils.newJButton("Login!", this);

		addButton(registerButton);
		addButton(loginButton);
		
		emailField = new JTextField();
		passwordField = new JPasswordField();
		passwordConfirmField = new JPasswordField();
		
		addTextField("Email: ", emailField);
		addTextField("Passwort", passwordField);
		addTextField("Passwort Wiederholen: ", passwordConfirmField);
		
		create();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			close();
			new LoginWindow();
		}
		if(e.getSource() == registerButton) {
			//TODO Kontrollabfragen, ungültige zeichen
			if(!passwordField.getPassword().equals(passwordConfirmField.getPassword())) {
				message(getFrame(), "Die beiden Passwörter stimmen nicht überein!");
			}
			//TODO Regsitrieren
		}
	}

}
