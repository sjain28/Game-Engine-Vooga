package authoring.resourceutility;
import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileImporter {

	private ResourceUI ui;
	private File fileToImport;
	
	public FileImporter(ResourceUI ui) {
		this.ui = ui;
		
		FileChooser chooser = new FileChooser();
		fileToImport = chooser.showOpenDialog(new Stage());
		
		System.out.println(fileToImport.toString());
		
		provideToUI();
	}
	
	private void provideToUI() {
		VoogaFileType type = ResourceDecipherer.decipherName(fileToImport.toString());
		String fileName = ResourceDecipherer.getExtension(fileToImport.toString(), '\\');
		ui.addItem(new VoogaFile(type, fileName));
	}
	
}
