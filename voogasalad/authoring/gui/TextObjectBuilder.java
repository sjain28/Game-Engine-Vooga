package authoring.gui;

import authoring.interfaces.model.EditElementable;
import javafx.stage.Stage;

public class TextObjectBuilder extends Builder{

    public TextObjectBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        makeXLocationPicker();
        makeYLocationPicker();
        makeCreate();
    }

    public void makeText(){
        
    }
    @Override
    public void compile () {
        // TODO Auto-generated method stub
        
    }

}
