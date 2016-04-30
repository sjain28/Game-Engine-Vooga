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

	private static final String MAINUUID = "MainUUID";
	private static final String SCROLL_SPEED = "ScrollSpeed";
	private static final String SCROLL_ANGLE = "ScrollAngle";
	private static final String CONT_SCROLL = "ContinuousScrollType";
	private static final String TRACKING_DIR = "TrackingDirection";
	private static final String SCROLL = "Scrolling";
	private static final String BGM = "BGM";

	private static final double WINDOW_WIDTH = 600;
	private static final double WINDOW_HEIGHT = 350;

	private CompleteAuthoringModelable model;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 */
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
		preferences.setScrolling((String) model.getGlobalVariables().get(this.model.getName()+ SCROLL).getValue());
		preferences.setSpeed((Double) model.getGlobalVariables().get(this.model.getName()+ SCROLL_SPEED).getValue());
		preferences.setMainSprite((String) model.getGlobalVariables().get(this.model.getName()+ MAINUUID).getValue());
		preferences.setAngle(Double.toString((Double) model.getGlobalVariables().get(this.model.getName()+ SCROLL_ANGLE).getValue()));
		preferences.setContinuousScrollType((String) model.getGlobalVariables().get(this.model.getName()+ CONT_SCROLL).getValue());
		preferences.setTrackingDirection((String) model.getGlobalVariables().get(this.model.getName()+ TRACKING_DIR).getValue());
		preferences.setListener(e -> {
			model.getGlobalVariables().put(preferences.getName()+ SCROLL, new VoogaString(preferences.getScrollingType()));
			model.getGlobalVariables().put(preferences.getName()+ MAINUUID, new VoogaString(preferences.getMainSpriteID()));
			model.getGlobalVariables().put(preferences.getName()+ SCROLL_SPEED, new VoogaNumber(preferences.getContinuousScrollSpeed()));
			model.getGlobalVariables().put(preferences.getName()+ SCROLL_ANGLE, new VoogaNumber(preferences.getScrollAngle()));
			model.getGlobalVariables().put(preferences.getName()+ CONT_SCROLL, new VoogaString(preferences.getContinuousScrollType()));
			model.getGlobalVariables().put(preferences.getName()+ TRACKING_DIR, new VoogaString(preferences.getTrackingDirection()));
			//model.getGlobalVariables().put(preferences.getName()+ BGM, new VoogaString(preferences.getBGM()));
			stage.close();
		});
		tp.getTabs().add(preferences);
		stage.setScene(new VoogaScene(tp, WINDOW_WIDTH, WINDOW_HEIGHT));
		stage.show();
	}

}
