package Import;

public class ImportManagerTest {

	public static void main(String[] args) {
		ImportManager importManager = new ImportManager();
		
		ImportRecord importRecord1 = new ImportRecord(1, "C:\\Users\\cn012540\\Pictures\\pics", 0);
		ImportRecord importRecord2 = new ImportRecord(1, "C:\\Users\\cn012540\\Pictures\\pics", 0);
		ImportRecord importRecord3 = new ImportRecord(1, "C:\\Users\\cn012540\\Pictures\\pics", 0);
		ImportRecord importRecord4 = new ImportRecord(1, "C:\\Users\\cn012540\\Pictures\\pics", 0);
		ImportRecord importRecord5 = new ImportRecord(1, "C:\\Users\\cn012540\\Pictures\\pics", 0);
		
		importManager.add(importRecord1);
		importManager.add(importRecord2);
		importManager.add(importRecord3);
		importManager.add(importRecord4);
		importManager.add(importRecord5);
		
		importManager.initialise();
		importManager.start();
		
		ImportJob importJob = new ImportJob(importRecord1);
		ImportRecord importRecord = importJob.run();
		System.out.println("Job status: " + importRecord.getStatus());
		
	}

}
