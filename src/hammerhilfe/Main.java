package hammerhilfe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {

	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
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
	
	private MainWindow mainWindow;
	
	public Main() {
		instance = this;
		
		ConnectionUtils.init();
		
		System.out.println("Trying to Connect to server...");
		if(!ConnectionUtils.canConnect(ConnectionUtils.ip)) {
			JOptionPane.showMessageDialog(null, "Verbindung konnte nicht hergestellt werden!");
			return;
		}
		Library.load();
		
		System.out.println("Creating Window");
		
		File file = new File("token");
		if(file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String email = br.readLine();
				String token = br.readLine();
				br.close();
				
				LoginInfo.email = email;
				LoginInfo.token = token;
				
				String c = ConnectionUtils.getWebpageContent("query_token_valid.php?email="+email+"&token="+token);
				
				if(c == null) {
					throw new NullPointerException();
				}
				
				if(c.equals("VALID")) {
					mainWindow = new MainWindow();
				}else {
					JOptionPane.showMessageDialog(null, "Die gespeicherten Login-Daten sind nicht mehr aktuell oder ungültig!");
					new LoginWindow();
				}
			}catch (Exception e) {
				e.printStackTrace();
				new LoginWindow();
			}
		}else {
			new LoginWindow();
		}
	}
	
	public static void logout() {
		JFrame frame = instance.mainWindow.getFrame();
		frame.setVisible(false);
		frame.dispose();
		new File("token").delete();
		System.gc();
		new LoginWindow();
	}

}
