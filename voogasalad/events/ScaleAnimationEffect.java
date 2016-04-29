package events;

import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;

public class ScaleAnimationEffect extends SpriteEffect {

	private Double mySizeScale;
	private List<Double[]> mySizeIncrements;

	public ScaleAnimationEffect(Double sizeScale, VoogaEvent event) {
		super(event);
		mySizeScale = sizeScale;
		setNeedsSprites(true);

	}

	@Override
	public void execute(ILevelData data){
		setSprites(data);
		createSizeIncrements();
		for (int i = 0; i < mySizeIncrements.size(); i++){
			getSprites().get(i).setHeight((Double) getSprites().get(i).getProperty(VoogaBundles.spriteProperties.getString("HEIGHT")).getValue() 
					+ mySizeIncrements.get(i)[0]);
			getSprites().get(i).setWidth((Double) getSprites().get(i).getProperty(VoogaBundles.spriteProperties.getString("WIDTH")).getValue() 
					+ mySizeIncrements.get(i)[1]);
		}

		clearSprites();
	}
	public void createSizeIncrements(){
		if (mySizeIncrements == null){
			for (Sprite sprite : getSprites()){
				Double origSizeX = (Double) sprite.getWidth().getValue();
				Double origSizeY = (Double) sprite.getHeight().getValue();
				Double newSizeX = origSizeX * mySizeScale;
				Double newSizeY = origSizeY * mySizeScale;

				mySizeIncrements.add(new Double[]{(newSizeX - origSizeX)/((AnimationEvent) getEvent()).getDuration(), 
						(newSizeY - origSizeY)/((AnimationEvent) getEvent()).getDuration()});
			}
		}
	}
}
