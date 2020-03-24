package hammerhilfe.panel;

import java.util.ArrayList;

import hammerhilfe.AngebotInfo;
import hammerhilfe.MainWindow;
import hammerhilfe.neupanel.NeueBestellungWindow;

public class SuchePanel extends ListAndPreviewWindow{

	private static final long serialVersionUID = 1L;


	public SuchePanel(MainWindow mainWindow, ArrayList<AngebotInfo> artikel) {
		super(mainWindow, artikel, "Neue Bestellung", new NeueBestellungWindow());
	}
}
