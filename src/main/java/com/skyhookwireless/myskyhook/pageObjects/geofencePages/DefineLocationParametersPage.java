package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

/**
 * DefineLocationParametersPage is a page object that represents the filter by
 * locations page of the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class DefineLocationParametersPage extends AbstractPageObject {

    /* Web Elements */

    // Country picker
    @FindBy(id = "country_id")
    private WebElement countryDropdown;

    // State picker
    @FindBy(id = "state")
    private WebElement stateDropdown;

    // Add location button
    @FindBy(id = "save_location_button")
    @CacheLookup
    private WebElement addLocationButton;

    // Location table rows
    @FindBy(tagName = "tr")
    private List<WebElement> locationTableRows;
    
    // Custom Geofilter
    @FindBy(id = "custom-geofilter")
    @CacheLookup
    private WebElement customGeofilter;

    // Create Button
    @FindBy(id = "submit_form")
    @CacheLookup
    private WebElement saveToGeofenceGroupButton;

    // Cancel Button
    @FindBy(id = "cancel")
    @CacheLookup
    private WebElement cancelButton;

    // Popup
    @FindBy(className = "popup")
    private WebElement pageModal;

    // CSS Paths
    By yesDelete = By.cssSelector(properties.getProperty("geofences.defineLocationParameters.yesDeleteButton"));
    By noDelete = By.cssSelector(properties.getProperty("geofences.defineLocationParameters.noDeleteButton"));
    By yesCancel = By.cssSelector(properties.getProperty("geofences.defineLocationParameters.yesCancelButton"));
    By noCancel = By.cssSelector(properties.getProperty("geofences.defineLocationParameters.noCancelButton"));

    /**
     * The constructor for a DefineLocationParamtersPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public DefineLocationParametersPage(WebDriver driver) {
	super(driver);

	Assert.assertTrue(driver.getCurrentUrl().contains("campaigns/locationparams"));
	log.info("URL is correct");
    }

    /**
     * Allows the user to select a country by name
     * 
     * @param country
     *            The name of the country to be selected
     * @return The current page
     */
    public DefineLocationParametersPage selectCountry(String country) {
	Select droplist = new Select(countryDropdown);

	if (country != null) {
	    droplist.selectByVisibleText(country);
	    log.info("Selected: " + country);
	} else {
	    log.error("Could not select country '" + country + "'. Invalid country name.");
	}
	return this;
    }

    /**
     * Allows the user to select a state by name
     * 
     * @param state
     *            The name of the country to be selected
     * @return The current page
     */
    public DefineLocationParametersPage selectState(String state) {
	Select droplist = new Select(stateDropdown);
	if (state != null) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[text()[contains(.,'" + state + "')]]")));
	    droplist.selectByVisibleText(state);
	    log.info("Selected: " + state);
	}
	return this;
    }

    /**
     * Allows the user to click the add location button
     * 
     * @return The current page
     */
    public DefineLocationParametersPage clickAddLocationButton() {
	addLocationButton.click();
	log.info("Add location button clicked");
	return PageFactory.initElements(driver, DefineLocationParametersPage.class);
    }

    /**
     * Allows the user to click the yes button in the delete modal
     * 
     * @return The current page
     */
    public DefineLocationParametersPage clickYesDeleteModal() {
	driver.findElement(yesDelete).click();
	try{
		Thread.sleep(3000);
	} catch (Exception e) {
		log.info("Sleep failed");
	}
	log.info("Yes button pressed in delete modal");
	return PageFactory.initElements(driver, DefineLocationParametersPage.class);
    }

    /**
     * Allows the user to click the no button in the delete modal
     * 
     * @return The current page
     */
    public DefineLocationParametersPage clickNoDeleteModal() {
	driver.findElement(noDelete).click();
	WebDriverWait wait = new WebDriverWait(driver, 3);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
			By.xpath("//div[@class='popup-content']")));
	log.info("No button pressed in delete modal");
	return this;
    }

    /**
     * Allows the user to click the yes button in the cancel modal
     * 
     * @return The current page
     */
    public DefineLocationParametersPage clickYesCancelModal() {
	driver.findElement(yesCancel).click();
	WebDriverWait wait = new WebDriverWait(driver, 3);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
			By.xpath("//div[@class='popup-content']")));
	log.info("Yes button pressed in cancel modal");
	return this;
    }

    /**
     * Allows the user to click the no button in the cancel modal
     * 
     * @return The current page
     */
    public DefineLocationParametersPage clickNoCancelModal() {
	driver.findElement(noCancel).click();
	WebDriverWait wait = new WebDriverWait(driver, 3);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
			By.xpath("//div[@class='popup-content']")));
	log.info("No button pressed in cancel modal");
	return this;
    }

    public DefineLocationParametersPage addLocation(String country, String state) {
	this.selectCountry(country);
	this.selectState(state);
	return this.clickAddLocationButton();
    }

    /**
     * Allows the user to delete a particular location
     * 
     * @param country
     *            The country of the location t be deleted
     * @param state
     *            The state of the location to be deleted
     * @return The current page
     */
    public DefineLocationParametersPage deleteLocation(String country, String state) {
	this.clickDeleteLocationButton(country, state);
	clickYesDeleteModal();
	log.info("Location '" + state + ", " + country + "' deleted");
	return PageFactory.initElements(driver, DefineLocationParametersPage.class);
    }

    /**
     * Allows the user to click the x button on a particular location
     * 
     * @param country
     *            The country of the location to have the x button clicked
     * @param state
     *            The state of the location to have the x button clicked
     * @return The current page
     */
    public DefineLocationParametersPage clickDeleteLocationButton(String country, String state) {
	boolean hit1 = false;
	boolean hit2 = false;

	for (WebElement row : locationTableRows) {
	    List<WebElement> data = row.findElements(By.tagName("td"));
	    for (WebElement cell : data) {

		if (hit2) {
		    cell.findElement(By.tagName("a")).click();
		    log.info("Location '" + state + ", " + country + "' delete button clicked");
		    return this;
		}

		if (hit1 && (cell.getText().contains(state))) {
		    hit2 = true;
		} else {
		    hit1 = false;
		}

		if (cell.getText().contains(country)) {
		    hit1 = true;
		}

	    }
	}

	log.error("Location '" + state + ", " + country + "' could not be found");
	throw new AssertionError("Location '" + state + ", " + country + "' could not be found");
    }

    /**
     * Allows the user to click the save geofence group button
     * 
     * @return Edit geofence group page
     */
    public EditGeofenceGroupPage clickSaveToGeofenceGroupButton() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	saveToGeofenceGroupButton.click();
	log.info("Save to geofence group button clicked");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }

    /**
     * Allows the user to click the cancel button, use this method if no changes
     * have been made to the page otherwise use
     * {@link #clickCancelButtonDiscardChanges()}
     * 
     * @return Edit geofence group page
     */
    public EditGeofenceGroupPage clickCancelButtonNoChanges() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }
    
    /**
     * Allows the user to click the cancel button, use this method if changes
     * have been made to the page
     * 
     * @return Edit geofence group page
     */
    public DefineLocationParametersPage clickCancelButtonChanges() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return this;
    }

    /**
     * Allows the user to click the cancel button, use this method when changes
     * have been made to the page and you do not wish to save them
     * 
     * @return Edit geofence group page
     */
    public EditGeofenceGroupPage clickCancelButtonDiscardChanges() {
	cancelButton.click();
	this.clickYesCancelModal();
	log.info("Cancel button clicked, changes discarded");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }
    
    /**
     * Allows the user to click on the custom geofilter
     */
    public CustomFilterPage clickCustomGeofilter() {
    customGeofilter.click();
    return PageFactory.initElements(driver, CustomFilterPage.class);
    }
    
    /**
     * @param country	Country of selected location
     * @param state	State of selected location
     * @return	Returns true if the location has been selected
     */
    public boolean isLocationSelected(String country, String state) {
	boolean hit1 = false;
	boolean hit2 = false;

	for (WebElement row : locationTableRows) {
	    List<WebElement> data = row.findElements(By.tagName("td"));
	    for (WebElement cell : data) {

		if (hit2) {
		    return true;
		}

		if (hit1 && (cell.getText().contains(state))) {
		    hit2 = true;
		} else {
		    hit1 = false;
		}

		if (cell.getText().contains(country)) {
		    hit1 = true;
		}

	    }
	}
	return false;
    }
}
