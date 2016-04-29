package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;

public class ScaleAnimationEffect extends SpriteEffect {

	private Double mySizeScale;
	private Map<Sprite, Double[]> mySizeIncrements;

	public ScaleAnimationEffect(Double sizeScale, VoogaEvent event) {
		super(event);
		mySizeScale = sizeScale;
		setNeedsSprites(true);

	}

	@Override
	public void execute(ILevelData data){
		setSprites(data);
		createSizeIncrements();
		for (Sprite sprite : getSprites()){
			sprite.setHeight( (Double) sprite.getProperty(VoogaBundles.spriteProperties.getString("HEIGHT")).getValue() + mySizeIncrements.get(sprite)[0]);
			sprite.setWidth( (Double) sprite.getProperty(VoogaBundles.spriteProperties.getString("WIDTH")).getValue() + mySizeIncrements.get(sprite)[1]);
		}

		clearSprites();
	}
	public void createSizeIncrements(){
		if (mySizeIncrements == null){
			mySizeIncrements = new HashMap<>();
			for (Sprite sprite : getSprites()){
				Double origSizeX = (Double) sprite.getWidth().getValue();
				Double origSizeY = (Double) sprite.getHeight().getValue();
				Double newSizeX = origSizeX * mySizeScale;
				Double newSizeY = origSizeY * mySizeScale;

				mySizeIncrements.put(sprite, new Double[]{(newSizeX - origSizeX)/((AnimationEvent) getEvent()).getDuration(), 
						(newSizeY - origSizeY)/((AnimationEvent) getEvent()).getDuration()});
			}
		}
	}
}
