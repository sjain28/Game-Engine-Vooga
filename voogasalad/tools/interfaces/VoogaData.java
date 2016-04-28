package tools.interfaces;

import javafx.beans.property.Property;
import javafx.scene.Node;


public interface VoogaData {

    public Object getValue ();

    public void setValue (Object o);

    public Node display ();

    public <T> Property<T> getProperty ();

    public <T> void setProperty (T newVal);
}
