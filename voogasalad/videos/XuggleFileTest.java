package videos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;

public class XuggleFileTest {
	public static final double SECONDS_BETWEEN_FRAMES = 10;

	private static final String inputFileName = "Darude - Sandstorm.mp4";
	private static final String outputFilePrefix = "Downloads:/newDarude";
	
	private static int myVideoStreamIndex = -1;
	private static long mLastPtsWrite = Global.NO_PTS;
	public static final long MICRO_SECONDS_BETWEEN_FRAMES = (long) (Global.DEFAULT_PTS_PER_SECOND*SECONDS_BETWEEN_FRAMES);
	
	public static void main(String[] args) {
		
		IMediaReader mediaReader = ToolFactory.makeReader(inputFileName);
		mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
		
		while(mediaReader.readPacket() == null);
	}
	
	private static class ImageSnapListener extends MediaListenerAdapter {
		
		public void onVideoPicture(IVideoPictureEvent event) {
			
			if (event.getStreamIndex() != myVideoStreamIndex) {
				
				if (myVideoStreamIndex == -1)
					myVideoStreamIndex = event.getStreamIndex();
				
				else 
					return;
			}
			
			if (mLastPtsWrite == Global.NO_PTS)
				mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;
			
			if (event.getTimeStamp() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES) {
				String outputFileName = dumpImageToFile(event.getImage());
				double seconds = ((double) event.getTimeStamp()) / Global.DEFAULT_PTS_PER_SECOND;
				System.out.printf("elapsedTime: %6.3f wrote: %s\n", seconds, outputFileName);
				
				mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
			}
		}
		
		private String dumpImageToFile(BufferedImage image) {
			try {
				String outputFileName = outputFilePrefix + System.currentTimeMillis() + ".png";
				ImageIO.write(image, "png", new File(outputFileName));
				
				return outputFileName;
			}
			catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
