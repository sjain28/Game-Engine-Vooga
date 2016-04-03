package authoring.resourceutility;
import javafx.scene.image.Image;

public enum VoogaFileType {
	FOLDER, IMAGE, AUDIO;
	
	private Image thumbnail;
		
	private VoogaFileType() {
		this.thumbnail = new Image("file:resources/" + this.name() + ".png");
	}
	
	public Image getFileThumbnail() {
		return this.thumbnail;
	}

}