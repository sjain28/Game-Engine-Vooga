package authoring.splash;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.items.NumberTextField;
import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import tools.GUIUtils;
import tools.Pair;

/**
 * Formats the initial game
 * @author Nick
 *
 */
public class ProjectInitializationPrompt extends StarterPrompt {

    private static final double WINDOW_WIDTH = 600;
    private static final double WINDOW_HEIGHT = 400;

    private GridPane container;
    private TextField name;
    private TextField description;
    private NumberTextField width;
    private NumberTextField height;
    private HBox dimensions;

    private EventHandler<ActionEvent> e;

    public ProjectInitializationPrompt () {
        super();
    }

    @Override
    public void setProceedEvent (EventHandler<ActionEvent> proceedEvent) {
        this.e = proceedEvent;
        Button proceed = new ButtonMaker().makeButton("OK", e);
        container.add(proceed, 0, 6);
    }

    @Override
    protected void initializeContainer () {
        container = new GridPane();
        container.setHgap(SPACING);
        container.setVgap(SPACING);
        container.setAlignment(Pos.CENTER);
        initializeFields();
        container.add(new CustomText("Welcome!", FontWeight.BOLD, HEADER_SIZE), 0, 0);
        container.add(new CustomText("Build a new game.", FontWeight.BOLD), 0, 1);
        container.add(new CustomText("Name:"), 0, 2);
        container.add(name, 1, 2);
        container.add(new CustomText("Description:"), 0, 3);
        container.add(description, 1, 3);
        container.add(new CustomText("Game Dimensions:"), 0, 4);
        container.add(dimensions, 1, 4);
    }

    private void initializeFields () {
        name = new TextField();
        name.setPromptText("E.g. Zombie Apocalypse II");
        description = new TextField();
        description.setPromptText("E.g. Survive against waves of terrifying zombies!");
        width = new NumberTextField();
        width.setText("600");
        height = new NumberTextField();
        height.setText("600");
        dimensions = GUIUtils.makeRow(width, new CustomText(" x "), height);
    }

    @Override
    protected void setTheScene () {
        Scene scene = new VoogaScene(container, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setScene(scene);
    }

    /**
     * returns name of the Game
     * 
     * @return
     */
    public String getName () {
        return name.getText();
    }

    /**
     * returns description of the game
     * 
     * @return
     */
    public String getDescription () {
        return description.getText();
    }

    /**
     * returns dimensions of the game
     * 
     * @return
     */
    public Pair<String, String> getDimension () {
        return new Pair<String, String>(width.getText(), height.getText());
    }

}
