package hammerhilfe;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseHandler {
	
	private static DatabaseHandler instance = null;
	
	public static DatabaseHandler getInstance() {
		return instance;
	}
	
	public static void connect() {
		try {
			Connection connection = (Connection) DriverManager.getConnection("jdbc://bk-medienberufe.de/ita54_ilju","ita54_ilju","ABsuJ03a");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Connection connection;
	private Statement statement;
	
	public DatabaseHandler(Connection connection) {
		this.connection = connection;
		try {
			this.statement = (Statement) connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getInteger(String query) {
		try {
			ResultSet rs = statement.executeQuery(query);
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
