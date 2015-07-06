package com.skyhookwireless.myskyhook.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.geofencePages.*;

/**
 * GeofencesPage is a page object that represents the geofences section of the
 * my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class GeofencesPage extends AbstractPageObject {

    /* Web Elements */

    // Project drop down
    @FindBy(id = "app_dropdown")
    private WebElement projectDropdown;

    // Table rows
    @FindBy(tagName = "tr")
    private List<WebElement> tableRows;
    
    // Xpath
    By actionBar = By.xpath("//a[@class='js-campaign-menu js-content-menu']");
    By popup = By.id("popup-overlay");

    // CSS Path
    By createNewGeofenceGroupButton = By.cssSelector(properties.getProperty("geofences.createNewGeofenceGroupButton"));

    /**
     * The constructor for a GeofencesPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public GeofencesPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("geofences.title"));
	log.info("Title is correct");
    }

    /**
     * Allows the user to select a project from the project drop down
     * 
     * @param projectName
     *            Project Name
     * @return The current page
     */
    public GeofencesPage selectProject(String projectName) {
	Select droplist = new Select(projectDropdown);

	if (projectName != null) {
	    String project = projectName.toString() + " (ACC)";
	    droplist.selectByVisibleText(project);
	    log.info("Selected: " + project);
	} else {
	    log.error("Could not select a project. Invalid project name.");
	}

	return this;
    }

    /**
     * Allows the user to click the create new geofence group button
     * 
     * @return Add geofence group page
     */
    public AddGeofenceGroupPage clickCreateNewGeofenceGroup() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(createNewGeofenceGroupButton));
    WebDriverWait wait2 = new WebDriverWait(driver, 10);
    wait2.until(ExpectedConditions.invisibilityOfElementLocated(popup));
	driver.findElement(createNewGeofenceGroupButton).click();
	log.info("Create new geofence group button clicked");
	return PageFactory.initElements(driver, AddGeofenceGroupPage.class);
    }

    /**
     * Allows the user to select a particular geofence group
     * 
     * @param geofenceGroupName
     *            The name of the geofence group
     * @return Edit geofence group page
     */
    public EditGeofenceGroupPage selectGeofenceGroup(String geofenceGroupName) {
	WebElement rowElement = getRowElement(geofenceGroupName);
	if (rowElement != null) {
	    rowElement.findElement(By.linkText(geofenceGroupName)).click();
	    log.info("Geofence group: " + geofenceGroupName + " clicked");
	    return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
	}

	log.error("Geofence group '" + geofenceGroupName + "' could not be found");
	throw new AssertionError("Geofence group '" + geofenceGroupName + "' could not be found");
    }

    /**
     * Allows the user to click the actions bar
     * 
     * @param geofenceGroupName
     *            The name of the geofence group
     * @return The current page
     */
    public GeofencesPage clickActionsBar(String geofenceGroupName) {
	
	WebElement rowElement = getRowElement(geofenceGroupName);

	if (rowElement != null) {

	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    WebElement rowActionBar = wait.until(ExpectedConditions.elementToBeClickable(rowElement.findElement(By.className("js-campaign-menu"))));
	    // rowElement.findElement(By.cssSelector(".actions.bdactions")).findElement(By.linkText("")).click();
	    rowActionBar.click();
	    log.info("Actions bar clicked");
	    return this;
	} else {
	    log.error("Action bar for '" + geofenceGroupName + "' could not be found");
	    throw new AssertionError("Action bar for '" + geofenceGroupName + "' could not be found");
	}
    }

    /**
     * Allows the user to click the clone link for a particular geofence group
     * 
     * @param geofenceGroupName
     *            The name of the geofence group
     * @return Clone geofence group page
     */
    public CloneGeofenceGroupPage clickCloneLink(String geofenceGroupName) {
	clickActionsBar(geofenceGroupName);
	driver.findElement(By.linkText("Clone")).click();
	log.info("Clone link for '" + geofenceGroupName + "' clicked");
	return PageFactory.initElements(driver, CloneGeofenceGroupPage.class);
    }

    /**
     * Allows the user to click the delete link for a particular geoefence
     * group. This will remove the geofence group from the project.
     * 
     * @param geofenceGroupName
     *            The name of the geofence group
     * @return The current page
     */
    public GeofencesPage clickDeleteLink(String geofenceGroupName) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='popup-overlay']")));
	clickActionsBar(geofenceGroupName);
	driver.findElement(By.linkText("Delete")).click();
	log.info("Delete link for '" + geofenceGroupName + "' clicked");
	return this;
    }
    
    /**
     * Allows the user to click the delete link for the top geoefence
     * group. This will remove the geofence group from the project.
     * 
     * @return The current page
     */
    public GeofencesPage clickDeleteLink() {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='popup-overlay']")));
    driver.findElement(actionBar).click();
	driver.findElement(By.linkText("Delete")).click();
	log.info("Delete link for the top item clicked");
	return this;
    }

    /**
     * Allows the user to click no in the modal that appears after clicking the
     * delete link
     * 
     * @return The current page
     */
    public GeofencesPage clickNoDeleteModal() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement popup = driver.findElement(By.className("popup"));
	popup.findElement(By.linkText("NO")).click();
	log.info("No button clicked in delete modal");
	return this;
    }

    /**
     * Allows the user to click yes in the modal that appears after clicking the
     * delete link
     * 
     * @return The current page
     */
    public GeofencesPage clickYesDeleteModal() {
	WebElement popup = driver.findElement(By.className("popup"));
	popup.findElement(By.linkText("YES")).click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='popup-content']")));
	log.info("Yes button clicked in delete modal");
	return this;
    }

    /**
     * Allows the user to delete a geofence group
     * 
     * @param geofenceGroupName
     *            The name of the geofence group to be deleted
     * @return The current page
     */
    public GeofencesPage deleteGeofenceGroup(String geofenceGroupName) {
	this.clickDeleteLink(geofenceGroupName);
	this.clickYesDeleteModal();
	return this;
    }
    

    /**
     * Allows the user to delete all geofence groups
     * 
     * @return The current page
     */
    public GeofencesPage deleteAllGeofenceGroups() {
    int numActionBar= driver.findElements(actionBar).size();
    log.info(numActionBar + " - Num of Action bar");
    for (int i=0; i<numActionBar; i++) {
    	this.clickDeleteLink();
    	this.clickYesDeleteModal();
    }
	return this;
    }

    /**
     * @param geofenceGroup
     *            The name of the geofence group
     * @return Returns true if the geofence group is present on the page
     */
    public boolean isGeofenceGroupPresent(String geofenceGroup) {
	return this.isElementPresent(By.linkText(geofenceGroup));
    }

    /**
     * Gets the web element for the row in which the geoefence group name was
     * found
     * 
     * @param geofenceGroupName
     * @return WebElement
     */
    private WebElement getRowElement(String geofenceGroupName) {
	for (WebElement row : tableRows) {
	    List<WebElement> data = row.findElements(By.tagName("td"));
	    for (WebElement cell : data) {
		if (cell.getText().contains(geofenceGroupName)) {
		    WebElement rowElement = cell.findElement(By.xpath(".."));
		    return rowElement;
		}
	    }
	}
	return null;
    }
}
