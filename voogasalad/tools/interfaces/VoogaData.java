package tools.interfaces;

import javafx.beans.property.Property;
import javafx.scene.Node;

/**
 * Interface created for passing data in the salad
 */
public interface VoogaData {

    Object getValue ();

    void setValue (Object o);

    Node display ();

    <T> Property<T> getProperty ();

    <T> void setProperty (T newVal);
}
