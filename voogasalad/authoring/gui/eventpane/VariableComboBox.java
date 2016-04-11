package authoring.gui.eventpane;

import java.util.Map;
import authoring.model.ElementManager;
import javafx.scene.control.ComboBox;
import tools.interfaces.VoogaData;

public class VariableComboBox extends ComboBox<String>{
    private Map<String,VoogaData> variables;
    
    public void onParentChanged(Map<String,VoogaData> data){
        variables = data;
        getItems().clear();
        getItems().addAll(variables.keySet());
    }
    
    public VoogaData getProperty(String key){
        return variables.get(key);
    }
    
}
