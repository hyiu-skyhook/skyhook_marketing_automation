package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

/**
 * CreateVenueSetPage is a page object that models the venue creation section of
 * the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class CreateVenueSetPage extends AbstractPageObject {

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
    
    //search field
    @FindBy(id = "query")
    private WebElement searchTextbox;
    
    // Filter Box
    @FindBy(id = "s2id_category")
    private WebElement searchFilterbox;
    
    // Venue Name (Ind Venue Tab)
    @FindBy(id = "venue_name")
    private WebElement indVenueName;
    
    //Venue Address (Ind Venue Tab)
    @FindBy(id = "venue_addr")
    private WebElement indVenueAddress;
    
    // Search venue category box
    @FindBy(className = "select2-input")
    private WebElement searchVenueCategory;
    
    // Error
    @FindBy(className = "form-errors")
    private WebElement submitError;
    
    // Zoom-in
    @FindBy(className = "leaflet-control-zoom-in")
    private WebElement zoomInButton;
    
    // Zoom-out
    @FindBy(className = "leaflet-control-zoom-out")
    private WebElement zoomOutButton;
    
    // Next Page of search results
    @FindBy(linkText = "next >>")
    private WebElement nextPage;
    
    By yesCancelButton = By.cssSelector(properties.getProperty("geofences.createVenueSet.yesCancelButton"));
    By noCancelButton = By.cssSelector(properties.getProperty("geofences.createVenueSet.noCancelButton"));
    By topResult = By.cssSelector(properties.getProperty("geofences.search.topResult"));
    By removeVenue = By.cssSelector("span.search-remove-venue");
    
    By mapPin = By.xpath("//img[@class='leaflet-marker-icon leaflet-zoom-animated leaflet-clickable']");
    By waitCircle = By.xpath("//div[@class='categories-block  ivenuesection3']/div[contains(@class,'ajax')]");
    By ivAddButton = By.xpath("//form[@id='independent']/div[2]/div/div[2]/table/tbody/tr/td[4]/div/span");
    By ivRemoveButton = By.xpath("//*[@id='independent']/div[3]/div[1]/div[2]/table/tbody/tr/td[4]/div/span");
    By ivVenueSetTextbox = By.xpath("(//input[@id='name'])[3]");
    By ivSearchButton = By.xpath("//form[@id='independent']/div/div/div[4]/button");
    By ivMap = By.xpath("//*[@id='ivenue_map']");
    By searchTab = By.xpath("//div[@id='venue-page']/div[2]/div/div/div[3]");
    By searchVenueSetTextbox = By.xpath("(//input[@id='name'])[2]");
    By ajaxLoader = By.xpath("//div[contains(@class,'ajax-loader')]");
    By searchButton = By.xpath("//a[contains(text(),'Search')]");
    By popupOverlay = By.xpath("//div[@id='popup-overlay']");
  
    
    String ivResultTablePath = "//div[@class='result-list']/table/tbody/tr";
    String ivAddedTablePath = "//div[@class='selection-list']/table/tbody/tr";
    String searchResultTable = "//*[@id='search']/div[4]/div[1]/div[2]/ul";
    String searchAddedTable = "//div[@class='search-selected-block-val']/ul";

    /**
     * The constructor for a CreateVenueSetPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public CreateVenueSetPage(WebDriver driver) {
	super(driver);
	
	Assert.assertEquals(driver.getTitle(), properties.getProperty("geofences.createVenueSet.title"));
	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the name your venue set textbox
     * 
     * @param name
     *            The name of the venue set
     * @return The current page
     */
    public CreateVenueSetPage typeVenueSetName(String name) {
	nameTextbox.sendKeys(name);
	log.info("Venue set name entered");
	return this;
    }
    
    /**
     * Allows the user to enter clear any text in the name your venue set textbox
     * 
     * @return The current page
     */
    public CreateVenueSetPage clearVenueSetName() {
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
    public CreateVenueSetPage clickVenueCheckbox(String venueName) {
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
    public CreateVenueSetPage clickVenueTree(String venueName) {
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
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(popupOverlay)); 
	saveButton.click();
	log.info("Save button clicked");
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }

    /**
     * Allows the user to click the save button when expecting a failure
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickSaveExpectingFailure() {
	saveButton.click();
	log.info("Save button clicked");
	return this;
    }

    /**
     * Allows the user to click the cancel button, then confirm
     * 
     * @return Edit geofence page
     */
    public EditGeofenceGroupPage clickCancelThenConfirm() {
	cancelButton.click();
	driver.findElement(yesCancelButton).click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, EditGeofenceGroupPage.class);
    }
    
    /**
     * Allows the user to click the cancel button, then cancel
     * 
     * @return Edit geofence page
     */
    public CreateVenueSetPage clickCancelThenCancel() {
	cancelButton.click();
	driver.findElement(noCancelButton).click();
	log.info("Cancel button clicked");
	return this;
    }
    
    // The following are functions for the "Search" tab
    
    /**
     * Allows the user to click the search tab
     * 
     * 
     * @return The current page, but under a different tab
     */
    public CreateVenueSetPage clickSearchTab() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(searchTab).click();
	return this;
    }
    
    /**
     * Allows the user to enter a string into the venue search box
     * 
     * 
     * @return The current page
     */
    public CreateVenueSetPage typeVenueToSearch(String name) {
	searchTextbox.sendKeys(name);
	return this;
    }
    
    /**
     * Allows the user to clear the venue search box
     * 
     * 
     * @return The current page
     */
    public CreateVenueSetPage clearVenueToSearch() {
	searchTextbox.clear();
	return this;
    }
    
    /**
     * Allows the user to click the venue category filter and enter a category name
     * 
     * 
     * @return The current page
     */
    
    public CreateVenueSetPage filterVenueToSearch(String categoryName) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    searchFilterbox.click();
    searchVenueCategory.sendKeys(categoryName + Keys.RETURN);
	return this;
    }
    
    /**
     * Allows the user to enter a string into the venue set name
     * (only usable under the search tab)
     * 
     * @return The current page
     */  
    
    public CreateVenueSetPage typeVenueSetNameForSearchTab(String name) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(searchVenueSetTextbox).sendKeys(name);
    log.info("Venue set name entered");
    return this;
    }
   

    /**
     * Allows the user to click the search button
     * 
     * 
     * @return The current page
     */
    
    public CreateVenueSetPage clickSearchButton() {
    for (int i = 0; i < 2; i++) {
    	driver.findElement(searchButton).click();
    }
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
    return this;  	
    }
    
    /**
     * Allows the user to add the top-most venue in the left-hand panel 
     * (returned search results)
     * 
     * @return The current page
     */
    
    public CreateVenueSetPage clickAddTopVenueButton() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement addVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("search-add-venue")));
    addVenuebtn.click();
    log.info("Added the top venue result");
    return this;
    }
    
    /**
     * Allows the user to remove the top-most venue in the left-hand panel 
     * (returned search results)
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickRemoveTopVenueButton() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement removeVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("search-remove-venue")));
    removeVenuebtn.click();
    log.info("Removed the top venue result");
    return this;
    }
    
    /**
     * Allows the user to add a venue from the returned search results.
     * Afterwards, check that the added venue has been greyed out 
     * and added to the 'venues added' box
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickAddVenueButton(int number) {
    String pathToResult = searchResultTable + "/li[" + number + "]" ;
    int numOfVenuesAddedBefore = driver.findElements(By.xpath(searchAddedTable + "/li")).size();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement addVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathToResult + "/span/span[2]/span/span")));
    addVenuebtn.click();
    Assert.assertTrue(driver.findElement(By.xpath(pathToResult)).getAttribute("class").equals("selected"));
    Assert.assertEquals(driver.findElements(By.xpath(searchAddedTable + "/li")).size(), numOfVenuesAddedBefore + 1);
    log.info("Added the " + number + "th venue result");
    return this;
    }
    
    /**
     * Allows the user to remove the n-th item specified by the input
     * (selected search results)
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickRemoveVenueButton(int number) {
    int numOfVenuesAddedBefore = driver.findElements(By.xpath(searchAddedTable + "/li")).size();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement removeVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(
    	    		By.xpath(searchAddedTable + "/li[" + number + "]/span/span[2]/span/span")));
    removeVenuebtn.click();
    Assert.assertEquals(driver.findElements(By.xpath(searchAddedTable + "/li")).size(), numOfVenuesAddedBefore - 1);
    log.info("Removed the " + number + "th venue result");
    return this;
    }
    
    // The following are functions for the 'Independent Venues' tab
    
    /**
     * Allows the user to click the Independent Venues tab
     * 
     * 
     * @return The current page, but under a different tab
     */
    public CreateVenueSetPage clickIndVenuesTab() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//div[5]")).click();
	return this;
    }
    
    /**
     * Allows the user to move to the next page via the page navigation
     * @return
     */
    public CreateVenueSetPage clickNextPage() {
    nextPage.click();
    return this;
    }
    
    /**
     * Allows the user to enter a string into the venue set name
     * (only usable under the search tab)
     * 
     * @return The current page
     */  
    
    public CreateVenueSetPage typeVenueSetNameForIndVenueTab(String name) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(ivVenueSetTextbox).sendKeys(name);
    log.info("Venue set name entered");
    return this;
    }
    
    /**
     * Allows the user to enter a string into the ind venue name box
     * 
     * 
     * @return The current page
     */
    public CreateVenueSetPage typeIndVenueName(String name) {
	indVenueName.sendKeys(name);
	return this;
    }
    /**
     * Allows the user to clear the venue search box
     * 
     * 
     * @return The current page
     */
    public CreateVenueSetPage clearIndVenueName() {
	indVenueName.clear();
	return this;
    }
    
    
    /**
     * Allows the user to enter a string into the ind venue addres box
     * 
     * 
     * @return The current page
     */
    public CreateVenueSetPage typeIndVenueAddress(String name) {
	indVenueAddress.sendKeys(name);
	return this;
    }
    
    
    /**
     * Allows the user to enter a string into the ind venue address box
     * 
     * @return The current page
     */
    public CreateVenueSetPage clearIndVenueAddress() {
	indVenueAddress.clear();
	return this;
    }
    
    /**
     * Allows the user to click the search button
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickIndVenueSearchButton() {
    driver.findElement(ivSearchButton).click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(waitCircle));
    return this;
    }
    
    /**
     * Allows the user to add the top-most independent venue in the results panel
     * (returned search results)
     * The sleep time was added in order to wait for the map to finish loading
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickMapTopIndVenue() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement mapVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("map it")));	
    mapVenuebtn.click();
    log.info("Mapped the top venue");
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {}
    return this;
    }
    
    /**
     * Allows the user to add the top-most independent venue in the results panel
     * (returned search results)
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickAddTopIndVenue() {
    WebElement topResult = driver.findElement(By.xpath(ivResultTablePath + "[1]"));
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement addVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(ivAddButton));	
    addVenuebtn.click();
    Assert.assertTrue(topResult.getAttribute("class").equals("selected"));
    log.info("Added the top venue result");
    return this;
    }
    
    /**
     * Allows the user to remove the top-most independent venue in the results panel
     * (returned search results)
     * 
     * @return The current page
     */
    public CreateVenueSetPage clickRemoveTopIndVenue(){
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement removeVenuebtn = wait.until(ExpectedConditions.elementToBeClickable(ivRemoveButton));	
    removeVenuebtn.click();
    Assert.assertFalse(this.isElementPresent(By.xpath(ivAddedTablePath + "[1]")));
    log.info("Removed the top venue result");
    return this;	
    }
   
    /**
     * Return the address in the rowNum specified
     * @param rowNum
     * @return
     */
    public String getAddress(int rowNum) {
    String outputAddress= null;
    if ( this.isElementPresent(By.xpath(ivResultTablePath )))
    	outputAddress = driver.findElement(By.xpath(ivResultTablePath + "[" + rowNum + "]/td[2]")).getText();
    return outputAddress;	
    }
    
    /**
     * Return the top most result for the search tab
     * @return
     */
   public String getTopSearchResult() {
   return driver.findElement(topResult).getText();
   }
    
   /**
    * Return the error on the current page
    * @return
    */
   public String getError() {
   return this.submitError.getText();	
   }
   
   /**
    * take a screenshot
    * @param picName
    */
   public void getMapScreenshot(String picName) {
   try {
		this.getScreenshot(driver.findElement(ivMap), picName);
   } catch (IOException e) {
		log.error("Screen capture failed");
   }
   }
    
   /**
    * Verify the zooming functionality for the map
    */
   public void verifyMap() {
   zoomInButton.click();
   zoomInButton.click();
   zoomOutButton.click();
   zoomOutButton.click();
   }
   
   /**
     * Used to verify whether a pin has been placed on the map
     * after the user clicks "map it"
     * @return true if a blue map pin is found
     */
   public boolean isMapUpdated() {
    if ( this.isElementPresent(mapPin) )
    	return true;
    else
    	return false;
    	
    }
    
   
  
    
}
