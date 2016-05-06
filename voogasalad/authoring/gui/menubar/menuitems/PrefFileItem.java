package authoring.gui.menubar.menuitems;

import authoring.GlobalPropertiesMapSetter;
import authoring.VoogaScene;
import authoring.gui.levelpreferences.DesignBoardPreferences;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

/**
 * Opens up a preferences menu for use to specify game specifics.
 * e.g. Scroll speed, background music, tracking direction, etc.
 * 
 * @author Harry Guo, Nick Lockett, Aditya Srinivasan, Arjun Desai
 *
 */
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
		try {
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
		preferences.setBGM((String) model.getGlobalVariables().get(this.model.getName()+ BGM).getValue());
		
		preferences.setListener(e -> {
			GlobalPropertiesMapSetter globalSetter = new GlobalPropertiesMapSetter(model,preferences);
			globalSetter.setGlobalProperties();
			stage.close();
		});
		
		tp.getTabs().add(preferences);
		stage.setScene(new VoogaScene(tp, WINDOW_WIDTH, WINDOW_HEIGHT));
		stage.show();
		} catch(Exception e) {
			
		}
	}
}
