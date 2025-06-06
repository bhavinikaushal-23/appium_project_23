package Extent_Report;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_Code {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;


	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentSparkReporter("C:\\Users\\kaushal\\Desktop\\ECLIPSE_OCTOBER2024\\ECLIPSE_OCTOBER2024_KAUSHAL-WS\\APPIUM_FRAMEWORK_BHAVINI_18MAY25\\reports\\extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("AUTOMATION REPORTS KAUSHAL");
		htmlReporter.config().setReportName("REPORTS TEST AUTOMATION 123");
		htmlReporter.config().setTheme(Theme.DARK);


		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("automation engg", "bhavini kaushal");
		extent.setSystemInfo("company","google");
		extent.setSystemInfo("build no","yo-123");

	}

	@AfterTest
	public void EndReport() {
		extent.flush();

	}
	
	
	@Test
	public void doHELLO() {
		test = extent.createTest("doHELLO test");
		System.out.println(" executing doHELLO test");

	}
	
	@Test
	public void doLogin() {
		test = extent.createTest("Login test");
		System.out.println(" executing login test");

	}
	@Test
	public void doLogin1() {
		test = extent.createTest("Login test 123");
		System.out.println(" executing login test");
		if(6<2) {
			System.out.println(true);
		}
		else {
			System.out.println(0);
		}

	}
@AfterMethod
	public void tearDown(ITestResult result) {
	 if(result.getStatus()==  ITestResult.FAILURE ) {
		 String methodName=	 result.getMethod().getMethodName() ;
			String logText = "<b>"+"TEST CASE: "+ methodName.toUpperCase()+ " - FAILURE"+ "</b>";
			Markup m = MarkupHelper.createLabel(logText , ExtentColor.RED);
			test.fail(m)	;
			
		 
	 }else  if(result.getStatus()==  ITestResult.SKIP ) {
		 
	 }
	 else  if(result.getStatus()==  ITestResult.SUCCESS) {
	String methodName=	 result.getMethod().getMethodName() ;
	String logText = "<b>"+"TEST CASE: "+ methodName.toUpperCase()+ " - PASSED"+ "</b>";
	
	
	Markup m = MarkupHelper.createLabel(logText , ExtentColor.GREEN);
	test.pass(m);
	 }
		
	}

}


