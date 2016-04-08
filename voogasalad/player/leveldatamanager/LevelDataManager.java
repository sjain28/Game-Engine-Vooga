package Player.leveldatamanager;

import java.util.List;

import events.VoogaEvent;
import gameengine.EventManager;
import gameengine.Sprite;
import gameengine.SpriteManager;
import gameengine.Variable;
import gameengine.GlobalVariableManager;

public class LevelDataManager {

	SpriteManager spriteManager;
	EventManager eventManager;
	GlobalVariableManager variableManager;
	
	public LevelDataManager() {
		// TODO Auto-generated constructor stub
	}

	public LevelDataManager(List<Sprite> spriteObjects, List<VoogaEvent> eventObjects, List<Variable> variableObjects) {
		// TODO Auto-generated constructor stub
		//spriteManager = new SpriteManager(spriteObjects);
		//eventManager = new EventManager(eventObjects);
		//variableManager = new VariableManager(VariableObjects);
	}

}
