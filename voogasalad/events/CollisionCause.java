package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class CollisionCause extends Cause{

	private List<Sprite> groupA;
	private List<Sprite> groupB;	
	private List<Sprite> collidedSprites; 
	private String archA;
	private String archB;
	
	public CollisionCause(String archetypeA, String archetypeB, VoogaEvent voogaEvent){ //Given 2 archetype names
		super(voogaEvent);
		archA = archetypeA;
		archB = archetypeB;
	}

	public void updateSprites(ILevelData data){
	        groupA = new ArrayList<>();
	        groupB = new ArrayList<>();
		if(archA.contains("-") && archB.contains("-")){		
			groupA.add(data.getSpriteByID(archA)); //If contains dash, it's a Sprite ID
			groupB.add(data.getSpriteByID(archB));
		}
		else{
			groupA = data.getSpritesByArch(archA);//Else, it's an arch name
			groupB = data.getSpritesByArch(archB);
		}
		collidedSprites = new ArrayList<>();
	}
	
	@Override
	public boolean check(ILevelData data) {
		updateSprites(data);
		collidedSprites.clear();
		boolean myVal = false;
		for(Sprite a: groupA){
			for(Sprite b: groupB){
				if(a.getImage().getBoundsInParent().intersects(b.getImage().getBoundsInParent())){
					myVal = true;
					collidedSprites.add(a);
					collidedSprites.add(b);					
				}
			}
		}
		getEvent().addSpritesFromCause(collidedSprites);
		return myVal;
	}
	
	public List<Sprite> getAllCollidedSprites(){
		return collidedSprites;
	}
}
