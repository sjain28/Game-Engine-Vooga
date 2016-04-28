package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gameengine.Sprite;
import physics.IPhysicsEngine;
import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;

public class CollisionCause extends Cause{

	private List<Sprite> collidedSprites; 
	private String archA;
	private String archB;
	private String myDirection; //Can be the Strings Horizontal, Above, or Below
	private Map<String, Integer> collisionDirections;
	private List<Sprite> groupA;
	private List<Sprite> groupB;

	
	public CollisionCause(String archetypeA, String archetypeB, VoogaEvent voogaEvent){ //Simple Collision
		super(voogaEvent);
		archA = archetypeA;
		archB = archetypeB;
		initMap();
		collidedSprites = new ArrayList<>();
	}
	
	public CollisionCause(String archetypeA, String archetypeB, String direction, VoogaEvent event){
		this(archetypeA, archetypeB, event);
		myDirection = direction;
	}

	private List<Sprite> getSprites(ILevelData data, String arch){
		List<Sprite> group = new ArrayList<>();
	        
		if(arch.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){		
			group.add(data.getSpriteByID(arch)); //If contains dash, it's a Sprite ID
		}
		else{
			group.addAll(data.getSpritesByArch(arch));//Else, it's an arch name
		}
		return group;
	}
	
	@Override
	public boolean check(ILevelData data) {
		
		collidedSprites = new ArrayList<>();
		boolean myVal = false;
		IPhysicsEngine physics = data.getPhysicsEngine();
		for(Sprite a: getSprites(data,archA)){
			for(Sprite b: getSprites(data,archB)){
				if(myDirection == null){
					if((physics.checkCollisionX(a, b) != 0) || (physics.checkCollisionY(a, b) != 0)){
						addSprites(a,b);
						myVal = true;
					}
				}else{
					if(handleCollision(a,b,data)){
						myVal = true;
					}
				}
			}
		}
		System.out.println();

		getEvent().addSpritesFromCause(collidedSprites);
		return myVal;
	}
	
	private void addSprites(Sprite a, Sprite b){
		collidedSprites.add(a);
		collidedSprites.add(b);
	}
	
	private boolean handleCollision(Sprite a, Sprite b, ILevelData data){
		IPhysicsEngine physics = data.getPhysicsEngine();

		if(myDirection.equals(VoogaBundles.EventMethods.getString("Horizontal"))){
			if(physics.checkCollisionX(a, b) != 0){
				addSprites(a,b);
				return true;
			}
			return false;
		}
		else{
			
			System.out.println(physics.checkCollisionY(a, b));
			
			if(physics.checkCollisionY(a, b) == collisionDirections.get(myDirection)){
				addSprites(a,b);
				return true;
			}
			return false;
		}
	}
	
	private void initMap(){
		collisionDirections = new HashMap<>();
		collisionDirections.put(VoogaBundles.EventMethods.getString("Above"), 1);
		collisionDirections.put(VoogaBundles.EventMethods.getString("Below"), -1);
	}
	
	public List<Sprite> getAllCollidedSprites(){
		return collidedSprites;
	}
	
	@Override
	public String toString(){
		return archA + " is colliding with " + archB;
	}
}
