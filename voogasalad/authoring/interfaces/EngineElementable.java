package authoring.interfaces;

import java.util.Map;
import javafx.scene.Node;
import tools.VoogaException;
import tools.interfaces.VoogaData;


/**
 * Forms a relationship between all elements, objects that are going to be present in the game.
 * This relationship makes it easier to define any basic options for these objects, such as dragging
 * them onto the
 * screen.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Harry Guo, Nick Lockett
 *
 */
public interface EngineElementable extends Elementable, Updatable{

    public Map<String, VoogaData> getVoogaProperties ();
    
    public void setVoogaProperties(Map<String,VoogaData> newVoogaProperties);

    public void addProperty (String name, VoogaData data);

    public void removeProperty (String name);

    public String getName ();
    
    public void setName(String name);

    public String getId ();

    public Node getNodeObject ();
    
    public void init() throws VoogaException;

}
