package Levels;

import java.util.List;

import GameEngine.EventManager;
import GameEngine.Sprite;
import GameEngine.SpriteManager;
import GameEngine.Variable;
import GameEngine.VariableManager;
import events.Event;

public class LevelManager {

	SpriteManager spriteManager;
	EventManager eventManager;
	VariableManager variableManager;
	
	public LevelManager() {
		// TODO Auto-generated constructor stub
	}

	public LevelManager(List<Sprite> spriteObjects, List<Event> eventObjects, List<Variable> variableObjects) {
		// TODO Auto-generated constructor stub
		//spriteManager = new SpriteManager(spriteObjects);
		//eventManager = new EventManager(eventObjects);
		//variableManager = new VariableManager(VariableObjects);
	}

}
