package com.skyhookwireless.myskyhook.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * SetPermissionsModal is a page object the represents the edit permissions
 * modal on the my.skyhookweb portal
 * 
 * @author evansapienza
 * 
 */
public class SetPermissionsModal extends AbstractPageObject {

    /* Web Elements */

    // Popup Div
    @FindBy(className = "popup-content")
    @CacheLookup
    private WebElement permissionsModal;

    // Save Changes Button
    @FindBy(id = "formsubmit")
    @CacheLookup
    private WebElement saveChangesButton;

    // Save Changes Button
    @FindBy(id = "cancel")
    @CacheLookup
    private WebElement cancelButton;

    // Billing Div
    @FindBy(id = "div_billing")
    @CacheLookup
    private WebElement billingDiv;

    // Database Download Div
    @FindBy(id = "div_ip_database")
    @CacheLookup
    private WebElement databaseDiv;

    // Analytics Div
    @FindBy(id = "div_analytics")
    @CacheLookup
    private WebElement analyticsDiv;

    // Geofence Management Div
    @FindBy(id = "div_campaign_management")
    @CacheLookup
    private WebElement campaignManagementDiv;

    // API Key Div
    @FindBy(id = "div_api_key")
    @CacheLookup
    private WebElement apiKeyDiv;

    /**
     * Constructor for the
     * 
     * @param driver
     *            The webdriver of the page
     */
    public SetPermissionsModal(WebDriver driver) {
	super(driver);
    }

    /**
     * Allows the user to click the view billing checkbox
     * 
     * @return The current page
     */
    public SetPermissionsModal clickViewBillingCheckbox() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	billingDiv.findElement(By.className("row-label")).click();
	log.info("View billing checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the IP database download checkbox
     * 
     * @return The current page
     */
    public SetPermissionsModal clickDatabaseDownloadCheckbox() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	databaseDiv.findElement(By.className("row-label")).click();
	log.info("IP database download checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the first geofence management checkbox
     * 
     * @return The current page
     */
    public SetPermissionsModal clickGeofenceManagementCheckbox() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	campaignManagementDiv.findElement(By.className("row-label")).click();
	log.info("Geofence management checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the first analytics checkbox
     * 
     * @param projectID
     *            the project ID of the project
     * @return The current page
     */
    public SetPermissionsModal clickAnalyticsCheckbox(String projectID) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement projectDiv = driver.findElement(By.id("project_" + projectID));
	projectDiv.findElement(By.id("div_analytics")).click();
	log.info("Analytics checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the project key checkbox for a specific project
     * 
     * @param projectID
     *            The project ID of the project
     * @return The current page
     */
    public SetPermissionsModal clickProjectKeyCheckbox(String projectID) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement projectDiv = driver.findElement(By.id("project_" + projectID));
	projectDiv.findElement(By.id("div_api_key")).click();
	log.info("API key checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the first project key checkbox
     * 
     * @return The current page
     */
    public SetPermissionsModal clickProjectKeyCheckbox() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	apiKeyDiv.findElement(By.className("row-label")).click();
	log.info("Project Key checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the project checkbox of the specified project
     * 
     * @param projectID
     *            The project ID of the project
     * @return The current page
     */
    public SetPermissionsModal clickProjectCheckbox(String projectID) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement projectDiv = driver.findElement(By.id("div_" + projectID));
	projectDiv.findElement(By.className("row-label ")).click();
	log.info("Path: " + "div_" + projectID);
	log.info("Project checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the save changes button
     * 
     * @return The dashboard page
     */
    public UserSettingsPage clickSaveChangesButton() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	saveChangesButton.click();
	log.info("Save changes button clicked");
	try {
	    Thread.sleep(3500);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	driver.navigate().refresh();
	return PageFactory.initElements(driver, UserSettingsPage.class);
    }

    /**
     * Allows the user to click the save changes button
     * 
     * @return The dashboard page
     */
    public UserSettingsPage clickCancelButton() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	cancelButton.click();
	log.info("Cancel button clicked");
	try {
	    Thread.sleep(3500);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return PageFactory.initElements(driver, UserSettingsPage.class);
    }
}
