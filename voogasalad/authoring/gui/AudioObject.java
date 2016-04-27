package authoring.gui;

import java.util.Map;
import java.util.ResourceBundle;

import authoring.interfaces.Elementable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import resources.VoogaBundles;
import tools.VoogaException;
import tools.interfaces.VoogaData;

/**
 * Object in java that represents audio. 
 * 
 * @author Aditya Srinivasan, Nick Lockett, Arjun Desai, Harry Guo
 *
 */
public class AudioObject extends ImageView implements Elementable {

	private MediaPlayer player;
	private ResourceBundle gameDisplayProperties;

	/**
	 * Constructor that takes in a media player.
	 * Assigns an image to represent the audio clip in the resource explorer.
	 * On click, plays the clip.
	 * 
	 * @param player: to play audio
	 */
	public AudioObject(MediaPlayer player) {
		this.player = player;
		gameDisplayProperties = VoogaBundles.GameDisplayProperties;
		this.setImage(new Image(gameDisplayProperties.getString("AudioObjectImage")));
		this.setOnMouseClicked(e -> {
			player.play();
		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, VoogaData> getVoogaProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVoogaProperties(Map<String, VoogaData> newVoogaProperties) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProperty(String name, VoogaData data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProperty(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getNodeObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() throws VoogaException {
		// TODO Auto-generated method stub

	}

}
