package authoring.interfaces.model;

import events.Event;

public interface EditEventable extends EditSpritable{
    public void addEvents (Event ... events);
    public void removeEvents (Event ... events);
   
}
