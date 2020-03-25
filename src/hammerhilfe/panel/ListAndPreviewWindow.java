package hammerhilfe.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.b100.swing.JGridPanel;
import hammerhilfe.AngebotInfo;
import hammerhilfe.ImagePanel;
import hammerhilfe.MainWindow;
import hammerhilfe.QueryThread;
import hammerhilfe.neupanel.NeuWindow;

public abstract class ListAndPreviewWindow extends JGridPanel implements ListSelectionListener, ActionListener{
	
	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	
	private ArrayList<AngebotInfo> artikel;
	
	private JList<AngebotInfo> list;
	private JGridPanel previewPanel;
	
	private JTextField previewTitle;
	private JLabel previewDescription;
	private ImagePanel previewImage;
	
	private String createButtonText;
	private JButton createButton;
	private NeuWindow createWindow;
	
	public ListAndPreviewWindow(MainWindow mainWindow, String createButtonText, NeuWindow createWindow) {
		this.mainWindow = mainWindow;
		this.createButtonText = createButtonText;
		this.createWindow = createWindow;
		create();
	}
	
	private void create() {
		
		Dimension dim1 = new Dimension(256, 0);

		list = new JList<>();
		previewPanel = new JGridPanel(10);
		previewPanel.getGridBagConstraints().anchor = GridBagConstraints.NORTHWEST;
		previewTitle = new JTextField();
		previewDescription = new JLabel();
		previewImage = new ImagePanel(null);
		previewImage.setImageHeight(256);
		createButton = new JButton(createButtonText);
		createButton.addActionListener(this);
		
		previewTitle.setEditable(false);
		
		previewPanel.add(previewTitle, 0, 0, 1, 1, 1, 0);
		previewPanel.add(previewDescription, 0, 1, 1, 1, 1, 0);
		previewPanel.add(previewImage, 0, 2, 1, 1, 1, 1);
		
		list.setPreferredSize(dim1);
		list.addListSelectionListener(this);
		
		add(list, 0, 0, 1, 1, 0, 1);
		add(previewPanel, 1, 0, 1, 2, 1, 1);
		add(createButton, 0, 1, 1, 1, 0, 0);
	}
	
	public abstract void update();
	
	public void setListData(ArrayList<AngebotInfo> list) {
		AngebotInfo[] arr = AngebotInfo.toArray(list);
		
		this.list.setListData(arr);
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
	
	public void actionPerformed(ActionEvent e) {
		createWindow.create(mainWindow.getFrame());
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
	
	public JList<AngebotInfo> getList() {
		return list;
	}
	
	public ArrayList<AngebotInfo> getArtikel() {
		return artikel;
	}
	
}
