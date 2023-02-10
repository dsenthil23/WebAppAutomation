package com.aws.bluage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class ScreenshotTake {
	
		WebDriver driver;
		

/*
		public static String takeSnapShot(WebDriver driver,String testCaseID) throws IOException{	

		File theDir = new File(System.getProperty("user.dir")+"//reports//"+testCaseID);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		String dirAddress = theDir.getAbsolutePath();
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());	
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);	
		String destination = dirAddress+"\\"+testCaseID+"-"+dateName+".png";
		//Move image file to new destination
		File DestFile=new File(destination);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		String screenshotPath = DestFile.getAbsolutePath();
		return screenshotPath;
		}
	*/
	public static String takeSnapShot(WebDriver driver,String testCaseID) throws IOException{	

		File theDir = new File(System.getProperty("user.dir")+"//reports//"+testCaseID);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		String dirAddress = theDir.getAbsolutePath();
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());	
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);	
		String destination = dirAddress+"\\"+testCaseID+"-"+dateName+".png";
		//Move image file to new destination
		File DestFile=new File(destination);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		String screenshotPath = DestFile.getAbsolutePath();
		
		File screenshotFile = new File(screenshotPath);
		FileInputStream fis = new FileInputStream(screenshotFile);
		byte[] bytes = new byte[(int)screenshotFile.length()];
		fis.read(bytes);
		//String base64path = new String(Base64.toBase64String(bytes)StandardCharsets.UTF_8);
		String base64img = new String(Base64.encode(bytes));
		fis.close();
		return base64img;
		}
	

	public static String reportScreenshot(WebDriver driver, String testCaseID) throws IOException
	{
		File theDir = new File(System.getProperty("user.dir")+"//reports//"+testCaseID);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		String dirAddress = theDir.getAbsolutePath();
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());	
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);	
		String destination = dirAddress+"\\"+testCaseID+dateName+".png";
		//Move image file to new destination
		File DestFile=new File(destination);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		String screenshotPath = DestFile.getAbsolutePath();
		return screenshotPath;
		}
}
