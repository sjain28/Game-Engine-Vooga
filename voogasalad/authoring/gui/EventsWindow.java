package authoring.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import authoring.interfaces.model.CompleteAuthoringModelable;
import events.Cause;
import events.Effect;
import events.VoogaEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.VoogaAlert;
import tools.VoogaException;

public class EventsWindow extends TabPane implements Observer {

	/**
	 * Constants
	 */
	private static final String NAME = "Event Manager";
	private static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

	/**
	 * Private instance variables
	 */
	private CompleteAuthoringModelable myManager;
	private Tab main;
	private ScrollPane scroller;
	private VBox content;
	private Map<VoogaEvent, ObservableList<String>> effects;
	private Map<VoogaEvent, ObservableList<String>> causes;

	/**
	 * Initialized the Events Window, responsible for displaying all the
	 * currently initialized Causes and Events and their links.
	 * 
	 */
	public EventsWindow(CompleteAuthoringModelable manager) {
		myManager = manager;
		myManager.addObserver(this);
		main = new Tab(NAME);
		content = new VBox();
		scroller = new ScrollPane(content);
		causes = new HashMap<VoogaEvent, ObservableList<String>>();
		effects = new HashMap<VoogaEvent, ObservableList<String>>();
		initialize();
		main.setContent(scroller);
		this.getTabs().add(main);
	}

	private void initialize() {
		for (VoogaEvent e : myManager.getEvents()) {
			if (!causes.keySet().contains(e) && !effects.keySet().contains(e)) {
				ObservableList<String> causesString = FXCollections.observableArrayList();
				for (Cause cause : e.getCauses()) {
					causesString.addAll(cleanString(cause.toString()));
				}
				causes.put(e, causesString);
				ObservableList<String> effectsString = FXCollections.observableArrayList();
				for (Effect effect : e.getEffects()) {
					effectsString.addAll(cleanString(effect.toString()));
				}
				effects.put(e, effectsString);
				HBox info = new HBox();
				ListView<String> causeList = new ListView<String>(causes.get(e));
				ListView<String> effectList = new ListView<String>(effects.get(e));
				Button delete = new Button("X");
				delete.setOnAction(ee -> delete(e, info));
				info.getChildren().addAll(causeList, effectList, delete);
				content.getChildren().add(info);
			}
		}

	}

	private String cleanString(String s) {
		String[] components = s.split(" ");
		StringBuilder ans = new StringBuilder();
		for (String c : components) {
			if (c.matches(UUID_REGEX)) {
				try {
					c = myManager.getSpriteNameFromId(c);
				} catch (VoogaException e) {
					new VoogaAlert("There is no Sprite with That ID");
					e.printStackTrace();
				}
			}
			ans.append(c);
			ans.append(" ");
		}

		return ans.toString();
	}

	private void delete(VoogaEvent e, HBox info) {
		myManager.getEvents().remove(e);
		content.getChildren().remove(info);
		causes.remove(e);
		effects.remove(e);
	}

	@Override
	public void update(Observable o, Object arg) {
		initialize();
	}

}
