package com.aws.bluage;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class WebDriverBaseTestNG {

	ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();
	WebDriver driver;

	ScreenshotTake st = new ScreenshotTake();
	protected ExtentTest test;
	ExtentSparkReporter spark;
	protected ExtentReports extent;

	@Parameters({ "testname" })
	@BeforeClass
	public void reportSetup(String testName) throws IOException {
		extent = new ExtentReports();
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File theDir = new File(System.getProperty("user.dir") + "//reports//" + testName);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
		// String destination = dirAddress+"\\"+testCaseID+"-"+dateName+".png";
		String dirAddress = theDir.getAbsolutePath();
		spark = new ExtentSparkReporter(dirAddress + "\\" + testName + "-" + dateName + ".html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("AMOS Test Report");
		spark.loadXMLConfig(System.getProperty("user.dir") + "/resources/extent-report-config.xml");
		extent.attachReporter(spark);
	}

	@BeforeMethod
	public void createDriver(Method method) throws IOException, InterruptedException, AWTException {

		Test testName = method.getAnnotation(Test.class);
		ConfigReader reader = new ConfigReader();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(reader.getUrl());
		Thread.sleep(3000);
		setDriver(driver);
	}

	public void setDriver(WebDriver driver) {
		this.threadLocal.set(driver);
	}

	public WebDriver getDriver() {
		return this.threadLocal.get();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}

		if (getDriver() != null)
			driver.quit();
		System.out.println("Test Ended - Browser Closed");

	}

	@AfterClass
	public void generateReport() {
		extent.flush();
	}

}
