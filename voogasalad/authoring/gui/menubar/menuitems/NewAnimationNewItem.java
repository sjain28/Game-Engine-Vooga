package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import authoring.gui.menubar.builders.AnimationEventBuilder;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;

public class NewAnimationNewItem extends PlayerMenuItemHandler {

	AnimationEventBuilder animationBuilder;
	
	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public NewAnimationNewItem(Menuable model) {
		super();
		animationBuilder = new AnimationEventBuilder(model);
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		animationBuilder.show();
	}

}
