package hammerhilfe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import hammerhilfe.panel.AngebotPanel;
import hammerhilfe.panel.SuchePanel;

public class MainWindow {
	
	private JFrame frame;
	private JTabbedPane tabs;
	private QueryThread queryThread;
	
	public MainWindow() {
		frame = new JFrame("Nachbarschaftshilfe");
		tabs = new JTabbedPane();

		tabs.add(new AngebotPanel(this, ConnectionUtils.get("angebot")), "Ich Biete");
		tabs.add(new SuchePanel(this, ConnectionUtils.get("suche")), "Ich Suche");
		tabs.add(new JPanel(), "Bestellnummer eingeben");
		
		frame.add(tabs);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		queryThread = new QueryThread();
		queryThread.start();
	}
	
	public QueryThread getQueryThread() {
		return queryThread;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTabbedPane getTabs() {
		return tabs;
	}
	
}
