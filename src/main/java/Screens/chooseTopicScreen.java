package Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class chooseTopicScreen  extends  ScreenBase {

	public chooseTopicScreen(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "flipboard.app:id/topic_picker_topic_row_topic_tag")
	public List<WebElement>  topic ;
	
	@AndroidFindBy(id="flipboard.app:id/icon_button_text")
	public WebElement skipLogin;
	
	@AndroidFindBy(id = "flipboard.app:id/topic_picker_continue_button")
	public WebElement pickerContinueButton;
	
	// How to Implement Method Chaining in POM
// 1. Return this for methods in the same page
	public chooseTopicScreen choseTopics(int topicsCount) throws Exception {
		
		for(int i=0;i<topicsCount;i++) {
			Thread.sleep(3000);
			topic.get(i).click();
			System.out.println("topics clciked = "+ topic.get(i));
		}
	//	Return this for methods in the same page
		return this;
	}
	
	public chooseTopicScreen clickContinue() {
		pickerContinueButton.click();
		System.out.println("chooseTopicScreen clickContinue !!!!");
		return this;
	}
	
	public HomeScreen skipLogin() {
		skipLogin.click();
		System.out.println(" skin login button clicked ");
		return new HomeScreen(driver);
	}

	

	
	
}
