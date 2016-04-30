package authoring.gui.menubar.menuitems;

import authoring.gui.animation.AnimationEventBuilder;
import authoring.gui.menubar.PlayerMenuItemHandler;
import player.gamedisplay.Menuable;

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
		animationBuilder = new AnimationEventBuilder();
	}
	
	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		animationBuilder.show();
	}

}
