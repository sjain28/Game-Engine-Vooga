package authoring.gui.menubar;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import authoring.interfaces.gui.Windowable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;
import tools.OrderedProperties;

/**
 * MenuPanel that houses all the menu items.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public class MenuPanel extends MenuBar implements Windowable {

	/**
	 * private instance variabls
	 */
	private OrderedProperties menubarProperties;
	private static final String MENU_KEY = "Menu";
	private static final String ITEM_KEY = "Item";

	@Deprecated
	public MenuPanel(CompleteAuthoringModelable elementManager, EventHandler<ActionEvent> menuItemEvent) {
		menubarProperties = VoogaBundles.menubarProperties;
		makeMenus(menuMap(menuItemEvent));
	}

	public MenuPanel(Menuable elementManager, EventHandler<ActionEvent> menuItemEvent, OrderedProperties properties) {
		menubarProperties = properties;
		makeMenus(menuMap(menuItemEvent));
	}

	/**
	 * Returns a map of menu names to menu items.
	 * 
	 * @param menuItemEvent
	 * @return
	 */
	private Map<String, List<MenuItem>> menuMap(EventHandler<ActionEvent> menuItemEvent) {
		Map<String, List<MenuItem>> menus = new LinkedHashMap<>();
		for (Enumeration<?> enumer = menubarProperties.propertyNames(); enumer.hasMoreElements();) {
			String key = (String) enumer.nextElement();
			if (key.endsWith(MENU_KEY)) {
				menus.put(menubarProperties.getProperty(key), new ArrayList<MenuItem>());
			} else if (key.endsWith(ITEM_KEY)) {
				String menuBelonging = findMenu(key.replace(ITEM_KEY, ""));
				List<MenuItem> items = menus.get(menuBelonging);
				MenuItem item = new MenuItem(menubarProperties.getProperty(key));
				item.setId(key);
				item.setOnAction(menuItemEvent);
				items.add(item);
				menus.put(menuBelonging, items);
			}
		}
		return menus;
	}

	/**
	 * Makes the menus given a menu map.
	 * 
	 * @param menuMap
	 */
	private void makeMenus(Map<String, List<MenuItem>> menuMap) {
		for (String menuName : menuMap.keySet()) {
			Menu menu = new Menu(menuName);
			menu.getItems().addAll(menuMap.get(menuName));
			this.getMenus().add(menu);
		}
	}

	/**
	 * Finds the menu that a menu item string corresponds to.
	 * 
	 * @param str
	 * @return
	 */
	private String findMenu(String str) {
		int start = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			if (Character.isUpperCase(str.charAt(i))) {
				start = i;
				break;
			}
		}
		String menu = str.substring(start);
		for (Enumeration<?> enumer = menubarProperties.propertyNames(); enumer.hasMoreElements();) {
			String key = (String) enumer.nextElement();
			if (key.startsWith(menu)) {
				return menubarProperties.getProperty(key);
			}
		}
		return null;
	}

	public Node getWindow() {
		return this;
	}

}
