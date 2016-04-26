package database;

import static org.junit.Assert.*;

import org.junit.Test;

public class VoogaDataBaseTest {

	@Test
	public void test() {
		VoogaDataBase database = new VoogaDataBase();
		database.addGame("game 1");
		database.addGame("game 2");
		database.addGame("game 3");
		database.addUser("Krista", "klo14", "password1", "mario.png");
		database.addUser("Jacob", "jb8902", "thisshouldbesecret", "mario.png");
		//database.getUser("klo14");
		database.printDataBase();
	}

}
