/**
 * 
 */
package tools;

import java.util.Map;
import javafx.scene.media.MediaPlayer;

/**
 * IGameSound interface that provides public API methods that are used
 * to play different sounds such as background music and sound effects
 * 
 * @author Hunter Lee
 *
 */
public interface IVoogaGameSound {
	
	public Map<String, MediaPlayer> getMediaMap();
	
	public void play(String soundname);
	
	public void playBGM();
	
	public void stopBGM();
	
	public void pauseBGM();

}
