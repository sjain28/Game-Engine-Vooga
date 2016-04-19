package authoring.resourceutility;

import java.io.Serializable;

import javafx.scene.layout.HBox;

/**
 * A simple visualization of a file, supporting a thumbnail and file name.
 * 
 * @author DoovalSalad
 *
 */
public class VoogaFile extends HBox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2209689862407448154L;
	/**
	 * Private instance variables
	 */
	private String filePath;
	private String fileName;
	private VoogaFileType fileType;

	/**
	 * Constructor to initialize private variables
	 * 
	 * @param fileType
	 * @param fileName
	 */
	public VoogaFile(VoogaFileType fileType, String fileName) {
		this.fileName = fileName;
		this.fileType = fileType;
	}

	/**
	 * Accessors
	 */
	public String toString() {
		return this.fileName;
	}
	
	public void setPath(String path) {
		this.filePath = path;
	}
	
	void setName(String name) {
		this.fileName = name;
	}

	public String getPath() {
		return this.filePath;
	}

	public VoogaFileType getType() {
		return this.fileType;
	}

}
