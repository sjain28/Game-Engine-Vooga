package tools;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;


/**
 * A class that orders properties files alphabetically.
 */
@SuppressWarnings("serial")
public class OrderedProperties extends Properties {

    /**
     * Initializes the class.
     */
    public OrderedProperties () {
        super();

        _names = new Vector<>();
    }

    /**
     * Load a stream of ordered objects
     * 
     * @param inStream
     * @return
     */
    public static OrderedProperties loadOrdered (InputStream inStream) {
        OrderedProperties op = new OrderedProperties();
        try {
            op.load(inStream);
        }
        catch (Exception e) {
            VoogaAlert alert = new VoogaAlert(e.getMessage());
            alert.showAndWait();
        }
        return op;
    }

    /**
     * Gets the names in order.
     */
    public Enumeration<Object> propertyNames () {
        return _names.elements();
    }

    /**
     * Puts an object into the properties file.
     */
    public Object put (Object key, Object value) {
        if (_names.contains(key)) {
            _names.remove(key);
        }

        _names.add(key);

        return super.put(key, value);
    }

    /**
     * Removes an object from the properties file.
     */
    public Object remove (Object key) {
        _names.remove(key);

        return super.remove(key);
    }

    private Vector<Object> _names;

}
