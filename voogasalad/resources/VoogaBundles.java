package resources;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import tools.OrderedProperties;

/**
 * A class to contain public static references to resource bundles,
 * so that multiple classes requiring access to these properties do
 * not need to independently and repeatedly instantiate them.
 * 
 * @author DoovalSalad
 *
 */
public class VoogaBundles {
    
	public final static ResourceBundle extensionProperties = ResourceBundle.getBundle("resources/extensions");
	public final static OrderedProperties menubarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/menunames.properties"));
	public final static OrderedProperties playerMenubarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/playermenunames.properties"));
	public final static OrderedProperties playerTesterMenubarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/playertestermenu.properties"));
	public final static OrderedProperties toolbarProperties = OrderedProperties.loadOrdered(new VoogaBundles().getClass().getResourceAsStream("/resources/toolbarbuttons.properties"));
	public final static Properties secrets = makeProperties("secret.properties");
	public final static Properties statsProperties = makeProperties("stats.properties");
	public final static Properties defaultglobalvars = makeProperties("defaultglobalvariables.properties");
	public final static ResourceBundle backendToGUIProperties = ResourceBundle.getBundle("resources/GUIClassMap");
	public final static ResourceBundle imageProperties = ResourceBundle.getBundle("resources/imageproperties");
	public final static ResourceBundle textProperties = ResourceBundle.getBundle("resources/textproperties");
	public final static ResourceBundle physicsEffectsToGUI = ResourceBundle.getBundle("resources/PhysicsEffectsToGUI");
	public final static ResourceBundle GameDisplayProperties = ResourceBundle.getBundle("resources/GameDisplay");
	public final static ResourceBundle exceptionProperties = ResourceBundle.getBundle("resources/exceptions");
	public final static Properties preferences = makeProperties("preferences.properties");
	public final static ResourceBundle EventMethods = ResourceBundle.getBundle("resources/EventMethods");
	public final static ResourceBundle designboardProperties = ResourceBundle.getBundle("resources/designboard");
	public final static ResourceBundle designboardPreferencesProperties = ResourceBundle.getBundle("resources/designboardpreferences");
	public final static ResourceBundle eventswindowProperties = ResourceBundle.getBundle("resources/eventswindow");
	public final static ResourceBundle propertiesPaneProperties = ResourceBundle.getBundle("resources/propertiesPane");
	public final static ResourceBundle spriteProperties = ResourceBundle.getBundle("resources/spritepropertiesmaptags");
	public final static ResourceBundle textMapProperties = ResourceBundle.getBundle("resources/textpropertiesmaptags");
	public final static ResourceBundle fileLocationProperties = ResourceBundle.getBundle("resources/filelocations");
	public final static ResourceBundle textStyles = ResourceBundle.getBundle("resources/textStyles");
	public final static ResourceBundle databaseProperties = ResourceBundle.getBundle("resources/database");
	public final static ResourceBundle leaderboardProperties = ResourceBundle.getBundle("resources/leaderboard");
	public final static ResourceBundle randomcolorProperties = ResourceBundle.getBundle("resources/randomcolor");
	
	public final static ResourceBundle causeNames = ResourceBundle.getBundle("resources/causes");
	public final static ResourceBundle effectNames = ResourceBundle.getBundle("resources/effects");
	public final static ResourceBundle eventBasics = ResourceBundle.getBundle("resources/eventBasics");
	
	
	private static Properties makeProperties(String path) {
		try {
			Properties props = new Properties();
			props.load(VoogaBundles.class.getResourceAsStream(path));
			return props;
		} catch (IOException e) {
			return null;
		}
		
	}
	
}
