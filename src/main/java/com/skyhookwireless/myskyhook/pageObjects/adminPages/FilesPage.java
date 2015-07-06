package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

public class FilesPage extends AbstractPageObject {
	
	// Switch between the tabs
    @FindBy(linkText = "Context Accelerator")
    private WebElement contextAcceleratorTab;
    
    @FindBy(linkText = "Precision Location")
    private WebElement precisionLocationTab;
    
    @FindBy(linkText = "Demo Apps")
    private WebElement demoAppsTab;
    
    @FindBy(linkText = "ADD SDK PACKAGE")
	private WebElement uploadSDK;
    
    @FindBy(linkText = "UPLOAD FILE")
    private WebElement uploadFile;
    
    @FindBy(id = "cancel")
    private WebElement cancelUploadButton;
    
	// Constructor
	public FilesPage(WebDriver driver) {
		super(driver);
		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.files.title"));
		log.info("Title is correct");
	}
	
	/**
	 * Allows the user to switch to the 'Precsion Location' tab
	 * @return this page
	 */
	public FilesPage switchToPrecisionLocation() {
		precisionLocationTab.click();
		log.info("Switched to the precision location tab");
		return this;
	}
	
	/**
	 * Allows the user to switch to the 'Context Accelerator' tab
	 * @return this page
	 */
	public FilesPage switchToContextAccelerator() {
		contextAcceleratorTab.click();
		log.info("Switched to the context accelerator tab");
		return this;
	}
	
	/**
	 * Allows the user to switch to the 'Demo App' tab
	 * @return this page
	 */
	public FilesPage switchToDemoApps() {
		demoAppsTab.click();
		log.info("Switched to the demo app tab");
		return this;
	}
	
	/**
	 * Allows the user to click the archive selected button
	 * @return this page
	 */
	public FilesPage clickArchiveSelected() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@value='Archive Selected']")).click();
		log.info("Archive Selected Clicked");
		return this;
	}
	
	/**
	 * Allows the user to click the un-archive selected button
	 * @return this page
	 */
	public FilesPage clickUnarchiveSelected() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@value='UnArchive Selected']")).click();
		log.info("UnArchive Selected Clicked");
		return this;
	}
	
	/**
	 * Allows the user to click the upload SDK button
	 * @return this page
	 */
	public FilesPage clickUploadSDK() {
		uploadSDK.click();
		log.info("Upload SDK Clicked");
		return this;
	}
	
	/**
	 * Allows the user to click the upload File button
	 * @return this page
	 */
	public FilesPage clickUploadFile() {
		uploadFile.click();
		log.info("Upload File Clicked");
		return this;
	}
	

	/**
	 * Allows the user to click the cancel (the upload) button
	 * @return this page
	 */
	public FilesPage clickCancelUpload() {
		cancelUploadButton.click();
		log.info("cancel button Clicked");
		return this;
	}
	
	
	/**
	 * Verify if the button indicated by the input linkName
	 * is present
	 * @param inputText
	 * @return
	 */
	public boolean isButtonPresent(String inputText) {
	if (this.isElementPresent(By.linkText(inputText)))
		return true;
	else
		return false;
	}
}
