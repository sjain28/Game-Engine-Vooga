package authoring.interfaces.model;

import events.Event;

public interface EditEventable {
    public void addEvents (Event ... events);
    public void removeEvents (Event ... events);
   
}
