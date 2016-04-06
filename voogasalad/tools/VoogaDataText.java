package tools;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import tools.interfaces.VoogaData;

public class VoogaDataText implements VoogaData{
    String text;
    
    public VoogaDataText(String text){
        this.text=text;
    }
    
    @Override
    public Object getValue () {
        return text;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof String)) return;
        text = (String) o;
    }

    @Override
    public Node display () {
        TextField textField = new TextField();
        textField.setText(text);
        return textField;
    }

}
