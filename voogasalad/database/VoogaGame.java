package database;


public class VoogaGame extends VoogaCellUser{


	private String myDescription;

	public VoogaGame(String resourceString, String description){
		super(resourceString);
		myDescription = description;
	}
	
	public String getDescription(){
		return myDescription;
	}



}
