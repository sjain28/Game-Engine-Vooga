package tools.bindings;

import resources.VoogaBundles;


public class ImageProperties extends NodeProperties {

    Double positionX;
    Double positionY;
    Double positionZ;

    Double width;
    Double height;
    Double opacity;
    Double rotation;
    
    public ImageProperties () {
        super(VoogaBundles.imageProperties);
    }

}
