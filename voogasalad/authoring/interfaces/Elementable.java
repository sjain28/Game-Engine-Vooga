package authoring.interfaces;

import java.util.Map;
import tools.interfaces.VoogaData;

/**
 * Forms a relationship between all elements, objects that are going to be present in the game.
 * This relationship makes it easier to define any basic options for these objects, such as dragging them onto the 
 * screen.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Harry Guo, Nick Lockett
 *
 */
public interface Elementable extends Updatable{
    
    public Map<String,VoogaData> getVoogaProperties();
    public void addProperty(String name, VoogaData data);
}
