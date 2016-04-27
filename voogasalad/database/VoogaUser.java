package database;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.VoogaString;

public class VoogaUser extends VoogaEntry{
	private String myPassword;
	public static final String DISPLAY_NAME = "DisplayName";
	public static final String USER_NAME = "UserName";
	public static final String PROF_PIC_LOC = "ProfPicLoc";

	
	public VoogaUser(String displayname,String username, String password, String profPicLocation){
		super();
		myPassword = password;
		setProperty(DISPLAY_NAME, new VoogaString(displayname));
		setProperty(USER_NAME, new VoogaString(username));
		setProperty(PROF_PIC_LOC, new VoogaString(profPicLocation));
	}
	public ImageView displayProfilePicture(){
		return new ImageView(new Image(getProperty(PROF_PIC_LOC).getValue().toString()));
	}
	public boolean verifyPassword(String password){
		if(password.equals(myPassword)){return true;}
		return false;
	}
	public String toString(){
		return getProperty(USER_NAME).getValue().toString();
	}
}
