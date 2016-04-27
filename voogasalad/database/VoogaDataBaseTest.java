package database;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class VoogaDataBaseTest {
	@Test
	public void testAddingUsers() {
		VoogaDataBase database = VoogaDataBase.getInstance();
		
		//add games and users
		database.addGame("game 1", "fun game for friends to play");
		database.addGame("game 2", "cool game with sharks");
		database.addGame("game 3", "scary game with dinosaurs");
		database.addUser("Krista", "klo14", "password1", "mario.png");
		database.addUser("Jacob", "jb8902", "thisshouldbesecret", "mario.png");
		System.out.println(database.getUser("klo14").verifyLoginInfo("klo14", "password1"));
		database.printDataBase();
		
		//get basic user and game information
		System.out.println(database.getGame("game 1").getProperty(VoogaGame.GAME_NAME).toString());
		System.out.println(database.getUser("klo14").getProperty(VoogaUser.DISPLAY_NAME).toString());
		System.out.println(database.getStatsbyUser("klo14"));
		System.out.println(database.getStatsbyGame("game 1"));
		
		//add in two play sessions
		VoogaPlaySession playsesh1 = new VoogaPlaySession(new Date(), 153, 4000, 3);
		database.getStatByGameAndUser("game 2", "klo14").addPlaySession(playsesh1);
		VoogaPlaySession playsesh2 = new VoogaPlaySession(new Date(), 324, 2000, 2);
		database.getStatByGameAndUser("game 2", "klo14").addPlaySession(playsesh2);

		//get stats from the play session
		List<VoogaStatInfo> stats = database.getStatsbyUser("klo14");
		for(VoogaStatInfo info : stats){
			
			System.out.println(info.getProperty(VoogaStatInfo.MY_GAME)+" : "+info.getPlayStats());
		}
		
		database.save();
	}
	@Test
	public void testLoading(){
		VoogaDataBase database = VoogaDataBase.getInstance();
		database.printDataBase();
	}
}
