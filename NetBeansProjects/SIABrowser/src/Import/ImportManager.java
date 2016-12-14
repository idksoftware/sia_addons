package Import;

public class ImportManager {
	ImportController importController = new ImportController();
	private Thread jobQueueThread = new Thread(importController);
	UDPListener udpListener = new UDPListener();
	private Thread udpListenerThread = new Thread(udpListener);
	
	private boolean running = false;
	
	public ImportManager() {
		
	}
	
	void initialise() {
		jobQueueThread.start();
		udpListenerThread.start();
	}
	
	void dispose() {
		jobQueueThread.interrupt();
		try {
			jobQueueThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		udpListenerThread.interrupt();
		try {
			udpListenerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	void start() {
		importController.start();
		udpListener.start();
	}
	void stop() {
		importController.stop();
		udpListener.start();
	}
	
	void pause() {
		importController.pause();
		udpListener.start();
	}
	
	
	
	void add(ImportRecord ir) {
		importController.add(ir);
	}
	
	ImportRecord get(int id) {
		return null;
	}
	
	String getStatus(int id) {
		return null;
	}
	
	
}
