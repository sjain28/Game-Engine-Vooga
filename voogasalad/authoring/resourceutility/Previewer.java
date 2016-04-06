package authoring.resourceutility;

import authoring.VoogaScene;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An abstract class inherited by ImagePreviewer and AudioPreviewer to allow
 * users to preview resources
 * @author DoovalSalad
 *
 */
public abstract class Previewer {
	
	/**
	 * Protected instance variables
	 */
	protected VoogaFile file;
	protected Group group;
	protected Stage stage;
	
	/**
	 * Constructs the previewer using the template method, allowing the
	 * subclasses to specify how to preview the item
	 * @param file
	 */
	public Previewer(VoogaFile file) {
		this.file = file;
		group = new Group();
		Scene scene = new VoogaScene(group);
		stage = new Stage();
		preview();
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Specifies how the previewing should be done
	 */
	abstract void preview();
	
}
