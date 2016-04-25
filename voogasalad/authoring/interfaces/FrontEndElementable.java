package authoring.interfaces;

import authoring.gui.Selector;

public interface FrontEndElementable extends Elementable {
    public Elementable getElementable();
    public void select(Selector selector);
}
