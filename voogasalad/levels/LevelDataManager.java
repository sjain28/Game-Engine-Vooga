package levels;

import java.util.List;

import events.Event;
import gameengine.EventManager;
import gameengine.Sprite;
import gameengine.SpriteManager;
import gameengine.Variable;
import gameengine.VariableManager;

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
