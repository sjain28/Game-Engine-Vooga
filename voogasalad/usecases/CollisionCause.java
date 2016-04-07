package usecases;

import java.util.List;

import usecases.Sprite;

public class CollisionCause extends Cause{

	private List<Sprite> groupA;
	private List<Sprite> groupB;
	
	public CollisionCause(Sprite a, Sprite b) {
		this.spriteA = a;  
		this.spriteB = b; 
	}

	@Override
	public boolean checkCause() {
		return checkCollided(spriteA, spriteB);
	}
	
	public boolean checkCollided(Sprite a, Sprite b) {
		return a.getBoundsInParent().intersects(b.getBoundsInParent());
	}

}
