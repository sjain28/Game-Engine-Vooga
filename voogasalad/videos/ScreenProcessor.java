package videos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import resources.VoogaBundles;
import tools.VoogaAlert;

/**
 * Implemented methods to handle image capture and conversion to local files
 * 
 * @author Michael Kuryshev
 */
public class ScreenProcessor implements IScreenProcessor {

	private static final String DOT_PNG = "dotpng";
	private static final String PNG = "png";
	/**
	 * Write an image to a file and return the file name
	 */
	@Override
	public String dumpImageToFile(BufferedImage image, String outputFilePrefix) {
		try {
			String outputFilename = outputFilePrefix
					+ System.currentTimeMillis()
					+ VoogaBundles.imageProperties.getString(DOT_PNG);
			ImageIO.write(image, VoogaBundles.imageProperties.getString(PNG),
					new File(outputFilename));
			return outputFilename;
		} catch (IOException e) {
			//TODO remove stacktrace print
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Convert a buffered image to another extension type
	 */
	@Override
	public BufferedImage convertImageToType(BufferedImage sourceImage,
			int targetType) {
		BufferedImage image;

		if (sourceImage.getType() == targetType) {
			image = sourceImage;
		} else {
			image = new BufferedImage(sourceImage.getWidth(),
					sourceImage.getHeight(), targetType);
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		}
		return image;
	}

	/**
	 * Take a screenshot of a scene and write it as a .png file
	 */
	@Override
	public void createSceneScreenshotPNG(Scene screenshotZone, String imageName) {
		WritableImage screenshot = screenshotZone.snapshot(null);
		// TODO where to save?
		File file = new File(
				VoogaBundles.imageProperties.getString("saveLocation")
						+ imageName
						+ VoogaBundles.imageProperties.getString(DOT_PNG));
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null),
					VoogaBundles.imageProperties.getString(PNG), file);
		} catch (IOException e) {
			VoogaAlert alert = new VoogaAlert(VoogaBundles.exceptionProperties.getString("SnapshotFail"));
			alert.showAndWait();
		}
	}
}
