package player.leveldatamanager;

import java.util.Set;
import java.util.Map.Entry;

import gameengine.BackEndText;
import authoring.interfaces.Elementable;
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
	private static final String ALIVE = "Alive";
	/**
	 * This method updates each sprite's Position
	 * before Events (causes and effects) are applied
	 * 
	 */
	public void update(ILevelData leveldata) {
		System.out.println("HERE :D");
		Set<Entry<String, Elementable>> elements = leveldata.getElementables();
		elements.stream().forEach((elempair) -> {
			Elementable elem = elempair.getValue();
			if(elem instanceof Sprite){
				System.out.println("here");
				if((Boolean) ((Sprite) elem).getProperty(ALIVE).getValue() == false){
					System.out.println("its dead");
					elements.remove(elem);
					System.out.println(elements.contains(elem));
				}
				else{
					applyGravity((Sprite) elem,leveldata.getPhysicsEngine());
					((Sprite) elem).update();
				}
			}
			if(elem instanceof BackEndText){
				((BackEndText) elem).update();
			}
		});
		
		/*
		for(int i = 0; i < sprites.size(); i++){
			Sprite s = sprites.get(i);
			for(String key : s.getPropertiesMap().keySet()){
				VoogaData data = s.getProperty(key);
				Object obj = data.getValue();
			}
			//Clean up all dead Sprite's
			if((Boolean) s.getProperty(ALIVE).getValue() == false){
				leveldata.removeSprite(s.getId());
			}
			else{
				//Apply gravity to all Sprites
				applyGravity(s, leveldata.getPhysicsEngine());
				s.update();
			}
		}
		*/
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
