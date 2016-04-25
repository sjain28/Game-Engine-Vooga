package videos;

/**
 * Adapted code to take screenshots or create videos from frames taken from the game
 * Runs at around 10 frames per second.  
 * 
 * @author Ilias Tsagklis, Michael Kuryshev
 */
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

public class XuggleFileCreatorTest {
    //TODO given player size, lcoation, and whether we want a snapshot or video
	//TODO apply the information to return the correct information
	//TODO adapt as much as possible to javafx for better use with other code
	
	//TODO fix errors with math of this... currently 60,10 yields 1:15 video with 6
	//TODO frames per second or so...
	
    private static final double FRAME_RATE = 60;
    private static final int SECONDS_TO_RUN_FOR = 10;
    private static final String outputFilename = "voogasalad/videos/hello.mp4";
    
    private static Dimension screenBounds;

    /**
     * Will adapt to pass in necessary arguments to take video or screenshot of
     * game play. In general, obtains image within bounds and encodes it to the 
     * ever growing video.
     * 
     * @param args
     */
    public static void main(String[] args) {

        final IMediaWriter writer = ToolFactory.makeWriter(outputFilename);
        
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

        // We tell it we're going to add one video stream, with id 0,
        // at position 0, and that it will have a fixed frame rate of FRAME_RATE.
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, 
                   screenBounds.width/2, screenBounds.height/2);

        long startTime = System.nanoTime();
        
        for (int index = 0; index < SECONDS_TO_RUN_FOR * FRAME_RATE; index++) {
            
            BufferedImage screen = getDesktopScreenshot();
            BufferedImage bgrScreen = convertImageToType(screen, 
                   BufferedImage.TYPE_3BYTE_BGR);
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, 
                   TimeUnit.NANOSECONDS);

            try {
                Thread.sleep((long) (1 / FRAME_RATE));
            } 
            
            catch (InterruptedException e) {
            	
            }
            
        }
        writer.close();
    }
    
    /**
     * If the bufferedImage is of the correct type, then just return it, otherwise,
     * we will reconvert the image to the demanded format type
     * 
     * @param sourceImage
     * @param targetType
     * @return
     */
    public static BufferedImage convertImageToType(BufferedImage sourceImage, int targetType) {
        
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
    
    /**
     * Old school .awt code to get a screenshot of the specified boundaries
     * 
     * @return
     */
    private static BufferedImage getDesktopScreenshot() {
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

}
