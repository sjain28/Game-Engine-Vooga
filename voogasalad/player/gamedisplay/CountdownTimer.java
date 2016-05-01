/**
 * 
 */
package player.gamedisplay;


/**
 * CountdownTimer that provides a label with a given time that decrements every second
 * Used in HUD as a JavaFX component (Label)
 * 
 * @author Hunter
 *
 */
@Deprecated
public class CountdownTimer {
/*
	// Default STARTTIME is 1 minute (60 seconds)
	private static final Integer STARTTIME = 60;
	private static final String TIMER_STRING = "Time remaining: ";
	private Timeline timeline;
	private Label timerLabel;
	private Integer timeSeconds = STARTTIME;

	/**
	 * Default constructor
	 * 
	 */
/*
	public CountdownTimer() {
		timerLabel = new Label();
		initialize();

	}

	/**
	 * Constructor that takes seconds as a parameter to count down from
	 * 
	 * @param seconds
	 */
/*
	public CountdownTimer(int seconds) {
		this();
		timeSeconds = seconds;
	}

	/**
	 * Initializes timeline and Start time and creates a dynamic label
	 * timerLabel is updated every step in the timeline (every second)
	 * 
	 */
/*
	private void initialize() {
		if (timeline != null) {
			timeline.stop();
		}
		timeSeconds = STARTTIME;

		// update timerLabel
		timerLabel.setText(TIMER_STRING + timeSeconds.toString());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(1),
						new EventHandler() {
					// KeyFrame event handler
					@Override
					public void handle(Event event) {
						timeSeconds--;
						// update timerLabel
						timerLabel.setText(
								TIMER_STRING + timeSeconds.toString());
						// Stop when time hits 0
						// Time over: losing screen can be added here
						if (timeSeconds <= 0) {
							timeline.stop();
						}
					}

				}));
		timeline.playFromStart();
	}

	/**
	 * Returns the label to be used in HUD
	 * 
	 * @return timerLabel;
	 */
/*
	public Label getTimeString() {
		return timerLabel;
	}
*/
}
