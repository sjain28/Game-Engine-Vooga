package gameengine;

import java.util.List;

import player.leveldatamanager.ObjectManager;
import player.leveldatamanager.EventManager;

/**
 * The GameEngine holds all of the managers and contains the initialize and update methods for the game. The game player
 * will have an instance of the GameEngine that allows it to update the game.
 * 
 * POTENTIALLY DELETE THIS CLASS CAUSE IT'S NOT REALLY DOING MUCH AND COULD BE HANDLED BY GAMECONTROLLER
 * REALLY JUST SERVING AS INTERMEDIARY
 */
public class LevelHandler {
	
		private ObjectManager myObjectManager;
		private EventManager myEventManager;
		
        public LevelHandler(ObjectManager objectmanager, EventManager eventmanager) {
        	myObjectManager = objectmanager;
        	myEventManager = eventmanager;
        }
		
		public void update(){
			myEventManager.update();
		}
}
