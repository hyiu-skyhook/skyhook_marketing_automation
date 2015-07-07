package com.skyhookwireless.skyhook.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Footer class used as a persistent object to model the header of a page object
 * @author Skyhook Wireless
 *
 */
public class Footer {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	
	private WebDriver driver;
	
	/* Web Elements */
	// Footer block
	@FindBy(className="footer")
	private WebElement footerBlock; // Limits element searching to exclusively the footer.

	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public Footer(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Allows the user to click the 'Resources' link in the footer of the page
	 * @return	Resources page
	 */
	public ResourcesPage clickResources() {
		footerBlock.findElement(By.linkText("Resources")).click();
		log.info("Resources link clicked");
		return PageFactory.initElements(this.driver, ResourcesPage.class);
	}
	
	
}
