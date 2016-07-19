package siaclient;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class ImportAddScene extends IASceneBase {

	private final FileChooser fileDialog = new FileChooser();
	
			
	ImportAddScene() {
		super("Import-add", "Import Add");
	}
	
	@Override
	void Init() {
		// TODO Auto-generated method stub
		BorderPane topBoxMain = addTopBox(menuBar, this.getTitle());
		borderPane1.setTop(topBoxMain);
		sceneMain.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		// fileDialog.getExtensionFilters().add(new ExtensionFilter("HTML Files", "*.htm", "*.html"));
		
		
		
		
		Button imagesBtn = new Button("_Add Images");
		imagesBtn.setOnAction(e -> openFile());
		
		Button foldersBtn = new Button("_Add Folders");
		foldersBtn.setOnAction(e -> DirectoryChooser());
		
		StackPane centrePane = new StackPane(imagesBtn, foldersBtn);
		centrePane.setPadding(new Insets(15, 15, 15, 15));
		borderPane1.setCenter(centrePane);
	}

	@Override
	void loadScenes() {
		// TODO Auto-generated method stub
		
	}
	
	private void openFile() {
		
		fileDialog.setTitle("Open Resume");
		File file = fileDialog.showOpenDialog(thestage);
		if (file == null) {
			return;
		}
		
		/*
		try {
			// Read the file and populate the HTMLEditor		
			byte[] resume = Files.readAllBytes(file.toPath());
			resumeEditor.setHtmlText(new String(resume));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public void DirectoryChooser() {
		final DirectoryChooser directoryChooser =
                new DirectoryChooser();
            final File selectedDirectory =
                    directoryChooser.showDialog(thestage);
            if (selectedDirectory != null) {
                selectedDirectory.getAbsolutePath();
            }
	}

}
