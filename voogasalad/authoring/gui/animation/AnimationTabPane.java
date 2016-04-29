package authoring.gui.animation;

import javafx.scene.control.TabPane;
import player.gamedisplay.Menuable;

/**
 * The TabPane that populates the main AnimationGUI Stage. It contains two tabs, one for
 * creating and saving paths, and the other for defining and previewing animation events.
 * 
 * @author Aditya Srinivasan
 *
 */
public class AnimationTabPane extends TabPane {
	
	/**
	 * Constants
	 */
	private static final String PATH_DEFINER = "Draw Path";
	private static final String ANIMATION_GUI = "Create Animation Event";
	
	/**
	 * Instantiates the tabs, for path building and animation event creation.
	 */
	public AnimationTabPane() {
		PathBuilder pathBuilder = new PathBuilder();
		pathBuilder.setText(PATH_DEFINER);
		AnimationEventGUI animationGUI = new AnimationEventGUI();
		animationGUI.setText(ANIMATION_GUI);
		animationGUI.selectedProperty().addListener((obs, old, n) -> {
			if(n) animationGUI.updatePathListings();
		});
		this.getTabs().addAll(pathBuilder, animationGUI);
	}

}
