package events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnimationFactory {

	private Map<String, AnimationEvent> myAnimationEvents;
    private Map<String, List<Double[]>> myPaths;
    private Map<String, List<AnimationEvent>> myAnimationSequences;

    private AnimationFactory () {
        myAnimationEvents = new HashMap<String, AnimationEvent>();
        myPaths = new HashMap<String, List<Double[]>>();
    }

    private static class SingletonHolder {
        private static final AnimationFactory INSTANCE = new AnimationFactory();
    }

    public static AnimationFactory getInstance () {
        return SingletonHolder.INSTANCE;
    }

    public void makeAnimationEvent (String name, Double duration) {
        AnimationEvent newEvent = new AnimationEvent(name, duration);
        myAnimationEvents.put(name, newEvent);
    }

    public void makePathEffect (String pathName, Boolean reverse, AnimationEvent event) {
        myAnimationEvents.get(event).addPathEffect(
                                                   new PathEffect(myPaths.get(pathName).get(0),
                                                                  myPaths.get(pathName).get(1),
                                                                  reverse, event));
    }

    public void makeRotateEffect (Double rotation, AnimationEvent event) {
        myAnimationEvents.get(event)
                .addRotateEffect(new RotateEffect(rotation, myAnimationEvents.get(event)));
    }

    public void addPath (String name, Double[] xCoord, Double[] yCoord) {
        myPaths.put(name, new ArrayList<Double[]>(Arrays.asList(xCoord, yCoord)));
    }

    public AnimationEvent cloneAnimationEvent (String eventName) {
        return myAnimationEvents.get(eventName).clone();
    }
    
    public List<AnimationEvent> cloneAnimationSequence (String sequenceName){
    	List<AnimationEvent> sequence = myAnimationSequences.get(sequenceName);
    	List<AnimationEvent> clonedSequence = new ArrayList<>();
    	
    	for (AnimationEvent event: sequence){
    		clonedSequence.add(event.clone());
    	}
    	for(int i = 0; i < clonedSequence.size()-1; i++){
    		clonedSequence.get(i).setNextEvent(clonedSequence.get(i+1));
    	}
    	return clonedSequence;
    }
    
    public void makeAnimationSequence(String sequenceName, List<AnimationEvent> eventsList){
    	for (int i = 0; i < eventsList.size() - 1; i++){
    		eventsList.get(i).setNextEvent(eventsList.get(i + 1));
    	}
    	myAnimationSequences.put(sequenceName, eventsList);
    }
    public Collection<String> getPathNames(){
        return Collections.unmodifiableCollection(myPaths.keySet());
    }
    
    public Map<String, AnimationEvent> getMyAnimationEvents() {
		return myAnimationEvents;
	}

	public Map<String, List<Double[]>> getMyPaths() {
		return myPaths;
	}

	public Map<String, List<AnimationEvent>> getMyAnimationSequences() {
		return myAnimationSequences;
	}
}
