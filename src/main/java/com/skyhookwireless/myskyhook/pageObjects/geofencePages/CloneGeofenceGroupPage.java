package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.*;

/**
 * CloneGeofenceGroupPage is a page object that represents the clone geofence
 * page of the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class CloneGeofenceGroupPage extends AbstractPageObject {

    /* Web Elements */

    // Project drop down
    @FindBy(id = "activator")
    private WebElement projectDropdown;

    // Clone Button
    @FindBy(id = "submitform")
    private WebElement cloneButton;

    // Cancel Button
    @FindBy(id = "cancel")
    private WebElement cancelButton;

    /**
     * Constructor for a CloneGeofenceGroupPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public CloneGeofenceGroupPage(WebDriver driver) {
	super(driver);
	
	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("geofences.clone.title"));
	log.info("Title is correct");
    }

    /**
     * Allows the user to select a project from the project drop down
     * 
     * @param projectName
     *            Project Name
     * @return The current page
     */
    public CloneGeofenceGroupPage selectProject(String projectName) {
	Select droplist = new Select(projectDropdown);

	if (projectName != null) {
	    String project = projectName.toString();
	    droplist.selectByVisibleText(project);
	    log.info("Selected: " + project);
	} else {
	    log.error("Could not select a project. Invalid project name.");
	}

	return this;
    }

    /**
     * Allows the user to click the clone button
     * 
     * @return Edit geofence group page
     */
    public EditGeofenceGroupPage clickClone() {
	cloneButton.click();
	log.info("Clone button clicked");
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }

    /**
     * Allows the user to click the cancel button
     * 
     * @return Geofences page
     */
    public GeofencesPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, GeofencesPage.class);
    }

}
