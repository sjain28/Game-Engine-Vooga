package authoring.gui.animation;

import java.util.ArrayList;
import java.util.List;

import authoring.resourceutility.ButtonMaker;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import tools.VoogaAlert;
import tools.VoogaException;
import tools.VoogaFileChooser;

/**
 * A flow pane that contains Frames, or glorified ImageViews, to represent a sequence in an animation.
 * 
 * @author Aditya Srinivasan
 *
 */
public class ImageFlowPane extends FlowPane {
	
	/**
	 * Constants
	 */
	private static final double SPACING = 10;
	private static final String INCORRECT = "Please select a valid image.";

	/**
	 * Initializes the FlowPane and instantiates the button for adding images.
	 */
	public ImageFlowPane() {
		super(SPACING, SPACING);
		this.getChildren().addAll(new ButtonMaker().makeButton("+", e -> addFrame()));
	}
	
	/**
	 * Adds a frame to the sequence of animation frames. If the image chosen in
	 * the file chooser is invalid, an exception will be thrown informing the
	 * user to choose a proper file.
	 */
	private void addFrame() {
		int positionToAdd = this.getChildren().size() - 1;
		VoogaFileChooser imageChooser = new VoogaFileChooser();
		try {
			String imagePath = imageChooser.launch();
			this.getChildren().add(positionToAdd, new Frame(imagePath));
		} catch (VoogaException e) {
			VoogaAlert alert = new VoogaAlert(INCORRECT);
			alert.showAndWait();
		}
	}
	
	/**
	 * Returns the list of image names as Strings.
	 * @return a List<String>
	 */
	public List<String> getImageNames() {
		List<String> imageNames = new ArrayList<>();
		this.getChildren().stream()
						  .filter(n -> indexOf(n) != (this.getChildren().size() - 1))
						  .map(n -> ((Frame) n).getImagePath())
						  .forEach(imageNames::add);
		return imageNames;
	}
	
	/**
	 * Returns the index in the list of children of this FlowPane as a function
	 * of the Node. If it is not contained, it returns -1.
	 * @param node
	 * @return
	 */
	private int indexOf(Node node) {
		for(int i = 0; i < this.getChildren().size(); i++) {
			if(this.getChildren().get(i) == node) {
				return i;
			}
		}
		return -1;
	}
}
