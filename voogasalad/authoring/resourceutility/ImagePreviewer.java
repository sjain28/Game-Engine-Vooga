package authoring.resourceutility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The previewer for image files.
 * @author DoovalSalad
 *
 */
public class ImagePreviewer extends Previewer {
	
	/**
	 * Constants
	 */
	private static final String PREFIX = "file:///";
	private static final double MAX_WIDTH = 900;

	/**
	 * Inherited constructor to allow file access
	 * @param file
	 */
	public ImagePreviewer(VoogaFile file) {
		super(file);
	}

	/**
	 * Inherited preview method to allow users to preview their image
	 */
	@Override
	void preview() {
		Image rawPreview = new Image(PREFIX + this.file.getPath());
		double width = (rawPreview.getWidth() > MAX_WIDTH) ? MAX_WIDTH : rawPreview.getWidth();
		Image adjustedPreview = new Image(PREFIX + this.file.getPath(), width, 0, true, false);
		ImageView preview = new ImageView(adjustedPreview);
		this.group.getChildren().add(preview);
	}

}
