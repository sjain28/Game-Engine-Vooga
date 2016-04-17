package player.leveldatamanager;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 * LevelDataManager interface that declares public methods
 * to be used throughout Game Player
 * 
 * @author Hunter Lee
 *
 */
public interface ILevelDataManager {

	List<Node> getDisplayableObjects(); 

	void update();
	
	void setKeyEvents(List<?> myKeyEvents);
	
	int getNextLevel();
	
	public void initialize(String levelFileName);

	void clear();

}