package authoring.resourceutility;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VoogaFile extends HBox {
	
	private Image thumbnail;
	private String fileName;

	public VoogaFile(VoogaFileType fileType, String fileName) {
		this.thumbnail = fileType.getFileThumbnail();
		this.fileName = fileName;
		
		makeBox();
		
	}
	
	private void makeBox() {
		Text file = new Text(fileName);
		HBox.setMargin(file, new Insets(0, 15, 0, 15));
		this.getChildren().addAll(new ImageView(this.thumbnail), file);
	}
	
}
