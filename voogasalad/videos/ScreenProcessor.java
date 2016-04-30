package videos;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;

import resources.VoogaBundles;
import tools.VoogaAlert;

public class ScreenProcessor implements IScreenProcessor{

	private Robot robot;
	private Dimension screenBounds;
	private boolean recordingState;
	
	public ScreenProcessor() {
		screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			new VoogaAlert(VoogaBundles.exceptionProperties.getString("VideoFail"));
			return;
		}
	}
	
	@Override
	public String dumpImageToFile(BufferedImage image, String outputFilePrefix) {
        try {
            String outputFilename = outputFilePrefix +
                 System.currentTimeMillis() + VoogaBundles.imageProperties.getString("dotpng");
            ImageIO.write(image, VoogaBundles.imageProperties.getString("png"), new File(outputFilename));
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
	public void createSceneScreenshotPNG(Scene screenshotZone, String imageName) {
		WritableImage screenshot = screenshotZone.snapshot(null);
//TODO where to save?
		File file = new File(
						VoogaBundles.imageProperties.getString("saveLocation")
						+ imageName
						+ VoogaBundles.imageProperties.getString("dotpng"));
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null),
					VoogaBundles.imageProperties.getString("png"), file);
		} catch (IOException e) {
			new VoogaAlert(VoogaBundles.exceptionProperties.getString("SnapshotFail"));
		}
	}

	@Override
    public void encodeScreenshots(String outputFileName, List<BufferedImage> images, double frameRate) {
        final IMediaWriter writer = ToolFactory.makeWriter(outputFileName);
        /*
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        long startTime = System.nanoTime();
        
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, screenBounds.width/2, screenBounds.height/2);
       
        while(checkIfStillRecording()) { 
System.out.println("Recording");
            BufferedImage screen = createDesktopScreenshotForVideo();
            BufferedImage bgrScreen = convertImageToType(screen, BufferedImage.TYPE_3BYTE_BGR);
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
            	
			try {
				Thread.sleep((long) (1 / frameRate));
System.out.println("Next frame for video");
			} catch (InterruptedException e) {
				new VoogaAlert(VoogaBundles.exceptionProperties.getString("VideoFail"));
			}
        }
        writer.close();
        */
        long nextFrameTime = 0;
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, IRational.make(30), 702, 304);
        
        for (BufferedImage n: images) {
        	writer.encodeVideo(0, n, nextFrameTime, TimeUnit.MILLISECONDS);
        	nextFrameTime += frameRate/1000;
        	
        }
        writer.close();
    }
/*	
	@Override 
	public void updateVideo(List<BufferedImage> videoImages) {
		videoImages.add(createDesktopScreenshotForVideo());
	}
	
	/**
	 * Take a snapshot of the screen for video
	 * 
	 * @return
	 */
/*
	private BufferedImage createDesktopScreenshotForVideo() {
            Rectangle captureSize = new Rectangle(screenBounds);
            return robot.createScreenCapture(captureSize);
	}
	
	/**
	 * Check to see whether more frames should be taken for recording
	 * @return
	 */
/*
	private boolean checkIfStillRecording() {
		return recordingState;
	}
*/
}
