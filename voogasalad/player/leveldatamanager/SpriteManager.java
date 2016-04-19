package player.leveldatamanager;

import java.util.List;
import gameengine.Sprite;
import physics.IPhysicsEngine;

/**In charge of taking in a Map of Sprite's and updating them
 * on each step, according to present physics effects
 * TODO: Possibly name this something different
 * 
 * @author Krista
 *
 */
public class SpriteManager {
	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 * 
	 */
	public void update(List<Sprite> sprites, IPhysicsEngine physics) {
		for(Sprite s: sprites){
			applyGravity(s, physics);
			s.update();
		}
	}
	/**
	 * Using gravity field of each sprite, updates sprites' velocity
	 * 
	 */
	private void applyGravity(Sprite e, IPhysicsEngine physics) {
		double gravityMagnitude = (double) ((Sprite) e).getProperty("gravity").getValue();
		physics.gravity((Sprite) e, gravityMagnitude);
	}
}