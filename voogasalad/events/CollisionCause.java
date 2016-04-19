package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;

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
	
	public void init(){
	        groupA = new ArrayList<>();
	        groupB = new ArrayList<>();
		if(archA.contains("-") && archB.contains("-")){		
			groupA.add(getEvent().getManager().getSprite(archA)); //If contains dash, it's a Sprite ID
			groupB.add(getEvent().getManager().getSprite(archB));
		}
		else{
			List<String> IdA = getEvent().getManager().getSpriteIDs(archA);//Else, it's an arch name
			List<String> IdB = getEvent().getManager().getSpriteIDs(archB);
			
			for(String o: IdA){	
				groupA.add(getEvent().getManager().getSprite(o));
			}
			for(String o: IdB){
				groupB.add(getEvent().getManager().getSprite(o));
			}
		}
		

		

		collidedSprites = new ArrayList<>();
	}
	
	@Override
	public boolean check() {
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
