package hammerhilfe;

public class User {
	
	private long id;
	private String name;
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
