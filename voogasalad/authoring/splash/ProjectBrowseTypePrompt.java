package authoring.splash;

import authoring.VoogaScene;
import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class ProjectBrowseTypePrompt extends StarterPrompt {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 200;
    private static final double SPACING = 10;

    private Button openAll;
    private Button openBySearch;
    private VBox container;

    /**
     * Screen to brows through games
     */
    public ProjectBrowseTypePrompt () {
        super();
    }

    @Override
    protected void setProceedEvent (EventHandler<ActionEvent> proceedEvent) {
        // TODO: name IDs the same as the classname and then use reflection in openCommand
        openAll = new ButtonMaker().makeButton("Browse All", proceedEvent);
        openAll.setId("OpenAll");
        openBySearch = new ButtonMaker().makeButton("Browse By Search", proceedEvent);
        openBySearch.setId("OpenBySearch");
        container.getChildren().addAll(openAll, openBySearch);
    }

    @Override
    protected void initializeContainer () {
        container = new VBox();
        container.setSpacing(SPACING);
        container.setAlignment(Pos.CENTER);
    }

    @Override
    protected void setTheScene () {
        Scene scene = new VoogaScene(container, WIDTH, HEIGHT);
        this.setScene(scene);
    }

}
