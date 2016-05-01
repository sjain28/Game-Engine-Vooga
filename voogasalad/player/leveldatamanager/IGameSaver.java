package player.leveldatamanager;

/**
 * GameSaver interface that provides public methods that save current progress
 * 
 * @author Hunter Lee
 *
 */
public interface IGameSaver {
	
	default void save(String fileName){
		
	}
	
	void saveCurrentProgress(String nameOfGame);
}
