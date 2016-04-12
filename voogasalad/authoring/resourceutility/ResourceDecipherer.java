package authoring.resourceutility;
import java.util.ResourceBundle;

import resources.VoogaBundles;

/**
 * A decrypter of file paths, in order to determine what kind of file a user
 * is trying to import.
 * @author DoovalSalad
 *
 */
public class ResourceDecipherer {
	
	private static ResourceBundle fileExtensions;
	private static final String IMAGE = "IMAGE";
	private static final String AUDIO = "AUDIO";
	
	/**
	 * Returns a VoogaFileType enum depending on the path and extension of the file
	 * @param path: path of the image
	 * @return a VoogaFileType (IMAGE or AUDIO)
	 */
	public static VoogaFileType decipherName(String path) {
		fileExtensions = VoogaBundles.extensionProperties;
		return VoogaFileType.valueOf(fileExtensions.getString(getExtension(path, '.')));
	}
	
	/**
	 * Determines if a file being imported is an image
	 * @param path: the path of the item
	 * @return: true if it is valid
	 */
	public static boolean isImage(String path) {
		return decipherName(path).name().equals(IMAGE);
	}
	
	/**
	 * Determines if a file being imported is an audio file
	 * @param path: the path of the item
	 * @return: true if it is valid
	 */
	public static boolean isAudio(String path) {
		return decipherName(path).name().equals(AUDIO);
	}

	/**
	 * Returns the extension given the path and delimiter to use. For example,
	 * would return "png" if a PNG file was passed as a parameter, along with the
	 * '.' delimiter to separate extension from the name.
	 * @param path: path of the item
	 * @param delimiter: character with which to separate
	 * @return
	 */
	public static String getExtension(String path, char delimiter) {
		String extension = path;

		int i = path.lastIndexOf(delimiter);
		if (i > 0) {
		    extension = path.substring(i+1).toLowerCase();
		}
		
		return extension;
	}
	
}