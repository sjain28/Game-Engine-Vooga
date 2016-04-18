/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import java.util.Map;

import events.KeyCause;
import gameengine.Sprite;
import javafx.scene.Node;

/**
 * @author Hunter Lee
 *
 */
public interface ILevelData {

	void refreshLevelData(String levelfilename);

	List<Sprite> getAllSprites();

	List<Node> getDisplayableNodes();

	Map<List<String>, KeyCause> getKeyCauses();

	Object getEvents();

	int getLevelNumber();

}
