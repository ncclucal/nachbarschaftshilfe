package hammerhilfe;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import de.b100.swing.JGridPanel;

public class CrashReportWindow {
	
	private Throwable throwable;
	private JFrame frame;
	private JLabel label;
	private JGridPanel panel;
	private JTextArea textArea;
	
	public CrashReportWindow(Throwable throwable) {
		this.throwable = throwable;
		
		frame = new JFrame("Crash Report");
		panel = new JGridPanel(20);
		label = new JLabel("Ein Fehler ist Aufgetetreten!");
		textArea = new JTextArea();
		
		textArea.setEditable(false);
		textArea.setText(getErrorText(throwable));
		
		panel.add(label, 0, 0, 1, 1, 1, 0);
		panel.add(textArea, 0, 1, 1, 1, 1, 1);
		
		frame.add(panel);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static String getErrorText(Throwable throwable) {
		String str = "";
		
		str += throwable.getClass().getName() + ": " + throwable.getMessage() + "\n";
		
		for(StackTraceElement el : throwable.getStackTrace()) {
			str += "        " + el + "\n";
		}
		
		if(throwable.getCause() != null) {
			str += "caused by: "+getErrorText(throwable.getCause());
		}
		
		return str;
	}
	
}
