package com.skyhookwireless.myskyhook.pageObjects.geofencePages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;


public class CustomFilterPage extends AbstractPageObject {
	

	/* Web Elements */

    // Account search field
    @FindBy(id = "map-wrapper")
    private WebElement map;
    
    // Save button
    @FindBy(id = "submit_form")
    private WebElement saveButton;
    
    
	
    By filterNameTextbox = By.xpath("//form[@id='title-filter']/input");
	By locationSearchTextbox = By.xpath("//input[@placeholder='Location Search']");
	
	By drawPolygonButton = By.cssSelector("#map > div.leaflet-control-container > div.leaflet-top.leaflet-left > div.leaflet-draw.leaflet-control > div:nth-child(1) > div > a");
	By deleteLastPoint = By.cssSelector("#map > div.leaflet-control-container > div.leaflet-top.leaflet-left > div.leaflet-draw.leaflet-control > div:nth-child(1) > ul > li:nth-child(1) > a");
	By cancelDrawing = By.cssSelector("#map > div.leaflet-control-container > div.leaflet-top.leaflet-left > div.leaflet-draw.leaflet-control > div:nth-child(1) > ul > li:nth-child(2) > a");
	By cancelDrawingRect = By.cssSelector("#map > div.leaflet-control-container > div.leaflet-top.leaflet-left > div.leaflet-draw.leaflet-control > div:nth-child(1) > ul > li > a");
	By editDrawing = By.cssSelector("#map > div.leaflet-control-container > div.leaflet-top.leaflet-left > div.leaflet-draw.leaflet-control > div:nth-child(2) > div > a.leaflet-draw-edit-edit");
	By removeDrawing = By.cssSelector("#map > div.leaflet-control-container > div.leaflet-top.leaflet-left > div.leaflet-draw.leaflet-control > div:nth-child(2) > div > a.leaflet-draw-edit-remove");
	
    /**
     * The constructor for a DefineLocationParamtersPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public CustomFilterPage(WebDriver driver) {
	super(driver);

	Assert.assertTrue(driver.getCurrentUrl().contains("campaigns/customgeofilter"));
	log.info("URL is correct");
    }
    
    /**
     * Click on the draw a polygon icon in the left-hand panel
     * @return
     */
    public CustomFilterPage clickDrawPolygon() {
    driver.findElement(drawPolygonButton).click();
    return this;
    }
    
    /**
     * Click on delete last point option
     * @return
     */
    public CustomFilterPage clickDeleteLastPoint() {
    driver.findElement(deleteLastPoint).click();
    return this;
    }
    
    /**
     * Cancel drawing (polygon)
     * @return
     */
    public CustomFilterPage clickCancelDrawing() {
    driver.findElement(cancelDrawing).click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelDrawing));
    return this;    	
    }
    
    /**
     * Cancel drawing (rectangle)
     * @return
     */
    public CustomFilterPage clickCancelDrawingRect() {
    driver.findElement(cancelDrawingRect).click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelDrawingRect));
    return this;    	
    }
    
    /**
     * Click the edit polygon button
     * @return
     */
    public CustomFilterPage clickEditDrawing() {
    driver.findElement(editDrawing).click();
    return this;   
    }
    
    /**
     * Click the save button from the expanded panel
     * that appears after clicking edit
     * @return
     */
    public CustomFilterPage clickSaveModal() {
    driver.findElement(By.linkText("Save")).click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Save")));
    return this;
    }
    
    /**
     * Click the remove polygon button
     * @return
     */
    public CustomFilterPage clickRemoveDrawing() {
    driver.findElement(removeDrawing).click();
    return this;   
    }
    
    /**
     * Click the save button
     * @return
     */
    public DefineLocationParametersPage clickSaveButton() {
    saveButton.click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(drawPolygonButton));
	return PageFactory.initElements(driver, DefineLocationParametersPage.class);
    }
    
    /**
     * Change the filter name from the default value to 
     * the user-provided input name
     * @param filterName
     * @return
     */
    public CustomFilterPage typeFilterName(String filterName) {
    driver.findElement(filterNameTextbox).clear();
    driver.findElement(filterNameTextbox).sendKeys(filterName);
    return this;
    }
    
    /**
     * Based on the location specified by the input address, 
     * mapbox will zoom in on that location
     * @param inputAddress - the name of the address that the map should zoom in on
     * @return
     */
    public CustomFilterPage typeSearchLocation(String inputAddress) {
    driver.findElement(locationSearchTextbox).sendKeys(inputAddress);
    driver.findElement(locationSearchTextbox).sendKeys(Keys.ENTER);
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		log.info("Map may have failed to load completely");
	}
    return this;
    }
    
    /**
     * Click on the specified location on the map
     * based on the relative positions away from the
     * draw polygon button on the left
     * @param x - the horizontal coordinate offset
     * @param y - the vertical coordinate offset
     */
    public void clickMapAt(int x, int y) {
    Actions builder = new Actions(driver);
    builder.moveToElement(driver.findElement(drawPolygonButton), x, y).perform();
    builder.click().perform();
    }
    
    /**
     * Clicks on a point relative the to draw polygon button, 
     * then drags the point across the map
     * based on the range given (applies to both x and y)
     * Used for editing existing polygon
     * @param x
     * @param y
     * @throws InterruptedException 
     */
    public void clickAndDragMapPoint(int x, int y, int xrange, int yrange) {
    Actions builder = new Actions(driver);
    builder.moveToElement(driver.findElement(drawPolygonButton), x, y).perform();
    builder.click().perform();
    builder.clickAndHold().perform();
    builder.moveByOffset(xrange, yrange).perform();;
    builder.moveByOffset(0, 5).perform();
    builder.release().perform();
    }
    
    /**
     * Clicks on a point relative the to draw polygon button, 
     * then drags the point across the map
     * based on the range given (applies to both x and y)
     * Customized for drawing rectangle polygon
     * @param x
     * @param y
     * @throws InterruptedException 
     */
    public void clickAndDragMapPointRect(int x, int y, int xrange, int yrange) {
    Actions builder = new Actions(driver);
    builder.moveToElement(driver.findElement(drawPolygonButton), x, y).perform();
    builder.clickAndHold().perform();
    builder.moveByOffset(xrange, yrange).perform();;
    builder.release().perform();
    }
    
    /**
     * Get screenshot of the specified element
     * @param inputElement
     */
    public void getMapScreenshot(String picName) {
    try {
		this.getScreenshot(map, picName);
	} catch (IOException e) {
		log.error("Screen capture failed");
	}
    }
    
    /**
     * Verify if the cancel button is toggled when the draw
     * polygon icon is clicked
     * @return
     */
    public boolean isCancelButtonToggled() {
    return driver.findElement(cancelDrawingRect).isDisplayed();
    }
    
}