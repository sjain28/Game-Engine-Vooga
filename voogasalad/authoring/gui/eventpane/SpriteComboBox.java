package authoring.gui.eventpane;


import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;

public class SpriteComboBox extends ComboBox<String> {
    public SpriteComboBox(EditEventable manager){
        setPadding(new Insets(10,10,10,10));
        getItems().addAll(manager.getMySpriteNames());
        //getItems().addAll(manager.getSpriteFactory().getAllArchetypeNames());
    }
}
