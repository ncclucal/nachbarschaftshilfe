package hammerhilfe.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.b100.swing.JGridPanel;
import hammerhilfe.AngebotInfo;
import hammerhilfe.ConnectionUtils;
import hammerhilfe.ImagePanel;
import hammerhilfe.LoginInfo;
import hammerhilfe.MainWindow;
import hammerhilfe.QueryThread;
import hammerhilfe.Utils;
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
	
	private int selected = -1;
	
	private JButton deleteButton;
	
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
		deleteButton = new JButton("Löschen");
		deleteButton.addActionListener(this);
		
		previewTitle.setEditable(false);
		
		previewPanel.add(previewTitle, 0, 0, 1, 1, 1, 0);
		previewPanel.add(previewDescription, 0, 1, 1, 1, 1, 0);
		previewPanel.add(previewImage, 0, 2, 1, 1, 1, 1);
		previewPanel.add(deleteButton, 1, 0, 1, 1, 0, 0);
		
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
		if(selected != list.getSelectedIndex()) {
			selected = list.getSelectedIndex();
			
			AngebotInfo a = list.getSelectedValue();
			
			previewTitle.setText("Informationen werden geladen...");
			previewDescription.setText("");
			
			QueryThread queryThread = mainWindow.getQueryThread();
			queryThread.setToQuery(a);
			queryThread.setListAndPreviewWindow(this);
			
			queryThread.interrupt();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createButton) {
			createWindow.create(mainWindow.getFrame());
		}
		if(e.getSource() == deleteButton ) {
			String email = LoginInfo.email;
			String token = LoginInfo.token;
			String artikelNummer = list.getSelectedValue().getNummer();

			email = Utils.toURL(email);
			token = Utils.toURL(token);
			artikelNummer = Utils.toURL(artikelNummer);
			
			String s = ConnectionUtils.getWebpageContent("delete_article.php?email="+email+"&token="+token+"&article="+artikelNummer);
			
			if(s.startsWith("ERROR")) {
				JOptionPane.showMessageDialog(mainWindow.getFrame(), "Fehler");
			}else {
				update();
			}
		}
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
	
	public NeuWindow getCreateWindow() {
		return createWindow;
	}
	
	public JButton getDeleteButton() {
		return deleteButton;
	}
	
}
