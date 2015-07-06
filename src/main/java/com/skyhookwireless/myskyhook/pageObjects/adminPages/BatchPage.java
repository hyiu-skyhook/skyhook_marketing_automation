package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

public class BatchPage extends AbstractPageObject {
	// The Load Progress Tab
    @FindBy( linkText = "Load Progress")
    private WebElement loadProgressTab;
    
	// The Request Data Tab
    @FindBy( linkText = "Request Data")
    private WebElement requestDataTab;
    
	// The File Information Tab
    @FindBy( linkText = "File Information")
    private WebElement fileInformationTab;
	
	
    public BatchPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.batch.title"));

	log.info("Title is correct");

    }
    
    /**
     * Allows the user to click the load progress tab
     * 
     * @return this page
     */
    public BatchPage clickRequestDataTab() {
	requestDataTab.click();
	log.info("Request data tab clicked");
	return this;
    }
    
    /**
     * Allows the user to click the load progress tab
     * 
     * @return this page
     */
    public BatchPage clickLoadProgressTab() {
	loadProgressTab.click();
	log.info("Load progress tab clicked");
	return this;
    }
    
    /**
     * Allows the user to click the file information tab
     * 
     * @return this page
     */
    public BatchPage clickFileInformationTab() {
	fileInformationTab.click();
	log.info("File infomration tab clicked");
	return this;
    }
    
    /**
     * Allows the user to verify header
     * 
     * @return this page
     */
    public void  verifyHeader(String tabName) {
	String pageHeader = driver.findElement(By.xpath("//div[@class='tab-content']/div/h2")).getText();
	Assert.assertEquals(pageHeader, tabName);
    }
    
    /**
     * Allows the user to verify 
     * 
     * @return this page
     */
    public void  verifyFileLocation(String filePath) {
	String fileLocation = driver.findElement(By.xpath("//tbody/tr/td[1]")).getText();
	Assert.assertEquals(fileLocation, filePath);
    }
}
