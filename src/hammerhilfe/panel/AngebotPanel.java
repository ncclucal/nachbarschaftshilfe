package hammerhilfe.panel;

import java.util.ArrayList;

import hammerhilfe.AngebotInfo;
import hammerhilfe.ConnectionUtils;
import hammerhilfe.MainWindow;
import hammerhilfe.neupanel.NeuesAngebotWindow;

public class AngebotPanel extends ListAndPreviewWindow{

	private static final long serialVersionUID = 1L;

	public AngebotPanel(MainWindow mainWindow) {
		super(mainWindow, "Neues Angebot", new NeuesAngebotWindow());
		update();
	}

	public void update() {
		setListData(ConnectionUtils.get("angebot"));
	}

}
