package authoring.interfaces.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import events.VoogaEvent;
import tools.interfaces.VoogaData;

public interface EditEventable extends EditSpritable,EditElementable{
    public void addEvents (VoogaEvent ... events);

    public void removeEvents (VoogaEvent ... events);

    public Map<String, VoogaData> getGlobalVariables ();
    
    public void addObserver (Observer o);
    
    public List<VoogaEvent> getEvents();

}