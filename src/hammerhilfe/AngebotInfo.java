package hammerhilfe;

import java.util.ArrayList;

public class AngebotInfo {
	
	private String title;
	private String nummer;
	
	public AngebotInfo(String title, String nummer) {
		this.title = title;
		this.nummer = nummer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getNummer() {
		return nummer;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}
	
	public String toString() {
		return getTitle();
	}
	
	////////////////////////////////////////////////
	/////////////////////STATIC/////////////////////
	////////////////////////////////////////////////
	
	public static AngebotInfo[] toArray(ArrayList<AngebotInfo> list) {
		AngebotInfo[] arr = new AngebotInfo[list.size()];
		
		int i=0;
		while(i < list.size()) {
			arr[i] = list.get(i);
			i++;
		}
		
		return arr;
	}
	
	public static ArrayList<AngebotInfo> toList(AngebotInfo[] arr){
		ArrayList<AngebotInfo> list = new ArrayList<AngebotInfo>();
		
		for(AngebotInfo artikel : arr) {
			list.add(artikel);
		}
		
		return list;
	}
	
}
