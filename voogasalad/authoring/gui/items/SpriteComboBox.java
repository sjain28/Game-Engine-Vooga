package authoring.gui.items;

import authoring.gui.refactoringevents.VoogaNode;
import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import tools.VoogaAlert;
import tools.VoogaException;


public class SpriteComboBox extends ComboBox<String> implements VoogaNode {
    private EditEventable manager;

    public SpriteComboBox (EditEventable manager) {
        this.manager = manager;
        setPadding(new Insets(10, 10, 10, 10));
        loadData();
    }

    protected void loadData () {
        getItems().addAll(manager.getMySpriteNames());
    }

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
    
    protected EditEventable getManager(){
        return manager;
    }

    @Override
    public String getData () {
        return getSpriteId();
    }
}
