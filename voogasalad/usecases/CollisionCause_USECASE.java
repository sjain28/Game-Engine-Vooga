package usecases;

import java.util.List;

import usecases.Sprite_USECASE;

public class CollisionCause_USECASE implements Cause_USECASE{

	private List<Sprite_USECASE> groupA;
	private List<Sprite_USECASE> groupB;
	
	private Sprite_USECASE spriteA;
	private Sprite_USECASE spriteB;
	
	public CollisionCause_USECASE(Sprite_USECASE a, Sprite_USECASE b) {
		this.spriteA = a;  
		this.spriteB = b; 
	}

	@Override
	public boolean checkCause() {
		return checkCollided(spriteA, spriteB);
	}
	
	public boolean checkCollided(Sprite_USECASE a, Sprite_USECASE b) {
		return a.getBoundsInParent().intersects(b.getBoundsInParent());
	}

}
