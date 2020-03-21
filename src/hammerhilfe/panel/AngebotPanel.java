package hammerhilfe.panel;

import java.util.ArrayList;

import hammerhilfe.AngebotInfo;
import hammerhilfe.MainWindow;
import hammerhilfe.NeuesAngebotWindow;

public class AngebotPanel extends ListAndPreviewWindow{

	private static final long serialVersionUID = 1L;

	public AngebotPanel(MainWindow mainWindow, ArrayList<AngebotInfo> artikel) {
		super(mainWindow, artikel, "Neues Angebot", new NeuesAngebotWindow());
	}

}
