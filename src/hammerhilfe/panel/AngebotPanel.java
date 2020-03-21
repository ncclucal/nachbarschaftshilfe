package hammerhilfe.panel;

import java.util.ArrayList;

import hammerhilfe.AngebotInfo;
import hammerhilfe.MainWindow;

public class AngebotPanel extends ListAndPreviewWindow{

	private static final long serialVersionUID = 1L;

	public AngebotPanel(MainWindow mainWindow, ArrayList<AngebotInfo> artikel) {
		super(mainWindow, artikel);
	}

}
