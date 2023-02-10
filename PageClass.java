package com.aws.bluagePageClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aws.bluage.Helper;

import junit.framework.Assert;

public class AirSegmentSelection {
WebDriver driver;
	
	public Helper help;
	
	public WebElement wait; 
	public int PageNumber;
	public List<WebElement> list;
	public boolean disapear;
//	public List<String> ngModelDigitShouldMatch;
	
	private int sleepDuringRender = 1000;
	
	
	By statusCheck = By.id("qtkts_0");
	
	@FindBy(id="qsel_0") WebElement selectionField;
	@FindBy(xpath = "//*[@style='text-align: right; width: 27px;']") WebElement recordList;
	@FindBy(xpath="//*[starts-with(@id,'qsel')]") List<WebElement> selectionFieldElement;
	
	public AirSegmentSelection(WebDriver driver)
	{
		this.driver = driver;
		help = new Helper(driver);	
		PageFactory.initElements(driver, this);
	}
	
	public int getRecordList()
	{
		List<WebElement> elements = driver.findElements(By.xpath("//*[@style='text-align: right; width: 27px;']"));
		int elementsCount = elements.size();
		System.out.println(elementsCount);
		return elementsCount;
	}
	
	public void fillSelectionFieldFromRecord(String value)
	{
		try
		{
			int elementsCount = selectionFieldElement.size();
			if(elementsCount!=0)
			{
				selectionFieldElement.get(0).click();
				selectionFieldElement.get(0).clear();
				selectionFieldElement.get(0).sendKeys(value);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fillSeletionFieldfromRecords(String selectionField)
	{
		try {
			List<WebElement> elements = driver.findElements(By.xpath("//*[@style='text-align: left; width: 9px;']"));
			int elementsCount = elements.size();
			if(elementsCount!=0)
			{
				elements.get(1).click();
				elements.get(1).clear();
				elements.get(1).sendKeys(selectionField);
			}
		}
	 catch(Exception e)
		{
		 e.printStackTrace();
		}
		
		
	}

	public void airSegmentDetails() throws InterruptedException
	{
		System.out.println(driver.findElement(statusCheck).isDisplayed());
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String spt = (String) jse.executeScript("return arguments[0].value;",driver.findElement(statusCheck));
		System.out.println(spt);
		Assert.assertEquals("OK", spt.trim());
		Thread.sleep(sleepDuringRender);
	}
	
	public void fillSelectionField(String selectionFieldValue) throws InterruptedException
	{
		selectionField.click();
		selectionField.clear();
		selectionField.sendKeys(selectionFieldValue);
		Thread.sleep(sleepDuringRender);
	}

}
