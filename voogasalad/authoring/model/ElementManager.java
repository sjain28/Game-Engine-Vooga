package authoring.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import GameEngine.Sprite;
import authoring.interfaces.gui.Saveable;
import events.Event;
import tools.interfaces.VoogaData;

public class ElementManager implements Saveable{
    private List<Sprite> spriteList;
    private List<Event> eventList;
    private Map<String,VoogaData> globalVariables;
    
    
    
    void addSprites(Sprite... sprites){
        spriteList.addAll(Arrays.asList(sprites));
    }
    
    void removeSprites(Sprite...sprites){
        spriteList.removeAll(Arrays.asList(sprites));
    }
    
    List<Sprite> getSprites(){
        return spriteList;
    }
    
    void addEvents(Event... events){
        eventList.addAll(Arrays.asList(events));
    }
    
    void removeEvents(Event...events){
        eventList.removeAll(Arrays.asList(events));
    }
    
    
    /**
     * Write Data to XML using XStream
     */
    @Override
    public void onSave () {
        
    }
    
}
