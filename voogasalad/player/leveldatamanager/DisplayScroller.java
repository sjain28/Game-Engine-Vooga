package player.leveldatamanager;

import java.util.HashMap;
import java.util.Map;

import gameengine.Sprite;
import player.gamedisplay.IGameDisplay;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

/**
 * DisplayScroller provides public methods that choose Nodes to display
 * in a visible frame
 * 
 * @author Hunter Lee
 *
 */
public class DisplayScroller implements IDisplayScroller {
	
	private static final double INCREASE_FACTOR = 1;
	
	private Sprite myScrollingSprite;
	private boolean isExponentialScroll;
	private IGameDisplay myGameDisplay;
	
	public DisplayScroller(IGameDisplay gamedisplay) {
		this.myGameDisplay = gamedisplay;
	}
	
	/**
	 * Stub scroll method that scrolls the display both horizontally and vertically
	 * 
	 * @param scrollsprite
	 */
	public void scroll(Map<String, VoogaData> globals, String currentlevel, Sprite scrollingsprite) {
		setContinuousScrollType(globals, currentlevel);
		scrollX(scrollingsprite);
		scrollY(scrollingsprite);
	} 
	
	/**
	 * Scrolls the display horizontally using addListener method and by translatingX
	 * 
	 * @param scrollsprite
	 */
	private void scrollX(Sprite scrollsprite) {
		scrollsprite.getNodeObject().translateXProperty().addListener((obs, old, n) -> {
			// TODO: Link to size of level instead of hardcoding
    		if (n.intValue() > 200 && n.intValue() < 3000) {
    			myGameDisplay.getScreen().setTranslateX(-(n.intValue() - 200));
    		}
		});
	}
	
	/**
	 * Scrolls the display vertically using addListener method and by translatingY
	 * 
	 * @param scrollsprite
	 */
	private void scrollY(Sprite scrollsprite) {
		scrollsprite.getNodeObject().translateYProperty().addListener((obs, old, n) -> {
			// TODO: Link to size of level instead of hardcoding
    		if (n.intValue() > 200 && n.intValue() < 3000) {
    			myGameDisplay.getScreen().setTranslateY(-(n.intValue() - 200));
    		}
		});
	}

	/**
	 * Creates a scrolling sprite per specification
	 * 
	 * @param scrollingType
	 * @param globals
	 * @param currentlevel
	 * @param mainsprite
	 * @return
	 */
	public Sprite createScrollingSprite(Map<String, VoogaData> globals, String currentlevel, Sprite mainsprite) {
		String scrollingType = (String) globals.get(currentlevel + "Scrolling").getValue();
		// Scrolling is centered on the main character
		if (scrollingType.equals("Tracking")) {
			return mainsprite;
		} else {
			// Create a scrolling sprite
			double scrollAngle = (double) globals.get(currentlevel + "ScrollAngle").getValue();
			double scrollSpeed = (double) globals.get(currentlevel + "ScrollSpeed").getValue();
			Sprite scrollSprite = new Sprite("A.png", "ScrollingSprite", new HashMap<String, VoogaData>(), new VoogaNumber());
			scrollSprite.getImage().setOpacity(0);
			scrollSprite.getVelocity().setVelocity(scrollSpeed, scrollAngle);
			myScrollingSprite = scrollSprite;
			return scrollSprite;			
		}
	}
	
	/**
	 * Set the boolean flag for the case of continuous (constant) scrolling
	 * 
	 * @param globals
	 * @param currentlevel
	 */
	private void setContinuousScrollType(Map<String, VoogaData> globals, String currentlevel) {
		String scrollType = (String) globals.get(currentlevel + "ContinuousScrollType").getValue();
		if (scrollType.equals("Exponential")) {
			isExponentialScroll = true;
		} else {
			isExponentialScroll = false;
		}
	}

	/**
	 * To be called right after scroll method in GameRunner
	 * 
	 * @param scrollingSprite
	 * @return
	 */
	public void increaseScrollingSpeed(Sprite scrollingSprite) {
		if (isExponentialScroll) {
			Double prevMagnitude = scrollingSprite.getVelocity().getMagnitude();
			prevMagnitude = prevMagnitude + INCREASE_FACTOR;
			scrollingSprite.getVelocity().setVelocity(prevMagnitude, scrollingSprite.getVelocity().getAngleDegree());
		}
	}

	/**
	 * @return the myScrollingSprite
	 */
	public Sprite getScrollingSprite() {
		return myScrollingSprite;
	}
}
