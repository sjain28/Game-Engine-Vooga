package tools;

import java.util.Comparator;

import javafx.scene.Node;

public class NodeZAxisComparator implements Comparator<Node>{
	@Override
	public int compare(Node o1, Node o2) {
        return ((Double) o1.getTranslateZ()).compareTo(o2.getTranslateZ());
	}
}
