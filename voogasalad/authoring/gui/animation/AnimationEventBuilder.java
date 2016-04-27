package authoring.gui.animation;

import authoring.UIManager;
import authoring.VoogaScene;
import authoring.gui.cartography.Connection;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.GameObject;
import authoring.resourceutility.ButtonMaker;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;

public class AnimationEventBuilder extends Stage {

	private CompleteAuthoringModelable elManager;

	private BezierCurve curve;
	private Connection line;

	private BorderPane border;
	private Group stack;
	
	private PathInterpolator interpolator;
	private Shape myShape;

	public AnimationEventBuilder(Menuable model) {
		interpolator = new PathInterpolator();
		elManager = ((UIManager) model).getManager();
		curve = new BezierCurve();
		line = new Connection(0, 0, 100, 100);
		initializePanes();
		initializeScene();
	}

	private void initializePanes() {
		stack = new Group();
		border = new BorderPane();
		// stack.setMinSize(Double.parseDouble(VoogaBundles.designboardProperties.getString("Width")),
		// Double.parseDouble(VoogaBundles.designboardProperties.getString("Height")));
		// for (Node element : elManager.getElements()) {
		// GameObject go = ((GameObject) element);
		// ImageView iv = new ImageView(go.getImage());
		// iv.setLayoutX(go.getTranslateX());
		// iv.setLayoutY(go.getTranslateY());
		// iv.setFitWidth(go.getFitWidth());
		// iv.setFitHeight(go.getFitHeight());
		// stack.getChildren().add(iv);
		// }
		border.setTop(toolbar());
		border.setCenter(stack);
		border.setBottom(controls());
	}

	private void initializeScene() {
		Scene scene = new VoogaScene(border, 700, 700);
		this.setScene(scene);
		this.show();
	}

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
		toolbar.getItems().addAll(spline, linear);
		return toolbar;
	}

	private HBox controls() {
		HBox row = new HBox();
		row.getChildren().add(new ButtonMaker().makeButton("OK", e -> {
			// TODO:
			// Use animation factory to make the animation
			String pathType = myShape.getClass().getSimpleName();
			Class<?> clazz;
			AnimationPath animationPath;
			try {
				clazz = Class.forName("authoring.gui.animation." + pathType + "Path");
	            animationPath = (AnimationPath) clazz.getConstructor(Shape.class).newInstance(myShape);
	            interpolator.interpolate(animationPath.getXControls(), animationPath.getYControls());
				this.close();
				//interpolator.interpolate();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}));
		return row;
	}

}
