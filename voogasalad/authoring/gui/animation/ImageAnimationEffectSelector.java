package authoring.gui.animation;

import authoring.CustomText;
import javafx.scene.layout.FlowPane;

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
		subnode = new ImageFlowPane();
	}

	/**
	 * Returns the relevant value from the subnode, in this case a list of image names.
	 */
	@Override
	protected Object getValue() {
		return ((ImageFlowPane) subnode).getImageNames();
	}

}
