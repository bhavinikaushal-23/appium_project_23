package utilities;

import java.io.File;
import java.net.URL;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

	public static AppiumDriverLocalService service ;
	
	
	public static AppiumDriverLocalService StartAppiumServer() {
		
		String logFilePath =  "src/test/resources/logs/logsFromAppium.txt";
		File logFile = new File(logFilePath);
		System.out.println(" log file == "+logFile);
		
		try {
			if(logFile.exists()) {
				logFile.delete();
				System.out.println("old logsFile deleted ");
			}
			logFile.getParentFile().mkdirs();
			logFile.createNewFile();
			System.out.println(" new file created ?? " );
		}
		catch(Exception e) {
			throw new RuntimeException ("FAILED TO CREATE APPIUM LOG FILE////DUHHHH -->"+e.getStackTrace() + e.getMessage());
		}
		
		
		service = new AppiumServiceBuilder()
	            .withAppiumJS(new File("C:\\Users\\kaushal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	            .usingPort(4723)
	            .withLogFile(logFile).
	            withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
	            .withArgument(() -> "--base-path", "/")
	            .build();
		
		
		
		System.out.println("Appium JS Path in StartAppiumServer: " + new File("...").exists());
		System.out.println("service value in appium start function  == ? "+service);
		service.start();
		System.out.println("!!! appium server started !!!!");
		
		URL appiumUrl = service.getUrl();
		
		System.out.println(" appiumUrl ===>> "+appiumUrl);
		if(service.isRunning()) {
			System.out.println("✅✅✅ Appium server started at: " + appiumUrl);
		}
		else {
			System.err.println("❌❌❌ Failed to start Appium server. ❌❌❌❌");
		}
	return service;
	}
	
	
	
//	public static void StartAppiumServer()


	//public static void main(String [] args) 
	public static void StartAppiumServer____old()	{
		System.out.println("enytering into StartAppiumServer function");
		service = new 	AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\kaushal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).
				usingAnyFreePort().
				withArgument(GeneralServerFlag.LOCAL_TIMEZONE).
				withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\logs\\logsFromAppium.txt")).
				build();
		
		System.out.println("Appium JS Path in StartAppiumServer: " + new File("...").exists());
		System.out.println("service == ? "+service);
		service.start();
		System.out.println("!!! appium server started !!!!");
		
		
		
		if(service.isRunning()) {
			System.out.println("✅✅✅ Appium server started at: " + service.getUrl());
		}
		else {
			System.err.println("❌❌❌ Failed to start Appium server. ❌❌❌❌");
		}
	}


	public static void stopAppiumServer() {
		if(service !=null && service.isRunning()) {
			service.stop();
			System.out.println("🛑 🛑 🛑  Appium server stopped.🛑 🛑 🛑 ");
		}
	}

}
