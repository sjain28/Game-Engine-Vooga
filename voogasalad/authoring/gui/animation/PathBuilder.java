package authoring.gui.animation;

import authoring.CustomText;
import authoring.gui.cartography.Connection;
import authoring.resourceutility.ButtonMaker;
import events.AnimationFactory;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import tools.GUIUtils;
import tools.VoogaAlert;
import tools.VoogaInfo;

/**
 * The tab that can be used for building a path, either linear or curved. Allows users to define
 * up to one path and name it for use later.
 * 
 * @author Aditya Srinivasan
 *
 */
public class PathBuilder extends Tab {

	/**
	 * Constants
	 */
	private static final double SPACING = 10;
	private static final double END_X = 100;
	private static final double END_Y = 100;

	/**
	 * Private instance variables
	 */
	private BezierCurve curve;
	private Connection line;

	private BorderPane border;
	private Group stack;

	private PathInterpolator interpolator;
	private Shape myShape;

	/**
	 * Initializes the interpolator, paths, and JavaFX scene components.
	 */
	PathBuilder() {
		interpolator = new PathInterpolator();
		curve = new BezierCurve();
		line = new Connection(0, 0, END_X, END_Y);
		initializePanes();
		initializeScene();
	}

	/**
	 * Initializes the panes on which content is added, including the
	 * paths, buttons, and text fields.
	 */
	private void initializePanes() {
		stack = new Group();
		border = new BorderPane();
		border.setTop(toolbar());
		border.setCenter(stack);
		border.setBottom(controls());
	}

	/**
	 * Sets the content of the tab to be the Border Pane.
	 */
	private void initializeScene() {
		this.setContent(border);
	}

	/**
	 * Generates the toolbar used for adding curves or lines.
	 * @return
	 */
	private ToolBar toolbar() {
		ToolBar toolbar = new ToolBar();
		Button spline = new ButtonMaker().makeButton("Add Curve", e -> {
			stack.getChildren().remove(line);
			if (!stack.getChildren().contains(curve)) {
				stack.getChildren().add(curve);
				myShape = curve.getCurve();
			}
		});
		Button linear = new ButtonMaker().makeButton("Add Line", e -> {
			stack.getChildren().remove(curve);
			if (!stack.getChildren().contains(line)) {
				stack.getChildren().add(line);
				myShape = line.getLine();
			}
		});
		toolbar.getItems().addAll(spline, linear, coordinates());
		return toolbar;
	}

	private HBox coordinates() {
		CustomText text = new CustomText("");
		border.setOnMouseMoved(e -> {
			text.setText(String.format("X:  %s   Y:  %s", e.getX(), e.getY()));
		});
		return GUIUtils.makeRow(text);
	}

	/**
	 * Defines the control bar used for naming the path and adding it to the map.
	 * If done successfuly, the user is informed through a popup.
	 * @return
	 */
	private VBox controls() {
		VBox row = new VBox(SPACING);
		row.setPadding(new Insets(SPACING));
		TextField name = new TextField();
		name.setPromptText("Name your path");
		GUIUtils.getInstance();
		row.getChildren().addAll(GUIUtils.makeRow(new CustomText("Name: "), name),
				new ButtonMaker().makeButton("Add Path", e -> {
					try {
						String pathType = myShape.getClass().getSimpleName();
						Class<?> clazz;
						AnimationPath animationPath;
						clazz = Class.forName("authoring.gui.animation." + pathType + "Path");
						animationPath = (AnimationPath) clazz.getConstructor(Shape.class).newInstance(myShape);
						interpolator.interpolate(animationPath.getXControls(), animationPath.getYControls());
						AnimationFactory.getInstance().addPath(name.getText(), interpolator.getXInterpolation(),
								interpolator.getYInterpolation());
						stack.getChildren().clear();
						VoogaInfo info = new VoogaInfo("Your path, \"" + name.getText() + "\", was created!");
						info.showAndWait();
						name.setText("");
					} catch (Exception e1) {
						VoogaAlert alert = new VoogaAlert("Please define a proper path and name.");
						alert.showAndWait();
					}
				}));
		return row;
	}

}
