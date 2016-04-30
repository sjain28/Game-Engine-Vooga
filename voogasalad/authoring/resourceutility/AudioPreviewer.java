package authoring.resourceutility;

import java.nio.file.Paths;

import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * The previewer for audio, allowing users to play, pause, and stop listening
 * to whatever audio file they import.
 * @author DoovalSalad
 *
 */
public class AudioPreviewer extends Previewer {
	
	/**
	 * Constants
	 */
	private static final String PLAY = "Play";
	private static final String PAUSE = "Pause";
	private static final String STOP = "Stop";
	
	/**
	 * Inherited constructor to access the audio file
	 * @param file
	 */
	public AudioPreviewer(VoogaFile file) {
		super(file);
	}

	/**
	 * Inherited method to initialize previewing of the file
	 */
	@Override
	void preview() {
		Media audio = new Media(Paths.get(this.file.getPath()).toUri().toString());
        MediaPlayer player = new MediaPlayer(audio);
        MediaView mediaView = new MediaView(player);
        HBox mediaControls = new HBox();
        mediaControls.getChildren().addAll(new ButtonMaker().makeButton(PLAY, e -> player.play()),
										   new ButtonMaker().makeButton(PAUSE, e -> player.pause()),
										   new ButtonMaker().makeButton(STOP, e -> {
											   player.stop();
											   this.stage.close();
										   }));
        this.group.getChildren().addAll(mediaView, mediaControls);
	}

}
