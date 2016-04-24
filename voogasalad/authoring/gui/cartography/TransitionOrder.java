package authoring.gui.cartography;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public enum TransitionOrder {
	FIRST, LAST;
	
	private Map<String, Color> orderColorMap;
	
	private TransitionOrder() {
		orderColorMap = new HashMap<String, Color>();
		orderColorMap.put("FIRST", Color.FORESTGREEN);
		orderColorMap.put("LAST", Color.ORANGERED);
	}
	
	public Color getColor() {
		return this.orderColorMap.get(this.name());
	}
	
}
