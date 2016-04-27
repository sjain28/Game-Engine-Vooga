package tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import authoring.tagextension.GameTagManager;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class VoogaFileChooser {
	
	private static final String USER_RESOURCES_PATH = "user_resources/";
	private static final String LOCAL_PATH = "voogasalad_DoovalSalad";
	private GameTagManager tagManager;
	private FileChooser fileChooser;

	public VoogaFileChooser() {
		tagManager = new GameTagManager();
		fileChooser = new FileChooser();
	}

	public String launch() throws VoogaException {
		String path = "";
		File file = fileChooser.showOpenDialog(null);
		if (isLocal(file)) {
			path = file.getPath().split(LOCAL_PATH)[1];
			path = path.substring(1);
		} else {
			path = moveToLocalPath(file);
		}
		tagManager.addTagsFromImage(path);
		return path;
	}

	public void setInitialDirectory(File file) {
		fileChooser.setInitialDirectory(file);
	}

	public void addFilters(ExtensionFilter... filters) {
		fileChooser.getExtensionFilters().addAll(filters);
	}

	private boolean isLocal(File file) {
		return file.getPath().contains(LOCAL_PATH);
	}

	private String moveToLocalPath(File file) throws VoogaException {
		String path = USER_RESOURCES_PATH + file.getName();
		File newLocation = new File(path);

		if (newLocation.exists()) {
			throw new VoogaException("This filename already exits");
		}

		try {
			Files.copy(file.toPath(), newLocation.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e) {
			throw new VoogaException("Could not import file");
		}

		return path;
	}

}
