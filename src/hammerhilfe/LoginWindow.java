package hammerhilfe;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	
	private JCheckBox saveInfoCheckbox;
	
	public LoginWindow(){
		
		setTitle("Einloggen");
		
		emailField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = SwingUtils.newJButton("Login", this);
		registerButton = SwingUtils.newJButton("Registrieren", this);
		saveInfoCheckbox = new JCheckBox(" Passwort Speichern");
		saveInfoCheckbox.setBackground(null);
		
		addButton(loginButton);
		addButton(registerButton);
		addTextField("Email: ", emailField);
		addTextField("Passwort: ", passwordField);
		addCheckBox(saveInfoCheckbox);
		
		
		create();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			if(emailField.getText().length() < 1) {
				message(getFrame(), "Ungueltige Email");
				return;
			}
			if(passwordField.getText().length() < 1) {
				message(getFrame(), "Ungueltiges Passwort");
				return;
			}
			if(Utils.stringContainsAnyChar(emailField.getText(), invalidCharacters)) {
				message(getFrame(), "Die Email-Adresse enthaelt ungueltige Zeichen!");
				return;
			}
			if(Utils.stringContainsAnyChar(passwordField.getText(), invalidCharacters)) {
				message(getFrame(), "Das Passwort enthaelt ungueltige Zeichen!");
				return;
			}
			String t = ConnectionUtils.getWebpageContent("login.php?email="+emailField.getText()+"&password="+passwordField.getText());
			if(t == null) {
				message(getFrame(), "Verbindung konnte nicht hergestellt werden.");
			}
			if(t.startsWith("ERROR:")) {
				message(getFrame(), t.substring("error:".length()));
				return;
			}
			String token = t.substring("TOKEN:".length());
			
			System.out.println("Token: "+token);
			
			LoginInfo.token = token;
			
			if(saveInfoCheckbox.isSelected() && LoginInfo.token != null) {
				System.out.println("Saving Token: "+LoginInfo.token);
				File file = new File("token");
				try {
					file.createNewFile();
					FileWriter fw = new FileWriter(file);
					fw.write(LoginInfo.token);
					fw.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			close();
			new MainWindow();
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