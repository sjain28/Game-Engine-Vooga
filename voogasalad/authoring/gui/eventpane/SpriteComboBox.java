package authoring.gui.eventpane;


import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import tools.VoogaAlert;
import tools.VoogaException;

public class SpriteComboBox extends ComboBox<String> {
    EditEventable manager;
    
    public SpriteComboBox(EditEventable manager){
        this.manager=manager;
        setPadding(new Insets(10,10,10,10));
        
        getItems().addAll(manager.getMySpriteNames());
        getItems().addAll(manager.getSpriteFactory().getAllArchetypeNames());
    }
    
    public String getSpriteId(){
        if (manager.getSpriteFactory().getAllArchetypeNames().contains(this.getValue())) {
            return this.getValue();
        }
        try {
            return manager.getSpriteIdFromName(this.getValue());
        }
        catch (VoogaException e) {
            new VoogaAlert(e.getMessage());
        }
        return null;
    }
}
