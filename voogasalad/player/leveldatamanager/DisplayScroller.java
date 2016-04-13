package player.leveldatamanager;

import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.Node;

/**
 * DisplayScroller provides public methods that choose Nodes to display
 * in a visible frame
 * 
 * @author Hunter Lee
 *
 */
public class DisplayScroller {

	private int myScreenSizeX;
	private int myScreenSizeY;
	private int myAdjustFactorX;
//	private int myAdjustFactorY;
	private int myConstantScrollCenter;
	
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
		myAdjustFactorX = myScreenSizeX / 2;
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
	public <E> List<Node> centerScroll(List<E> allNodes, double mainCharXPos) {
		
		//double mainCharXPos = mainCharLocation.getX();
		System.out.println("The nodes here are " + allNodes);
		List<Node> nodesToDisplay;
		if (mainCharXPos <= myAdjustFactorX) {
			nodesToDisplay = allNodes.stream()
					.map(n -> (Node) n)
					.filter(n -> n.getLayoutX() <= myScreenSizeX)
					.collect(Collectors.toList());
		}
		else {
			nodesToDisplay = allNodes.stream()
					.map(n -> (Node) n)
					.filter(n -> n.getLayoutX() <= mainCharXPos + myAdjustFactorX)
					.filter(n -> n.getLayoutX() >= mainCharXPos - myAdjustFactorX)
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
	
}
