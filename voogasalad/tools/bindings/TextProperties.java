package tools.bindings;

import java.awt.Font;
import java.util.ResourceBundle;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;
import resources.VoogaBundles;

public class TextProperties extends NodeProperties{

     Double positionX;
     Double positionY;
     Double positionZ;
    
     Double width;
     Double height;
     Double opacity;
    
     String text;
     
    public TextProperties () {
        super(VoogaBundles.textProperties);
    }
   
}
