package tools;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.interfaces.VoogaData;

public class VoogaDataImage implements VoogaData{
    
    String imagePath;
    ImageView image;
    
    public VoogaDataImage(String imagePath){
        this.imagePath= imagePath;
    }
    
    @Override
    public Object getValue () {
        return imagePath;
    }

    @Override
    public void setValue (Object o) {
        if (!(o instanceof String)) return;
        imagePath = (String) imagePath;
    }

    @Override
    public Node display () {
        Button button = new Button();
        button.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream(imagePath))));
        return button;
    }

	@Override
	public Property<Object> getProperty() {
		// TODO Auto-generated method stub
		return null;
	}

}
