package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class KeyCauseGUI implements EventGUI{
    private TextField textField;
    
    public KeyCauseGUI(EditEventable manager){
        textField = new TextField();
    }
    @Override
    public Node display () {
        return textField;
    }

    @Override
    public String getDetails () {
        return "KeyCause "+textField.getText();
    }
    
}
