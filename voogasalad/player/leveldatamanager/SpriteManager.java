package player.leveldatamanager;

import java.util.List;
import gameengine.Sprite;
import physics.IPhysicsEngine;
import tools.VoogaBoolean;
import tools.interfaces.VoogaData;

/**In charge of taking in a Map of Sprite's and updating them
 * on each step, according to present physics effects
 * TODO: Possibly name this something different
 * 
 * @author Krista
 *
 */
public class SpriteManager {
	private static final String ALIVE = "Alive";
	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 * 
	 */
	public void update(ILevelData myLevelData, IPhysicsEngine physics) {
		List<Sprite> sprites = myLevelData.getAllSprites();
		for(int i = 0; i < sprites.size(); i++){
			Sprite s = sprites.get(i);
			//Clean up all dead Sprites
			for(String key : s.getPropertiesMap().keySet()){
				System.out.println(key);
				VoogaData data = s.getProperty(key);
				Object obj = data.getValue();
				System.out.println(obj.toString());
			}
			if((Boolean) s.getProperty(ALIVE).getValue() == false){
				System.out.println("removing sprite");
				myLevelData.removeSprite(s.getId());
			}
			else{
				//Apply gravity to all Sprites
				applyGravity(s, physics);
				s.update();
			}
		}
	}
	/**
	 * Using gravity field of each sprite, updates sprites' velocity
	 * 
	 */
	private void applyGravity(Sprite e, IPhysicsEngine physics) {
		double gravityMagnitude = (double) ((Sprite) e).getProperty("Gravity").getValue();
		physics.gravity((Sprite) e, gravityMagnitude);
	}
}
