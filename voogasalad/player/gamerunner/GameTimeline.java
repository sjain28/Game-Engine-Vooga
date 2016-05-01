/**
 * 
 */
package player.gamerunner;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Class that serves as a Timeline for GameRunner that contains extra calls
 * to be included in its IGameRunner API.
 * 
 * @author Hunter Lee
 *
 */
@Deprecated
public class GameTimeline {
	
    private static final double FRAME_RATE = 60;
    private static final double MILLISECOND_DELAY = 1000 / FRAME_RATE;
//    private static final double SPEEDCONTROL = 10;
    
	private Timeline myTimeline;

	/**
	 * Default constructor
	 * 
	 */
	public GameTimeline() {
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

	private Object step() {
		return null;
	}
	
}
