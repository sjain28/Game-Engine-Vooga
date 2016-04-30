package authoring.splash;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.tagextension.GameTagLibrary;
import authoring.tagextension.GamesListing;
import authoring.tagextension.TagDisplayContainer;
import authoring.tagextension.TagLabel;
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
import tools.GUIUtils;

/**
 * The GUI through which game libraries can be searched based on user-defined tags.
 * 
 * @author adityasrinivasan
 *
 */
public class ProjectOpenBySearchPrompt extends StarterPrompt implements Observer {
	
	/**
	 * Constants
	 */
	private static final String TAG_PROMPT = "Search for a game using some tags, e.g. cartoon, desert, fire";
	private static final String TITLE = "Dooval's Arcade";
	private static final String SUBTITLE = "Explore other games.";
	private static final String SEARCH = "Search:";
	
	private static final double WINDOW_WIDTH = 500;
	private static final double WINDOW_HEIGHT = 500;
	private static final double SPACING = 10;
	
	/**
	 * Private instance variables
	 */
	private VBox container;
	private TextField searchBar;
	private GameTagLibrary library;
	private TagDisplayContainer tagHolder;
	private ObservableList<String> tags;
	private GamesListing gamesListing;
	private EventHandler<ActionEvent> e;
	
	public ProjectOpenBySearchPrompt() {
		super();
	}
	
	@Override
	protected void initializeContainer() {
		initializeTagLibrary();
		tagHolder = new TagDisplayContainer();
		tagHolder.addObserver(this);
		container = new VBox();
		container.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		initializeSearchBar();
		container.getChildren().addAll(GUIUtils.makeRow(new CustomText(TITLE, FontWeight.BOLD, HEADER_SIZE)),
									   GUIUtils.makeRow(new CustomText(SUBTITLE, FontWeight.BOLD)),
							  	       GUIUtils.makeRow(new CustomText(SEARCH), searchBar),
							  	       tagHolder.display(),
							  	       gamesListing);
	}
	
	private void initializeTagLibrary() {
		library = new GameTagLibrary();
		gamesListing = new GamesListing();
		tags = FXCollections.observableArrayList();
	}
	
	private void initializeSearchBar() {
		searchBar = new TextField();
		searchBar.setPromptText(TAG_PROMPT);
		searchBar.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				tagHolder.addTag(searchBar.getText());
				searchBar.clear();
			}
		});
	}
	
	@Override
	protected void setTheScene() {
		Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setScene(scene);
	}

	@Override
	protected void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		this.e = proceedEvent;
	}
	
	private List<String> getMatchingGames() {
		return library.getListOfGameNamesByTag(tags);
	}

	/**
	 * Observes the tags in the container, either added or deleted, and updates
	 * the listing of relevant games accordingly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof TagDisplayContainer) {
			List<TagLabel> tagLabels = (List<TagLabel>) arg;
			tags.clear();
			tagLabels.stream()
		     		 .map(TagLabel::toString)
		     		 .forEach(tags::add);
			gamesListing.populateGamesList(getMatchingGames(), this.e);
		}
	}

}
