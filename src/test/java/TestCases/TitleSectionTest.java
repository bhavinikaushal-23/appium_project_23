package TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Screens.HomeScreen;
import Screens.LoginScreen;
import base.TestBase;

public class TitleSectionTest extends TestBase{
	LoginScreen login;
	HomeScreen HomeScreen;


	@BeforeTest
	public void init() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUp();
		System.out.println(" HELLO INIT for TitleSectionTest , SETUP METHOD- BEFORE TEST ");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		login = new LoginScreen(driver);
		HomeScreen = new HomeScreen(driver);
	}
	@Test	
	public void validateTitle() throws Exception {
		login.clickGetStartedbutton().choseTopics(3).clickContinue().skipLogin();
		HomeScreen.goToTitleSection(2);
	}

	@AfterTest
	public void quitDriver() throws Exception {

System.out.println(" calling QUIT from TestBase");
quit();
	}


}
