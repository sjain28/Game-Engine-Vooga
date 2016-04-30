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

	public AnimationFactory () {
		myAnimationEvents = new HashMap<>();
		myPaths = new HashMap<>();
		myAnimationSequences = new HashMap<>();
		myPaths = new HashMap<>();
	}

	private static class SingletonHolder {
		private static final AnimationFactory INSTANCE = new AnimationFactory();
	}

	public static AnimationFactory getInstance () {
		return SingletonHolder.INSTANCE;
	}

	public AnimationEvent makeAnimationEvent (String name, Integer duration) {
		System.out.println("making animation event");
		AnimationEvent newEvent = new AnimationEvent(name, duration);
		myAnimationEvents.put(name, newEvent);
		System.out.println("Event map size: " + myAnimationEvents.size());
		return newEvent;
	}

	public void makePathEffect (String pathName, Boolean reverse, AnimationEvent event) {
		System.out.println("Size of path points " + myPaths.get(pathName).get(0).length + ", " + myPaths.get(pathName).get(1).length);
		event.addPathEffect(
				new PathEffect(myPaths.get(pathName).get(0),
						myPaths.get(pathName).get(1),
						reverse, event));
	}

	public void makeRotateEffect (Double rotation, AnimationEvent event) {
		event.addRotateEffect(new RotateEffect(rotation, event));
	}
	public void makeScaleAnimationEffect(Double scale, AnimationEvent event){
		event.addScaleAnimationEffect(new ScaleAnimationEffect(scale, event));
	}
	public void makeImageAnimationEffect(List<String> images, Integer cycles, AnimationEvent event){
		event.addImageAnimationEffect(new ImageAnimationEffect(images, cycles, event));

	}

	public void addPath (String name, Double[] xCoord, Double[] yCoord) {
		myPaths.put(name, new ArrayList<Double[]>(Arrays.asList(xCoord, yCoord)));
	}

	public AnimationEvent cloneAnimationEvent (String eventName) {
		System.out.println("Events map size during cloning" + myAnimationEvents.size());
		System.out.println("Event name during cloning " + eventName);
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
	public void setMyPaths(Map<String, List<Double[]>> myPaths) {
		this.myPaths = myPaths;
	}
	public void setMyAnimationSequences(Map<String, List<AnimationEvent>> myAnimationSequences) {
		this.myAnimationSequences = myAnimationSequences;
	}

	public void setMyAnimationEvents(Map<String, AnimationEvent> myAnimationEvents) {
		this.myAnimationEvents = myAnimationEvents;
	}
}
