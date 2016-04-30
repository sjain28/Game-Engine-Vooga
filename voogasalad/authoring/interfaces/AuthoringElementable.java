package authoring.interfaces;

import authoring.gui.Selector;

public interface AuthoringElementable extends Elementable {
    public EngineElementable getElementable();
    public void select(Selector selector);
}
