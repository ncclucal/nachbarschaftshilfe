package hammerhilfe;

public class QueryThread extends Thread{
	
	private String currentArticleId;
	private ListAndPreviewWindow listAndPreviewWindow;
	private boolean sleeping = false;
	
	public QueryThread() {
		super("QueryThread");
	}
	
	public void run() {
		System.out.println("Query Thread Running!");
		
		while(true) {
			try {
				sleeping = true;
				Thread.sleep(10000);
			}catch (Exception e) {
			}
			sleeping = false;
			
			tryQuery();
		}
	}
	
	private void tryQuery() {
		boolean a = query();
		if(!a) {
			tryQuery();
		}
	}
	
	private boolean query() {
		try {
			String queriedArticle = currentArticleId;
			String str = ConnectionUtils.getWebpageContent("query_article_info.php?article="+queriedArticle);
			
			String[] arr = str.split("<br>");
			
			//Wenn ein anderer Artikel zum Abfragen eingetragen wurde, werden
			//die Informationen für diesen Artikel nicht eingetragen und es
			//werden die Informationen für den eingetragenen Artikel abgefragt
			if(currentArticleId.equals(queriedArticle)) {
				listAndPreviewWindow.getPreviewTitle().setText(arr[0]);
				listAndPreviewWindow.getPreviewDescription().setText(arr[2]);
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getCurrentArticleId() {
		return currentArticleId;
	}
	
	public void setCurrentArticleId(String currentArticleId) {
		this.currentArticleId = currentArticleId;
	}
	
	public ListAndPreviewWindow getListAndPreviewWindow() {
		return listAndPreviewWindow;
	}
	
	public void setListAndPreviewWindow(ListAndPreviewWindow listAndPreviewWindow) {
		this.listAndPreviewWindow = listAndPreviewWindow;
	}
	
	public boolean isSleeping() {
		return sleeping;
	}

}
