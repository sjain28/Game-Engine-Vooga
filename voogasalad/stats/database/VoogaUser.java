package stats.database;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.VoogaString;

public class VoogaUser extends CellEntry{
	private String myPassword;
	public static final String DISPLAY_NAME = "display_name";
	public static final String USER_NAME = "user_name";
	public static final String PROF_PIC_LOC = "prof_pic_loc";
	/**
	 * Vooga User Constructor
	 * @param displayname
	 * @param username
	 * @param password
	 * @param profPicLocation
	 */
	public VoogaUser(String displayname,String username, String password, String profPicLocation){
		super();
		myPassword = password;
		setProperty(DISPLAY_NAME, new VoogaString(displayname));
		setProperty(USER_NAME, new VoogaString(username));
		setProperty(PROF_PIC_LOC, new VoogaString(profPicLocation));
	}
	/**
	 * Returns the display prof pic
	 * @return
	 */
	public ImageView displayProfilePicture(){
		return new ImageView(new Image("file:" + getProperty(PROF_PIC_LOC).getValue().toString()));
	}
	/**
	 * Verifies a password
	 * @param password
	 * @return
	 */
	public boolean verifyPassword(String password){
		if(password.equals(myPassword)){return true;}
		return false;
	}
}
