package authoring.gui;

import java.util.ArrayList;
import java.util.List;
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
import tools.VoogaAlert;

/**
 * Displays information stored in the database. Can be flexibally graphed in any
 * potential combination
 * 
 * @author Nick
 *
 */
public class DataBaseDisplay extends Stage {

	private static final double HEADER_HEIGHT = 50;
	private static final double HEADER_WIDTH = 700;
	private static final double DATA_WIDTH = 700;
	private static final double DATA_HEIGHT = 400;
	private String USR = "UserName";

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

	public DataBaseDisplay() {
		user = database.getUser(VoogaBundles.preferences.getProperty(USR));
		VBox content = new VBox();
		HBox header = makeHeader();
		data = makeData();
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
		t.setFill(Color.WHITE);
		t.setFont(Font.font(FONT_SIZE));
		ans = GUIUtils.makeRow(pict, t);
		ans.setAlignment(Pos.BASELINE_LEFT);
		ans.setPrefSize(HEADER_WIDTH, HEADER_HEIGHT);
		return ans;
	}

	private HBox makeData() {
		HBox ans = new HBox();

		plots = new TabPane();
		plotMaker = new Tab("Plot Maker");
		plotMaker.setContent(infoOptions());
		plots.getTabs().addAll(plotMaker);
		plots.setPrefWidth(DATA_WIDTH * TABPANE_SIZE_FACTOR);

		ans.getChildren().addAll(makeLists(), plots);
		ans.setPrefSize(DATA_WIDTH, DATA_HEIGHT);
		return ans;
	}

	private VBox infoOptions() {
		VBox scene = new VBox();
		HBox xData = dataSelector("X Value");
		HBox yData = dataSelector("Y Value");
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
			VoogaAlert alert = new VoogaAlert("There is no data.");
			alert.showAndWait();
		}

		Button set = new ButtonMaker().makeButton("Set", e -> {
			// TODO: THIS IS BAD CODING... CHANGE
			if (label.contains("X")) {
					nextX = cellData.getValue();
			}
			if (label.contains("Y")) {
					nextY = cellData.getValue();
			}
		});

		return GUIUtils.makeRow(t, cellData, set);
	}

	private void makeNewTab() {
		Tab tab = new Tab(nextX + nextY);
		StatsVisualizer gmaker = new StatsVisualizer();
		List<CellEntry> xlist = new ArrayList<>();
		List<CellEntry> ylist = new ArrayList<>();
		if (((StatCell) selectedCell).getAuthorStats().get(0).getPropertyOptions().contains(nextX)) {
			xlist = ((StatCell) selectedCell).getAuthorStats();
		} else if (((StatCell) selectedCell).getPlayStats().get(0).getPropertyOptions().contains(nextX)) {
			xlist = ((StatCell) selectedCell).getPlayStats();
		}

		if (((StatCell) selectedCell).getAuthorStats().get(0).getPropertyOptions().contains(nextY)) {
			ylist = ((StatCell) selectedCell).getAuthorStats();
		} else if (((StatCell) selectedCell).getPlayStats().get(0).getPropertyOptions().contains(nextY)) {
			ylist = ((StatCell) selectedCell).getPlayStats();
		}
		tab.setContent(gmaker.getVoogaStatsScatterPlot(xlist, ylist, nextX, nextY));
		plots.getTabs().add(tab);
		System.out.println(plots.getTabs().size());
	}

	private Node makeLists() {
		Accordion lists = new Accordion();
		TitledPane games = new TitledPane();
		games.setText("Games");
		ListView<String> actualGames = new ListView<>();
		List<String> authoredGames = database.getStatsbyUser(user.getProperty(VoogaUser.USER_NAME).toString()).stream()
				.map(e -> e.getProperty(StatCell.MY_GAME).toString()).collect(Collectors.toList());
		actualGames.getItems().setAll(authoredGames);
		actualGames.setOnMouseClicked(e -> clickList(actualGames.getSelectionModel().getSelectedItem()));
		games.setContent(actualGames);
		lists.getPanes().addAll(games);
		lists.setPrefWidth(DATA_WIDTH * ACCORDION_SIZE_FACTOR);
		return lists;
	}

	private void clickList(String game) {
		database.getGame(game);
		selectedCell = database.getStatByGameAndUser(
				database.getGame(game).getProperty(VoogaGame.GAME_NAME).getValue().toString(),
				user.getProperty(VoogaUser.USER_NAME).getValue().toString());
		plotMaker.setContent(infoOptions());
	}
}
