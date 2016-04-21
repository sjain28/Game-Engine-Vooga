package tools.bindings;

import java.util.ResourceBundle;
import resources.VoogaBundles;


public class ImageProperties extends NodeProperties {

    Double positionX;
    Double positionY;
    Double positionZ;

    Double width;
    Double height;
    Double opacity;

    public ImageProperties () {
        super(VoogaBundles.imageProperties);
    }

}
