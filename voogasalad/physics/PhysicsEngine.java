package physics;

import tools.interfaces.VoogaData;

public interface PhysicsEngine {

	void translateCoordinates(VoogaData sprite);
	
	boolean detectCollision(VoogaData first, VoogaData second);
	
	void resolveCollision(VoogaData first, VoogaData second);
}
