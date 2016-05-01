package authoring.splash;

import authoring.CustomText;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;

public class GameRowDisplay extends HBox {
	
    private static final double IMAGE_SIZE = 50;
    private static final double WIDTH = 300;
    private static final double NUM_OF_IMAGES = 5;
    private static final double SPACING = 10;

    private String name;
    private int index;

    /**
     * builds out an HBox to display the games, given the parameters of where the games are stored
     * 
     * @param name
     * @param index
     */
    public GameRowDisplay (String name, int index) {
        this.setPrefWidth(WIDTH);
        this.setSpacing(SPACING);
        this.name = name;
        this.index = index;
        setImage();
        setText();
    }

    private void setImage () {
        int imageIndex = (int) (index % NUM_OF_IMAGES);
        Image image =
                new Image(this.getClass().getResourceAsStream("/gamelogo" + imageIndex + ".png"));
        ImageView iv = new ImageView(image);
        iv.setFitWidth(IMAGE_SIZE);
        iv.setFitHeight(IMAGE_SIZE);
        this.getChildren().add(iv);
    }

    private void setText () {
        this.getChildren().add(new CustomText(name, FontWeight.BOLD));
    }

}
