package com.skyhookwireless.skyhook.pageObjects;

import java.util.ArrayList;
import java.util.List;

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
public class LandingPage extends AbstractPageObject {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	/* Web Elements */


	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public LandingPage(WebDriver driver) {
		super(driver);

		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("landing.title"));
	}
	
	public String[] getRibbonIcons() {
		String[] iconArray = new String[4];
		List<WebElement> wbList = new ArrayList<WebElement>();
		wbList = driver.findElements(By.xpath(
				"//*[contains(@id,'hs_cos_wrapper_module')]/div/div[@class='social-ribbon']/li/a"));
		iconArray[0]= wbList.get(0).getAttribute("href");
		iconArray[1]= wbList.get(1).getAttribute("href");
		iconArray[2]= wbList.get(2).getAttribute("href");
		iconArray[3]= wbList.get(3).getAttribute("href");
		return iconArray;
	}
	
}