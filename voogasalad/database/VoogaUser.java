package database;

import tools.interfaces.VoogaData;

public class VoogaUser implements VoogaInfoCell{
	private String myPassword;
	public VoogaUser(String displayname, String username, String password){
		//TODO: add in the displayname and username here
		myPassword = password;
	}
	@Override
	public VoogaData getProperty(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}
