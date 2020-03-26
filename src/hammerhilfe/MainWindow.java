package hammerhilfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.b100.swing.JGridPanel;
import hammerhilfe.panel.AngebotNummerEingabePanel;
import hammerhilfe.panel.AngebotPanel;
import hammerhilfe.panel.SuchePanel;

public class MainWindow implements ActionListener{
	
	private JFrame frame;
	private JGridPanel panel;
	private JTabbedPane tabs;
	private QueryThread queryThread;
	private JMenuBar menuBar;
	private JMenu accountMenu;
	private JMenuItem logoutMenuitem;
	
	private SuchePanel suchePanel;
	private AngebotPanel angebotPanel;
	
	public MainWindow() {
		frame = new JFrame("Nachbarschaftshilfe");
		tabs = new JTabbedPane();
		panel = new JGridPanel(0);

		suchePanel = new SuchePanel(this);
		angebotPanel = new AngebotPanel(this);
		
		tabs.add(angebotPanel, "Ich Biete");
		tabs.add(suchePanel, "Ich Suche");
		
		tabs.add(new AngebotNummerEingabePanel(this), "Bestellnummer eingeben");
		
		menuBar = new JMenuBar();
		accountMenu = new JMenu("Konto");
		logoutMenuitem = new JMenuItem("Logout");
		logoutMenuitem.addActionListener(this);
		menuBar.add(accountMenu);
		accountMenu.add(logoutMenuitem);
		
		panel.add(menuBar, 0, 0, 1, 1, 1, 0);
		panel.add(tabs, 0, 1, 1, 1, 1, 1);
		
		frame.add(panel);
		
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

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logoutMenuitem) {
			Main.logout();
		}
	}
	
}
