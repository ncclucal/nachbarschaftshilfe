package hammerhilfe;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
//			throw new RuntimeException(new NullPointerException("Test"));
			
			new Main();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				new CrashReportWindow(e);
			}catch (Exception e2) {}
		}
	}
	
	public Main() {
		ConnectionUtils.init();
		
		if(!ConnectionUtils.canConnect(ConnectionUtils.ip)) {
			JOptionPane.showMessageDialog(null, "Verbindung konnte nicht hergestellt werden!");
			return;
		}
		
//		new LoginWindow();
		new MainWindow();
	}

}
