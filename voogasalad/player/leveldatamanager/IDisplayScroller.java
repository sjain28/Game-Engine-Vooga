/**
 * 
 */
package player.leveldatamanager;

import java.util.Map;

import gameengine.Sprite;
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
	
	void increaseScrollingSpeed(Sprite scrollingSprite);
	
	Sprite getScrollingSprite();

}
