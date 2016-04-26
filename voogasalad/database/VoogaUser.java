package database;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.interfaces.VoogaData;

public class VoogaUser extends VoogaCellUser{

	String myUsername;
	String myPassword;
	String myProfPicLocation;
	
	public VoogaUser(String resourceString, String username, String password, String profPicLocation){
		super(resourceString);
		myUsername = username;
		myPassword = password;
		myProfPicLocation = profPicLocation;
	}
	
	public ImageView displayProfilePicture(){
		return new ImageView(new Image(myProfPicLocation));
	}

}
