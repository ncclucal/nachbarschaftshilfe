package hammerhilfe;

import javax.swing.JFrame;

import de.b100.swing.JGridPanel;

public class Window {
	
	private JFrame frame;
	private JGridPanel panel;
	
	public Window() {
		frame = new JFrame();
		panel = new JGridPanel();
		
		frame.add(panel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
