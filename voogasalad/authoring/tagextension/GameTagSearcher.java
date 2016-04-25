package authoring.tagextension;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.splash.GamesListing;
import authoring.splash.StarterPrompt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

public class GameTagSearcher extends StarterPrompt implements Observer {
	
	private static final double WINDOW_WIDTH = 500;
	private static final double WINDOW_HEIGHT = 300;
	private static final double SPACING = 10;
	
	private VBox container;
	private TextField searchBar;
	private GameTagLibrary library;
	private TagDisplayContainer tagHolder;
	private ObservableList<String> tags;
	private GamesListing gamesListing;
	private EventHandler<ActionEvent> e;
	
	public GameTagSearcher() {
		super();
		library = new GameTagLibrary();
	}
	
	@Override
	protected void initializeContainer() {
		tags = FXCollections.observableArrayList();
		tagHolder = new TagDisplayContainer();
		tagHolder.addObserver(this);
		container = new VBox();
		container.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		searchBar = new TextField();
		gamesListing = new GamesListing();
		searchBar.setPromptText("Search for a game using some tags, e.g. cartoon, desert, fire");
		searchBar.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				tagHolder.addTag(searchBar.getText());
				searchBar.clear();
			}
		});
		container.getChildren().addAll(makeRow(new CustomText("Dooval's Arcade", FontWeight.BOLD, HEADER_SIZE)),
									   makeRow(new CustomText("Explore other games.", FontWeight.BOLD)),
							  	       makeRow(new CustomText("Search:"), searchBar),
							  	       tagHolder.display(),
							  	       gamesListing);
	}
	
	@Override
	protected void setTheScene() {
		Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setScene(scene);
	}

	@Override
	protected void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		this.e = proceedEvent;
		gamesListing.populateGamesList(getMatchingGames(), proceedEvent);
	}
	
	private List<String> getMatchingGames() {
		return library.getListOfGameNamesByTag(tags);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof TagDisplayContainer) {
			List<TagLabel> tagLabels = (List<TagLabel>) arg;
			tags.clear();
			tagLabels.stream()
		     		 .map(TagLabel::getTag)
		     		 .forEach(tags::add);
			gamesListing.populateGamesList(getMatchingGames(), this.e);
		}
	}
	

}
