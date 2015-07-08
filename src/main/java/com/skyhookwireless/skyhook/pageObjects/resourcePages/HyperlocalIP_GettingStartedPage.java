package com.skyhookwireless.skyhook.pageObjects.resourcePages;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import com.skyhookwireless.skyhook.pageObjects.*;

public class HyperlocalIP_GettingStartedPage extends AbstractPageObject {

    public HyperlocalIP_GettingStartedPage(WebDriver driver) {

	super(driver);

	// Assert URL.
	Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("resources.hyperlocal.gettingStartedLink")));
	log.info("URL is correct");

    }
}
