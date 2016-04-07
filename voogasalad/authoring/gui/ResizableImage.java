package authoring.gui;

import java.beans.EventHandler;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class ResizableImage extends StackPane {

	private StackPane pane;
	private Rectangle iv;
	private ObservableList<Double> Coins;

	public ResizableImage(Rectangle iv) {
		this.pane = this;
		this.iv = iv;
		this.getChildren().add(iv);
		Coins = FXCollections.observableArrayList();
		// UpperLeft
		Coins.add(iv.getX());
		Coins.add(iv.getY());
		// LowerRight
		Coins.add(iv.getX() + iv.getWidth());
		Coins.add(iv.getY() + iv.getHeight());
		// Moving
		Coins.add(iv.getX() + (iv.getWidth() / 2));
		Coins.add(iv.getY() + (iv.getHeight()));

		this.getChildren().addAll(createControlAnchorsFor(Coins));
	}

	private ObservableList<Anchor> createControlAnchorsFor(final ObservableList<Double> points) {
		ObservableList<Anchor> anchors = FXCollections.observableArrayList();

		// Coin GaucheHaut
		DoubleProperty xProperty = new SimpleDoubleProperty(points.get(0));
		DoubleProperty yProperty = new SimpleDoubleProperty(points.get(1));

		xProperty.addListener((obs, old, newVal) -> {
			System.out.println(old + " et " + newVal);
			this.iv.setX((double) newVal);
			this.iv.setWidth((double) this.iv.getWidth() - ((double) newVal - (double) old));
			anchors.get(2).setCenterX((double) newVal + this.iv.getWidth() / 2);
		});

		yProperty.addListener((obs, old, newVal) -> {
			this.iv.setY((double) newVal);
			this.iv.setHeight((double) this.iv.getHeight() - ((double) newVal - (double) old));
		});
		anchors.add(new Anchor(Color.GOLD, xProperty, yProperty));

		// Coin DroiteBas
		DoubleProperty xProperty2 = new SimpleDoubleProperty(points.get(2));
		DoubleProperty yProperty2 = new SimpleDoubleProperty(points.get(3));

		xProperty2.addListener((obs, old, newVal) -> {
			this.iv.setWidth((double) this.iv.getWidth() - ((double) old - (double) newVal));
			anchors.get(2).setCenterX((double) newVal - this.iv.getWidth() / 2);
		});

		yProperty2.addListener((obs, old, newVal) -> {
				this.iv.setHeight((double) this.iv.getHeight() - ((double) old - (double) newVal));
				anchors.get(2).setCenterY((double) newVal);
		});
		anchors.add(new Anchor(Color.GOLD, xProperty2, yProperty2));

		// Moving
		DoubleProperty xPropertyM = new SimpleDoubleProperty(points.get(4));
		DoubleProperty yPropertyM = new SimpleDoubleProperty(points.get(5));
		xPropertyM.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
				iv.setX((double) x - iv.getWidth() / 2);
				// anchors.get(0).setCenterX((double) x-
				// rectangle.getWidth()/2);
				// anchors.get(0).setVisible(false);
			}
		});

		yPropertyM.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
				iv.setY((double) y - iv.getHeight());
				Coins.set(1, (double) y);
			}
		});
		anchors.add(new Anchor(Color.GOLD, xPropertyM, yPropertyM));

		return anchors;
	}

	// a draggable anchor displayed around a point.
	class Anchor extends Circle {
		private final DoubleProperty x, y;

		Anchor(Color color, DoubleProperty x, DoubleProperty y) {
			super(x.get(), y.get(), 20);
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			this.x = x;
			this.y = y;

			x.bind(centerXProperty());
			y.bind(centerYProperty());
			enableDrag();
		}

		// make a node movable by dragging it around with the mouse.
		private void enableDrag() {
			final Delta dragDelta = new Delta();
			setOnMousePressed(e -> {
				dragDelta.x = getCenterX() - e.getX();
				dragDelta.y = getCenterY() - e.getY();
				getScene().setCursor(Cursor.MOVE);
			});
			setOnMouseReleased(e -> {
				getScene().setCursor(Cursor.HAND);
			});
			setOnMouseDragged(e -> {
				double newX = e.getX() + dragDelta.x;
				if (newX > 0 && newX < getScene().getWidth()) {
					setCenterX(newX);
				}
				double newY = e.getY() + dragDelta.y;
				if (newY > 0 && newY < getScene().getHeight()) {
					setCenterY(newY);
				}

				// Recompute screen;
				pane.getChildren().add(iv);
			});
			setOnMouseEntered(e -> {
				if (!e.isPrimaryButtonDown()) {
					getScene().setCursor(Cursor.HAND);
				}
			});
			setOnMouseExited(e -> {
				if (!e.isPrimaryButtonDown()) {
					getScene().setCursor(Cursor.DEFAULT);
				}
			});
		}

		// records relative x and y co-ordinates.
		private class Delta {
			double x, y;
		}

	}

}
