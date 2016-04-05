package authoring.resourceutility;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResourceTreeView extends TreeView<VoogaFile> {

	private TreeItem<VoogaFile> root;
	private static final String LOCATION = "file:resources/";
	private static final String EXTENSION = ".png";
	
	public ResourceTreeView(VoogaFile projectRoot) {		
		root = new TreeItem<VoogaFile>(projectRoot, makeImage(projectRoot.getType()));
		root.setExpanded(true);
		this.setEditable(true);
		this.setRoot(root);
		
		this.setCellFactory(callback -> {
			return new TextFieldTreeCellImpl();
		});
		
	}
	
	public void addItem(VoogaFile file) {
		TreeItem<VoogaFile> treeFile = new TreeItem<VoogaFile>(file, makeImage(file.getType()));
		root.getChildren().add(treeFile);
	}
	
	private ImageView makeImage(VoogaFileType type) {
		return new ImageView(new Image(LOCATION	 + type.name() + EXTENSION));
	}

	
}
