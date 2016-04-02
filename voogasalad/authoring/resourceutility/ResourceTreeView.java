package authoring.resourceutility;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ResourceTreeView extends TreeView<VoogaFile> {

	private TreeItem<VoogaFile> root;
	
	public ResourceTreeView(VoogaFile projectName) {
		root = new TreeItem<VoogaFile>(projectName);
		this.setRoot(root);
	}
	
	public void addItem(VoogaFile file) {
		TreeItem<VoogaFile> treeFile = new TreeItem<VoogaFile>(file);
		root.getChildren().add(treeFile);
	}
	
}
