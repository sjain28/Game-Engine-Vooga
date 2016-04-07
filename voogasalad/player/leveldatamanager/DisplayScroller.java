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

	private int myScreenSize;
	private int myAdjustFactor;
	private int myConstantScrollCenter;
	
	/**
	 * Default constructor that sets the screen size to be used
	 * in the class and myAdjustFactor which is defined to be
	 * half of the screen size
	 * 
	 * @param screensize
	 */
	public DisplayScroller(int screensize) {
		
		myScreenSize = screensize;
		myAdjustFactor = screensize / 2;
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
		List<Node> nodesToDisplay;
		if (mainCharXPos <= myAdjustFactor) {
			nodesToDisplay = allNodes.stream()
					.map(n -> (Node) n)
					.filter(n -> n.getLayoutX() <= myScreenSize)
					.collect(Collectors.toList());
		}
		else {
			nodesToDisplay = allNodes.stream()
					.map(n -> (Node) n)
					.filter(n -> n.getLayoutX() <= mainCharXPos + myAdjustFactor)
					.filter(n -> n.getLayoutX() >= mainCharXPos - myAdjustFactor)
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
	 * @return the myScreenSize
	 */
	public int getScreenSize() {
		return myScreenSize;
	}
	
	/**
	 * @param myScreenSize the myScreenSize to set
	 */
	public void setScreenSize(int myScreenSize) {
		this.myScreenSize = myScreenSize;
	}
	
}
