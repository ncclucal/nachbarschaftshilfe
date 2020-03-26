package hammerhilfe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
		
		System.out.println("Trying to Connect to server...");
		if(!ConnectionUtils.canConnect(ConnectionUtils.ip)) {
			JOptionPane.showMessageDialog(null, "Verbindung konnte nicht hergestellt werden!");
			return;
		}
		System.out.println("Creating Window");
		
		File file = new File("token");
		if(file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				LoginInfo.token = br.readLine();
				br.close();
				new MainWindow();
			}catch (Exception e) {
				e.printStackTrace();
				new LoginWindow();
			}
		}else {
			new LoginWindow();
		}
	}

}
