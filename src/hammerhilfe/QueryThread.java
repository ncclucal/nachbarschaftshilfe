package hammerhilfe;

import hammerhilfe.panel.ListAndPreviewWindow;

public class QueryThread extends Thread{
	
	private ListAndPreviewWindow listAndPreviewWindow;
	private AngebotInfo toQuery;
	private boolean sleeping = false;
	
	public QueryThread() {
		super("QueryThread");
	}
	
	public void run() {
		System.out.println("Query Thread Running!");
		
		while(true) {
			sleeping = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			sleeping = false;
			
			if(toQuery != null) {
				AngebotInfo a = toQuery;
				ListAndPreviewWindow l = listAndPreviewWindow;
				toQuery = null;
				listAndPreviewWindow = null;
				
				String str = ConnectionUtils.getWebpageContent("query_article_info.php?article="+a.getNummer());
				
				String[] arr = str.split("<br>");
				if(!(toQuery != null || listAndPreviewWindow != null)) {
					l.getPreviewTitle().setText(arr[0]);
					l.getPreviewDescription().setText(arr[1]);
					l.getPreviewImage().setImage(Library.getImageForString(arr[1]));
					l.getDeleteButton().setEnabled(LoginInfo.email.equals(arr[2]));
				}
			}
		}
	}
	
	public AngebotInfo getToQuery() {
		return toQuery;
	}
	
	public void setToQuery(AngebotInfo toQuery) {
		this.toQuery = toQuery;
	}
	
	public boolean isSleeping() {
		return sleeping;
	}
	
	public ListAndPreviewWindow getListAndPreviewWindow() {
		return listAndPreviewWindow;
	}
	
	public void setListAndPreviewWindow(ListAndPreviewWindow listAndPreviewWindow) {
		this.listAndPreviewWindow = listAndPreviewWindow;
	}

}
