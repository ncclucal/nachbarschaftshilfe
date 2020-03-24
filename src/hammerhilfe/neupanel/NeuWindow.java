package hammerhilfe.neupanel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.b100.swing.JGridPanel;

public abstract class NeuWindow implements ActionListener{
	
	private JFrame frame;
	private JGridPanel panel;
	private JPanel buttonPanel;
	
	private JTextField titleField;
	private JTextArea descriptionArea;
	private JButton createButton;
	
	protected String createButtonText = "Erstellen";
	
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
	
}
