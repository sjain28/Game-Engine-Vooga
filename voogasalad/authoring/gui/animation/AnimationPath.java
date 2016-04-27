package authoring.gui.animation;

import javafx.scene.shape.Shape;

public abstract class AnimationPath {
	
	protected Shape myShape;
	
	public AnimationPath(Shape shape) {
		this.myShape = shape;
	}
	
	public abstract double[] getXControls();
	public abstract double[] getYControls();
	
}
