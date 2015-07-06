package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skyhookwireless.myskyhook.pageObjects.*;

/**
 * EditGeofenceGroupPage is a page object that represents the edit geofence
 * group page
 * 
 * @author evansapienza
 * 
 */
public class EditGeofenceGroupPage extends AbstractPageObject {

    /* Web Elements */

    // Geofence Group Name Field
    @FindBy(id = "name")
    private WebElement nameTextbox;

    // Description Textbox
    @FindBy(id = "description")
    private WebElement descriptionTextbox;

    // Customer Payload Textbox
    @FindBy(id = "cu_text")
    private WebElement payloadTextbox;

    // Do Not Expire Checkbox
    @FindBy(id = "notexpire")
    private WebElement expireCheckbox;

    // Save Changes Button
    @FindBy(id = "submitform")
    private WebElement saveChangesButton;

    // Cancel Button
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    
    // Location list
    @FindBy(id = "div_location_list")
    private WebElement locationListDiv;

    // Delete Confirmation Popup
    @FindBy(className = "popup")
    private WebElement popup;


    // CSS Paths
    By backButton = By.cssSelector(properties.getProperty("geofences.editGeofenceGroup.backButton"));
    By expirationDateField = By.cssSelector(properties.getProperty("geofences.editGeofenceGroup.expirationTextbox"));
    By defineLocationButton = By.cssSelector(properties.getProperty("geofences.editGeofenceGroup.defineLocationButton"));
    By createVenueSetButton = By.cssSelector(properties.getProperty("geofences.editGeofenceGroup.createVenueSetButton"));
    By firstVenueCount = By.cssSelector(properties.getProperty("geofences.editGeofenceGroup.firstVenueCount"));
    // X Paths
    By polygonFilterName = By.xpath("//div[@id='div_polygon_list']/p");
    By waitCircle = By.xpath("//div[@class='categories-block  ivenuesection3']/div[contains(@class,'ajax')]");
    By venueCountWaitCircle = By.xpath("//img[contains(*,'/img/ajax-loader.gif')]");
    By deleteVenueSet = By.xpath("//a[@class='action-delete delete-vset']");
    By popupMsg = By.xpath("//*[@id='popup-overlay']");

