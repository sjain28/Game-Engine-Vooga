package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.DesignBoardPreferences;
import authoring.gui.menubar.MenuItemHandler;
import authoring.gui.menubar.builders.PreferencesSetter;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import tools.VoogaString;

public class PrefFileItem extends MenuItemHandler { 
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
	
	private static final double WINDOW_WIDTH = 400;
	private static final double WINDOW_HEIGHT = 350;
	
	private static final String MAIN_CHARACTER = "Main_Character";
	
	private CompleteAuthoringModelable model;

	public PrefFileItem(Menuable model) {
		super();
		this.model = (CompleteAuthoringModelable) model.getManager();
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
		Stage stage = new Stage();
		TabPane tp = new TabPane();
		DesignBoardPreferences preferences = new DesignBoardPreferences(model);
		preferences.setText("Preferences for \"" + this.model.getName() + "\"");
		preferences.setName(this.model.getName());
		preferences.setListener(e -> {
			if(!preferences.isContinuous()) {
				model.getGlobalVariables().put(MAIN_CHARACTER, new VoogaString(preferences.getSpriteIDtoTrack()));
			}
		});
		tp.getTabs().add(preferences);
		stage.setScene(new VoogaScene(tp, WINDOW_WIDTH, WINDOW_HEIGHT));
		stage.show();
	}

}
