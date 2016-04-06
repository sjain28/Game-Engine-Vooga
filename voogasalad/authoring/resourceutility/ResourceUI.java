package authoring.resourceutility;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The class initialized by users of this utility. It is a VBox containing the
 * TreeView structure of folders and files, with buttons to create new folders
 * and import new files.
 * @author DoovalSalad
 *
 */
public class ResourceUI extends VBox {
	
	/**
	 * Private instance variables
	 */
	private HBox buttonContainer;
	private ResourceTreeView rtv;
	
	/**
	 * Constants
	 */
	private static final String DEFAULT_PROJECT_NAME = "My Project";
	private static final String ADD_FOLDER_PROMPT = "Add Folder";
	private static final String IMPORT_FILE_PROMPT = "Import File";
	private static final double SPACING = 10;

	/**
	 * The constructor, which initializes the nodes that make up the entire UI.
	 */
	public ResourceUI() {
		buttonContainer = new HBox(SPACING);
		
		rtv = new ResourceTreeView(new VoogaFile(VoogaFileType.FOLDER, DEFAULT_PROJECT_NAME));
	
		this.getChildren().addAll(rtv, buttonContainer);
		
		makeAddButtons();
		
	}
	
	/**
	 * Makes buttons to add folders and import files.
	 */
	private void makeAddButtons() {
		ButtonMaker maker = new ButtonMaker();
		this.buttonContainer.getChildren().addAll(maker.makeButton(ADD_FOLDER_PROMPT, e -> promptFolderName()),
												  maker.makeButton(IMPORT_FILE_PROMPT, e -> importFile()));
		
	}
	
	/**
	 * Opens up the NamePrompter for adding folders.
	 */
	private void promptFolderName() {
		new NamePrompter(rtv);
	}
	
	/**
	 * Opens the FileImporter for importing files.
	 */
	private void importFile() {
		new FileImporter(rtv);
	}

}
