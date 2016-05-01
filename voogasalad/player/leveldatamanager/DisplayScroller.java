package player.leveldatamanager;

import java.util.HashMap;
import java.util.Map;

import gameengine.Sprite;
import player.gamedisplay.IGameDisplay;
import resources.VoogaBundles;
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

	private static final double INCREASE_FACTOR = 1.00005;
	private static final double SCROLL_FACTOR = 0.01;

	private Sprite myScrollingSprite;
	private IGameDisplay myGameDisplay;
	private String myScrollingType;
	private String myTrackingDirection;
	private boolean isExponentialScroll;
	private Double myMidScreenX;
	private Double myMidScreenY;
	private Double myMinScrollX;
	private Double myMinScrollY;
	private Double myMaxScrollX;
	private Double myMaxScrollY;
	private Double myTrackX;
	private Double myTrackY;

	/**
	 * Default constructor that sets the game display to scroll
	 * 
	 * @param gamedisplay
	 */
	public DisplayScroller(IGameDisplay gamedisplay) {
		this.myGameDisplay = gamedisplay;
		this.myMidScreenX = Double.parseDouble(VoogaBundles.preferences.getProperty("GameWidth"))/3;
		this.myMidScreenY = Double.parseDouble(VoogaBundles.preferences.getProperty("GameHeight"))/3;
	}

	/**
	 * Stub scroll method that scrolls the display both horizontally and vertically
	 * 
	 * @param scrollsprite
	 */
	public void scroll(Map<String, VoogaData> globals, String currentlevel, Sprite scrollingsprite) {
		if (myScrollingType.equals("Tracking")) {
			isExponentialScroll = false;
			if (myTrackingDirection.equals("X")) {
				scrollX(scrollingsprite);
			} else if (myTrackingDirection.equals("Y")) {
				scrollY(scrollingsprite);
			} else {
				scrollX(scrollingsprite);
				scrollY(scrollingsprite);
			}
		} else { // Consistent scrolling
			setContinuousScrollType(globals, currentlevel);
			scrollX(scrollingsprite);
			scrollY(scrollingsprite);
		}

	}
	/**
	 * Scrolls the display horizontally using addListener method and by
	 * translatingX
	 * 
	 * @param scrollsprite
	 */
	private void scrollX(Sprite scrollsprite) {
		scrollsprite.getNodeObject().translateXProperty().addListener((obs, old, n) -> {
			if (n.intValue() > myMinScrollX && n.intValue() < myMaxScrollX) {
				myGameDisplay.getScreen().setTranslateX(-(n.intValue() - myTrackX));
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
			if (n.intValue() > myMinScrollY && n.intValue() < myMaxScrollY) {
				myGameDisplay.getScreen().setTranslateY(-(n.intValue() - myTrackY));
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
				System.out.println("FROM MAP tracking: " + globals.get(currentlevel + "TrackingDirection").getValue());
				myTrackingDirection = (String) globals.get(currentlevel + "TrackingDirection").getValue();
				establishXandYBounds(globals, currentlevel);
			} catch(Exception e) {
				VoogaAlert alert = new VoogaAlert("Please specify your finish line.");
				alert.showAndWait();
			}
			System.out.println("FROM MAP scrolling: " + globals.get(currentlevel + "Scrolling").getValue());
			myScrollingType = (String) globals.get(currentlevel + "Scrolling").getValue();
			// Scrolling is centered on the main character
			if (myScrollingType.equals("Tracking")) {
				return mainsprite;
			} else {
				// Create a scrolling sprite and return it
				System.out.println("1");
				double scrollAngle = (double) globals.get(currentlevel + "ScrollAngle").getValue();
				System.out.println("2");
				double scrollSpeed = (double) globals.get(currentlevel + "ScrollSpeed").getValue();
				System.out.println("3");
				Sprite scrollSprite = new Sprite("/A.png", "ScrollingSprite", new HashMap<>(),
						new VoogaNumber());
				System.out.println("4");
				scrollSprite.getImage().setOpacity(0);
				System.out.println("5");
				scrollSprite.getPosition().setXY(mainsprite.getPosition().getX(), mainsprite.getPosition().getY());
				System.out.println("6");
				scrollSprite.getVelocity().setVelocity(scrollSpeed * SCROLL_FACTOR, scrollAngle);
				System.out.println("7");
				myScrollingSprite = scrollSprite;
				return scrollSprite;
			}
		} catch (Exception e) {
			VoogaAlert alert = new VoogaAlert("Please choose your tracking method.");
			alert.showAndWait();
		}
		return null;
	}
	
	private void establishXandYBounds(Map<String, VoogaData> globals, String currentlevel) {
		Double endX = (Double) globals.get(currentlevel + "EndX").getValue();
		Double endY = (Double) globals.get(currentlevel + "EndY").getValue();
		if(endX >= myMidScreenX) { myMinScrollX = myMidScreenX; myMaxScrollX = endX; myTrackX = myMinScrollX.doubleValue();}
		else { myMaxScrollX = myMidScreenX; myMinScrollX = endX; myTrackX = myMaxScrollX.doubleValue();}
		if(endY >= myMidScreenY) { myMinScrollY = myMidScreenY; myMaxScrollY = endY; myTrackY = myMinScrollY.doubleValue();}
		else { myMaxScrollY = myMidScreenY; myMinScrollY = endY; myTrackY = myMaxScrollY.doubleValue();}
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
			prevMagnitude = prevMagnitude * INCREASE_FACTOR;
			scrollingSprite.getVelocity().setVelocity(prevMagnitude, scrollingSprite.getVelocity().getAngleDegree());
//			System.out.println("This is Velocity magnitude: " + scrollingSprite.getVelocity().getMagnitude());
//			System.out.println("This is the angle: " + scrollingSprite.getVelocity().getAngleDegree());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	/**
	 * @return the myScrollingSprite
	 */
	public Sprite getScrollingSprite() {
		return myScrollingSprite;
	}
}