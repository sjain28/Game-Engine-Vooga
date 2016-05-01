package authoring.gui;

import authoring.GlobalPropertiesMapSetter;
import authoring.gui.levelpreferences.DesignBoardPreferences;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TabPane;
import tools.VoogaAlert;

/**
 * 
 * The GUI class that manages the housing for the design board.
 * 
 * @author Aditya Srinivasan, Arjun Desai
 *
 */

public class DesignBoardHousing extends TabPane {

    private static final String PREF_ALERT = "Please enter all preference values.";

    private SimpleStringProperty mySceneName;

    /**
     * Initializes the housing for the Design Board. This contains functionality
     * to create new Scenes of the Design Board
     * 
     * @param elem:
     *        Interface for Manager to Update Backend
     */
    public DesignBoardHousing (CompleteAuthoringModelable elem, boolean bypass) {
        mySceneName = new SimpleStringProperty();
        if (!bypass) {
            DesignBoardPreferences preferences = new DesignBoardPreferences(elem);
            preferences.setClosable(false);
            preferences.setListener(e -> {
                try {
                    mySceneName.set(preferences.getName());
                    GlobalPropertiesMapSetter setter = new GlobalPropertiesMapSetter(elem,preferences);
                    setter.setGlobalProperties();
                    
                    this.getTabs().remove(preferences);
                    this.getTabs().add(new DesignBoard(elem));
                    elem.setName(preferences.getName());

                }
                catch (Exception ee) {
                    VoogaAlert alert = new VoogaAlert(PREF_ALERT);
                    alert.showAndWait();
                }
            });
            this.getTabs().add(preferences);
        }
        else {
            this.getTabs().add(new DesignBoard(elem));
        }
    }

    /**
     * allows for access to the Design board currently displayed.
     * 
     * @return The currently Displayed Design Board.
     */
    public DesignBoard getDesignBoard () {
        return ((DesignBoard) this.getTabs().get(getSelectionModel().getSelectedIndex()));
    }

    /**
     * Adds a new Scene and it's associated design board.
     * 
     * @param elem:
     *        Interface for Manager to update Backend
     */
    public void addScene (CompleteAuthoringModelable elem) {
        DesignBoard design = new DesignBoard(elem);
        this.getTabs().add(design);
    }

    /**
     * @return the name of the current scene
     */
    public Property<String> getName () {
        return this.mySceneName;
    }

}
