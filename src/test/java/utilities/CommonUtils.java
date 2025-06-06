package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class CommonUtils {

	
	public static AppiumServer service;
	public static AndroidDriver driver;
	public static UiAutomator2Options options;
	public static URL serverURL;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();

	private static String APPIUM_PORT;
	private static int IMPLICIT_WAIT_TIME;
	private static int EXPLICIT_WAIT_TIME;

	private static String BASE_PKG;
	private static String APP_ACTIVITY;
	private static String APP_PATH;
	private static String BROWSER_NAME;
	private static String PLATFORM_NAME;
	private static String PLATFORM_VERSION;
	private static String DEVICE_NAME;

	// FOR IOS SPECIFIC
	private static String UDID;
	private static String BUNDLE_ID;
	private static String APP;

	private static Properties prop = new Properties();
	private static FileInputStream fis;

	public static void load_AndroidConfProperties(String loadPropertyFile) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + loadPropertyFile);
			prop.load(fis);

			IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait", "10"));
			System.out.println("IMPLICIT_WAIT_TIME from property file = " + IMPLICIT_WAIT_TIME);

			EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait", "20"));
			System.out.println("EXPLICIT_WAIT_TIME from property file = " + EXPLICIT_WAIT_TIME);

			BASE_PKG = prop.getProperty("base.pkg", "");
			APP_ACTIVITY = prop.getProperty("application.activity", "");
			APP_PATH = prop.getProperty("application.path", "");
			System.out.println(" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX app path is === "+ APP_PATH);
			BROWSER_NAME = prop.getProperty("browser.name", "");
			PLATFORM_NAME = prop.getProperty("platform.name", "Android");
			             System.out.println("PLATFORM_NAME === " + PLATFORM_NAME);
			DEVICE_NAME = prop.getProperty("device.name", "emulator-5554");
			PLATFORM_VERSION = prop.getProperty("platform.version", "14");
			APPIUM_PORT = prop.getProperty("appium.server.port", "4723");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Set_AndroidCapabilities() {
		System.out.println("Setting mobile capabilities");
	//	reinstallAppIfRequired();
	//	System.out.println("CALLING reinstallAppIfRequired");

		options = new UiAutomator2Options()
				.setPlatformName(PLATFORM_NAME)
				.setPlatformVersion(PLATFORM_VERSION)
				.setDeviceName(DEVICE_NAME)
				.setAutomationName("UiAutomator2")
				/* .setApp(APP_PATH) */
				.setAppPackage(BASE_PKG)
				 .setAppActivity(APP_ACTIVITY) 
				.amend("uiautomator2ServerLaunchTimeout", 100000)
				.amend("adbExecTimeout", 100000)
				.autoGrantPermissions()
				.setNoReset(true);

		System.out.println("Capabilities set with UiAutomator2Options-- OK OK SET CAPABILITIES  ");
		
	}

	public static void load_IOSConfProperties(String loadPropertyFile) {
		// iOS config loader - to be implemented
	}

	public static void Set_IOSCapabilities(String loadPropertyFile) {
		// iOS capabilities setter - to be implemented
	}

	
	

	public static AppiumDriver getAndroidDriver() throws Exception {
		try {
			Thread.sleep(4000);
			serverURL = new URL("http://127.0.0.1:" + APPIUM_PORT + "/");
		//	serverURL = new URL("http://127.0.0.1:4723/");
			System.out.println("Appium server URL: in common utils ==>APPIUM_PORT  =====> " + serverURL);
		} catch (Exception e) {
			throw new RuntimeException("Invalid Appium Server URL: " + e.getMessage());
		}

		driver = new AndroidDriver(serverURL, options);
		System.out.println("Driver initialized: " + driver);
		Thread.sleep(4000);
		System.out.println("Connected to Appium server at: " + serverURL);
		return driver;
	}

	public static AppiumDriver getIOSDriver() {
		return driver;
	}
	
	// Validate Device Connection Before Starting the Session
	//Make sure adb detects a connected or running emulator.
	
	public static boolean isDeviceConnected() {
		try {
			Process process = Runtime.getRuntime().exec("adb devices");
			Scanner s = new Scanner(process.getInputStream()).useDelimiter("\\A");
			String output = s.hasNext() ? s.next(): "";
			return output.lines().anyMatch(line ->line.endsWith("device"));
			
 		
			}catch(Exception e) {
				System.out.println("failed to check connected devices : " +e.getMessage());
				return false;
				
			}
	}
	
	
	
	
	private static void reinstallAppIfRequired() {
        String appPackage = prop.getProperty("base.pkg");
        String apkPath = prop.getProperty("application.path");
        
        
        
        System.out.println("apkPath from properties = "+ apkPath +"  and pkg --  +appPackage");

        if (apkPath != null && !apkPath.equalsIgnoreCase("null") && !apkPath.trim().isEmpty()) {
            try {
                boolean isInstalled = driver.isAppInstalled(appPackage);
                System.out.println("App installed status: " + isInstalled);

                if (isInstalled) {
                    System.out.println("Uninstalling app: ==XXXX==>>> " + appPackage);
                    driver.removeApp(appPackage);
                    Thread.sleep(2000);
                   
                }

                File apkFile = new File(apkPath);
                if (apkFile.exists()) {
                    System.out.println("Installing app from: " + apkPath);
                    driver.installApp(apkFile.getAbsolutePath());
                    System.out.println("App reinstalled successfully.");
                } else {
                    System.err.println("APK file not found at: " + apkPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to reinstall the app.");
            }
        } else {
            System.out.println("No valid APK path provided in properties. Skipping reinstall.");
        }
    }


	public static String captureScreenshot(AppiumDriver driver, String screenshotName) {
        String destPath = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + ".png";
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(destPath));
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
        return destPath;
    }
	
}
