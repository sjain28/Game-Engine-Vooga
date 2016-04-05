package authoring.model;

import authoring.interfaces.Elementable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

public class VoogaText extends TextField implements Elementable{
    
    
    public VoogaText(Object id){
        this.setId(id.toString());
        setBackground(Background.EMPTY);
    }
    
    

}
