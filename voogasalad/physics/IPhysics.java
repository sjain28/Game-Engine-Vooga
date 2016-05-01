/**
 * 
 */
package physics;

import gameengine.Sprite;

/**
 * @author Hunter Lee
 *
 */
@Deprecated
public interface IPhysics {

	void translateX(Sprite sprite, Double amount);
	
	void translateY(Sprite sprite, Double amount);
	
	void accelerateX(Sprite sprite, Double amount);
	
	void accelerateY(Sprite sprite, Double amount);
	
	void jump(Sprite sprite, Double magnitude);
	
	void gravity(Sprite sprite, Double gravitationalConstant);

}
