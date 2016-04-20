package tools.bindings;

import java.awt.Font;
import java.util.ResourceBundle;
import javafx.scene.layout.Border;

public class TextProperties extends NodeProperties{

     Double positionX;
     Double positionY;
     Double positionZ;
    
     Double width;
     Double height;
     Double opacity;
    
     Font font;
     Border border;
     String text;
    
    public TextProperties (ResourceBundle bundle) {
        super(bundle);
    }
   
}
