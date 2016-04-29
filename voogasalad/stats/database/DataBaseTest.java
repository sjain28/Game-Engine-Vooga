package stats.database;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class DataBaseTest {
	@Test
	public void testAddingUsers() {
		VoogaDataBase database = VoogaDataBase.getInstance();

		//add games and users
		database.addGame("game 1", "fun game for friends to play");
		database.addGame("game 2", "cool game with sharks");
		database.addGame("game 3", "scary game with dinosaurs");
		database.addUser("Krista", "klo14", "password1", "mario.png");
		database.addUser("Jacob", "jb8902", "thisshouldbesecret", "mario.png");
		System.out.println(database.verifyLoginInfo("klo14", "password1"));
		database.printDataBase();
		
		//get basic user and game information
		System.out.println(database.getGame("game 1").getProperty(VoogaGame.GAME_NAME).toString());
		System.out.println(database.getUser("klo14").getProperty(VoogaUser.DISPLAY_NAME).toString());
		System.out.println(database.getStatsbyUser("klo14"));
		System.out.println(database.getStatsbyGame("game 1"));
		
		//get stats from the play session
		List<CellEntry> stats = database.getStatsbyUser("klo14");
		for(CellEntry info : stats){
			StatCell statinfo = (StatCell) info;
			System.out.println(info.getProperty(StatCell.MY_GAME)+" : "+statinfo.getPlayStats());
		}
		
		database.save();
	}
	@Test
	public void testLoading(){
		VoogaDataBase database = VoogaDataBase.getInstance();
		database.printDataBase();
	}
	@Test
	public void testSingleUserSignIn(){
		VoogaDataBase database = VoogaDataBase.getInstance();
		database.addUser("Harry Potter", "hp67", "hello", null);
		//database.clear();
		database.save();
		System.out.println("logged in: " + database.verifyLoginInfo("hp67", "hello"));
	}
	@Test
	public void addPlaySession(){
		VoogaDataBase database = VoogaDataBase.getInstance();
		//database.clear();
		
		PlaySession playsesh5 =new PlaySession(new Date());
		System.out.println("here");
		System.out.println(((StatCell) database.getStatByGameAndUser("game 2", "klo14")).getPlayStats());
		
		((StatCell) database.getStatByGameAndUser("game 2", "klo14")).addPlaySession(playsesh5);
		database.save();
	}
}
