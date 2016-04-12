package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import javafx.stage.Stage;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class VariableBuilder extends Builder {
   
    public VariableBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        makeInfo("Variable Name", 0);
        makeInfo("Value", 1);
        makeCreate();
    }

    @Override
    public void compile () {
        VoogaData value = new VoogaNumber();
        try{
            value.setValue(Double.parseDouble(getInfo(1)));
            getManager().addGlobalVariable(getInfo(), value);
        }
       catch(Exception e){
           numberError("Please Input a number");
       }
       quit();
    }

}
