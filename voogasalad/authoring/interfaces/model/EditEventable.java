package authoring.interfaces.model;

import events.VoogaEvent;

public interface EditEventable{
    public void addEvents (VoogaEvent ... events);
    public void removeEvents (VoogaEvent ... events);

   
}