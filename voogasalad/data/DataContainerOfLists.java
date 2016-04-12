package data;

import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import tools.interfaces.VoogaData;


public class DataContainerOfLists {

    private List<VoogaEvent> eventList;
    private List<Elementable> elementableList;
    private Map<String, VoogaData> variableMap;
    private SpriteFactory spriteFactory;
    
    public DataContainerOfLists () {
    }

    public DataContainerOfLists (List<Elementable> ElementableList,
                                 Map<String, VoogaData> variableMap,
                                 List<VoogaEvent> eventList) {
        this.eventList = eventList;
        System.out.println(this.eventList);
        this.elementableList = ElementableList;
        this.variableMap = variableMap;
        this.spriteFactory = spriteFactory;
    }

    public List<Elementable> getElementableList () {

        return this.elementableList;
    }

    public List<VoogaEvent> getEventList () {
        return this.eventList;
    }

    public SpriteFactory getSpriteFactory () {
        return this.spriteFactory;
    }

    public Map<String, VoogaData> getVariableMap () {
        return this.variableMap;
    }
    
    public String toString () {
        return "Data Container";
    }

}