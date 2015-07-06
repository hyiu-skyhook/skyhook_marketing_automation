package com.skyhookwireless.myskyhook.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * UsageReportsPage is a page object that represents a usage reports page on the
 * my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class UsageReportsPage extends AbstractPageObject {

    /* Web Elements */

    // Page container
    @FindBy(className = "page-container")
    @CacheLookup
    private WebElement pageContainer;

    // CSS Paths
    private By usageReportsTable = By.cssSelector(properties.getProperty("usagereports.usageReportsTable"));
    private By historyTable = By.cssSelector(properties.getProperty("usagereports.historyTable"));
    private By yesSubmitButton = By.cssSelector(properties.getProperty("usagereports.yesButton"));
    private By noSubmitButton = By.cssSelector(properties.getProperty("usagereports.noButton"));

    // Xpaths
    private By submitUsageButton = By.xpath(properties.getProperty("usagereports.submitPath"));

    public UsageReportsPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("usagereports.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to click the first visible 'report usage' link
     * 
     * @return The current page
     */
    public UsageReportsPage clickReportUsage() {
	pageContainer.findElement(By.linkText("report usage")).click();
	log.info("Report usage link clicked");
	return this;
    }

    /**
     * Allows the user to enter an string (should be integer) in the first
     * visible location requests input
     * 
     * @param lookups
     *            The number of lookups to be entered
     * @return The current page
     */
    public UsageReportsPage typeLookups(String lookups) {
	pageContainer.findElement(By.name("locationRequests")).clear();
	pageContainer.findElement(By.name("locationRequests")).sendKeys(lookups);
	log.info("Value entered in location requests textbox");
	return this;
    }

    /**
     * Allows the user to click the first visible submit button the page
     * 
     * @return The refreshed usage reports page
     */
    public UsageReportsPage clickSubmitUsage() {
	pageContainer.findElement(submitUsageButton).click();
	log.info("Submit usage button clicked");
	return PageFactory.initElements(driver, UsageReportsPage.class);
    }

    /**
     * Allows the user to click the first visible submit button the page when
     * expecting a failure
     * 
     * @return The current page
     */
    public UsageReportsPage clickSubmitUsageExpectingFailure() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	pageContainer.findElement(submitUsageButton).click();
	log.info("Submit usage button clicked [Expecting Failure]");
	return this;
    }

    /**
     * Allows the user to click the yes submit button for the usage reports
     * modal
     * 
     * @return The current page
     */
    public UsageReportsPage clickYesSubmitButton() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(yesSubmitButton).click();
	log.info("Yes submit button clicked");
	// Wait for modal to fade
	try {
	    Thread.sleep(5000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return PageFactory.initElements(driver, UsageReportsPage.class);
    }

    /**
     * Allows the user to click the no button for the usage reports modal
     * 
     * @return The current page
     */
    public UsageReportsPage clickNoSubmitButton() {
	driver.findElement(noSubmitButton).click();
	log.info("No button clicked");
	// Wait for modal to fade
	try {
	    Thread.sleep(5000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return this;
    }

    /**
     * @return Returns true if the usage reports table is present
     */
    public boolean isUsageReportsTablePresent() {
	return this.isElementPresent(usageReportsTable);
    }

    /**
     * @return Returns true if the history table is present
     */
    public boolean isHistoryTablePresent() {
	return this.isElementPresent(historyTable);
    }

    /**
     * @return Returns true if a report usage link is present
     */
    public boolean isReportUsageLinkPresent() {
	return this.isElementPresent(By.linkText("report usage"));
    }

    /**
     * @return Returns true if there is an error message present
     */
    public boolean isErrorMessagePresent() {
	return this.isElementPresent(By.className("form-errors"));
    }

    /**
     * @return Returns first found error message
     */
    public String getErrorMessage() {
	return driver.findElement(By.className("form-errors")).getText();
    }

    /**
     * @param usageValue
     *            Location lookup value to be found
     * @return Returns true if the table contains the given usage value
     */
    public boolean isUsageSubmitted(String usageValue) {
	String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();
	bodyText = bodyText.replaceAll(",", "");
	if (bodyText.contains(usageValue))
	    
	    return true;
	else
	    return false;
    }
}
