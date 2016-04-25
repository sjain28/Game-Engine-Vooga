package authoring.gui;

import java.util.Map;

import authoring.interfaces.Elementable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import tools.interfaces.VoogaData;

public class AudioObject extends ImageView implements Elementable {
	
	private MediaPlayer player;
	
	public AudioObject(MediaPlayer player) {
		this.player = player;
		this.setImage(new Image("file:resources/AUDIO.png"));
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

}
