package videos;

/**
 * Attempt at video encoding with xuggler, issues importing jars with maven though
 * MUST IMPORT XUGGLER JAR TO TEST, issues with Maven persist
 * @author mykuryshev
 */

/*
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.IContainer;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;
/**
 *
 * @author Aryan Naim 
 * This class converts JPEG images to MPEG (.mp4) extension video using Xuggler library
 */
public class XugglerVideoEncodeTester {
/*
    private int videoFrameRate = 60;
    private long durationPerFrame = 1000; 
    private String outputFilenamePath = "";
    //list of JPEG pictures to be converted 
    private List<String> jpegFilePathList = new ArrayList<String>();
    //list of actual images in memory to be iterated through & encoded by Xuggler 
    private List<BufferedImage> jpegFileList = new ArrayList<BufferedImage>();

      public void setDurationPerFrame(long  durationPerFrame) {
        this.durationPerFrame = new Double(Math.ceil(durationPerFrame * 1.15)).longValue();
    }

     public long getDurationPerFrame() {
        return durationPerFrame;
    }
     
    /** 
     * Method that converts JPEG images to MPEG (.mp4) extension video using Xuggler library
     */
/*
    public void convertJpegFramesToMpegVideo() {
        System.out.println("convertJpegFramesToMpegVideo, start...");
        BufferedImage img = null;
        IContainer container = IContainer.make();
        IMediaWriter writer = null;
        long nextEncodeTime = getDurationPerFrame();
        writer = ToolFactory.makeWriter(getOutputFilenamePath(), container);

        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,IRational.make(videoFrameRate),1024,768);

        File imgFile = null;
        for (int i = 0; i < jpegFilePathList.size(); i++) {
            imgFile = new File(getJpegFilePathList().get(i));
            if (imgFile.exists()) {

                try {

                    img = ImageIO.read(imgFile);

                    jpegFileList.add(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("convertJpegFramesToMpegVideo, file path: " + imgFile.getPath() + " does not exist!");
            }
        }
        
        for (int i = 0; i <jpegFileList.size(); i++) {
            System.out.println("convertJpegFramesToMpegVideo encoded frame counter: " + i);
            img = jpegFileList.get(i);
            writer.encodeVideo(0, img, nextEncodeTime, TimeUnit.MILLISECONDS);
            nextEncodeTime = nextEncodeTime + getDurationPerFrame();
        }
        writer.close();
   System.out.println("convertJpegFramesToMpegVideo complete");
    }

    public int getFrameRate() {
        return videoFrameRate;
    }

    public void setFrameRate(int frameRate) {
        this.videoFrameRate = frameRate;
    }
    public String getOutputFilenamePath() {
        return outputFilenamePath;
    }

    public void setOutputFilenamePath(String outputFilenamePath) {
        this.outputFilenamePath = outputFilenamePath;
    }

    public List<String> getJpegFilePathList() {
        return jpegFilePathList;
    }

    public void setJpegFilePathList(List<String> jpegFilePathList) {
        this.jpegFilePathList = jpegFilePathList;
    }

    public List<BufferedImage> getJpegFileList() {
        return jpegFileList;
    }

    public void setJpegFileList(List<BufferedImage> jpegFileList) {
        this.jpegFileList = jpegFileList;
    }
*/
}