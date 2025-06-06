package Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeScreen extends ScreenBase {

	public HomeScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	
	@AndroidFindBys({
		@AndroidBy(id="flipboard.app:id/sliding_title_strip_container"),
		@AndroidBy(id="flipboard.app:id/sliding_title_text_view"),
	})
	public List<WebElement> title ;
	
	
	
public void goToTitleSection(int sectionCount) {
	title.get(sectionCount-1).click(); // -1 as index starts from 0
	System.out.println(" Title chosen == "+title);
}

@AndroidFindBy(id="flipboard.app:id/bottom_nav_view_icon")
public  List<WebElement>  bottomNavigationButton;

public void goToBottomNavigationButtonSection(int bottomNavigationsectionCount) {
	title.get(bottomNavigationsectionCount-1).click(); // -1 as index starts from 0
System.out.println(" bottom Navigation section chosen == "+bottomNavigationsectionCount);
}

}
	