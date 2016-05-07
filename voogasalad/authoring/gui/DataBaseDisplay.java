// This entire file is part of my masterpiece.
// Nick Lockett

/*
 * I believe that this class is my best work because it embodies the extensibility and flexibility that Duvall worked so
 * hard to teach us from a design perspective for the user.  This code is free from code smells, broken up into 
 * many different methods which are named clearly and enact only a single task, uses a properties file to store often 
 * changed values, and uses functional programming to work with lists in a far easier way.  Most importantly however, is 
 * that is can be extended incredibly easily to compare any stats saved in the database in any combination without any 
 * modification.  Furthermore, since it only depends upon the database, it coudl easily be packed and tossed around to any 
 * other similarly structured project, and would work with no modification.  (so long as the database interfaces were matched)
 * 
 * 
 */
package authoring.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import authoring.VoogaScene;
import authoring.resourceutility.ButtonMaker;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.VoogaBundles;
import stats.database.CellEntry;
import stats.database.StatCell;
import stats.database.VoogaDataBase;
import stats.database.VoogaGame;
import stats.database.VoogaUser;
import stats.visualization.StatsVisualizer;
import tools.GUIUtils;

/**
 * Displays information stored in the database. Can be flexibly graphed in any
 * potential combination
 * 
 * @author Nick
 *
 */
public class DataBaseDisplay extends Stage {

	private double HEADER_HEIGHT;
	private double HEADER_WIDTH;
	private double DATA_HEIGHT;
	private double DATA_WIDTH;
	private String USR;
	
	private String X_CHECK = "X";
	private String Y_CHECK = "Y";
	
	
	private static final double FONT_SIZE = 30;
	private static final double TABPANE_SIZE_FACTOR = 3d / 4d;
	private static final double ACCORDION_SIZE_FACTOR = 1d / 4d;
	
	private VoogaDataBase database = VoogaDataBase.getInstance();
	private VoogaUser user;
	private CellEntry selectedCell;
	private Tab plotMaker;
	private TabPane plots;
	private String nextX = "";
	private String nextY = "";
	private HBox data;

	private ResourceBundle databaseProperties;

	public DataBaseDisplay() {
		databaseProperties = VoogaBundles.databaseProperties;
		HEADER_HEIGHT = Double.parseDouble(databaseProperties.getString("HeaderHeight"));
		HEADER_WIDTH = Double.parseDouble(databaseProperties.getString("HeaderWidth"));
		DATA_HEIGHT = Double.parseDouble(databaseProperties.getString("DataHeight"));
		DATA_WIDTH = Double.parseDouble(databaseProperties.getString("DataWidth"));
		USR = databaseProperties.getString("Username");
		user = database.getUser(VoogaBundles.preferences.getProperty(USR));


		VBox content = new VBox();
		HBox header = makeHeader();
		data = makeInfoDisplay();
		content.getChildren().addAll(header, data);
		this.setScene(new VoogaScene(content));
	}

	private HBox makeHeader() {
		HBox ans;
		ImageView pict = new ImageView();
		try {
			pict = user.displayProfilePicture();
		} catch (Exception IllegalArgumentException) {
		}
		pict.setFitHeight(HEADER_HEIGHT);
		pict.setPreserveRatio(true);
		Text t = new Text(user.getProperty(VoogaUser.DISPLAY_NAME).toString());
		t.setFill(Color.valueOf(databaseProperties.getString("textColor")));
		t.setFont(Font.font(FONT_SIZE));
		ans = GUIUtils.makeRow(pict, t);
		ans.setAlignment(Pos.BASELINE_LEFT);
		ans.setPrefSize(HEADER_WIDTH, HEADER_HEIGHT);
		
		return ans;
	}

