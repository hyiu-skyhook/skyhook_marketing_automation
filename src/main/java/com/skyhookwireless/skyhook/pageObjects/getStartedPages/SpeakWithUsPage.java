package com.skyhookwireless.skyhook.pageObjects.getStartedPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.skyhookwireless.skyhook.pageObjects.AbstractPageObject;

/**
 * Footer class used as a persistent object to model the header of a page object
 * @author Skyhook Wireless
 *
 */
public class SpeakWithUsPage extends AbstractPageObject {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	
	private WebDriver driver;
	
	/* Web Elements */


	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public SpeakWithUsPage(WebDriver driver) {
		super(driver);

		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("getStarted.speakWithUs.title"));
	}
}