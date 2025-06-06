package TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Screens.LoginScreen;
import Screens.chooseTopicScreen;
import base.TestBase;
import utilities.AppiumServer;

public class FlipboardLoginTest extends TestBase{

	LoginScreen login;
	chooseTopicScreen chooseTopicScreen;

	@BeforeTest
	public void init() {
		setUp();
		System.out.println(" HELLO INIT for FlipboardLoginTest , SETUP METHOD- BEFORE TEST ");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login = new LoginScreen(driver);
		chooseTopicScreen = new chooseTopicScreen(driver);
		takeScreenShots();
	}

	@Test(priority=1)
	public void validateGetStartedButton() {
		login.clickGetStartedbutton();
		takeScreenShots();
	}
	
	@Test(priority=2)
	public void chooseTopicTest() throws Exception {
		chooseTopicScreen.choseTopics(3).clickContinue();
		takeScreenShots();

	}

	@AfterTest
	public void quitDriver() throws Exception {
	System.out.println(" calling QUIT from TestBase");
		quit();
	}
}
