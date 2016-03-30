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
    public void setImage(String imagePath);
    public Image getImage();
}
