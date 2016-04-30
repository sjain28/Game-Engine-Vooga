package authoring.gui.items;

import java.util.Map;
import authoring.gui.refactoringevents.VoogaNode;
import authoring.interfaces.Elementable;
import authoring.interfaces.Elementable;
import authoring.interfaces.model.EditEventable;
import authoring.model.ElementManager;
import gameengine.Sprite;
import javafx.scene.control.ComboBox;
import tools.interfaces.VoogaData;

public class VariableComboBox extends ComboBox<String> implements VoogaNode{
    private Map<String,VoogaData> variables;
    private EditEventable manager;
    
    public VariableComboBox(EditEventable manager){
        this.manager=manager;
        this.setPromptText("Select a Variable");
    }
    
    public void resetVariables(String name){
        Elementable element = manager.getSpriteFactory().getArchetype(name);
        if (element== null){
            element = manager.getVoogaElement(name);
        }

        resetVariables(element.getVoogaProperties());
    }
    
    public void resetVariables(Map<String,VoogaData> data){
        variables = data;
        getItems().clear();
        getItems().addAll(data.keySet());
    }
    
    public VoogaData getProperty(String key){
        return variables.get(key);
    }
    
    public void initialize (Object data){
        resetVariables((Map<String,VoogaData>) data);
    }

    @Override
    public String getData () {
        return variables.get(this.getValue()).getClass().toString();
    }
    
}
