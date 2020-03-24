package hammerhilfe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import hammerhilfe.panel.AngebotNummerEingabePanel;
import hammerhilfe.panel.AngebotPanel;
import hammerhilfe.panel.SuchePanel;

public class MainWindow {
	
	private JFrame frame;
	private JTabbedPane tabs;
	private QueryThread queryThread;
	
	private SuchePanel suchePanel;
	private AngebotPanel angebotPanel;
	
	public MainWindow() {
		frame = new JFrame("Nachbarschaftshilfe");
		tabs = new JTabbedPane();

		suchePanel = new SuchePanel(this, ConnectionUtils.get("suche"));
		angebotPanel = new AngebotPanel(this, ConnectionUtils.get("angebot"));
		
		tabs.add(angebotPanel, "Ich Biete");
		tabs.add(suchePanel, "Ich Suche");
		
		tabs.add(new AngebotNummerEingabePanel(this), "Bestellnummer eingeben");
		
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
	
	public AngebotPanel getAngebotPanel() {
		return angebotPanel;
	}
	
	public SuchePanel getSuchePanel() {
		return suchePanel;
	}
	
}
