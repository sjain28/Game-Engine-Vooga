package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.items.NumberTextField;
import authoring.resourceutility.ButtonMaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import resources.VoogaBundles;
import tools.GUIUtils;

/**
 * GUI object to help change text within the design board.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */


public class TextPropertyModifier extends Stage {
    private Scene scene;
    private VBox root;
    private Text node;

    private ComboBox<String> fonts;
    private NumberTextField fontSize;
    private ColorPicker color;
    private ComboBox<String> style;

    private static final double RGB_MAX = 255;
    private static final double VBOX_LENGTH = 20;
    
    public TextPropertyModifier (Text n) {
        node = n;
        root = new VBox(VBOX_LENGTH);
        initialize();
        scene = new VoogaScene(root);

        this.setScene(scene);
    }

    private void initialize () {
    	fonts = new ComboBox<>();
        fonts.getItems().addAll(Font.getFamilies());
        root.getChildren().add(GUIUtils.makeRow(new CustomText("Font"), fonts));

        fontSize = new NumberTextField();
        root.getChildren().add(GUIUtils.makeRow(new CustomText("Font Size"), fontSize));

        color = new ColorPicker();
        root.getChildren().add(GUIUtils.makeRow(new CustomText("Color"), color));

        style = new ComboBox<>();
        style.getItems().addAll(VoogaBundles.textStyles.keySet());
        root.getChildren().add(GUIUtils.makeRow(new CustomText("Style"), style));

        ButtonMaker maker = new ButtonMaker();
        Button apply = maker.makeButton("Apply", e -> {
            establishStyle();
            this.close();
        });
        Button cancel = maker.makeButton("Cancel", e -> this.close());
        root.getChildren().add(GUIUtils.makeRow(apply, cancel));
    }

    private void establishStyle () {
        String hex = String.format( "#%02X%02X%02X",
                                    (int)(color.getValue().getRed() * RGB_MAX),
                                    (int)( color.getValue().getGreen() * RGB_MAX),
                                    (int)( color.getValue().getBlue() * RGB_MAX) );
        String result =
                "-fx-font-family: " + fonts.getValue() + "; " +
                        "-fx-font-size: " + fontSize.getText() + "; " +
                        "-fx-fill: " + hex+ "; " +
                        VoogaBundles.textStyles.getString(style.getValue());

        node.styleProperty().set(result);

    }

}
