package authoring.gui;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TabPane;
import resources.VoogaBundles;


public class DesignBoardHousing extends TabPane {
	
	private SimpleStringProperty mySceneName;
   
/**
 * Initializes the housing for the Design Board. This contains functionality to create new Scenes of the Design Board
 * 
 * @param elem: Interface for Manager to Update Backend 
 */
    public DesignBoardHousing (CompleteAuthoringModelable elem) {

    	mySceneName = new SimpleStringProperty();
    	DesignBoardPreferences preferences = new DesignBoardPreferences(elem);
    	preferences.setListener(e -> {
    		mySceneName.set(preferences.getName());
    		this.getTabs().remove(preferences);
            this.getTabs().add(new DesignBoard(elem));
    	});
    	this.getTabs().add(preferences);
    }

    /**
     * allows for access to the Design board currently displayed.
     * @return The currently Displayed Design Board.
     */
    public DesignBoard getDesignBoard() {
        return ((DesignBoard) this.getTabs().get(getSelectionModel().getSelectedIndex()));
    }

    /**
     * Adds a new Scene and it's associated design board. 
     * @param elem: Interface for Manager to update Backend 
     */
    public void addScene (CompleteAuthoringModelable elem) {
       DesignBoard design = new DesignBoard(elem);
       this.getTabs().add(design);
    }

	public Property<String> getName() {
		return this.mySceneName;
	}

}
