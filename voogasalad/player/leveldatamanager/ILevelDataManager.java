/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import javafx.scene.Node;

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