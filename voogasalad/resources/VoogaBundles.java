package resources;

import java.util.ResourceBundle;

import auxiliary.OrderedProperties;

/**
 * A class to contain public static references to resource bundles,
 * so that multiple classes requiring access to these properties do
 * not need to independently and repeatedly instantiate them.
 *
 */
public class VoogaBundles {

	public final static ResourceBundle extensionProperties = ResourceBundle.getBundle("resources/extensions");
	public final static OrderedProperties menubarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/menunames.properties"));
	public final static ResourceBundle toolbarProperties = ResourceBundle.getBundle("resources/toolbarbuttons");
	public final static ResourceBundle backendToGUIProperties = ResourceBundle.getBundle("resources/GUIClassMap");
}
