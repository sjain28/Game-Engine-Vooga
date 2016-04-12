/**
 * 
 */
package player.leveldatamanager;

import java.util.List;

import authoring.interfaces.Elementable;
import authoring.model.VoogaText;
import gameengine.Sprite;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

/**
 * LevelDataManager interface that declares public methods
 * to be used throughout Game Player
 * 
 * @author Hunter Lee
 *
 */
public interface ILevelDataManager {

//	Sprite getSprite(String id);
//	
//	List<String> getSpriteIDs(String archetype);
//	
//	Elementable addSprite(String archetype);
//	
//	void removeSprite(Object id);
//	
//	VoogaData getGlobalVar(String variable);
//	
//	VoogaText getText(Object id);
	
	List<Node> getDisplayableObjects(); 
	
	void update();
}
