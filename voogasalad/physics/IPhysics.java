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

	public void translateX(Sprite sprite, Double amount);
	public void translateY(Sprite sprite, Double amount);
	public void accelerateX(Sprite sprite, Double amount);
	public void accelerateY(Sprite sprite, Double amount);
	public void jump(Sprite sprite, Double magnitude);
	public void gravity(Sprite sprite, Double gravitationalConstant);

}
