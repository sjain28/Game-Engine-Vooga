package data;

import java.util.List;
import java.util.Map;

import authoring.interfaces.Elementable;
import events.AnimationFactory;
import events.VoogaEvent;
import gameengine.Sprite;
import tools.interfaces.VoogaData;


/** For now, DataContainerOfLists will contain 4 objects: A list of Elementables, a Map of variables,
 * a list of events, and a SpriteFactory that is used to generate archetypes. An instance of this class 
 * will contain all the objects necessary to load into the levelDataManager to be sent to the different managers.
 **/

public class DataContainerOfLists {

	
    private List<VoogaEvent> eventList;
    private List<Elementable> elementableList;
    private Map<String, VoogaData> variableMap;
    private Map<String, Sprite> archetypeMap;
    private AnimationFactory myAnimationFactory;
    
    public DataContainerOfLists () {
    }

    public DataContainerOfLists (List<Elementable> ElementableList,
                                 Map<String, VoogaData> variableMap,
                                 List<VoogaEvent> eventList,Map<String,Sprite> archetypeToFactory,
                                 AnimationFactory animationFactory) {
        this.eventList = eventList;
        this.elementableList = ElementableList;
        this.variableMap = variableMap;
        this.archetypeMap = archetypeToFactory;
        myAnimationFactory = animationFactory;
    }

    public List<Elementable> getElementableList () {

        return this.elementableList;
    }

    public List<VoogaEvent> getEventList () {
        return this.eventList;
    }

    public Map<String,Sprite> getArchetypeMap () {
        return this.archetypeMap;
    }

    public Map<String, VoogaData> getVariableMap () {
        return this.variableMap;
    }
    
    public AnimationFactory getAnimationFactory(){
    	return this.myAnimationFactory;
    }
}
