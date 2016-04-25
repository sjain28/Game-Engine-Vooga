package videos;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

/**
 * Take screenshots from the player and later converting them to 
 * video format
 * 
 * @author Michael Kuryshev
 *
 */
public class ScreenshotToVideo {
	
	private String outputFileName;
	private ScreenProcessor processor = new ScreenProcessor();
	private Dimension screenBounds;
	
	//TODO pass in bounds for the screenshot being taken
	public ScreenshotToVideo() {
		
	}
	
    public void ScreenshotEncoder(int screenShotCount, Rectangle frameLocation) {
    	//TODO extract the frame
        final IMediaWriter writer = ToolFactory.makeWriter(outputFileName);
        
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

        // We tell it we're going to add one video stream, with id 0,
        // at position 0, and that it will have a fixed frame rate of FRAME_RATE.
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, 
                   screenBounds.width/2, screenBounds.height/2);

        long startTime = System.nanoTime();
            
        BufferedImage screen = processor.createDesktopScreenshotForVideo(screenBounds);
        BufferedImage bgrScreen = processor.convertImageToType(screen, 
               BufferedImage.TYPE_3BYTE_BGR);
        
        writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, 
               TimeUnit.NANOSECONDS);
            
        writer.close();
    }
}
