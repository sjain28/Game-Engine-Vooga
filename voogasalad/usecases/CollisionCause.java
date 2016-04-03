package usecases;

import usecases.Sprite;

public class CollisionCause implements Cause{

	private Sprite spriteA;
	private Sprite spriteB;
	
	public CollisionCause(Sprite spriteA, Sprite b) {
		// TODO Auto-generated constructor stub
		this.spriteA = spriteA;  
		this.spriteB = spriteB; 
	}

	@Override
	public boolean checkCause() {
		// TODO Auto-generated method stub
		return checkCollided(spriteA, spriteB);
	}
	
	public boolean checkCollided(Sprite a, Sprite b) {
		// TODO Auto-generated method stub
		return a.getBoundsInParent().intersects(b.getBoundsInParent());
	}

}
