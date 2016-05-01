package tests;

import org.junit.Test;

import stats.database.VoogaDataBase;

public class LoginTest {

	@Test
	public void test() {
		VoogaDataBase database = VoogaDataBase.getInstance();
		System.out.println("logged in: " + database.verifyLoginInfo("hp67", "hello"));
	}

}
