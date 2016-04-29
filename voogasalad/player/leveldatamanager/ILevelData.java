/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import authoring.interfaces.Elementable;
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
    
    Sprite getMainSprite();

	Set<Entry<String, Elementable>> getElementables();
    
	List<Node> getDisplayableNodes();
	
	boolean getSaveNow();

	String getNextLevelName();

	void setNextLevelName(String levelNumber);
	
	Sprite getSpriteByID(String id);

	List<Sprite> getSpritesByArch(String archA);
	
    void removeSpriteByID(String id);
	
	VoogaData getGlobalVar(String myVarName);
	
	Sprite addSprite(String archetype);
	
	void updatedGlobalTimer(double time);

	IPhysicsEngine getPhysicsEngine();

	void saveProgress(String playerName, String filePath, String gameName);
	
	KeyEventContainer getKeyEventContainer();
	
	Map<String, VoogaData> getGlobalVariables();

	Map<String, Elementable> getElements();

}
