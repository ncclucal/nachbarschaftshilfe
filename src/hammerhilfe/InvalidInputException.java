package hammerhilfe;

public class InvalidInputException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String reason = null;
	private String string = null;
	private int position = -1;
	
	public InvalidInputException() {
		
	}
	
	public InvalidInputException(String reason) {
		this.reason = reason;
	}
	
	public InvalidInputException(String string, int position) {
		this.string = string;
		this.position = position;
	}
	
	public String getReason() {
		return reason;
	}
	
	public String getString() {
		return string;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean hasReason() {
		return reason != null;
	}
	
	public boolean hasString() {
		return string != null;
	}
	
	public boolean hasPosition() {
		return position != -1;
	}

}
