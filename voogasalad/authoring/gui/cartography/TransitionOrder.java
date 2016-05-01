package authoring.gui.cartography;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * Enum class to help keep the levels transitioning in order.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public enum TransitionOrder {
	FIRST, LAST;

	private Map<String, Color> orderColorMap;

	/**
	 * Instantiates the map of first to last.
	 */
	private TransitionOrder() {
		orderColorMap = new HashMap<>();
		orderColorMap.put("FIRST", Color.FORESTGREEN);
		orderColorMap.put("LAST", Color.ORANGERED);
	}

	/**
	 * Gets the color of the enum
	 * @return
	 */
	public Color getColor() {
		return this.orderColorMap.get(this.name());
	}

}
