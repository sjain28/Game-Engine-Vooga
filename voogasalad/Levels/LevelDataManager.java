package Levels;

import java.util.List;

import GameEngine.EventManager;
import GameEngine.Sprite;
import GameEngine.SpriteManager;
import GameEngine.Variable;
import GameEngine.VariableManager;
import events.Event;

public class LevelDataManager {

	SpriteManager spriteManager;
	EventManager eventManager;
	VariableManager variableManager;
	
	public LevelDataManager() {
		// TODO Auto-generated constructor stub
	}

	public LevelDataManager(List<Sprite> spriteObjects, List<Event> eventObjects, List<Variable> variableObjects) {
		// TODO Auto-generated constructor stub
		//spriteManager = new SpriteManager(spriteObjects);
		//eventManager = new EventManager(eventObjects);
		//variableManager = new VariableManager(VariableObjects);
	}

}
