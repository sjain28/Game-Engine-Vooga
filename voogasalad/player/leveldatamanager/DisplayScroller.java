package player.leveldatamanager;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.BoundingBox;
import javafx.scene.Node;
import tools.Vector;

/**
 * DisplayScroller provides public methods that choose Nodes to display
 * in a visible frame
 * 
 * @author Heeb
 *
 */
public class DisplayScroller {

	private int myScreenSize;
	private int myAdjustFactor;
	
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
		
	}
	
	/**
	 * Takes in the raw list of all Nodes in the game and mainCharLocation
	 * returns a new list of Nodes to be displayed in GameDisplay
	 * 
	 * @param allNodes, rightEdgeLocatioin
	 * @return
	 */
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
	 * 
	 * @param allNodes, rightEdgeLocatioin
	 * @return
	 */
	public <E> List<Node> scroll(List<E> allNodes, Vector mainCharLocation) {
		
		BoundingBox myBoundingBox = new 
		List<Node> nodesToDisplay = allNodes.stream()
											.map(n -> (Node) n)
											.filter(n -> n.getL)
		
		
		
		allNodes.forEach(Node node -> {
			if ())
		});
		
		
		List<Entry> updatedEntries = 
			    entryList.stream()
			             .peek(e -> e.setTempId(tempId))
			             .collect (Collectors.toList());
		
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
