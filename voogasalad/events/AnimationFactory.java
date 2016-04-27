package events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationFactory {
	
	private Map<String, AnimationEvent> myAnimationEvents;
	private Map<String, List<Double[]>> myPaths;

	public AnimationFactory() {
		myAnimationEvents = new HashMap<String, AnimationEvent>();
		myPaths = new HashMap<String, List<Double[]>>();
	}
	
	public void makeAnimationEvent(String name){
		AnimationEvent newEvent = new AnimationEvent(name);
		myAnimationEvents.put(name, newEvent);
	}
	
	public void makePathEffect(String pathName, AnimationEvent event){
		myAnimationEvents.get(event).addPathEffect(
				new PathEffect(myPaths.get(pathName).get(0), myPaths.get(pathName).get(1), myAnimationEvents.get(event)));
	}
	
	public void makeRotateEffect(Double rotation, AnimationEvent event){
		myAnimationEvents.get(event).addRotateEffect(new RotateEffect(rotation, myAnimationEvents.get(event)));
	}
	
	public void addPath(String name, Double[] xCoord, Double[] yCoord){
		myPaths.put(name, new ArrayList<Double[]>(Arrays.asList(xCoord, yCoord)));
	}
	
	public AnimationEvent cloneAnimationEvent(String eventName){
		AnimationEvent originalEvent = myAnimationEvents.get(eventName);
		AnimationEvent newEvent = new AnimationEvent(originalEvent.getName());
		newEvent.addRotateEffect(originalEvent.getRotateEffect());
		newEvent.addPathEffect(originalEvent.getPathEffect());
		return newEvent;
	}
	
}
