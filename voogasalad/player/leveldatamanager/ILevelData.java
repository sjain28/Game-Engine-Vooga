/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import authoring.interfaces.Elementable;
import events.AnimationEvent;
import events.VoogaEvent;
import gameengine.Sprite;
import javafx.scene.Node;
import physics.IPhysicsEngine;
import tools.Pair;
import tools.interfaces.VoogaData;

/**
 * LevelData interface that provides public methods related to LevelData
 * 
 * @author Hunter Lee
 *
 */
public interface ILevelData {

	void refreshLevelData(String levelfilename);

	void setNextLevelName(String levelNumber);

	void removeSpriteByID(String id);

	void saveProgress(String filePath);

	void addEventAndPopulateKeyCombos(VoogaEvent event);

	void updatedGlobalTimer(double time);

	boolean getSaveNow();

	String getNextLevelName();

	Boolean containsSprite(String id);

	Sprite getMainSprite();

	Sprite getSpriteByID(String id);

	Sprite addSprite(String archetype);

	IPhysicsEngine getPhysicsEngine();

	VoogaData getGlobalVar(String myVarName);

	AnimationEvent getAnimationFromFactory(String myAnimationName);

	KeyEventContainer getKeyEventContainer();

	List<Pair<Node, Boolean>> getDisplayableNodes();

	List<Sprite> getSpritesByArch(String archA);

	Set<Entry<String, Elementable>> getElementables();

	Map<String,VoogaData> getGlobalVariables();

	Map<String, Elementable> getElements();
}
