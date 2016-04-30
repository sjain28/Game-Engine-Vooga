package tools;

import java.util.Comparator;

import javafx.scene.Node;

public class PairZAxisComparator implements Comparator<Pair<Node, Boolean>>{
	@Override
	public int compare(Pair<Node, Boolean> o1, Pair<Node, Boolean> o2) {
        return ((Double) o1.getFirst().getTranslateZ()).compareTo(o2.getFirst().getTranslateZ());
	}
}
