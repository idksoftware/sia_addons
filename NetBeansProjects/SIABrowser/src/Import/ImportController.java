package Import;

public class ImportController implements Runnable {
	
	static final int IDLE_TIME = 1000;

	private ImportJobQueue importJobQueue = new ImportJobQueue();
	
	
	private boolean running = false;
	
	public ImportController() {
		
	}
	
	void start() {
		running = true;
	}
	void stop() {
		running = false;
	}
	
	void pause() {
		running = false;
	}
	
	boolean add(ImportRecord ir) {
		importJobQueue.add(ir);
		return true;
	}
	@Override
	public void run() {
		while (true) {
			if (running) {
				if (importJobQueue.isEmpty() == true) {
					try {
						Thread.sleep(IDLE_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					
				}
			} else {
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	ImportRecord get(int id) {
		return null;
	}
	
	String getStatus(int id) {
		return null;
	}
}

	