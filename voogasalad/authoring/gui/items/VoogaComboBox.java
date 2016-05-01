package authoring.gui.items;

import authoring.gui.refactoringevents.VoogaNode;
import javafx.scene.control.ComboBox;

/**
 * Super class of Combo boxes to be displayed in the GUI.
 * 
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai, Nick Lockett
 *
 */
public class VoogaComboBox extends ComboBox implements VoogaNode{
    String data;

    /**
     * Provides the data for the combo box.
     */
    @Override
    public String getData () {
        return data;
    }
    
}
