package player.leveldatamanager;

import java.util.HashMap;
import java.util.Map;

import gameengine.Sprite;
import player.gamedisplay.IGameDisplay;
import tools.VoogaAlert;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

/**
 * DisplayScroller provides public methods that choose Nodes to display in a
 * visible frame
 * 
 * @author Hunter Lee
 *
 */
public class DisplayScroller implements IDisplayScroller {

	private static final double INCREASE_FACTOR = 1;
	private static final double SCROLL_FACTOR = 0.01;
	private static final int MIN_SCROLL = 300;

	private Sprite myScrollingSprite;
	private IGameDisplay myGameDisplay;
	private String myScrollingType;
	private boolean isExponentialScroll;
	private Double myMaxScrollX;
	private Double myMaxScrollY;

	/**
	 * Default constructor that sets the game display to scroll
	 * 
	 * @param gamedisplay
	 */
	public DisplayScroller(IGameDisplay gamedisplay) {
		this.myGameDisplay = gamedisplay;
	}

	/**
	 * Stub scroll method that scrolls the display both horizontally and vertically
	 * 
	 * @param scrollsprite
	 */
	public void scroll(Map<String, VoogaData> globals, String currentlevel, Sprite scrollingsprite) {
		if (myScrollingType.equals("Tracking")) {
			isExponentialScroll = false;
		} else {
			setContinuousScrollType(globals, currentlevel);
		}
		scrollX(scrollingsprite);
		//		scrollY(scrollingsprite);
	}
	/**
	 * Scrolls the display horizontally using addListener method and by
	 * translatingX
	 * 
	 * @param scrollsprite
	 */
	private void scrollX(Sprite scrollsprite) {
		scrollsprite.getNodeObject().translateXProperty().addListener((obs, old, n) -> {
			if (n.intValue() > MIN_SCROLL && n.intValue() < myMaxScrollX) {
				myGameDisplay.getScreen().setTranslateX(-(n.intValue() - MIN_SCROLL));
			}
		});
	}

	/**
	 * Scrolls the display vertically using addListener method and by
	 * translatingY
	 * 
	 * @param scrollsprite
	 */
	private void scrollY(Sprite scrollsprite) {
		scrollsprite.getNodeObject().translateYProperty().addListener((obs, old, n) -> {
			if (n.intValue() > MIN_SCROLL && n.intValue() < myMaxScrollY) {
				myGameDisplay.getScreen().setTranslateY(-(n.intValue() - MIN_SCROLL));
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
		try {
			try {
			myMaxScrollX = (Double) globals.get(currentlevel + "EndX").getValue();
			myMaxScrollY = (Double) globals.get(currentlevel + "EndY").getValue();
			} catch(Exception e) {
				VoogaAlert alert = new VoogaAlert("Please specify your finish line.");
				alert.showAndWait();
			}
			myScrollingType = (String) globals.get(currentlevel + "Scrolling").getValue();
			// Scrolling is centered on the main character
			if (myScrollingType.equals("Tracking")) {
				return mainsprite;
			} else {
				// Create a scrolling sprite and return it
				double scrollAngle = (double) globals.get(currentlevel + "ScrollAngle").getValue();
				double scrollSpeed = (double) globals.get(currentlevel + "ScrollSpeed").getValue();
				Sprite scrollSprite = new Sprite("/A.png", "ScrollingSprite", new HashMap<String, VoogaData>(),
						new VoogaNumber());
				scrollSprite.getImage().setOpacity(0);
				scrollSprite.getPosition().setXY(mainsprite.getPosition().getX(), mainsprite.getPosition().getY());
				scrollSprite.getVelocity().setVelocity(scrollSpeed * SCROLL_FACTOR, scrollAngle);
				myScrollingSprite = scrollSprite;
				return scrollSprite;
			}
		} catch (Exception e) {
			VoogaAlert alert = new VoogaAlert("Please choose your tracking method.");
			alert.showAndWait();
		}
		return null;
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