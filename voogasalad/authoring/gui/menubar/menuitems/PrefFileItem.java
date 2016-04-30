package authoring.gui.menubar.menuitems;

import authoring.VoogaScene;
import authoring.gui.levelpreferences.DesignBoardPreferences;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import tools.VoogaNumber;
import tools.VoogaString;

public class PrefFileItem extends MenuItemHandler { 
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
	
	private static final double WINDOW_WIDTH = 600;
	private static final double WINDOW_HEIGHT = 350;
	
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
		System.out.println("printing size" + model.getGlobalVariables().size());
		preferences.setScrolling((String) model.getGlobalVariables().get(this.model.getName()+"Scrolling").getValue());
		preferences.setSpeed((Double) model.getGlobalVariables().get(this.model.getName()+"ScrollSpeed").getValue());
		preferences.setMainSprite((String) model.getGlobalVariables().get(this.model.getName()+"MainUUID").getValue());
		preferences.setAngle(Double.toString((Double) model.getGlobalVariables().get(this.model.getName()+"ScrollAngle").getValue()));
		preferences.setContinuousScrollType((String) model.getGlobalVariables().get(this.model.getName()+"ContinuousScrollType").getValue());
		preferences.setListener(e -> {
			System.out.println("YO : " + preferences.getMainSpriteID());
			model.getGlobalVariables().put(preferences.getName()+"Scrolling", new VoogaString(preferences.getScrollingType()));
			model.getGlobalVariables().put(preferences.getName()+"MainUUID", new VoogaString(preferences.getMainSpriteID()));
			model.getGlobalVariables().put(preferences.getName()+"ScrollSpeed", new VoogaNumber(preferences.getContinuousScrollSpeed()));
			model.getGlobalVariables().put(preferences.getName()+"ScrollAngle", new VoogaNumber(preferences.getScrollAngle()));
			model.getGlobalVariables().put(preferences.getName()+"ContinuousScrollType", new VoogaString(preferences.getContinuousScrollType()));
			stage.close();
		});
		tp.getTabs().add(preferences);
		stage.setScene(new VoogaScene(tp, WINDOW_WIDTH, WINDOW_HEIGHT));
		stage.show();
	}

}
