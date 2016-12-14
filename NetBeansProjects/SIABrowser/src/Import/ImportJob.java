package Import;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import Import.ImportRecord.EStatus;


public class ImportJob {
	ImportRecord importRecord;
	public ImportJob(ImportRecord ipRec) {
		importRecord = ipRec;
	}
	

	public ImportRecord run() {
		/*
		System.out.println("Running");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Complete");
		*/
		final String EXE_NAME = "siaarc.exe";
		ProcessBuilder processBuilder;
		BufferedReader br = null;
		String exePath = "C:\\Users\\cn012540\\Documents\\SimpleArchive-master\\Release\\" + EXE_NAME;
		String sourcePathOption = "--source-path=\"" + importRecord.getPath() + "\"";
		try {
			
			/*
			siaarc add --source-path="C:\Users\cn012540\Pictures\pics"
			
			ProcessBuilder processBuilder = new ProcessBuilder(arguments);
processBuilder.redirectOutput(Redirect.PIPE);
processBuilder.directory(new File(exePath));
process = processBuilder.start();

			
			String[] array = new String[] { "C:\\Users\\cn012540\\Documents\\SimpleArchive-master\\Release\\siaarc.exe",
					"--source-path=\"C:\\Users\\cn012540\\Pictures\\pics\"" };
					
			C:\\Users\\cn012540\\Documents\\SimpleArchive-master\\Release\\siaarc.exe --source-path="C:\Users\cn012540\Pictures\pics"
			*/
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add(exePath);
			arrayList.add("add");
			arrayList.add(sourcePathOption);
			List<String> params = arrayList;

			processBuilder = new ProcessBuilder(params);
			//processBuilder.directory(new File(exePath));
			Process process = processBuilder.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		String line;

//		System.out.printf("Output of running %s is:", Arrays.toString(args));

		try {
			while ((line = br.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		importRecord.setStatus(EStatus.Complete);
		return importRecord;
	}
		
}
