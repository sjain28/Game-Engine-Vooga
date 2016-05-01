package authoring.resourceutility;

import java.util.Iterator;
import java.util.Stack;

import javafx.scene.control.TreeItem;

/**
 * An iterator to iterate through the items in the TreeView
 * @author DoovalSalad
 *
 * @param <T> the parameterization of the TreeView (in this case, VoogaFile)
 */
public class TreeIterator<T> implements Iterator<TreeItem<T>> {
	
	/**
	 * Private instance stack
	 */
    private Stack<TreeItem<T>> stack = new Stack<>();

    /**
     * Initializes the stack with the root of the tree.
     * @param root
     */
    public TreeIterator(TreeItem<T> root) {
        stack.push(root);
    }

    /**
     * Determines whether the stack has more items.
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next item from the stack.
     */
    @Override
    public TreeItem<T> next() {
        TreeItem<T> nextItem = stack.pop();
        nextItem.getChildren().forEach(stack::push);

        return nextItem;
    }
}