package hammerhilfe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.b100.swing.JGridPanel;

public class ListAndPreviewWindow extends JGridPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	
	private ArrayList<AngebotInfo> artikel;
	
	private JList<AngebotInfo> list;
	private JGridPanel previewPanel;
	
	private JTextField previewTitle;
	private JLabel previewDescription;
	private ImagePanel previewImage;
	
	public ListAndPreviewWindow(MainWindow mainWindow, ArrayList<AngebotInfo> artikel) {
		this.mainWindow = mainWindow;
		this.artikel = artikel;
		create();
	}
	
	private void create() {
		
		Dimension dim1 = new Dimension(256, 0);

		list = new JList<>(AngebotInfo.toArray(artikel));
		previewPanel = new JGridPanel(10);
		previewPanel.getGridBagConstraints().anchor = GridBagConstraints.NORTHWEST;
		
		previewTitle = new JTextField();
		previewDescription = new JLabel();
		previewImage = new ImagePanel(null);
		previewImage.setImageHeight(256);
		
		previewTitle.setEditable(false);
		
		previewPanel.add(previewTitle, 0, 0, 1, 1, 1, 0);
		previewPanel.add(previewDescription, 0, 1, 1, 1, 1, 0);
		previewPanel.add(previewImage, 0, 2, 1, 1, 1, 1);
		
		list.setPreferredSize(dim1);
		list.addListSelectionListener(this);
		
		add(list, 0, 0, 1, 1, 0, 1);
		add(previewPanel, 1, 0, 1, 1, 1, 1);
	}
	
	public void valueChanged(ListSelectionEvent e) {
//		AngebotInfo a = list.getSelectedValue();
//		
//		String str = ConnectionUtils.getWebpageContent("query_article_info.php?article="+a.getNummer());
//		
//		String[] arr = str.split("<br>");
//		previewTitle.setText(arr[0]);
//		previewDescription.setText(arr[1]);
		
		QueryThread queryThread = mainWindow.getQueryThread();
		
		queryThread.setListAndPreviewWindow(this);
		queryThread.setCurrentArticleId(list.getSelectedValue().getNummer());
		
		previewTitle.setText("Infos werden geladen...");
		previewDescription.setText("");
		
		if(queryThread.isSleeping())
			queryThread.interrupt();
	}
	
	public JTextField getPreviewTitle() {
		return previewTitle;
	}
	
	public JLabel getPreviewDescription() {
		return previewDescription;
	}
	
	public ImagePanel getPreviewImage() {
		return previewImage;
	}
	
}
