package authoring.splash;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.tagextension.GamesListing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import tools.GUIUtils;
/**
 * Opens all the Current Games
 * 
 * @author Nick
 *
 */
public class ProjectOpenAllPrompt extends StarterPrompt {
	
	private static final double WINDOW_WIDTH = 600;
	private static final double WINDOW_HEIGHT = 300;
	
	private static final String GAMES_FOLDER_PATH = "games";
	
	private VBox container;
	private GamesListing gamesListing;
	
	public ProjectOpenAllPrompt() {
		super();
	}
	
	@Override
	public void setProceedEvent(EventHandler<ActionEvent> proceedEvent) {
		gamesListing.populateGamesList(getGamesList(), proceedEvent);
	}
	
	@Override
	protected void initializeContainer() {
		container = new VBox();
		container.setSpacing(SPACING);
		container.setPadding(new Insets(SPACING));
		gamesListing = new GamesListing();
		container.getChildren().addAll(GUIUtils.makeRow(new CustomText("Welcome!", FontWeight.BOLD, HEADER_SIZE)),
									   GUIUtils.makeRow(new CustomText("Choose from all these games.", FontWeight.BOLD)),
							  	       GUIUtils.makeRow(gamesListing));
	}
	
	private List<String> getGamesList() {
		List<String> myGames = new ArrayList<String>();
		File gamesFolder = new File(GAMES_FOLDER_PATH);
		for(File game : gamesFolder.listFiles()) {
			myGames.add(game.getName());
		}
		return myGames;
	}
	
	@Override
	protected void setTheScene() {
		Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setScene(scene);
	}

}
