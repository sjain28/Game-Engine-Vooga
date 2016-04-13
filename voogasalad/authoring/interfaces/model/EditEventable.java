package authoring.interfaces.model;

import java.util.Collection;
import java.util.Map;
import events.VoogaEvent;
import tools.interfaces.VoogaData;

public interface EditEventable extends EditSpritable,EditElementable{
    public void addEvents (VoogaEvent ... events);

    public void removeEvents (VoogaEvent ... events);

    public Map<String, VoogaData> getGlobalVariables ();

    public Collection<String> getMySpriteNames ();

   
}