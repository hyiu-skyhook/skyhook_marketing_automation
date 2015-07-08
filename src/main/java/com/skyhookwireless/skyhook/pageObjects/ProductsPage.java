package com.skyhookwireless.skyhook.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Footer class used as a persistent object to model the header of a page object
 * @author Skyhook Wireless
 *
 */
public class ProductsPage extends AbstractPageObject {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	
	
	/* Web Elements */
	@FindBy(linkText="Context Accelerator")
	WebElement contextAcceleratorText;
	
	@FindBy(linkText="Precision Location")
	WebElement precisionLocationText;
	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public ProductsPage(WebDriver driver) {
		super(driver);

		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("products.title"));
	}
	
    /**
     * Verify that the page is currently at the context acceleration section
     * 
     * @return Products page
     */
    public boolean verifyProducts_ContextAccelerator() {
    String textColor= contextAcceleratorText.getCssValue("color");
    log.info("Color is " + textColor);
    if (textColor.equals("rgba(255, 95, 0, 1)"))
    	return true;
    else
    	return false;
    }
    
    /**
     * Verify that the page is currently at the context acceleration section
     * 
     * @return Products page
     */
    public boolean verifyProducts_PrecisionLocation() {
    String textColor= precisionLocationText.getCssValue("color");
    log.info("Color is " + textColor);
    if (textColor.equals("rgba(255, 95, 0, 1)"))
    	return true;
    else
    	return false;
    }
}