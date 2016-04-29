package data;

import java.util.List;
import java.util.Map;
import authoring.interfaces.Elementable;
import events.AnimationEvent;
import events.VoogaEvent;
import gameengine.Sprite;
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
    private Map<String, Sprite> archetypeMap;
    private Map<String, AnimationEvent> myAnimationEvents;
    private Map<String, List<Double[]>> myPaths;
    private Map<String, List<AnimationEvent>> myAnimationSequences;
    
    public DataContainerOfLists () {
    }

    public DataContainerOfLists (List<Elementable> ElementableList,
                                 Map<String, VoogaData> variableMap,
                                 List<VoogaEvent> eventList,Map<String,Sprite> archetypeToFactory,
                                  Map<String, AnimationEvent> animationEvents,
                                  Map<String, List<Double[]>> paths,
                                  Map<String, List<AnimationEvent>> animationSequences) {
        this.eventList = eventList;
        System.out.println(this.eventList);
        this.elementableList = ElementableList;
        this.variableMap = variableMap;
        this.archetypeMap = archetypeToFactory;
        myAnimationEvents = animationEvents;
        myPaths = paths;
        myAnimationSequences = animationSequences;
    }
    
//    public void addButton(List<Button> ButtonList){
//    	this.ButtonList = ButtonList;
//    }
//    
//    public List<Button> getButtonList () {
//
//        return this.ButtonList;
//    }

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
    
    public Map<String, AnimationEvent> getAnimations(){
    	return this.myAnimationEvents;
    }
    
    public Map<String, List<Double[]>> getPaths(){
    	return this.myPaths;
    }
    
    public Map<String, List<AnimationEvent>> getSequences(){
    	return this.myAnimationSequences;
    }
}
