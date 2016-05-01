package authoring.gui.items;

import java.util.Map;

import authoring.gui.refactoringevents.VoogaNode;
import authoring.interfaces.Elementable;
import authoring.interfaces.model.EditEventable;
import javafx.scene.control.ComboBox;
import tools.interfaces.VoogaData;

/**
 * ComboBox for creating new variable.
 * 
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai, Nick Lockett
 *
 */
public class VariableComboBox extends ComboBox<String> implements VoogaNode{
	
	/**
	 * private instance variables
	 */
    private Map<String,VoogaData> variables;
    private EditEventable manager;
    
    /**
     * Initialization.
     * Gets a manager to communicate with back end.
     * @param manager
     */
    public VariableComboBox(EditEventable manager){
        this.manager=manager;
        this.setPromptText("Select a Variable");
    }
    
    /**
     * reset variables of a sprite
     * @param name: of sprite
     */
    public void resetVariables(String name){
        Elementable element = manager.getSpriteFactory().getArchetype(name);
        if (element== null){
            element = manager.getVoogaElement(name);
        }

        resetVariables(element.getVoogaProperties());
    }
    
    /**
     * Clears the variables of a sprite.
     * @param data
     */
    public void resetVariables(Map<String,VoogaData> data){
        variables = data;
        getItems().clear();
        getItems().addAll(data.keySet());
    }
    
    /**
     * Get variable based on a key.
     * @param key
     * @return
     */
    public VoogaData getProperty(String key){
        return variables.get(key);
    }
    
    /**
     * Initializes an object with blank variables.
     * @param data
     */
    public void initialize (Object data){
        resetVariables((Map<String,VoogaData>) data);
    }

    /**
     * To string method for debugging.
     */
    @Override
    public String getData () {
        return variables.get(this.getValue()).getClass().toString();
    }
    
}
