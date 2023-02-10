package com.aws.bluage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aws.bluagePageClass.AdditionalSelections;
import com.aws.bluagePageClass.ControlDataPage;
import com.aws.bluagePageClass.DisplayQCErrorDetails;
import com.aws.bluagePageClass.DisplayUpdateQCErrorIF011R06;
import com.aws.bluagePageClass.FunctionKeysPress;
import com.aws.bluagePageClass.GlobalMax;
import com.aws.bluagePageClass.GlobalMaxMainMenu;
import com.aws.bluagePageClass.MasterFileMaintenance;
import com.aws.bluagePageClass.SegmentMisMatchDataPage;
import com.aws.bluagePageClass.TransactionRunner;
import com.aws.bluagePageClass.UpdateQCError;

public class TestNG046_VerifyQCErrorHOTELSegmentdoesNotMatch extends WebDriverBaseTestNG {

	WebDriver driver;
	String gscript, region, MenuSelection, MasterFileSelection, testCaseID = "AMOS-501";
	String countryCode, pnrlocator, selectionID1, selectionID2, subCode, agentData;

	@DataProvider(name = "HOTELSegmentdoesNotMatch")
	public Object[][] excelDataProvider() throws IOException {
		ExcelReader excelReader = new ExcelReader();
		Object[][] arrObj = excelReader.getExcelData("TestData\\qcTestData.xlsx", "level1HOTELSegmentNotMatch");
		return arrObj;
	}

	@Test(testName="AMOS-501", dataProvider = "HOTELSegmentdoesNotMatch")
	public void verifyQCErrorHOTELSegmentdoesNotMatch(String pnrlocator,String selectionID1,String selectionID2,
			String subCode, String selectionValue) throws IOException, InterruptedException
	{
		region = "1";
		MenuSelection = "1";
		MasterFileSelection = "3";
		gscript = "MU030CCall";
		//loading the webdriver
		driver = getDriver();
	
		//creating objects for pageClass
		TransactionRunner tr = new TransactionRunner(driver);
		FunctionKeysPress fk = new FunctionKeysPress(driver);
		AdditionalSelections asl = new AdditionalSelections(driver);
		DisplayUpdateQCErrorIF011R06 dcr = new DisplayUpdateQCErrorIF011R06(driver);
		DisplayQCErrorDetails dqc = new DisplayQCErrorDetails(driver);
		UpdateQCError uqc = new UpdateQCError(driver);
		SegmentMisMatchDataPage smd = new SegmentMisMatchDataPage(driver);
		ScreenshotTake st = new ScreenshotTake();	
		GlobalMax gm = new GlobalMax(driver);
		GlobalMaxMainMenu gmm = new GlobalMaxMainMenu(driver);
		MasterFileMaintenance mfm = new MasterFileMaintenance(driver);
		ScreenshotTake sc = new ScreenshotTake();
		
		test = extent.createTest(testCaseID);
		
		tr.screenSelection(gscript);
		test.log(Status.PASS, "Region Selection Screen",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		gm.fillRegion(region);
		test.log(Status.PASS, "Region " + region + " has been selected",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.enterKey();
		test.log(Status.PASS, "Menu Selection Screen",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		gmm.fillMenu(MenuSelection);
		test.log(Status.PASS, "Selected " + MenuSelection + " as Menu",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.enterKey();
		test.log(Status.PASS, "Master File Maintenance Screen",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		mfm.fillMasterFile(MasterFileSelection);
		test.log(Status.PASS, "Selected " + MasterFileSelection + " value",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.enterKey();
		test.log(Status.PASS, "QUALITY CONTROL PROCESS",
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.f9Press();
		test.log(Status.PASS, "Pressing F9 key", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		asl.fillPnr(pnrlocator);
		test.log(Status.PASS, "Selecting the "+ pnrlocator+" ", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.enterKey();
		dcr.verifyQuestatusBeforeUpdate();
		test.log(Status.PASS, "Verifying the Queue Status before Update", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());	
		dcr.selectionField(selectionID1);
		test.log(Status.PASS, "Selection Field Entry " + selectionID1 + " to view M.I.R Query Display ", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.f2Press();
		dcr.selectionField(selectionID2);
		test.log(Status.PASS, "Update Quality Control errors Screen ", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		uqc.subField(subCode);
		test.log(Status.PASS, "Filled the Sub code "+ subCode, 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		smd.fillSelectionfield(selectionValue);
		test.log(Status.PASS, "Filled the Selection field "+ selectionValue, 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		fk.f12Press();
		test.log(Status.PASS, "Pressing F12 Key", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());
		dcr.verifyQuestatusAfterUpdate();
		test.log(Status.PASS, "Verifying the Queue Status After Update", 
				MediaEntityBuilder.createScreenCaptureFromBase64String(sc.takeSnapShot(driver, testCaseID)).build());		
		
		
	}
}
