package hammerhilfe;

public class DatabaseHandler {
	
	private static DatabaseHandler instance = new DatabaseHandler();
	
	public static DatabaseHandler getInstance() {
		return instance;
	}
	
	public int getInteger(String query) {
		//TODO
		return 0;
	}

}
