package tools;

import java.util.Comparator;

import javafx.scene.Node;

/**
 * Use for comparing nodes with respect to the z-axis
 */
public class NodeZAxisComparator implements Comparator<Node>{
	
	/**
	 *	Compare Z-axis of the two nodes
	 */
	@Override
	public int compare(Node o1, Node o2) {
        return ((Double) o1.getTranslateZ()).compareTo(o2.getTranslateZ());
	}
}
