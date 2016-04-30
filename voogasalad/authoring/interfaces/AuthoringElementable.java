package authoring.interfaces;

import authoring.gui.Selector;

public interface AuthoringElementable extends Elementable {
    public Elementable getElementable();
    public void select(Selector selector);
}
