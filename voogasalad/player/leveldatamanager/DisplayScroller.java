package player.leveldatamanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gameengine.Sprite;
import javafx.scene.Node;
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
	private String myScrollingSpriteID;
	
	private int myScreenSizeX;
	private int myScreenSizeY;
	private int myAdjustFactorX;
	private int myConstantScrollCenter;
	
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
	public Sprite createScrollingSprite(Map<String, VoogaData> globals, 
										 String currentlevel, Sprite mainsprite) {
		System.out.println(globals.keySet());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String scrollingType = (String) globals.get(currentlevel + "Scrolling").getValue();

		// Scrolling is centered on the main character
		if (scrollingType.equals("Tracking")) {
			// set main character equal to scrolling sprite
			return mainsprite;
			// only scroll in X direction
		} else {
//			(scrollingType.equals("Continuous")) { // Scrolling is centered on the new scrolling sprite
			
			// create a sprite based on scrollAngle and scrollSpeed
			//Get scroll angle
			double scrollAngle = (double) globals.get(currentlevel + "ScrollAngle").getValue();
			double scrollSpeed = (double) globals.get(currentlevel + "ScrollSpeed").getValue();
			//create a sprite
			Sprite scrollSprite = new Sprite("A.png", "ScrollingSprite", new HashMap<String, VoogaData>(), new VoogaNumber());
			scrollSprite.getImage().setOpacity(0);
			scrollSprite.getVelocity().setVelocity(scrollSpeed, scrollAngle);
			myScrollingSprite = scrollSprite;
			return scrollSprite;			
		}
		
	}
	
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
	public void increateScrollingSpeed(Sprite scrollingSprite) {
		if (isExponentialScroll) {
			Double prevMagnitude = scrollingSprite.getVelocity().getMagnitude();
			prevMagnitude = prevMagnitude + INCREASE_FACTOR;
			scrollingSprite.getVelocity().setVelocity(prevMagnitude, scrollingSprite.getVelocity().getAngleDegree());
		}
	}
	
//	/**
//	 * @return the myScrollingSpriteID
//	 */
//	public String getMyScrollingSpriteID() {
//		return myScrollingSpriteID;
//	}
	

	/**
	 * @return the myScrollingSprite
	 */
	public Sprite getScrollingSprite() {
		return myScrollingSprite;
	}
//	/**
//	 * @param myScrollingSprite the myScrollingSprite to set
//	 */
//	public void setScrollingSprite(Sprite myScrollingSprite) {
//		this.myScrollingSprite = myScrollingSprite;
//	}
//	
//	
	
	
	
	

	/**
	 * Default constructor that sets the screen size to be used
	 * in the class and myAdjustFactorX which is defined to be
	 * half of the screen size
	 * 
	 * @param screensize
	 */
	public DisplayScroller(int x, int y) {

		myScreenSizeX = x;
		myScreenSizeY = y;
		myAdjustFactorX = myScreenSizeX / 2; // Magic value?
		//		myAdjustFactorY = myScreenSizeY / 2;
		myConstantScrollCenter = 0;

	}


	/**
	 * Takes in the raw list of all Nodes in the game and mainCharLocation
	 * returns a new list of Nodes to be displayed in GameDisplay
	 * 
	 * @param allNodes, rightEdgeLocation
	 * @return
	 * @deprecated Generic scroll structure for reference
	 */
	@Deprecated
	public <E> List<Node> scroll(List<E> allNodes, int mainCharLocation) {

		//		BoundingBox myBoundingBox = new 
		//		List<Node> nodesToDisplay = allNodes.stream()
		//											.map(n -> (Node) n)
		//											.filter(n -> n.getL)
		//		
		//		
		//		
		//		allNodes.forEach(Node node -> {
		//			if ())
		//		});
		//		
		//		
		//		List<Entry> updatedEntries = 
		//			    entryList.stream()
		//			             .peek(e -> e.setTempId(tempId))
		//			             .collect (Collectors.toList());
		//		
		//		return nodesToDisplay;
		return null;

	}

	/**
	 * Takes in the raw list of all Nodes in the game and Vector
	 * returns a new list of Nodes to be displayed in GameDisplay
	 * To be called at every time step
	 * 
	 * Scrolling type: centered scroll
	 * 
	 * @param allNodes, rightEdgeLocation
	 * @return
	 */
	public <E> List<Node> centerScroll(List<E> allNodes, double centeredCharXPos) {
		//double mainCharXPos = mainCharLocation.getX();
		
//		if (DEBUG) (ArrayListList<Nodes>) allNodes;
		System.out.println("CENTER SCROLLING");
		
		List<Node> nodesToDisplay;
		if (centeredCharXPos <= myAdjustFactorX) {
			nodesToDisplay = allNodes.stream()
					.map(n -> (Node) n)
					.filter(n -> n.getLayoutX() <= myScreenSizeX)
					.collect(Collectors.toList());
		}
		else {
			nodesToDisplay = allNodes.stream()
					.map(n -> (Node) n)
					.filter(n -> n.getLayoutX() <= centeredCharXPos + myAdjustFactorX)
					.filter(n -> n.getLayoutX() >= centeredCharXPos - myAdjustFactorX)
					.collect(Collectors.toList());
		}
		return nodesToDisplay;

	}

	/**
	 * Takes in the raw list of all Nodes in the game and Vector
	 * returns a new list of Nodes to be displayed in GameDisplay
	 * To be called at every time step
	 * 
	 * Scrolling type: constant scrolling to the right
	 * 
	 * @param allNodes, rightEdgeLocatioin
	 * @return
	 */
	public <E> List<Node> constantScroll(List<E> allNodes, int speed) {

		List<Node> nodesToDisplay;
		nodesToDisplay = centerScroll(allNodes, myConstantScrollCenter);
		myConstantScrollCenter+=speed;
		return nodesToDisplay;

	}

	/**
	 * @return the myScreenSizeX
	 */
	public int getScreenSizeX() {
		return myScreenSizeX;
	}

	/**
	 * @param myScreenSizeX the myScreenSizeX to set
	 */
	public void setScreenSizeX(int myScreenSizeX) {
		this.myScreenSizeX = myScreenSizeX;
	}

	/**
	 * @return the myScreenSizeY
	 */
	public int getScreenSizeY() {
		return myScreenSizeY;
	}

	/**
	 * @param myScreenSizeY the myScreenSizeY to set
	 */
	public void setScreenSizeY(int myScreenSizeY) {
		this.myScreenSizeY = myScreenSizeY;
	}


	@Override
	public void setScreenSizeX() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setScreenSizeY() {
		// TODO Auto-generated method stub
		
	}







}
