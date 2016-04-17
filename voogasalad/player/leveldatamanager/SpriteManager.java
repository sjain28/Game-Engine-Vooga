package player.leveldatamanager;

import java.util.List;
import gameengine.Sprite;
import physics.IPhysicsEngine;
import physics.StandardPhysics;

/**In charge of taking in a Map of Sprite's and updating them
 * on each step, according to present physics effects
 * TODO: Possibly name this something different
 * 
 * @author Krista Opsahl-Ong
 *
 */
public class SpriteManager {
	private IPhysicsEngine myPhysics;
	
	public SpriteManager(){
		myPhysics = new StandardPhysics();
	}
	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 * 
	 */
	public void update(List<Sprite> sprites) {
		for(Sprite s: sprites){
			applyGravity(s);
			s.update();
		}
	}
	
	/**
	 * Using gravity field of each sprite, updates sprites' velocity
	 * 
	 */
	private void applyGravity(Sprite e) {
		double gravityMagnitude = (double) ((Sprite) e).getProperty("gravity").getValue();
		myPhysics.gravity((Sprite) e, gravityMagnitude);
	}
}
