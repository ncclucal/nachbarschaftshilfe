package hammerhilfe;

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Main();
	}
	
	public Main() {
		ConnectionUtils.init();
		
		new LoginWindow();
	}

}