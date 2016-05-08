package authoring.gui.animation;

import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import authoring.Command;
import events.AnimationFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import tools.Pair;

public class DisplayPathCBoxCommand implements Command, Observer, Detailable {
	
	private static final String FIELD = "path";
	
	private VBox container;
	private ComboBox<String> paths;
	
	public DisplayPathCBoxCommand(Mediator mediator, VBox container) {
		this.container = container;
		this.paths = new ComboBox<>();
		this.paths.valueProperty().addListener((obs, old, n) -> {
			
		});
		AnimationFactory.getInstance().addObserver(this);
	}

	@Override
	public void execute() {
		if(container.getChildren().contains(paths)) {
			container.getChildren().remove(paths);
		} else {
			container.getChildren().add(paths);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof AnimationFactory && arg instanceof Map) {
			((Collection<String>) AnimationFactory.getInstance().getPathNames()).stream()
																				.filter(s -> !paths.getItems().contains(s))
											   									.forEach(s -> paths.getItems().add(s));
		}
	}

	@Override
	public Pair<String, Object> getDetails() {
		if(paths.getValue() != null && !paths.getValue().isEmpty()) {
			return new Pair<>(FIELD, paths.getValue());
		}
		return null;
	}

}
