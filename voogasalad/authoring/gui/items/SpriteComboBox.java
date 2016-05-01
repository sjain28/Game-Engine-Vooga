package authoring.gui.items;

import authoring.gui.refactoringevents.VoogaNode;
import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * Sprite ComboBox super class that contains all the sprite names.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class SpriteComboBox extends ComboBox<String> implements VoogaNode {
	
	/**
	 * private instance variables
	 */
    private EditEventable manager;
    private static final double COMBO_BOX_PADDING = 10;
    
    /**
     * Instantiates with instance of manager to extract information.
     * @param manager
     */
    public SpriteComboBox (EditEventable manager) {
        this.manager = manager;
        setPadding(new Insets(COMBO_BOX_PADDING, COMBO_BOX_PADDING, COMBO_BOX_PADDING, COMBO_BOX_PADDING));
        loadData();
    }

    /**
     * Loads the data into the combobox
     */
    protected void loadData () {
        getItems().addAll(manager.getMySpriteNames());
    }

    /**
     * Get sprite ID from the sprite name.
     * @return
     */
    public String getSpriteId () {
        try {
            return manager.getSpriteIdFromName(this.getValue());
        }
        catch (VoogaException e) {
            VoogaAlert alert = new VoogaAlert(e.getMessage());
            alert.showAndWait();
        }
        return null;
    }
    
    /**
     * Gets the manager for this combobox.
     * @return
     */
    protected EditEventable getManager(){
        return manager;
    }

    /**
     * Gets ID. 
     */
    @Override
    public String getData () {
        return getSpriteId();
    }
}
