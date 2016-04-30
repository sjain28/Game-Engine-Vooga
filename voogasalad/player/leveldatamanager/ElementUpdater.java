package player.leveldatamanager;

import java.util.Map.Entry;
import java.util.Set;

import authoring.interfaces.Elementable;
import gameengine.Sprite;
import physics.IPhysicsEngine;
import resources.VoogaBundles;

/**In charge of taking in a Map of Sprite's and updating them
 * on each step, according to present physics effects
 * TODO: Possibly name this something different
 * 
 * @author Krista
 *
 */
public class ElementUpdater {
	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 * 
	 */
	public void update(ILevelData leveldata) {
		Set<Entry<String, Elementable>> elements = leveldata.getElementables();
		elements.stream().forEach((elempair) -> {
			Elementable elem = elempair.getValue();
			if(elem instanceof Sprite){
				if(!(Boolean) ((Sprite) elem).getProperty(VoogaBundles.spriteProperties.getString("ALIVE")).getValue()){
					leveldata.removeSpriteByID(elem.getId());
				}
				else{applyGravity((Sprite) elem,leveldata.getPhysicsEngine());}
			}
			elem.update();
		});
	}
	/**
	 * Using gravity field of each sprite, updates sprites' velocity
	 * 
	 */
	private void applyGravity(Sprite e, IPhysicsEngine physics) {		
		double gravityMagnitude = (double) ((Sprite) e).getProperty(VoogaBundles.spriteProperties.getString("GRAVITY")).getValue();
		physics.gravity((Sprite) e, gravityMagnitude);
	}
}
