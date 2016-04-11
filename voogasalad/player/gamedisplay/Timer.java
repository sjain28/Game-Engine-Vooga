package player.gamedisplay;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Timer class that extends Label
 * 
 * @author Hunter Lee
 *
 */
public class Timer extends Label
{
	private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	public Timer()
	{
		bindToTime();
	}

	private void bindToTime() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}