/**
 * 
 */
package player.leveldatamanager;

import java.util.List;
import java.util.Map;

import gameengine.Sprite;
import javafx.scene.Node;
import tools.interfaces.VoogaData;

/**
 * DisplayScroller interface that declares public methods
 * 
 * @author Hunter Lee
 *
 */
public interface IDisplayScroller {

	void scroll(Map<String, VoogaData> globals, String currentlevel, Sprite scrollingsprite);
	
	Sprite createScrollingSprite(Map<String, VoogaData> globals, String currentlevel, Sprite mainsprite);
	
	void increateScrollingSpeed(Sprite scrollingSprite);
	
	Sprite getScrollingSprite();


	
	
//	<E> List<Node> centerScroll(List<E> allNodes, double mainCharXPos);
//
//	<E> List<Node> constantScroll(List<E> allNodes, int speed);

//	int getScreenSizeX();
//
//	int getScreenSizeY();
//
//	void setScreenSizeX();
//
//	void setScreenSizeY();

}
