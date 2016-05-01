package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.List;

import authoring.interfaces.model.EditEventable;
import authoring.resourceutility.ButtonMaker;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;

public class EventAccoridion extends Tab {
	private List<TitledPane> tiles;
	private EditEventable manager;

	private BorderPane pane;
	private Accordion accordion;
	private HBox buttons;

	private String name = "";
	private static final double BUTTON_PADDING = 10;
	
	public EventAccoridion(EditEventable manager, String name, Button... addedButtons) {
		this.name = name;
		this.manager = manager;
		pane = new BorderPane();
		accordion = new Accordion();

		initializeButtons(addedButtons);
		pane.setBottom(buttons);
		pane.setCenter(accordion);

		tiles = new ArrayList<>();
		generateTiles(1);

		this.setText(name);
		this.setContent(pane);
		this.setClosable(false);
	}

	private void generateTiles(int count) {
		for (int i = 0; i < count; i++) {
			try {
				tiles.add(populateTiles(name + " " + (tiles.size() + 1)));
			} catch (VoogaException e) {
				VoogaAlert alert = new VoogaAlert(e.getMessage());
				alert.showAndWait();
			}
		}
	}

	private TitledPane populateTiles(String name) throws VoogaException {
		TitledPane tile = createTile();
		tile.setText(name);
		tile.setOnMouseClicked((MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				tiles.remove(tile);
				accordion.getPanes().remove(tile);
			}
		});
		accordion.getPanes().add(tile);
		return tile;
	}

	private TitledPane createTile() throws VoogaException {
		if (name == null) {
			return null;
		}

		String className = VoogaBundles.backendToGUIProperties.getString(name);
		Class<?> c = null;

		try {
			c = Class.forName(className);
			EventTitledPane titledPane;
			Object o = c.getConstructor(EditEventable.class).newInstance(manager);
			titledPane = (EventTitledPane) o;
			return titledPane;
		} catch (Exception e) {
			throw new VoogaException(e.getMessage());
		}
	}

	private void initializeButtons(Button... addedButtons) {
		buttons = new HBox();
		buttons.setPadding(new Insets(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING));
		buttons.getChildren().addAll(new ButtonMaker().makeButton("Add " + name, e -> generateTiles(1)));
		buttons.getChildren().addAll(addedButtons);
	}

	public List<String> getDetails() {
		try {
			List<String> eventList = new ArrayList<>();
			for (TitledPane pane : accordion.getPanes()) {
				EventTitledPane eventPane = (EventTitledPane) pane;
				eventList.add(eventPane.getDetails());
			}
			return eventList;
		} catch (VoogaException e) {
			VoogaAlert va = new VoogaAlert(e.getMessage());
			va.showAndWait();
		}
		return null;
	}
}