    /**
     * The constructor for an EditGeofenceGroupPage
     * 
     * @param driver
     *            The webdriver of the page
     */
    public EditGeofenceGroupPage(WebDriver driver) {
	super(driver);

	Assert.assertTrue(driver.getCurrentUrl().contains("campaigns/edit"));
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
    public EditGeofenceGroupPage typeGeofenceGroupName(String geofenceGroupName) {
	nameTextbox.clear();
	nameTextbox.sendKeys(geofenceGroupName);
	log.info("Geofence group name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the geofence group name field
     * 
     * @return The current page
     */
    public EditGeofenceGroupPage clearGeofenceGroupName() {
	nameTextbox.clear();
	log.info("Geofence group name field cleared");
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
    public EditGeofenceGroupPage typeExpirationDate(String expirationDate) {
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
    public EditGeofenceGroupPage clearExpirationDate() {
	driver.findElement(expirationDateField).clear();
	log.info("Expiration date field cleared");
	return this;
    }

    /**
     * Allows the user to click the do not expire checkbox
     * 
     * @return The current page
     */
    public EditGeofenceGroupPage clickDoNotExpireCheckbox() {
	expireCheckbox.click();
	log.info("Do not expire checkbox clicked");
	return this;
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
     * Allows the user to enter a string in the description textbox
     * 
     * @param description
     *            The description of the geofence group being created
     * @return The current page
     */
    public EditGeofenceGroupPage typeDescription(String description) {
	descriptionTextbox.clear();
	descriptionTextbox.sendKeys(description);
	log.info("Description entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the description textbox
     * 
     * @return The current page
     */
    public EditGeofenceGroupPage clearDescription() {
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
    public EditGeofenceGroupPage typePayload(String payload) {
	payloadTextbox.clear();
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
    public EditGeofenceGroupPage clearPayload() {
	payloadTextbox.clear();
	log.info("Customer supplied payload textbox cleared");
	return this;
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
     * Allows the user to click the save changes button, if expecting a failure
     * use {@link #clickSaveExpectingFailure()}
     * 
     * @return Edit geofence group page
     * @see #clickSaveExpectingFailure()
     */
    public GeofencesPage clickSave() {
	saveChangesButton.click();
	log.info("Save changes button clicked");
	return PageFactory.initElements(driver, GeofencesPage.class);
    }

    /**
     * Allows the user to click the save changes button when a failure is
     * expected
     * 
     * @return The current page
     */
    public EditGeofenceGroupPage clickSaveExpectingFailure() {
	saveChangesButton.click();
	log.info("Save changes button clicked [Expecting Failure]");
	return this;
    }

    /**
     * Allows the user to click the define location parameters button
     * 
     * @return Define location parameters page
     */
    public DefineLocationParametersPage clickLocationParametersButton() {
	driver.findElement(defineLocationButton).click();
	log.info("Define location parameters button pressed");
	return PageFactory.initElements(driver, DefineLocationParametersPage.class);
    }

    /**
     * Allows the user to click the create new venue set button
     * 
     * @return Create new venue set page
     */
    public CreateVenueSetPage clickCreateNewVenueSetButton() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(popupMsg));
	driver.findElement(createVenueSetButton).click();
	log.info("Create new venue set button pressed");
	return PageFactory.initElements(driver, CreateVenueSetPage.class);
    }

    /**
     * Allows the user to click the hyperlink to a venue set
     * 
     * @param venueSetName
     *            The name of the venue set
     * @return The current page
     */
    public EditVenueSetPage clickVenueSet(String venueSetName) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.className("note-text")).findElement(
		By.xpath("//table//a[contains(.,'" + venueSetName + "')]")).click();
	return PageFactory.initElements(driver, EditVenueSetPage.class);
    }

    /**
     * Allows the user to click the delete button for a particular venue set
     * 
     * @param venueSetName
     *            The name of the venue set
     * @return The current page
     */
    public EditGeofenceGroupPage clickDeleteVenueSet(String venueSetName) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement nameLink = driver.findElement(By.className("note-text")).findElement(
		By.xpath("//table//a[contains(.,'" + venueSetName + "')]"));
	WebElement tableData = nameLink.findElement(By.xpath(".."));
	WebElement tableRow = tableData.findElement(By.xpath(".."));
	tableRow.findElement(By.linkText("")).click();
	log.info(venueSetName + " deletion link clicked");
	return this;
    }

    /**
     * Allows the user to click the yes button in the delete modal
     * 
     * @return The current page
     */
    public EditGeofenceGroupPage clickYesDeleteModal() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	popup.findElement(By.linkText("YES")).click();
	log.info("Yes button clicked in delete modal");
	return this;
    }

    /**
     * Allows the user to click the no button in the delete modal
     * 
     * @return The current page
     */
    public EditGeofenceGroupPage clickNoDeleteModal() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	popup.findElement(By.linkText("No")).click();
	log.info("No button clicked in delete modal");
	return this;
    }

    /**
     * Allows the user to delete a venue set
     * 
     * @param venueSetName
     *            The name of the venue set to be deleted
     * @return The current page
     */
    public EditGeofenceGroupPage deleteVenueSet(String venueSetName) {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement nameLink = driver.findElement(By.className("note-text")).findElement(
		By.xpath("//table//a[contains(.,'" + venueSetName + "')]"));
	WebElement tableData = nameLink.findElement(By.xpath(".."));
	WebElement tableRow = tableData.findElement(By.xpath(".."));
	tableRow.findElement(By.linkText("")).click();
	clickYesDeleteModal();
	log.info(venueSetName + " deleted");
	return this;
    }
    
    /**
     * Allows the user to delete the top venue set
     * @return
     */
    public EditGeofenceGroupPage deleteVenueSet() {
    driver.findElement(deleteVenueSet).click();
    this.clickYesDeleteModal();
    return this;	
    }

    /**
     * 
     * @param country
     *            The country to check
     * @param state
     *            The state to check
     * @return Returns true if the location is in the location list
     */
    public boolean isLocationPresent(String country, String state) {
	if (((state == null) || (state.equals(""))) && (country != null))
	    return locationListDiv.findElement(By.tagName("p")).getText().contains(country);
	else if ((country != null) && (state != null))
	    return locationListDiv.findElement(By.tagName("p")).getText().contains(state + ", " + country);
	else
	    return false;
    }

    /**
     * @param venueSetName
     *            Name of venue set to be checked
     * @return Returns true if the venue set is present
     */
    public boolean isVenueSetPresent(String venueSetName) {
	try {
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    WebElement nameLink = driver.findElement(By.className("note-text")).findElement(
		    By.xpath("//table//a[contains(.,'" + venueSetName + "')]"));
	    WebElement tableData = nameLink.findElement(By.xpath(".."));
	    WebElement tableRow = tableData.findElement(By.xpath(".."));
	    tableRow.findElement(By.linkText(""));
	    return true;
	} catch (Throwable T) {
	    return false;
	}
    }

    /**
     * @return Returns true if the do not expire checkbox is checked
     */
    public boolean isDoNotExpireCheckboxChecked() {
	if (this.expireCheckbox.getAttribute("value").equals("1"))
	    return true;
	else
	    return false;
    }

    /**
     * @return Returns the text from the geofence group name textbox
     */
    public String getGeofenceGroupName() {
	return this.nameTextbox.getAttribute("value");
    }

    /**
     * @return Returns the text from the description textbox
     */
    public String getDescription() {
	return this.descriptionTextbox.getText();
    }

    /**
     * @return Returns the text from the payload textbox
     */
    public String getPayload() {
	return this.payloadTextbox.getText();
    }
    
    /**
     * @return Returns all the geofilter names
     * separated by commas
     */
    public String getFilterName() {
    log.info("List of filter names: " + driver.findElement(polygonFilterName).toString());
    return driver.findElement(polygonFilterName).getText(); 
    }

    /**
     * @return Returns the number of venue count after applying the filter
     */
    public int getVenueSetCount() {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(venueCountWaitCircle));
    try {
    Thread.sleep(5000);
    } catch(Exception e) {
    log.info("sleep failed");	
    }
    return Integer.parseInt(driver.findElement(firstVenueCount).getText().replace(",", ""));
    }
}
