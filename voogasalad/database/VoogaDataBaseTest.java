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
		database.addUser("Krista", "klo14", "password1");
		database.addUser("Jacob", "jb8902", "thisshouldbesecret");
		//database.getUser("klo14");
		database.printDataBase();
	}

}
