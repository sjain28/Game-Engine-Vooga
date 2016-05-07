package tools.bindings;

import resources.VoogaBundles;

/**
 * All properties that nodes can have in VoogaSalad
 */
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
