package player.leveldatamanager;

import java.util.Map.Entry;
import java.util.Set;

import authoring.interfaces.Elementable;
import gameengine.Sprite;
import physics.IPhysicsEngine;
import resources.VoogaBundles;

/**In charge of taking in a Map of Elements and updating them
 * on each step, according to present physics effects
 * 
 * @author Krista
 *
 */
public class ElementUpdater {
	/**
	 * Element Variable Keys
	 */
	private String myAliveKey;
	private String myGravityKey;
	
	public ElementUpdater(){
		myAliveKey = VoogaBundles.spriteProperties.getString("ALIVE");
		myGravityKey = VoogaBundles.spriteProperties.getString("GRAVITY");
	}
	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 */
	public void update(ILevelData leveldata) {
		Set<Entry<String, Elementable>> elements = leveldata.getElementables();
		elements.stream().forEach((elempair) -> {
			Elementable elem = elempair.getValue();
			if(elem instanceof Sprite){
				if((Boolean) ((Sprite) elem).getProperty(myAliveKey).getValue()){
					applyGravity((Sprite) elem,leveldata.getPhysicsEngine());
				}
			}
			elem.update();
		});
	}
	/**
	 * Using gravity field of each sprite, updates Sprites' velocity
	 */
	private void applyGravity(Sprite e, IPhysicsEngine physics) {		
		double gravityMagnitude = (double) ((Sprite) e).getProperty(myGravityKey).getValue();
		physics.gravity((Sprite) e, gravityMagnitude);
	}
}
