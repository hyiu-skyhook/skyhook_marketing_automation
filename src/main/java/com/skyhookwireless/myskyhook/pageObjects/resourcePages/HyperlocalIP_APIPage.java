package com.skyhookwireless.myskyhook.pageObjects.resourcePages;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import com.skyhookwireless.myskyhook.pageObjects.*;

public class HyperlocalIP_APIPage extends AbstractPageObject {

	public HyperlocalIP_APIPage(WebDriver driver) {
		
		super(driver);
		
		// Assert URL.
		Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("resources.hyperlocal.apiLink")));
		log.info("URL is correct");
	}
}
