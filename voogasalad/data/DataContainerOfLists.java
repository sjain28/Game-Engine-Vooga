package data;

import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import events.VoogaEvent;
import gameengine.SpriteFactory;
import javafx.scene.control.Button;
import tools.interfaces.VoogaData;


/** For now, DataContainerOfLists will contain 4 objects: A list of Elementables, a Map of variables,
 * a list of events, and a SpriteFactory that is used to generate archetypes. An instance of this class 
 * will contain all the objects necessary to load into the levelDataManager to be sent to the different managers.
 **/

public class DataContainerOfLists {

	
    private List<VoogaEvent> eventList;
    private List<Elementable> elementableList;
    private Map<String, VoogaData> variableMap;
    private SpriteFactory spriteFactory;
    private List<Button> ButtonList; 
    
    public DataContainerOfLists () {
    }

    public DataContainerOfLists (List<Elementable> ElementableList,
                                 Map<String, VoogaData> variableMap,
                                 List<VoogaEvent> eventList,SpriteFactory factory) {
        this.eventList = eventList;
        System.out.println(this.eventList);
        this.elementableList = ElementableList;
        this.variableMap = variableMap;
        this.spriteFactory = factory;
    }
    
    public void addButton(List<Button> ButtonList){
    	this.ButtonList = ButtonList;
    }
    
    public List<Button> getButtonList () {

        return this.ButtonList;
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
}
