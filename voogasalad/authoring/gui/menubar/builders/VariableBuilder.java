package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class VariableBuilder extends PropertyBuilder {
    
    public VariableBuilder(EditElementable editor){
        super(editor);
    }
    
	@Override
    public void compile () {
        try{
            myManager.addGlobalVariable(getName(), getValue());
        }
       catch(Exception e) {
           numberError("Please input a valid number");
       }
       quit();
    }

}
