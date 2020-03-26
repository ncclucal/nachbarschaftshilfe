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
			{
				//TODO Kontrollabfragen, ungï¿½ltige zeichen
				String password = Utils.toString(passwordField.getPassword());
				String passwordConfirm = Utils.toString(passwordConfirmField.getPassword());
				
				if(!password.equals(passwordConfirm)) {
					message(getFrame(), "Die beiden Passwoerter stimmen nicht überein!");
				}else {
					String email = emailField.getText();
					
					String str = ConnectionUtils.getWebpageContent("register.php?email="+email+"&password="+password);
					
					System.out.println(str);
				}
			}
			System.gc();
		}
	}
	
	

}
