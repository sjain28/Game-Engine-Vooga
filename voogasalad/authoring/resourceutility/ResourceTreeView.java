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
		root = new TreeItem<VoogaFile>(projectRoot, makeImage(projectRoot));
		root.setExpanded(true);
		this.setEditable(true);
		this.setRoot(root);
		
		this.setCellFactory(callback -> {
			return new TextFieldTreeCellImpl(this);
		});
		
	}
	
	/**
	 * Adds a VoogaFile to the resource explorer, and nests files within folders if
	 * they are selected appropriately.
	 * @param file
	 */
	public void addItem(VoogaFile file) {
		TreeItem<VoogaFile> parentDirectory = this.getSelectionModel().getSelectedItem();
		add(file, parentDirectory);
	}
	
	public void addItemToFolder(VoogaFile file, VoogaFile folder) {
		TreeItem<VoogaFile> parentDirectory = findTreeItemFromFileByRoot(folder, root);
		add(file, parentDirectory);
	}
	
	private void add(VoogaFile child, TreeItem<VoogaFile> parentDirectory) {
		TreeItem<VoogaFile> childItem = new TreeItem<VoogaFile>(child, makeImage(child));
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
	
	
	public TreeItem<VoogaFile> findTreeItemFromFileByRoot(VoogaFile file, TreeItem<VoogaFile> root) {
		TreeIterator<VoogaFile> iterator = new TreeIterator<VoogaFile>(root);
		TreeItem<VoogaFile> fileItem = null;
		while(iterator.hasNext()) {
		    TreeItem<VoogaFile> iterItem = iterator.next();
		    VoogaFile iterFile = iterItem.getValue();
		    if(iterFile.toString().equals(file.toString())) {
		    	fileItem = iterItem;
		    	break;
		    }
		}
		return fileItem;
	}
	
	public void reorder(VoogaFile child, VoogaFile newParent) {
		TreeItem<VoogaFile> childItem = findTreeItemFromFileByRoot(child, root);
		TreeItem<VoogaFile> newParentItem = findTreeItemFromFileByRoot(newParent, root);
		if(findTreeItemFromFileByRoot(newParent, childItem) == null) {
			childItem.getParent().getChildren().remove(childItem);
			newParentItem.getChildren().add(childItem);
		}
	}
	
	/**
	 * Returns an image view based on the type of file to add. In other words, adds
	 * the thumbnail for folder, image, and audio files.
	 * @param type
	 * @return
	 */
	private ImageView makeImage(VoogaFile file) {
		return new ImageView(new Image(LOCATION	 + file.getType().name() + EXTENSION));
	}

	/**
	 * Gets all the names of a particular file type, to avoid importing or creating
	 * items that already exist.
	 * @param fileType
	 * @return
	 */
	public List<String> getFileNamesOfType(VoogaFileType fileType) {
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

	public void remove(TreeItem<VoogaFile> treeItem) {
		TreeIterator<VoogaFile> iterator = new TreeIterator<VoogaFile>(root);
		while(iterator.hasNext()) {
		    TreeItem<VoogaFile> iterItem = iterator.next();
		    if(treeItem == iterItem) {
		    	root.getChildren().remove(iterItem);
		    	break;
		    }
		}
	}
	
}
