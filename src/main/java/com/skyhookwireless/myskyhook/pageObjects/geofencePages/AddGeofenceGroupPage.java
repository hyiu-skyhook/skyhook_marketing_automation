package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.skyhookwireless.myskyhook.pageObjects.*;

/**
 * AddGeofenceGroupPage is a page object that represents the add geofence group
 * page of the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class AddGeofenceGroupPage extends AbstractPageObject {

    /* Web Elements */

    // Geofence Group Name Field
    @FindBy(id = "name")
    private WebElement nameTextbox;

    // Description Textbox
    @FindBy(id = "description")
    private WebElement descriptionTextbox;

    // Project API Key Dropdown
    @FindBy(id = "activator")
    private WebElement apiKeyDropdown;

    // Customer Payload Textbox
    @FindBy(id = "cu_text")
    private WebElement payloadTextbox;

    // Do Not Expire Checkbox
    @FindBy(id = "notexpire")
    private WebElement expireCheckbox;

    // Create Button
    @FindBy(id = "submitform")
    private WebElement createButton;

    // Cancel Button
    @FindBy(id = "cancel")
    private WebElement cancelButton;

    // CSS Paths
    By backButton = By.cssSelector(properties.getProperty("geofences.addGeofenceGroup.backButton"));
    By expirationDateField = By.cssSelector(properties.getProperty("geofences.addGeofenceGroup.expirationTextbox"));

    /**
     * The constructor for an AddGeofenceGroupPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public AddGeofenceGroupPage(WebDriver driver) {
	super(driver);

	Assert.assertTrue(driver.getCurrentUrl().contains("campaigns/add"));
	log.info("URL is correct");
    }

    /**
     * Allows the user to click the back to geofence groups button
     * 
     * @return Geofences page
     */
    public GeofencesPage clickBack() {
	driver.findElement(backButton).click();
	log.info("Back to geofence groups button clicked");
	return PageFactory.initElements(driver, GeofencesPage.class);
    }

    /**
     * Allows the user to enter a string in the geofence group name field
     * 
     * @param geofenceGroupName
     *            The name of the geofence group
     * @return The current page
     */
    public AddGeofenceGroupPage typeGeofenceGroupName(String geofenceGroupName) {
	nameTextbox.sendKeys(geofenceGroupName);
	log.info("Geofence group name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the geofence group name field
     * 
     * @return The current page
     */
    public AddGeofenceGroupPage clearGeofenceGroupName() {
	nameTextbox.clear();
	log.info("Geofence group name field cleared");
	return this;
    }

    /**
     * Allows the user to select the Project API Key
     * 
     * @param projectName
     *            The project name of where the geofence group will be added
     * @return The current page
     */
    public AddGeofenceGroupPage selectProjectAPIKey(String projectName) {
	Select droplist = new Select(apiKeyDropdown);

	if (projectName != null) {
	    droplist.selectByVisibleText(projectName);
	    log.info("Selected: " + projectName);
	} else {
	    log.error("Could not select a project. Invalid project name.");
	}

	return this;
    }

    /**
     * Allows the user to enter a string in the expiration date field
     * 
     * @param expirationDate
     *            String representing a date (Must be in the following format:
     *            'MM-dd-yyyy')
     * @return The current page
     */
    public AddGeofenceGroupPage typeExpirationDate(String expirationDate) {
	driver.findElement(expirationDateField).clear();
	driver.findElement(expirationDateField).sendKeys(expirationDate);
	log.info("Expiration date entered: " + expirationDate);
	return this;
    }

    /**
     * Allows the user to clear any text in the expiration date field
     * 
     * @return The current page
     */
    public AddGeofenceGroupPage clearExpirationDate() {
	driver.findElement(expirationDateField).clear();
	log.info("Expiration date field cleared");
	return this;
    }

    /**
     * Allows the user to click the do not expire checkbox
     * 
     * @return The current page
     */
    public AddGeofenceGroupPage clickDoNotExpireCheckbox() {
	expireCheckbox.click();
	log.info("Do not expire checkbox clicked");
	return this;
    }

    /**
     * Allows the user to enter a string in the description textbox
     * 
     * @param description
     *            The description of the geofence group being created
     * @return The current page
     */
    public AddGeofenceGroupPage typeDescription(String description) {
	descriptionTextbox.sendKeys(description);
	log.info("Description entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the description textbox
     * 
     * @return The current page
     */
    public AddGeofenceGroupPage clearDescription() {
	descriptionTextbox.clear();
	log.info("Description textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the customer supplied payload
     * textbox
     * 
     * @param payload
     *            The payload that will be triggered when a geofence is hit
     * @return The current page
     */
    public AddGeofenceGroupPage typePayload(String payload) {
	payloadTextbox.sendKeys(payload);
	log.info("Customer supplied payload entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the customer supplied payload
     * textbox
     * 
     * @return The current page
     */
    public AddGeofenceGroupPage clearPayload() {
	payloadTextbox.clear();
	log.info("Customer supplied payload textbox cleared");
	return this;
    }

    /**
     * Allows the user to click the create geofence group button, if expecting a
     * failure use {@link #clickCreateExpectingFailure()}
     * 
     * @return Edit geofence group page
     * @see #clickCreateExpectingFailure()
     */
    public EditGeofenceGroupPage clickCreate() {
	createButton.click();
	log.info("Create button clicked");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }

    /**
     * Allows the user to click the create geofence group button when a failure
     * is expected
     * 
     * @return The current page
     */
    public AddGeofenceGroupPage clickCreateExpectingFailure() {
	createButton.click();
	log.info("Create button clicked [Expecting Failure]");
	return this;
    }

    /**
     * Allows the user to create a geofence group, if expecting a failure use
     * {@link #createGeofenceGroupExpectingFailure(String, String, String, String, String, boolean)}
     * 
     * @param geofenceGroupName
     *            The name of the new geofence group
     * @param projectAPIKey
     *            The project where the geofence group will be created (if NULL
     *            then the currently selected project will be used)
     * @param expirationDate
     *            The expiration date of the geofence group
     * @param description
     *            The description of the geofence group
     * @param payload
     *            The customer supplied payload of the geofence group
     * @param doNotExpire
     *            Determine whether the do not expire checkbox should be checked
     *            or not
     * @return The edit geofence group page
     * @see #createGeofenceGroupExpectingFailure(String, String, String, String,
     *      String, boolean)
     */
    public EditGeofenceGroupPage createGeofenceGroup(String geofenceGroupName, String projectAPIKey,
	    String expirationDate, String description, String payload, boolean doNotExpire) {
	this.typeGeofenceGroupName(geofenceGroupName);
	if (projectAPIKey != null)
	    this.selectProjectAPIKey(projectAPIKey);
	this.typeDescription(description);
	this.typePayload(payload);
	if ((!expireCheckbox.isSelected()) && doNotExpire)
	    this.clickDoNotExpireCheckbox();
	else
	    if (expirationDate != null)
		this.typeExpirationDate(expirationDate);
	return this.clickCreate();
    }

    /**
     * Allows the user to create a geofence group when expecting a failure
     * 
     * @param geofenceGroupName
     *            The name of the new geofence group
     * @param projectAPIKey
     *            The project where the geofence group will be created (if NULL
     *            then the currently selected project will be used)
     * @param expirationDate
     *            The expiration date of the geofence group
     * @param description
     *            The description of the geofence group
     * @param payload
     *            The customer supplied payload of the geofence group
     * @param doNotExpire
     *            Determine whether the do not expire checkbox should be checked
     *            or not
     * @return The current page
     */
    public AddGeofenceGroupPage createGeofenceGroupExpectingFailure(String geofenceGroupName, String projectAPIKey,
	    String expirationDate, String description, String payload, boolean doNotExpire) {
	this.typeGeofenceGroupName(geofenceGroupName);
	if (projectAPIKey != null)
	    this.selectProjectAPIKey(projectAPIKey);
	this.typeExpirationDate(expirationDate);
	this.typeDescription(description);
	this.typePayload(payload);
	if ((!expireCheckbox.isSelected()) && doNotExpire)
	    this.clickDoNotExpireCheckbox();
	return this.clickCreateExpectingFailure();
    }

    /**
     * Allows the user to click the cancel button
     * 
     * @return Geofences Page
     */
    public GeofencesPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, GeofencesPage.class);
    }

    /**
     * Allows the user to determine if the do not expire checkbox is checked
     * 
     * @return A boolean value indicating if the checkbox is checked or not
     */
    public boolean isExpireCheckboxChecked() {
	if (expireCheckbox.isSelected())
	    return true;
	else
	    return false;
    }

    /**
     * @param projectName
     *            Returns true if project is located in project api key drop
     *            down
     * @return Returns true if the project is located in the project api key
     *         drop down
     */
    public boolean isProjectAPIKeyPresent(String projectName) {
	Select droplist = new Select(apiKeyDropdown);
	try {
	    droplist.selectByVisibleText(projectName);
	} catch (Exception e) {
	    return false;
	}

	return true;
    }
}
