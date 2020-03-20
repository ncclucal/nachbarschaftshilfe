package hammerhilfe;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.b100.swing.JGridPanel;

public class ListAndPreviewWindow extends JGridPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Artikel> artikel;
	
	private JList<Artikel> list;
	private JGridPanel previewPanel;
	
	private JTextField previewTitle;
	private JTextArea previewDescription;
	
	public ListAndPreviewWindow(ArrayList<Artikel> artikel) {
		this.artikel = artikel;
		create();
	}
	
	private void create() {
		
		Dimension dim1 = new Dimension(256, 0);

		list = new JList<>(Artikel.toArray(artikel));
		previewPanel = new JGridPanel(10);
		previewTitle = new JTextField();
		previewDescription = new JTextArea();
		
		previewTitle.setEditable(false);
		previewDescription.setEditable(false);
		previewDescription.setBorder(previewTitle.getBorder());
		
		previewPanel.add(previewTitle, 0, 0, 1, 1, 1, 0);
		previewPanel.add(previewDescription, 0, 1, 1, 1, 1, 1);
		
		list.setPreferredSize(dim1);
		list.addListSelectionListener(this);
		
		add(list, 0, 0, 1, 1, 0, 1);
		add(previewPanel, 1, 0, 1, 1, 1, 1);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		Artikel a = list.getSelectedValue();
		
		previewTitle.setText(a.getTitle());
		previewDescription.setText(a.getDescription());
	}
	
}
