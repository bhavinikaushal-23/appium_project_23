package Screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginScreen extends  ScreenBase 
{
@AndroidFindBy(id="flipboard.app:id/first_launch_cover_continue")
	public WebElement continueButton;



	public LoginScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Method chaining Method chaining is when methods return the current object (or
	 * another Page Object), so you can call multiple methods in a single line:
	 * 
	 * loginPage.enterUsername("user") .enterPassword("pass") .clickLogin();
	 * 
	 */	public chooseTopicScreen clickGetStartedbutton() {
		continueButton.click();
		return new chooseTopicScreen(driver);
		
	}
	
	

}
