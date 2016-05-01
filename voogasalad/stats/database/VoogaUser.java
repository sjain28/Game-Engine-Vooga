package stats.database;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.VoogaString;

/**
 * Data base contains a list of users. In each game, all the personal information, such as the profile picture and name
 * of the user, are saved in the user.
 * 
 * @author Joshua Xu
 *
 */

public class VoogaUser extends CellEntry{
	private String myPassword;
	public static final String DISPLAY_NAME = "display_name";
	public static final String USER_NAME = "user_name";
	public static final String PROF_PIC_LOC = "prof_pic_loc";
	
	
	public VoogaUser(String displayname,String username, String password, String profPicLocation){
		super();
		myPassword = password;
		setProperty(DISPLAY_NAME, new VoogaString(displayname));
		setProperty(USER_NAME, new VoogaString(username));
		setProperty(PROF_PIC_LOC, new VoogaString(profPicLocation));
	}
	
	/**
	 * Returns the User's profile picture. Typically used when displaying the player profile.
	 * @param 
	 * @return ImageView 
	 */
	
	public ImageView displayProfilePicture(){
		return new ImageView(new Image("file:" + getProperty(PROF_PIC_LOC).getValue().toString()));
	}
	/**
	 * Returns whether the password the user typed was correct. 
	 * This is used whenever verifying whether the user should be logged in.
	 * @param passwordAttempt
	 * @return Boolean 
	 */
	
	public boolean verifyPassword(String passwordAttempt){
		if(passwordAttempt.equals(myPassword)){return true;}
		return false;
	}
	public String toString(){
		return getProperty(USER_NAME).getValue().toString();
	}
}
