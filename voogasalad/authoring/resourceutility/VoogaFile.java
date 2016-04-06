package authoring.resourceutility;
import javafx.scene.layout.HBox;

public class VoogaFile extends HBox {
	
	private String filePath;
	private String fileName;
	private VoogaFileType fileType;

	public VoogaFile(VoogaFileType fileType, String fileName) {
		this.filePath = fileName;
		this.fileName = fileName;
		this.fileType = fileType;
	}
	
	public String toString() {
		return this.fileName;
	}
	
	public String getPath() {
		return this.filePath;
	}
	
	public VoogaFileType getType() {
		return this.fileType;
	}
	
}
