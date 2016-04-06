package authoring.resourceutility;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the view for the resource explorer, using a TreeView to display
 * folders, images, and audio
 * @author DoovalSalad
 *
 */
public class ResourceTreeView extends TreeView<VoogaFile> {

	/**
	 * Private instance variables
	 */
	private TreeItem<VoogaFile> root;
	private static final String LOCATION = "file:resources/";
	private static final String EXTENSION = ".png";
	
	/**
	 * The constructor that initializes the root of the project, and establishes
	 * the cell factory for TreeCell objects
	 * @param projectRoot
	 */
	public ResourceTreeView(VoogaFile projectRoot) {		
		root = new TreeItem<VoogaFile>(projectRoot, makeImage(projectRoot.getType()));
		root.setExpanded(true);
		this.setEditable(true);
		this.setRoot(root);
		
		this.setCellFactory(callback -> {
			return new TextFieldTreeCellImpl();
		});
		
	}
	
	/**
	 * Adds a VoogaFile to the resource explorer, and nests files within folders if
	 * they are selected appropriately.
	 * @param file
	 */
	void addItem(VoogaFile file) {
		TreeItem<VoogaFile> parentDirectory = this.getSelectionModel().getSelectedItem();
		TreeItem<VoogaFile> childItem = new TreeItem<VoogaFile>(file, makeImage(file.getType()));
		if(parentDirectory != null) {
			VoogaFile parent = parentDirectory.getValue();
			if(parent.getType() == VoogaFileType.FOLDER) {
				parentDirectory.getChildren().add(childItem);
				parentDirectory.setExpanded(true);
			} else {
				root.getChildren().add(childItem);
			}
			return;
		}		
		root.getChildren().add(childItem);
	}
	
	/**
	 * Returns an image view based on the type of file to add. In other words, adds
	 * the thumbnail for folder, image, and audio files.
	 * @param type
	 * @return
	 */
	private ImageView makeImage(VoogaFileType type) {
		return new ImageView(new Image(LOCATION	 + type.name() + EXTENSION));
	}

	/**
	 * Gets all the names of a particular file type, to avoid importing or creating
	 * items that already exist.
	 * @param fileType
	 * @return
	 */
	List<String> getFileNamesOfType(VoogaFileType fileType) {
		TreeIterator<VoogaFile> iterator = new TreeIterator<VoogaFile>(root);
		List<String> matchingItems = new ArrayList<String>();
		while(iterator.hasNext()) {
		    TreeItem<VoogaFile> item = iterator.next();
		    VoogaFile file = item.getValue();
		    if(file.getType() == fileType) {
		    	matchingItems.add(file.toString());
		    }
		}
		return matchingItems;
	}
	
}
