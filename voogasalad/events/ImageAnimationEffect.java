package events;

import java.util.Collections;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class ImageAnimationEffect extends SpriteEffect {

	private List<String> myImages;
	private Integer myCycleTime;
	private Integer myCounter;
	private Integer myCycles;
	private Integer myCurrentImageInt;

	public ImageAnimationEffect(List<String> images, Integer numCycles, AnimationEvent voogaEvent) {
		super(voogaEvent);
		setNeedsSprites(true);
		myCycles = numCycles;
		myImages = images;
		myCurrentImageInt = 0;
		myCounter = myCycleTime;
	}
	protected void setCycleTime(Integer duration){
		myCycleTime = (duration / myCycles) / myImages.size();
	}
	
	// TODO: make image strings into images
	@Override
	public void execute(ILevelData data) {
		setSprites(data);	
		myCounter++;
		for (Sprite sprite : getSprites()){
			if (myCounter >= myCycleTime){	
				//sprite.getImage().setImage(myImages.get(myCurrentImageInt));
				myCurrentImageInt++;
				myCounter = 0;
			}
		}
		if(myCurrentImageInt >= myImages.size()){
			myCurrentImageInt = 0;
		}
		clearSprites();
	}

	@Override
	public String toString() {
		return "";
	}

	public ImageAnimationEffect clone(AnimationEvent event) {
		return new ImageAnimationEffect(Collections.unmodifiableList(myImages), myCycles, (AnimationEvent) getEvent());
	}
	public void setCounter(Integer count){
		myCounter = count;
	}
}
