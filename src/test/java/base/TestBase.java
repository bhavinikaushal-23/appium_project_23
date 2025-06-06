package base;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;
import utilities.AppiumServer;
import utilities.CommonUtils;

public class TestBase {


	public AppiumDriver driver;

	public static String loadPropertyFile= "Android_flipboard.properties"; 
	public static Logger log= LogManager.getLogger(TestBase.class);

	
	
	public void setUp() {
		if(driver ==null) {

			System.out.println(" hello  setUp ");
			PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\Log4j.properties");

			if(loadPropertyFile.startsWith("Android_")) {
				System.out.println(" hello  if calling prop file--->(loadPropertyFile.startsWith(\"Android_\" ");
AppiumServer.StartAppiumServer();


				log.info("appium server STARTED");
				System.out.println(" hello  appium server STARTED ");

CommonUtils.load_AndroidConfProperties(loadPropertyFile);		
				log.info(loadPropertyFile + " property file loaded !!!");

				CommonUtils.Set_AndroidCapabilities();
				try {
					driver= CommonUtils.getAndroidDriver();
					System.out.println("  driver in test base ==="+driver );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException("CommonUtils.getAndroidDriver = "+e.getMessage()) ;
				}


			}
			else if(loadPropertyFile.startsWith("IOS_")) {
			}}
		System.out.println(" THIS IS END OF SETUP METHOD IN TESTBSE CLASS");
		takeScreenShots();
	}


	public void quit() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
		log.info("test case execution completed ✅✅✅ ");

		AppiumServer.stopAppiumServer();
		log.info("appium server stopped");
		takeScreenShots();
		
		
	}
	
	
	
	public  void takeScreenShots() {
		
		Date date = new Date();
String fileName= date.toString().replace(":", "_").replace(" ", "_")+".jpg" ;
			

File scrFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	
			
			try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\test\\resources\\SCREENSHOTS_1\\"+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	}
}
