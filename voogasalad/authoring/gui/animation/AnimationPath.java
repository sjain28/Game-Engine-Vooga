package authoring.gui.animation;

import javafx.scene.shape.Shape;

/**
 * The abstract class extended by the individual curve and line paths. Allows each subclass to define its
 * control coordinates which are then used for interpolation. Ensures that additional paths (polygon, polynomial, etc.)
 * can be created with ease and no need for modification of existing classes.
 * 
 * @author Aditya Srinivasan
 *
 */
public abstract class AnimationPath {

	protected Shape myShape;

	public AnimationPath(Shape shape) {
		this.myShape = shape;
	}

	/**
	 * Returns the X controls for the path, as defined by the subclass.
	 * @return
	 */
	public abstract Double[] getXControls();

	/**
	 * Returns the Y controls for the path, as defined by the subclass.
	 * @return
	 */
	public abstract Double[] getYControls();

}
