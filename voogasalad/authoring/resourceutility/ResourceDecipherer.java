package authoring.resourceutility;

import java.util.MissingResourceException;

import resources.VoogaBundles;
import tools.VoogaException;

/**
 * A decrypter of file paths, in order to determine what kind of file a user is
 * trying to import.
 * 
 * @author DoovalSalad
 *
 */
public class ResourceDecipherer {

	private static final String IMAGE = "IMAGE";
	private static final String AUDIO = "AUDIO";
	private static final String EXTENSION_ERROR = "This file format is not supported!";

	/**
	 * Returns a VoogaFileType enum depending on the path and extension of the
	 * file
	 * 
	 * @param path:
	 *            path of the image
	 * @return a VoogaFileType (IMAGE or AUDIO)
	 * @throws VoogaException 
	 */
	public static VoogaFileType decipherName(String path) throws VoogaException {
		try {
			return VoogaFileType.valueOf(VoogaBundles.extensionProperties.getString(getExtension(path, '.')));
		} catch(MissingResourceException e) {
			throw new VoogaException(EXTENSION_ERROR);
		}
	}

	/**
	 * Determines if a file being imported is an image
	 * 
	 * @param path:
	 *            the path of the item
	 * @return: true if it is valid
	 * @throws VoogaException 
	 */
	public static boolean isImage(String path) throws VoogaException {
		return decipherName(path).name().equals(IMAGE);
	}

	/**
	 * Determines if a file being imported is an audio file
	 * 
	 * @param path:
	 *            the path of the item
	 * @return: true if it is valid
	 * @throws VoogaException 
	 */
	public static boolean isAudio(String path) throws VoogaException {
		return decipherName(path).name().equals(AUDIO);
	}

	/**
	 * Returns the extension given the path and delimiter to use. For example,
	 * would return "png" if a PNG file was passed as a parameter, along with
	 * the '.' delimiter to separate extension from the name.
	 * 
	 * @param path:
	 *            path of the item
	 * @param delimiter:
	 *            character with which to separate
	 * @return
	 */
	public static String getExtension(String path, char delimiter) {
		String extension = path;

		int i = path.lastIndexOf(delimiter);
		if (i > 0) {
			extension = path.substring(i + 1).toLowerCase();
		}

		return extension;
	}

}
