package authoring.interfaces;

import java.util.Map;
import tools.interfaces.VoogaData;

public interface Elementable{
    public Map<String, VoogaData> getVoogaProperties ();
    
    public void setVoogaProperties(Map<String,VoogaData> newVoogaProperties);

    public void addProperty (String name, VoogaData data);

    public void removeProperty (String name);

    public String getName ();
    
    public void setName(String name);

    public String getId ();
}
