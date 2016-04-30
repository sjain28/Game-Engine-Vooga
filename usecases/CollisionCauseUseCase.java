package usecases;

import java.util.List;

public class CollisionCauseUseCase implements CauseUseCase{

	private List<SpriteUseCase> groupA;
	private List<SpriteUseCase> groupB;
	
	private SpriteUseCase spriteA;
	private SpriteUseCase spriteB;
	
	public CollisionCauseUseCase(SpriteUseCase a, SpriteUseCase b) {
		this.spriteA = a;  
		this.spriteB = b; 
	}

	@Override
	public boolean checkCause() {
		return checkCollided(spriteA, spriteB);
	}
	
	public boolean checkCollided(SpriteUseCase a, SpriteUseCase b) {
		return a.getBoundsInParent().intersects(b.getBoundsInParent());
	}

}
