package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import tools.OrderedProperties;

/**
 * A class to contain public static references to resource bundles,
 * so that multiple classes requiring access to these properties do
 * not need to independently and repeatedly instantiate them.
 *
 */
public class VoogaBundles {
    
	public final static ResourceBundle extensionProperties = ResourceBundle.getBundle("resources/extensions");
	public final static OrderedProperties menubarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/menunames.properties"));
	public final static OrderedProperties playerMenubarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/playermenunames.properties"));
	public final static ResourceBundle toolbarProperties = ResourceBundle.getBundle("resources/toolbarbuttons");
	public final static ResourceBundle backendToGUIProperties = ResourceBundle.getBundle("resources/GUIClassMap");
	public final static ResourceBundle imageProperties = ResourceBundle.getBundle("resources/imageproperties");
        public final static ResourceBundle textProperties = ResourceBundle.getBundle("resources/textproperties");
        public final static ResourceBundle physicsEffectsToGUI = ResourceBundle.getBundle("resources/PhysicsEffectsToGUI");
	
}
