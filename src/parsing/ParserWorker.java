package parsing;

public class ParserWorker extends Thread {
	
	private String url;

	public ParserWorker(String url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			Parser.parseAndSave(url);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
