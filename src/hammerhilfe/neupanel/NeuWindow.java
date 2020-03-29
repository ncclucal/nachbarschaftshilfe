package hammerhilfe.neupanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.b100.swing.JGridPanel;
import hammerhilfe.ConnectionUtils;
import hammerhilfe.LoginInfo;
import hammerhilfe.Utils;
import hammerhilfe.panel.ListAndPreviewWindow;

public abstract class NeuWindow implements ActionListener{
	
	protected ListAndPreviewWindow listAndPreviewWindow;
	
	protected JFrame frame;
	protected JGridPanel panel;
	protected JPanel buttonPanel;
	
	protected JTextField titleField;
	protected JTextArea descriptionArea;
	protected JButton createButton;
	
	protected String createButtonText = "Erstellen";
	
	protected String typ;
	
	public NeuWindow() {
	}
	
	public void create(JFrame parent) {
		frame = new JFrame();
		panel = new JGridPanel();
		titleField = new JTextField();
		descriptionArea = new JTextArea();
		buttonPanel = new JPanel();
		createButton = new JButton(createButtonText);
		createButton.addActionListener(this);

		buttonPanel.add(createButton);
		
		panel.add(new JLabel("Titel: "), 0, 0, 1, 1, 1, 0);
		panel.add(titleField, 0, 1, 1, 1, 1, 0);
		panel.add(new JLabel("Beschreibung: "), 0, 2, 1, 1, 1, 0);
		panel.add(descriptionArea, 0, 3, 1, 1, 1, 1);
		panel.add(buttonPanel, 0, 4, 1, 1, 1, 0);
		
		frame.add(panel);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String title = titleField.getText();
		String description = descriptionArea.getText();
		String email = LoginInfo.email;
		String token = LoginInfo.token;

		title = Utils.toURL(title);
		description = Utils.toURL(description);
		email = Utils.toURL(email);
		token = Utils.toURL(token);
		typ = Utils.toURL(typ);
		
		String s = ConnectionUtils.getWebpageContent("eintragen.php?email=" + email + "&titel=" + title
				+ "&description=" + description + "&token=" + token + "&typ=" + typ);
		
		System.out.println("\""+s+"\"");
		
		listAndPreviewWindow.update();
	}
	
	public void setListAndPreviewWindow(ListAndPreviewWindow listAndPreviewWindow) {
		this.listAndPreviewWindow = listAndPreviewWindow;
	}
	
	public ListAndPreviewWindow getListAndPreviewWindow() {
		return listAndPreviewWindow;
	}
	
}
