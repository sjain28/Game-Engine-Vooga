package authoring.gui;

import java.util.ResourceBundle;

import authoring.gui.levelpreferences.DesignBoardPreferences;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TabPane;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaNumber;
import tools.VoogaString;

/**
 * 
 * The GUI class that manages the housing for the design board.
 * 
 * @author Harry Guo, Arjun Desai, Aditya Srinivasan, Nick Lockett
 *
 */

public class DesignBoardHousing extends TabPane {

	private SimpleStringProperty mySceneName;
	
	private ResourceBundle designboardProperties;

	/**
	 * Initializes the housing for the Design Board. This contains functionality
	 * to create new Scenes of the Design Board
	 * 
	 * @param elem:
	 *            Interface for Manager to Update Backend
	 */
	public DesignBoardHousing(CompleteAuthoringModelable elem, boolean bypass) {
		mySceneName = new SimpleStringProperty();
		designboardProperties = VoogaBundles.designboardProperties;
		if (!bypass) {
			DesignBoardPreferences preferences = new DesignBoardPreferences(elem);
			preferences.setClosable(false);
			preferences.setListener(e -> {
				try {
//<<<<<<< HEAD
//					mySceneName.set(preferences.getName());
//					elem.getGlobalVariables().put(
//							preferences.getName()+designboardProperties.getString("Scrolling"), new VoogaString(preferences.getScrollingType()));
//					elem.getGlobalVariables().put(
//							preferences.getName()+designboardProperties.getString("mainUUID"), new VoogaString(preferences.getMainSpriteID()));
//					elem.getGlobalVariables().put(
//							preferences.getName()+designboardProperties.getString("ScrollSpeed"), new VoogaNumber(preferences.getContinuousScrollSpeed()));
//					elem.getGlobalVariables().put(
//							preferences.getName()+designboardProperties.getString("ScrollAngle"), new VoogaNumber(preferences.getScrollAngle()));
//					elem.getGlobalVariables().put(
//							preferences.getName()+designboardProperties.getString("contScrollType"), new VoogaString(preferences.getContinuousScrollType()));
//					elem.getGlobalVariables().put(
//							preferences.getName()+designboardProperties.getString("TrackingDirection"), new VoogaString(preferences.getTrackingDirection()));
//					this.getTabs().remove(preferences);
//					this.getTabs().add(new DesignBoard(elem));
//					elem.setName(preferences.getName());
//=======
				mySceneName.set(preferences.getName());
				elem.getGlobalVariables().put(preferences.getName()+"Scrolling", new VoogaString(preferences.getScrollingType()));
				elem.getGlobalVariables().put(preferences.getName()+"MainUUID", new VoogaString(preferences.getMainSpriteID()));
				elem.getGlobalVariables().put(preferences.getName()+"ScrollSpeed", new VoogaNumber(preferences.getContinuousScrollSpeed()));
				elem.getGlobalVariables().put(preferences.getName()+"ScrollAngle", new VoogaNumber(preferences.getScrollAngle()));
				elem.getGlobalVariables().put(preferences.getName()+"ContinuousScrollType", new VoogaString(preferences.getContinuousScrollType()));
				elem.getGlobalVariables().put(preferences.getName()+"TrackingDirection", new VoogaString(preferences.getTrackingDirection()));
				elem.getGlobalVariables().put(preferences.getName()+"BGM", new VoogaString(preferences.getBGM()));
				this.getTabs().remove(preferences);
				this.getTabs().add(new DesignBoard(elem));
				elem.setName(preferences.getName());
//>>>>>>> ed3c744dd78c2928f7b542a1f88fdaeee3726a5c
				} catch(Exception ee) {
					VoogaAlert alert = new VoogaAlert(designboardProperties.getString("prefAlert"));
					alert.showAndWait();
				}
			});
			this.getTabs().add(preferences);
		} else {
			this.getTabs().add(new DesignBoard(elem));
		}
	}

	/**
	 * allows for access to the Design board currently displayed.
	 * 
	 * @return The currently Displayed Design Board.
	 */
	public DesignBoard getDesignBoard() {
		return ((DesignBoard) this.getTabs().get(getSelectionModel().getSelectedIndex()));
	}

	/**
	 * Adds a new Scene and it's associated design board.
	 * 
	 * @param elem:
	 *            Interface for Manager to update Backend
	 */
	public void addScene(CompleteAuthoringModelable elem) {
		DesignBoard design = new DesignBoard(elem);
		this.getTabs().add(design);
	}

	/**
	 * @return the name of the current scene
	 */
	public Property<String> getName() {
		return this.mySceneName;
	}

}
