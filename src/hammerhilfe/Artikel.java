package hammerhilfe;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Artikel {
	
	private ArtikelTyp typ;
	private String title;
	private String description;
	private BufferedImage image;
	
	public Artikel(ArtikelTyp typ, String title, String description) {
		this.typ = typ;
		this.title = title;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTyp(ArtikelTyp typ) {
		this.typ = typ;
	}
	
	public String toString() {
		return title;
	}
	
	////////////////////////////////////////////////
	/////////////////////STATIC/////////////////////
	////////////////////////////////////////////////
	
	public static Artikel[] toArray(ArrayList<Artikel> list) {
		Artikel[] arr = new Artikel[list.size()];
		
		int i=0;
		while(i < list.size()) {
			arr[i] = list.get(i);
			i++;
		}
		
		return arr;
	}
	
	public static ArrayList<Artikel> toList(Artikel[] arr){
		ArrayList<Artikel> list = new ArrayList<Artikel>();
		
		for(Artikel artikel : arr) {
			list.add(artikel);
		}
		
		return list;
	}
	
}
