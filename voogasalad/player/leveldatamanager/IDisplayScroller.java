/**
 * 
 */
package player.leveldatamanager;

import java.util.List;

import javafx.scene.Node;

/**
 * DisplayScroller interface that declares public methods
 * 
 * @author Hunter Lee
 *
 */
public interface IDisplayScroller {

	<E> List<Node> centerScroll(List<E> allNodes, double mainCharXPos);
	
	<E> List<Node> constantScroll(List<E> allNodes, int speed);
	
	int getScreenSizeX();
	
	int getScreenSizeY();
	
	void setScreenSizeX();
	
	void setScreenSizeY();
}
