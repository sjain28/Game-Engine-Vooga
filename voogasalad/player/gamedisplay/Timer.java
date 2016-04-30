/**
 * 
 */
package player.gamedisplay;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * CountdownTimer that provides a label with a given time that decrements every second
 * Used in HUD as a JavaFX component (Label)
 * 
 * @author Hunter
 *
 */
@Deprecated
public class Timer {

	// Default STARTTIME is 0 seconds
	private static final Integer STARTTIME = 0;
	private static final String TIMER_STRING = "Time: ";
	private Timeline timeline;
	private Label timerLabel;
	private Integer timeSeconds = STARTTIME;

	/**
	 * Default constructor
	 * 
	 */
	public Timer() {
		timerLabel = new Label();
		initialize();

	}

	/**
	 * Constructor that takes seconds as a parameter to count down from
	 * 
	 * @param seconds
	 */
	public Timer(int seconds) {
		this();
		timeSeconds = seconds;
	}

	/**
	 * Initializes timeline and Start time and creates a dynamic label
	 * timerLabel is updated every step in the timeline (every second)
	 * 
	 */
	private void initialize() {
		if (timeline != null) {
			timeline.stop();
		}
		timeSeconds = STARTTIME;

		// update timerLabel
		timerLabel.setText(TIMER_STRING + timeSeconds.toString());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),new EventHandler() {
					public void handle(Event event) {timeSeconds++;
						timerLabel.setText(TIMER_STRING + timeSeconds.toString());}}));
		timeline.playFromStart();
	}

	/**
	 * Returns the label to be used in HUD
	 * 
	 * @return timerLabel;
	 */
	public Label getTimeLabel() {
		return timerLabel;
	}
	public int getTime(){
		return timeSeconds;
	}
}