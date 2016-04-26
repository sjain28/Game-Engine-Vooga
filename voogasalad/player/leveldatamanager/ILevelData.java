/**
 * 
 */
package player.leveldatamanager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import authoring.interfaces.Elementable;
import events.KeyCause;
import events.VoogaEvent;
import gameengine.Sprite;
import javafx.scene.Node;
import physics.IPhysicsEngine;
import tools.interfaces.VoogaData;

/**
 * @author Hunter Lee
 *
 */
public interface ILevelData {

	void refreshLevelData(String levelfilename);

    void removeSpriteByID(String id);
    
    Sprite getCenteredSprite();

	Set<Entry<String, Elementable>> getElementables();
    
	List<Node> getDisplayableNodes();

	Map<List<String>, KeyCause> getKeyCauses();

	Collection<VoogaEvent> getEvents();

	String getNextLevelName();

	void setNextLevelName(String levelNumber);
	
	Sprite getSpriteByID(String id);

	List<Sprite> getSpritesByArch(String archA);
	
	void removeSprite(String id);

	List<List<String>> getKeyPressCombos();
	
	VoogaData getGlobalVar(String myVarName);
	
	Sprite addSprite(String archetype);
	
	void updatedGlobalTimer(double time);

	IPhysicsEngine getPhysicsEngine();

	void saveProgress(String playerName, String filePath);

	List<List<String>> getKeyReleasedCombos();
}
