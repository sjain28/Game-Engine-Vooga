package videos;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

public class ScreenProcessor implements IScreenProcessor{

	@Override
	public String dumpImageToFile(BufferedImage image, String outputFilePrefix) {
        try {
            String outputFilename = outputFilePrefix +
                 System.currentTimeMillis() + ".png";
            ImageIO.write(image, "png", new File(outputFilename));
            return outputFilename;
        }
        catch (IOException e) {
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public BufferedImage convertImageToType(BufferedImage sourceImage, int targetType) {
        BufferedImage image;

        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        }
        else {
            image = new BufferedImage(sourceImage.getWidth(), 
                 sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }
        return image;
	}

	@Override
	public BufferedImage createDesktopScreenshotForVideo(Dimension screenBounds) {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(screenBounds);
            return robot.createScreenCapture(captureSize);
        } 
        catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
	}

	@Override 
	public void createSceneScreenshotPNG(Scene screenshotZone, String imageName) {
		WritableImage screenshot = screenshotZone.snapshot(null);
		
	    File file = new File("gameplay_images_videos/" + imageName +".png");
	    System.out.println("Saved");
	    try {
	        ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), "png", file);
	    } 
	    catch (IOException e) {
	    	System.out.println("Failed");
	        // TODO add error handling
	    }
	}
}
