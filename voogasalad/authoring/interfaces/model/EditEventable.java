package authoring.interfaces.model;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import events.VoogaEvent;
import tools.interfaces.VoogaData;

public interface EditEventable extends EditSpritable,EditElementable{
    
	void addEvents (VoogaEvent ... events);

    void removeEvents (VoogaEvent ... events);

    Map<String, VoogaData> getGlobalVariables ();
    
    void addObserver (Observer o);
    
    List<VoogaEvent> getEvents();

}