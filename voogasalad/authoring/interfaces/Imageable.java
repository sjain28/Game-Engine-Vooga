package authoring.interfaces;

import javafx.scene.image.Image;

/**
 * An interface implemented by GameObject and VoogaImage that defines how
 * imaging is specified.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public interface Imageable {
    
    /**
     * Set image based on the path of the image in the local device
     * 
     * @param imagePath: String representing image path
     */
    public void setImage(String imagePath);
    
    /**
     * 
     * @return Image that the Imageable contains
     */
    public Image getImage();
}
