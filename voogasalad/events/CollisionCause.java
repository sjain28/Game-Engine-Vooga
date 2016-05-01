package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.Sprite;
import physics.IPhysicsEngine;
import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;

/**
 * 
 * @author Saumya Jain
 * This class detects collisions between Sprites
 *
 */
 
public class CollisionCause extends Cause{

	private List<Sprite> collidedSprites; 
	private String archA;
	private String archB;
	private String myDirection = VoogaBundles.EventMethods.getString("None"); 
	private Map<String, Integer> collisionDirections;

	/**
	 * @param spriteA Archetype or ID of a Sprite in the collision
	 * @param spriteB Archetype or ID of a different Sprite
	 * @param voogaEvent An event that this cause triggers
	 */
	public CollisionCause(String spriteA, String spriteB, VoogaEvent event){
		super(event);
		archA = spriteA;
		archB = spriteB;
		initMap();
		collidedSprites = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param spriteA Archetype or ID of a Sprite in the collision
	 * @param spriteB Archetype or ID of a different Sprite
	 * @param voogaEvent An event that this cause triggers
	 * @param direction The direction of the collision (Above, Below, Horizontal)
	 */
	public CollisionCause(String spriteA, String spriteB, String direction, VoogaEvent event){
		this(spriteA, spriteB, event);
		myDirection = direction;
	}
	/**
	 * 
	 * @param data A LevelData object containing Sprites
	 * @param arch A spriteID or archetype name 
	 * @return
	 */
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
	/**
	 * Checks for collisions between the groups of Sprites specified in the constructor
	 */
	@Override
	public boolean check(ILevelData data) {
		
		collidedSprites = new ArrayList<>();
		boolean myVal = false;
		IPhysicsEngine physics = data.getPhysicsEngine();
		for(Sprite a: getSprites(data,archA)){
			for(Sprite b: getSprites(data,archB)){
				if(myDirection.equals(VoogaBundles.EventMethods.getString("None"))){
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
		getEvent().addSpritesFromCause(collidedSprites);
		return myVal;
	}
	/**
	 * Adds Sprites that have collided to a list that's updated every cycle
	 * @param a Sprite
	 * @param b Sprite
	 */
	private void addSprites(Sprite a, Sprite b){
		collidedSprites.add(a);
		collidedSprites.add(b);
	}
	/**
	 * Queries physics engine to determine if two sprites are colliding
	 * @param a A sprite being checked for collisions
	 * @param b Being checked for collision with a
	 * @param data Leveldata object
	 * @return whether A and B are colliding in the direction specified by the user
	 */
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
			if(physics.checkCollisionY(a, b) == collisionDirections.get(myDirection)){
				addSprites(a,b);
				return true;
			}
			return false;
		}
	}
	/**
	 * Initializes the mapping of collision directions to numerical values returned by physics
	 */
	private void initMap(){
		collisionDirections = new HashMap<>();
		collisionDirections.put(VoogaBundles.EventMethods.getString("Above"), 1);
		collisionDirections.put(VoogaBundles.EventMethods.getString("Below"), -1);
	}
	/**
	 * @return all sprites that have been involved in collisions during a cycle
	 */
	public List<Sprite> getAllCollidedSprites(){
		return collidedSprites;
	}
	
	@Override
	public String toString(){
		return archA + " is colliding with " + archB;
	}
}
