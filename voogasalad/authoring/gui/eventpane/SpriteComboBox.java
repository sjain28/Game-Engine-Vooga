package authoring.gui.eventpane;


import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import tools.VoogaAlert;
import tools.VoogaException;

public class SpriteComboBox extends ComboBox<String> {
    EditEventable manager;
    
    public SpriteComboBox(EditEventable manager,String... loadings){
        this.manager=manager;
        setPadding(new Insets(10,10,10,10));
        
        loadData(loadings);
    }
    
    private void loadData (String[] loadings){
        if (loadings.length==0){
            getItems().addAll(manager.getMySpriteNames());
            getItems().addAll(manager.getSpriteFactory().getAllArchetypeNames());
            return;
        }
        
        for (String load : loadings){
            if (load.equals("Sprite")){
                getItems().addAll(manager.getMySpriteNames());
            }
            if (load.equals("Archetype")){
                getItems().addAll(manager.getSpriteFactory().getAllArchetypeNames());
            }
        }
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
