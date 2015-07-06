package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

/**
 * CreateVenueSetPage is a page object that models the venue creation section of
 * the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class EditVenueSetPage extends AbstractPageObject {

    /* Web Elements */

    // Name venue set field
    @FindBy(id = "name")
    private WebElement nameTextbox;

    // Save Button
    @FindBy(id = "submit_form")
    private WebElement saveButton;

    // Cancel Button
    @FindBy(id = "cancel")
    private WebElement cancelButton;

    /**
     * The constructor for a EditVenueSetPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public EditVenueSetPage(WebDriver driver) {
	super(driver);
	
	Assert.assertEquals(driver.getTitle(), properties.getProperty("geofences.editVenueSet.title"));
	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the name your venue set textbox
     * 
     * @param name
     *            The name of the venue set
     * @return The current page
     */
    public EditVenueSetPage typeVenueSetName(String name) {
	nameTextbox.clear();
	nameTextbox.sendKeys(name);
	log.info("Venue set name entered");
	return this;
    }
    
    /**
     * Allows the user to enter clear any text in the name your venue set textbox
     * 
     * @return The current page
     */
    public EditVenueSetPage clearVenueSetName() {
	nameTextbox.clear();
	log.info("Venue set name cleared");
	return this;
    }

    /**
     * Allows the user to click the checkbox of a particular venue
     * 
     * @param venueName
     *            The venue whose checkbox will be clicked
     * @return The current page
     */
    public EditVenueSetPage clickVenueCheckbox(String venueName) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement spanElement = driver.findElement(By.xpath("//span[contains(.,'"+venueName+"')]"));
	WebElement listItem = spanElement.findElement(By.xpath(".."));
	listItem.findElement(By.tagName("label")).click();
	log.info(venueName + ": checkbox clicked");
	return this;
    }
    
    /**
     * Allows the user to click the tree symbol of a particular venue
     * 
     * @param venueName
     *            The venue whose tree symbol will be clicked
     * @return The current page
     */
    public EditVenueSetPage clickVenueTree(String venueName) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement spanElement = driver.findElement(By.xpath("//span[contains(.,'"+venueName+"')]"));
	WebElement listItem = spanElement.findElement(By.xpath(".."));
	listItem.findElement(By.tagName("a")).click();
	log.info(venueName + ": tree clicked");
	return this;
    }

    /**
     * Allows the user to click the save button
     * 
     * @return Edit geofence page
     */
    public EditGeofenceGroupPage clickSave() {
	saveButton.click();
	log.info("Save button clicked");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }

    /**
     * Allows the user to click the save button when expecting a failure
     * 
     * @return The current page
     */
    public EditVenueSetPage clickSaveExpectingFailure() {
	saveButton.click();
	log.info("Save button clicked");
	return this;
    }

    /**
     * Allows the user to click the cancel button
     * 
     * @return Edit geofence page
     */
    public EditGeofenceGroupPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }
}
