package authoring.gui.items;

import authoring.gui.refactoringevents.VoogaNode;
import javafx.scene.control.ComboBox;

public class VoogaComboBox extends ComboBox implements VoogaNode{
    String data;

    @Override
    public String getData () {
        return data;
    }
    
    
    
    
}
