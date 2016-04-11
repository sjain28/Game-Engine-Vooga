/**
 * 
 */
package player.gamedisplay;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * CountdownTimer that provides a label with a given time that decrements every second
 * Used in HUD
 * 
 * @author Hunter
 *
 */
public class CountdownTimer {
	
    private static final Integer STARTTIME = 60;
    private static final String TIMER_STRING = "Time remaining: ";
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTTIME;
    
    /**
     * Default constructor
     * 
     */
    public CountdownTimer() {
    	initialize();
 
    }
    
    /**
     * Constructor that takes seconds as a parameter to count down from
     * 
     * @param seconds
     */
    public CountdownTimer(int seconds) {
    	this();
    	timeSeconds = seconds;
    }
    
    /**
     * Initializes timeline and Starttime and creates a dynamic label
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
     * @return
     */
    public Label getTimeString() {
    	return timerLabel;
    }
}
