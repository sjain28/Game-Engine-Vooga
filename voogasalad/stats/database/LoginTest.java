package stats.database;

import org.junit.Test;

public class LoginTest {

	@Test
	public void test() {
		VoogaDataBase database = VoogaDataBase.getInstance();
		System.out.println("logged in: " + database.verifyLoginInfo("hp67", "hello"));
	}

}