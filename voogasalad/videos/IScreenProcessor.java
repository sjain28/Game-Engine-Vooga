package videos;

import java.awt.image.BufferedImage;

import javafx.scene.Scene;

/**
 * General interface for scene or screen capture, image conversion, and video processing
 * 
 * @author Michael Kuryshev
 */
interface IScreenProcessor {
	
	/**
	 * Set the image to some local file location
	 * Xuggler uses java.awt and so we do as well here
	 * 
	 * @param image
	 * @return
	 */
	String dumpImageToFile(BufferedImage image, String outputFilePrefix);
	
	/**
	 * Check if the image is of the correct output format, if not, 
	 * convert it to the new type
	 * 
	 * @param source
	 * @param targetType
	 * @return
	 */
	BufferedImage convertImageToType(BufferedImage sourceImage, int targetType);
	
	/**
	 * Take a single snapshot of the screen for a PNG file image
	 * 
	 * @param screenshotZone
	 */
	void createSceneScreenshotPNG(Scene screenshotZone, String imageName);

	/**
	 * Encode an array of buffered images into an mp4 video
	 * 
	 * @param images
	 * @param outputFileName
	 */
}
