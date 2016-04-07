package events;

import java.util.List;
import java.util.Map;
import gameengine.Sprite;
import javafx.scene.image.ImageView;

public class CollisionCause extends Cause{

	private List<Sprite> groupA;
	private List<Sprite> groupB;
	
	private Map<Sprite, Sprite> collisionPairs; 
	
	public CollisionCause(List<Object> groupAID, List<Object> GroupBID){
		
	}

	@Override
	public boolean check() {
		ImageView a = spriteA.getImage();
		ImageView b = spriteB.getImage();
		return b.getBoundsInParent().intersects(a.getBoundsInParent());
	}

}
