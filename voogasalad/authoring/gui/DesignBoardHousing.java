package authoring.gui;

import authoring.gui.levelpreferences.DesignBoardPreferences;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TabPane;
import tools.VoogaAlert;
import tools.VoogaNumber;
import tools.VoogaString;

public class DesignBoardHousing extends TabPane {

	private SimpleStringProperty mySceneName;

	/**
	 * Initializes the housing for the Design Board. This contains functionality
	 * to create new Scenes of the Design Board
	 * 
	 * @param elem:
	 *            Interface for Manager to Update Backend
	 */
	public DesignBoardHousing(CompleteAuthoringModelable elem, boolean bypass) {
		mySceneName = new SimpleStringProperty();
		if (!bypass) {
			DesignBoardPreferences preferences = new DesignBoardPreferences(elem);
			preferences.setClosable(false);
			preferences.setListener(e -> {
				try {
				mySceneName.set(preferences.getName());
				elem.getGlobalVariables().put(preferences.getName()+"Scrolling", new VoogaString(preferences.getScrollingType()));
				elem.getGlobalVariables().put(preferences.getName()+"MainUUID", new VoogaString(preferences.getMainSpriteID()));
				elem.getGlobalVariables().put(preferences.getName()+"ScrollSpeed", new VoogaNumber(preferences.getContinuousScrollSpeed()));
				elem.getGlobalVariables().put(preferences.getName()+"ScrollAngle", new VoogaNumber(preferences.getScrollAngle()));
				elem.getGlobalVariables().put(preferences.getName()+"ContinuousScrollType", new VoogaString(preferences.getContinuousScrollType()));
				this.getTabs().remove(preferences);
				this.getTabs().add(new DesignBoard(elem));
				elem.setName(preferences.getName());
				} catch(Exception ee) {
					new VoogaAlert("Please enter all preference values.");
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
	
	public Property<String> getName() {
		return this.mySceneName;
	}

}
