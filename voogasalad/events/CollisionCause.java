package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;

public class CollisionCause extends Cause{

	private List<Sprite> groupA;
	private List<Sprite> groupB;	
	private List<Sprite> collidedSprites; 
	
	public CollisionCause(List<Object> groupAID, List<Object> groupBID, VoogaEvent voogaEvent){ 
		super(voogaEvent);
		init(groupAID, groupBID);
	}
	
	public CollisionCause(String archetypeA, String archetypeB, VoogaEvent voogaEvent){ //Given 2 archetype names
		super(voogaEvent);
		List<Object> groupA = getEvent().getManager().getSpriteIDs(archetypeA);
		List<Object> groupB = getEvent().getManager().getSpriteIDs(archetypeB);
		init(groupA, groupB);
	}
	
	public void init(List<Object> groupAID, List<Object> groupBID){
		for(Object o: groupAID){
			groupA.add(getEvent().getManager().getSprite(o));
		}
		for(Object o: groupBID){
			groupB.add(getEvent().getManager().getSprite(o));
		}
		collidedSprites = new ArrayList<Sprite>();
	}
	
	@Override
	public boolean check() {
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
		return myVal;
	}
	
	public List<Sprite> getAllCollidedSprites(){
		return collidedSprites;
	}
	
	

}
