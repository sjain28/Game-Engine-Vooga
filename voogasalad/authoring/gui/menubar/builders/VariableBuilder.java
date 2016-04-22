package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;

public class VariableBuilder extends PropertyBuilder {
    
    public VariableBuilder(EditElementable editor){
        super(editor);
    }
    
	@Override
    public void compile () {
        try{
            //myManager.addGlobalVariable(getName(), getValue());
        }
       catch(Exception e) {
           numberError("Please input a valid number");
       }
       quit();
    }

}
