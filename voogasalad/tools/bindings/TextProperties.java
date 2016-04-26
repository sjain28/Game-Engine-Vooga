package tools.bindings;

import resources.VoogaBundles;


public class TextProperties extends NodeProperties {

    Double opacity;

    String text;
    
    Double positionX;
    Double positionY;
    Double positionZ;
    Double rotation;
    
    String style;
    
    public TextProperties () {
        super(VoogaBundles.textProperties);
    }

}