	private HBox makeInfoDisplay() {
		HBox ans = new HBox();

		plots = new TabPane();
		plotMaker = new Tab(databaseProperties.getString("PlotMaker"));
		plotMaker.setContent(plotMaker());
		plots.getTabs().add(plotMaker);
		plots.setPrefWidth(DATA_WIDTH * TABPANE_SIZE_FACTOR);

		ans.getChildren().addAll(makeGameList(), plots);
		ans.setPrefSize(DATA_WIDTH, DATA_HEIGHT);
		return ans;
	}

	private VBox plotMaker() {
		VBox scene = new VBox();
		HBox xData = dataSelector(databaseProperties.getString("xVal"));
		HBox yData = dataSelector(databaseProperties.getString("yVal"));
		
		Button apply = new Button("Apply");
		apply.setOnAction(e -> makeNewTab());
		scene.getChildren().addAll(xData, yData, apply);
		return scene;
	}

	private HBox dataSelector(String label) {
		Text t = new Text(label);
		t.setFill(Color.WHITE);

		ComboBox<String> cellData = new ComboBox<>();
		try {
			cellData.setItems(selectedCell.getPropertyOptions());
			cellData.getItems().addAll(((StatCell) selectedCell).getAuthorStats().get(0).getPropertyOptions());
			cellData.getItems().addAll(((StatCell) selectedCell).getPlayStats().get(0).getPropertyOptions());
		} catch (Exception ArrayIndexOutOfBoundsException) {
		}

		Button set = new ButtonMaker().makeButton("Set", e -> {
			if (label.contains(X_CHECK)) {
				nextX = cellData.getValue();
			}
			if (label.contains(Y_CHECK)) {
				nextY = cellData.getValue();
			}
		});
		return GUIUtils.makeRow(t, cellData, set);
	}

	private void makeNewTab() {
		Tab tab = new Tab(nextX + nextY);
		StatsVisualizer gmaker = new StatsVisualizer();
		CellEntry authorCell = ((StatCell) selectedCell).getAuthorStats().get(0);
		CellEntry playCell = ((StatCell) selectedCell).getPlayStats().get(0);
		
		List<CellEntry> xlist = new ArrayList<>();
		List<CellEntry> ylist = new ArrayList<>();
		if (authorCell.getPropertyOptions().contains(nextX)) {
			xlist = ((StatCell) selectedCell).getAuthorStats();
		} else if (playCell.getPropertyOptions().contains(nextX)) {
			xlist = ((StatCell) selectedCell).getPlayStats();
		}

		if (authorCell.getPropertyOptions().contains(nextY)) {
			ylist = ((StatCell) selectedCell).getAuthorStats();
		} else if (playCell.getPropertyOptions().contains(nextY)) {
			ylist = ((StatCell) selectedCell).getPlayStats();
		}
		tab.setContent(gmaker.getVoogaStatsScatterPlot(xlist, ylist, nextX, nextY));
		plots.getTabs().add(tab);
	}

	private Node makeGameList() {
		Accordion lists = new Accordion();
		TitledPane gameHousing= new TitledPane();
		
		gameHousing.setText(databaseProperties.getString("Games"));
		ListView<String> gameList = new ListView<>();
		
		List<String> games = database.getStatsbyUser(user.getProperty(VoogaUser.USER_NAME).toString()).stream()
				.map(e -> e.getProperty(StatCell.MY_GAME).toString()).collect(Collectors.toList());
		
		gameList.getItems().setAll(games);
		gameList.setOnMouseClicked(e -> clickList(gameList.getSelectionModel().getSelectedItem()));
		gameHousing.setContent(gameList);
		lists.getPanes().addAll(gameHousing);
		lists.setPrefWidth(DATA_WIDTH * ACCORDION_SIZE_FACTOR);
		return lists;
	}

	private void clickList(String game) {
		database.getGame(game);
		selectedCell = database.getStatByGameAndUser(
				database.getGame(game).getProperty(VoogaGame.GAME_NAME).getValue().toString(),
				user.getProperty(VoogaUser.USER_NAME).getValue().toString());
		plotMaker.setContent(plotMaker());
	}
}
