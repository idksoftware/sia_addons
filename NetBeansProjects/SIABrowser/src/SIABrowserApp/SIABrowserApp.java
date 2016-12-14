package SIABrowserApp;


import idk.config.ConfigInfo;
import idk.config.ConfigReader;


import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.log4j.Log4JLogger;
//import idk.imgarchive.base.workspacemanager.Workspace;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class SIABrowserApp  extends Application {
	 static int error = 0;
    static int port = -1;
    static boolean silent = false;
    
    private Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/root.png")));
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("classpath is: " + System.getProperty("java.class.path"));
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        int c = 0;
    	Getopt opts = new Getopt(args, "p");
    	while ((c = opts.getopt()) != -1)
		{
			 switch(c)
			 {
			 case 'p':
				 if (opts.getOption().equalsIgnoreCase("port") == true) {	
					 try {
						 port = Integer.parseInt(opts.getOptarg());
					 } catch (NumberFormatException e) {
						 consoleOut("Invalid port number \"" + opts.getOptarg() + "\"");
						 return;
					 }
				 }
				 break;
			
			 case 's': //Silent
				 silent = true;
				 break;
			 }
		}
    	consoleOut(bannerString + "\r\n");
    	String homePath = System.getenv(ConfigInfo.SIA_HOME);
    	
        if (homePath == null) {
        		// If null then use the default place for the host platform
        		// Also the IMGARCHIVE_USERHOME will also be defaulted if this is not found.
        		OSValidator osValidator = new OSValidator();
        		homePath = osValidator.defaultApplicationPath() + File.separator + "imgarchive";
        		if ((new File(homePath)).exists() == false) {
        			Log.fatal("Can not read enviroment variable \"IMGARCHIVE_HOME\", check configuration: using \"C:\"");
        			consoleOut("Can not read enviroment variable \"IMGARCHIVE_HOME\", check configuration: using \"C:\"");
        			return;
        		}
        }
       
        if (ConfigReader.read(homePath) == false) {
        	return;
        }
        final String configPath = System.getenv(ConfigInfo.SIA_HOME) + File.separator + "config";
		final String logsPath = System.getenv(ConfigInfo.SIA_HOME) + File.separator + "logs";
		if (ConfigReader.read(homePath) == false) {
			try {
				Log4JLogger.createLogger(configPath, logsPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.fatal("No configuration found");
			//errorMessage = ConfigReader.getErrorMessage();
			//return exitCodes = ExitCodes.ErrorReadingConfigFile;
			return;
		}
		
		final String Path = ConfigInfo.getConfigPath();
		try {
			Log4JLogger.createLogger(ConfigInfo.getConfigPath(), ConfigInfo.getLogPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		launch(args);
		/*
        try {    // Try to get LOCK //    
        	if (!AppLock.setLock("SIALock")) {
        		throw new RuntimeException("Image Archive not started. Only one application instance may run at the same time!");
        	}     
        
        	
		    

			
			//KeywordSet keywordSet = KeywordReader.read("C:\\Users\\cn012540\\Documents\\SIA Workspace\\keywords.xml");
			//keywordSet.print();
		
        	launch(args);
	 		
	 	}
        finally
        {    
        	AppLock.releaseLock(); // Release lock
        }
        */
        // This closes all threads.
        Runtime.getRuntime().exit(0);
    }
    
    static void consoleOut(String msg) {
    	if (silent == true) {
    		System.out.println(msg);
    	}
    }
    
    static void consoleOut(String msg, Exception e) {
    	if (silent == true) {
    		System.out.println(msg + " Exception: " + e.getMessage());
    	}
    }
    
    
	
    static String bannerString = "Welcome to the ImgArchive default setup.\n" + "Software version: 0.9.1 community\n"
    		+ "Copyright (c) 2010, 2012, IDK Software Ltd. All rights reserved.\n"
    		+ "This software comes with ABSOLUTELY NO WARRANTY.\n" + "This is free software for personal use.";

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Tree View Sample");        
        
        MainPane root = new MainPane(primaryStage, true);       
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
		
	}
    		
}


