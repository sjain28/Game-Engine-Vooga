package tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import authoring.tagextension.GameTagManager;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import resources.VoogaBundles;

/**
 * Obtaining, selecting or modifying file path locally or globally
 */
public class VoogaFileChooser {

	// TODO add to path resource bundle
	private static final String USER_RESOURCES_PATH = "user_resources/";
	private static final String LOCAL_PATH = "voogasalad_DoovalSalad";
	private GameTagManager tagManager;
	private FileChooser fileChooser;

	public VoogaFileChooser() {
		tagManager = new GameTagManager();
		fileChooser = new FileChooser();
	}

	/**
	 * Open a file from some path and set to local
	 * 
	 * @return
	 * @throws VoogaException
	 */
	public String launch() throws VoogaException {
		String path;
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

	/**
	 * Add extension filters to the file selector
	 * @param filters
	 */
	public void addFilters(ExtensionFilter... filters) {
		fileChooser.getExtensionFilters().addAll(filters);
	}

	/**
	 * Return whether a file path is local
	 * 
	 * @param file
	 * @return
	 */
	private boolean isLocal(File file) {
		return file.getPath().contains(LOCAL_PATH);
	}

	/**
	 * Modify an object to new local path and return the path
	 * 
	 * @param file
	 * @return
	 * @throws VoogaException
	 */
	private String moveToLocalPath(File file) throws VoogaException {
		String path = USER_RESOURCES_PATH + file.getName();
		File newLocation = new File(path);

		if (newLocation.exists()) {
			throw new VoogaException(
					VoogaBundles.exceptionProperties.getString("FileExists"));
		}

		try {
			Files.copy(file.toPath(), newLocation.toPath(),
					StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e) {
			throw new VoogaException(
					VoogaBundles.exceptionProperties.getString("CantImport"));
		}

		return path;
	}

	/**
	 * Getters and setters below
	 * 
	 * @param file
	 */
	public void setInitialDirectory(File file) {
		fileChooser.setInitialDirectory(file);
	}
}
