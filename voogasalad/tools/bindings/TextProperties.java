package tools.bindings;

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
