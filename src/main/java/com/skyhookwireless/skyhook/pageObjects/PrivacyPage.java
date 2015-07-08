package com.skyhookwireless.skyhook.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Footer class used as a persistent object to model the header of a page object
 * @author Skyhook Wireless
 *
 */
public class PrivacyPage extends AbstractPageObject {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	
	
	/* Web Elements */
	
	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public PrivacyPage(WebDriver driver) {
		super(driver);

		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("privacy.title"));
	}

}