package Base;

import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ScreenBase {

//	public static AndroidDriver  driver;
	public static AppiumDriver  driver;
	public WebDriverWait wait;
	
	
	public ScreenBase( AppiumDriver driver) {
		this.driver = driver;
	}
	
	public void hideKeyboard() {
		
		((AndroidDriver) driver).hideKeyboard();
	//	driver.hideKeyboard();
	//	((IOSDriver) driver).hideKeyboard();
		
		
		}
	
	public void  Enter() {
		((AndroidDriver)	driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	}
}
