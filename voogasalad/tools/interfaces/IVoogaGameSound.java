package tools.interfaces;

/**
 * IGameSound interface that provides public API methods that are used to play
 * different sounds such as background music and sound effects
 * 
 * @author Hunter Lee
 *
 */
public interface IVoogaGameSound {
	
	/**
	 * Play background music: constant sound
	 */
	void playBGM();

	/**
	 * Stop background, restart will play from start
	 */
	void stopBGM();

	/**
	 * Pause background music, restart plays from pause point
	 */
	void pauseBGM();

}
