package authoring.gui;

import authoring.interfaces.model.EditElementable;
import javafx.stage.Stage;

public class VariableBuilder extends Builder {
   
    public VariableBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        makeInfo("Variable Name", 0);
        makeInfo("Value", 1);
        makeCreate();
    }

    @Override
    public void compile () {
        // TODO LINK TO BACKEND STRUCTURES
        quit();
    }

}
