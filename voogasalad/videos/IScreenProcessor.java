package videos;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;

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
	 * Take a single snapshot of the screen for a PNG file image
	 * 
	 * @param screenshotZone
	 */
	public void createSceneScreenshotPNG(Scene screenshotZone, String imageName);

	/**
	 * Encode an array of buffered images into an mp4 video
	 * 
	 * @param images
	 * @param outputFileName
	 */
	void encodeScreenshots(String outputFileName, List<BufferedImage> images, double frameRate);
	
	/**
	 * Add buffered images to video array for alter compression if enabled
	 * 
	 * @param videoImages
	 */
//	void updateVideo(List<BufferedImage> videoImages);
}
