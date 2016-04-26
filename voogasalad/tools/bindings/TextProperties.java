package tools.bindings;

import java.awt.Font;
import java.util.ResourceBundle;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;
import resources.VoogaBundles;


public class TextProperties extends NodeProperties {

    Double opacity;

    String text;
    
    Double positionX;
    Double positionY;
    Double positionZ;
    
    String style;

    public TextProperties () {
        super(VoogaBundles.textProperties);
    }

}
