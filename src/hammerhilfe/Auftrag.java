package hammerhilfe;

import java.util.UUID;

public class Auftrag {
	
	private long auftragsnummer;
	private String beschreibung;
	
	public static Auftrag neuerAuftrag(String beschreibung) {
		Auftrag auftrag = new Auftrag();
		
		auftrag.auftragsnummer = DatabaseHandler.getInstance().getInteger("select max(nh_auftrag.id) from nh_auftrag") + 1;
		auftrag.beschreibung = beschreibung;
		
		
		return auftrag;
	}
	
}
