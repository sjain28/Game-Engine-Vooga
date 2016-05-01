package authoring.gui.animation;

import authoring.gui.items.NumberTextField;
import javafx.scene.layout.HBox;
import tools.GUIUtils;

/**
 * A subclass of the abstract effect selector. It allows users to define sequences of images that will be strung together
 * for the duration of an animation.
 * 
 * @author Aditya Srinivasan
 *
 */
public class ImageAnimationEffectSelector extends AbstractEffectSelector {
	
	/**
	 * Constants
	 */
	private static final String IMAGE_SELECTOR = "Image Animation Effect";
	
	private NumberTextField numberOfCycles;
	
	/**
	 * Initializes the selector and the heading.
	 */
	ImageAnimationEffectSelector() {
		super();
		initializeHeader(IMAGE_SELECTOR);
	}

	/**
	 * Initializes the subclass' subnode, in this case a FlowPane for images.
	 */
	@Override
	protected void initializeSubnode() {
		numberOfCycles = new NumberTextField();
		numberOfCycles.setPromptText("Number of cycles");
		numberOfCycles.sanitizeForInteger();
		subnode = GUIUtils.makeRow(new ImageFlowPane(), numberOfCycles);
	}

	/**
	 * Returns the relevant value from the subnode, in this case a list of image names.
	 */
	@Override
	protected Object getValue() {
		return ((ImageFlowPane) ((HBox) subnode).getChildren().get(0)).getImageNames();
	}
	
	/**
	 * Returns the number of cycles.
	 * @return integer value of number of cycles
	 */
	public Integer getNumberOfCycles() {
		return Integer.parseInt(numberOfCycles.getText());
	}

}
