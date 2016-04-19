package tools.bindings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import resources.VoogaBundles;
import tools.VoogaException;

public class ImageProperties extends NodeProperties{
    
     Double positionX;
     Double positionY;
     Double positionZ;
    
     Double width;
     Double height;
     Double opacity;
    
    public ImageProperties (ResourceBundle bundle) {
        super(bundle);
    }
    
    
   
    
    
    
}
