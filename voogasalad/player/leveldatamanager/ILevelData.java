/**
 * 
 */
package player.leveldatamanager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import javafx.scene.Node;
import physics.IPhysics;
import physics.IPhysicsEngine;
import physics.StandardPhysics;
import tools.interfaces.VoogaData;

/**
 * @author Hunter Lee
 *
 */
public interface ILevelData {

	void refreshLevelData(String levelfilename);

	List<Sprite> getAllSprites();

	List<Node> getDisplayableNodes();

	Map<List<String>, KeyCause> getKeyCauses();

	Collection<VoogaEvent> getEvents();

	String getNextLevelName();

	void setNextLevelName(String levelNumber);
	
	Sprite getSpriteByID(String id);

	List<Sprite> getSpritesByArch(String archA);

	List<List<String>> getKeyCombos();

	VoogaData getGlobalVar(String myVarName);

	IPhysicsEngine getPhysicsEngine();
}
