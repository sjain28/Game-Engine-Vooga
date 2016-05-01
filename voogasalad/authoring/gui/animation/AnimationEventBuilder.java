package authoring.gui.animation;

import authoring.VoogaScene;
import javafx.stage.Stage;

/**
 * The stage upon which the Animation event GUI is built upon.
 * 
 * @author Aditya Srinivasan
 *
 */
public class AnimationEventBuilder extends Stage {

	/**
	 * Constants
	 */
	private static final double WIDTH = 600;
	private static final double HEIGHT = 450;

	/**
	 * Establishes the scene with the tab pane that allows users to create paths or define animation events.
	 */
	public AnimationEventBuilder() {
		this.setScene(new VoogaScene(new AnimationTabPane(), WIDTH, HEIGHT));
	}

}
