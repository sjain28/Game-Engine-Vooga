package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.PreferencesSetter;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;

public class PrefFileItem extends MenuItemHandler { 
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */

	public PrefFileItem(Menuable model) {

		super();
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		//PreferencesSetter pref = new PreferencesSetter();
		//pref.setTitle("Preferences");
		// spref.show();
	}

}
