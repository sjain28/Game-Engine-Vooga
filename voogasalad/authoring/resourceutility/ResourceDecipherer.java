package authoring.resourceutility;
import java.util.ResourceBundle;

import resources.VoogaBundles;

public class ResourceDecipherer {
	
	private static ResourceBundle fileExtensions;
	private static final String IMAGE = "IMAGE";
	private static final String AUDIO = "AUDIO";
	
	
	public ResourceDecipherer() {
		
	}
	
	public static VoogaFileType decipherName(String path) {
		fileExtensions = VoogaBundles.extensionProperties;
		return VoogaFileType.valueOf(fileExtensions.getString(getExtension(path, '.')));
	}
	
	public static boolean isImage(String path) {
		return decipherName(path).name().equals(IMAGE);
	}
	
	public static boolean isAudio(String path) {
		return decipherName(path).name().equals(AUDIO);
	}

	public static String getExtension(String path, char delimiter) {
		String extension = "";

		int i = path.lastIndexOf(delimiter);
		if (i > 0) {
		    extension = path.substring(i+1);
		}
		
		return extension;
	}
	
}
