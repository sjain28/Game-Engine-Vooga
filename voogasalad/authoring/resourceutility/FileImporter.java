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
		provideToUI();
	}

	private void provideToUI() {
		if (fileToImport != null) {
			VoogaFileType type = ResourceDecipherer.decipherName(fileToImport.toString());
			String fileName = ResourceDecipherer.getExtension(fileToImport.toString(), '\\');
			ui.addItem(new VoogaFile(type, fileName));
		}
	}

}
