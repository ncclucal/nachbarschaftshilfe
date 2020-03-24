package hammerhilfe.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hammerhilfe.AngebotInfo;
import hammerhilfe.ConnectionUtils;
import hammerhilfe.MainWindow;

public class AngebotNummerEingabePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private JTextField angebotNummerField;
	private JButton button;
	
	public AngebotNummerEingabePanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setLayout(new FlowLayout());
		
		angebotNummerField = new JTextField(20);
		button = new JButton("Suche!");
		button.addActionListener(this);
		
		add(angebotNummerField);
		add(button);
	}

	public void actionPerformed(ActionEvent e) {
		String str = ConnectionUtils.getWebpageContent("query_article_info.php?article="+angebotNummerField.getText());
		
		if(str.startsWith("ERROR:")) {
			JOptionPane.showMessageDialog(mainWindow.getFrame(), str.substring("ERROR:".length()));
			return;
		}
		
		String[] arr = str.split("<br>");

		String typ = arr[4];
		
		ListAndPreviewWindow listAndPreviewWindow = null;
		
		if(typ.equalsIgnoreCase("suche")) {
			listAndPreviewWindow = mainWindow.getSuchePanel();
			mainWindow.getTabs().setSelectedIndex(1);
		}else if(typ.equalsIgnoreCase("angebot")) {
			listAndPreviewWindow = mainWindow.getAngebotPanel();
			mainWindow.getTabs().setSelectedIndex(0);
		}else {
			System.out.println("Ungültiger Typ");
		}
		
		if(listAndPreviewWindow != null) {
			ArrayList<AngebotInfo> artikels = listAndPreviewWindow.getArtikel();
			
			int v=0;
			int i=0;
			while(i < artikels.size()) {
				if(artikels.get(i).getNummer().equals(angebotNummerField.getText())) {
					v = i;
				}
				i++;
			}
			
			System.out.println(v);
			listAndPreviewWindow.getList().setSelectedIndex(v);
			
			mainWindow.getFrame().repaint();
		}
	}

}
