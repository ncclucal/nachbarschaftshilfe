package hammerhilfe;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow {
	
	private JFrame frame;
	private JTabbedPane tabs;
	
	public MainWindow() {
		frame = new JFrame("Nachbarschaftshilfe");
		tabs = new JTabbedPane();

		Artikel artikel1 = new Artikel(ArtikelTyp.ANGEBOT, "Artikel1", "Bla bla bla");
		Artikel artikel2 = new Artikel(ArtikelTyp.ANGEBOT, "Artikel2", "mehr Bla bla bla");
		
		artikel1.setImage(Utils.loadImage("img/eier.png"));
		ArrayList<Artikel> list = new ArrayList<Artikel>();

		list.add(artikel1);
		list.add(artikel2);
		
		tabs.add(new ListAndPreviewWindow(ConnectionUtils.get("angebot")), "Ich Biete");
		tabs.add(new ListAndPreviewWindow(ConnectionUtils.get("suche")), "Ich Suche");
		tabs.add(new JPanel(), "Bestellnummer eingeben");
		
		frame.add(tabs);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
