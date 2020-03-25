package hammerhilfe.panel;

import hammerhilfe.ConnectionUtils;
import hammerhilfe.MainWindow;
import hammerhilfe.neupanel.NeueBestellungWindow;

public class SuchePanel extends ListAndPreviewWindow{

	private static final long serialVersionUID = 1L;


	public SuchePanel(MainWindow mainWindow) {
		super(mainWindow, "Neue Bestellung", new NeueBestellungWindow());
		update();
	}


	public void update() {
		setListData(ConnectionUtils.get("suche"));
	}
}
