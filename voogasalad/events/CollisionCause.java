package events;

import gameengine.Sprite;
import javafx.scene.image.ImageView;

public class CollisionCause implements Cause{

	private Sprite spriteA;
	private Sprite spriteB;
	
	
	public CollisionCause(Sprite a, Sprite b) {
		spriteA = a;
		spriteB = b;
	}

	@Override
	public boolean check() {
		ImageView a = spriteA.getImage();
		ImageView b = spriteB.getImage();
		return b.getBoundsInParent().intersects(a.getBoundsInParent());
	}

}
