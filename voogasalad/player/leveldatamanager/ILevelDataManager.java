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

	List<Node> getDisplayableObjects(); 

	void update();

}