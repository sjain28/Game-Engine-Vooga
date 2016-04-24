package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import gameengine.Sprite;
import physics.IPhysicsEngine;
import player.leveldatamanager.ILevelData;

public class CollisionCause extends Cause{

	private List<Sprite> groupA;
	private List<Sprite> groupB;	
	private List<Sprite> collidedSprites; 
	private String archA;
	private String archB;
	private String myDirection; //Can be the Strings Horizontal, Above, or Below
	private Map<String, Integer> collisionDirections;
	private ResourceBundle directions = ResourceBundle.getBundle("EventMethods");
	
	public CollisionCause(String archetypeA, String archetypeB, VoogaEvent voogaEvent){ //Simple Collision
		super(voogaEvent);
		archA = archetypeA;
		archB = archetypeB;
		initMap();
	}
	
	public CollisionCause(String archetypeA, String archetypeB, String direction, VoogaEvent event){
		this(archetypeA, archetypeB, event);
		myDirection = direction;
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
		IPhysicsEngine physics = data.getPhysicsEngine();

		for(Sprite a: groupA){
			for(Sprite b: groupB){
				if(myDirection == null){
					if((physics.checkCollisionX(a, b) != 0) || (physics.checkCollisionY(a, b) != 0)){
						addSprites(a,b);
						myVal = true;
					}
				}else{
					myVal = handleCollision(a,b,data);
				}
			}
		}
		getEvent().addSpritesFromCause(collidedSprites);
		return myVal;
	}
	
	private void addSprites(Sprite a, Sprite b){
		collidedSprites.add(a);
		collidedSprites.add(b);
	}
	
	private boolean handleCollision(Sprite a, Sprite b, ILevelData data){
		IPhysicsEngine physics = data.getPhysicsEngine();

		if(myDirection.equals(directions.getString("Horizontal"))){
			if(physics.checkCollisionX(a, b) != 0){
				addSprites(a,b);
				return true;
			}
			return false;
		}
		else{
			if(physics.checkCollisionY(a, b) == collisionDirections.get(myDirection)){
				addSprites(a,b);
				return true;
			}
			return false;
		}
	}
	
	private void initMap(){
		collisionDirections = new HashMap<>();
		collisionDirections.put(directions.getString("Above"), -1);
		collisionDirections.put(directions.getString("Below"), 1);
	}
	
	public List<Sprite> getAllCollidedSprites(){
		return collidedSprites;
	}
	
	@Override
	public String toString(){
		return archA + " is colliding with " + archB;
	}
}
