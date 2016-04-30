package tools;

import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Utilities for the graphic unit interface
 */
public class GUIUtils {
	
	private static final double SPACING = 10;
	
	private GUIUtils () {
    }

    private static class SingletonHolder {
        private static final GUIUtils INSTANCE = new GUIUtils();
    }

    public static GUIUtils getInstance () {
        return SingletonHolder.INSTANCE;
    }
	
	public static HBox makeRow(Node...nodes) {
		HBox row = new HBox(SPACING);
		Arrays.asList(nodes).stream()
							.forEach(row.getChildren()::add);
		return row;
	}

}
