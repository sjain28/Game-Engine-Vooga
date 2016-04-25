package videos;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Rectangle;

import javafx.scene.Scene;

public interface IScreenProcessor {
	
	/**
	 * Set the image to some local file location
	 * Xuggler uses java.awt and so we do as well here
	 * 
	 * @param image
	 * @return
	 */
	public String dumpImageToFile(BufferedImage image, String outputFilePrefix);
	
	/**
	 * Check if the image is of the correct output format, if not, 
	 * convert it to the new type
	 * 
	 * @param source
	 * @param targetType
	 * @return
	 */
	public BufferedImage convertImageToType(BufferedImage sourceImage, int targetType);
	
	/**
	 * Take a snapshot of the screen, dependent on the area demanded
	 * 
	 * @return
	 */
	public BufferedImage createDesktopScreenshotForVideo(Dimension screenBounds);
	
	/**
	 * Take a single snapshot of the screen for a PNG file image
	 * 
	 * @param screenshotZone
	 */
	public void createSceneScreenshotPNG(Scene screenshotZone, String imageName);
}
