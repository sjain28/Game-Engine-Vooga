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
	private static AnimationFactory animationFactory;

	private AnimationFactory () {
		myAnimationEvents = new HashMap<>();
		myPaths = new HashMap<>();
		myAnimationSequences = new HashMap<>();
		myPaths = new HashMap<>();
	}

	public synchronized static AnimationFactory getInstance () {
		if(animationFactory == null) {
			animationFactory = new AnimationFactory();
		}
		return animationFactory;
	}

	public AnimationEvent makeAnimationEvent (String name, Integer duration) {
		AnimationEvent newEvent = new AnimationEvent(name, duration);
		myAnimationEvents.put(name, newEvent);
		return newEvent;
	}

	public void makePathEffect (String pathName, Boolean reverse, AnimationEvent event) {
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

	public void makeAnimationSequence(String sequenceName, List<String> eventsList){
		List<AnimationEvent> eventSequence = new ArrayList<>();
		for (String event : eventsList){
			eventSequence.add(myAnimationEvents.get(event).clone());
		}
		for (int i = 0; i < eventsList.size() - 1; i++){
			eventSequence.get(i).setNextEvent(eventSequence.get(i + 1));
		}
		myAnimationSequences.put(sequenceName, eventSequence);
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

	public static void set(AnimationFactory animationFactory) {
		AnimationFactory.animationFactory = animationFactory;
	}
}
