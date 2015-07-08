package com.skyhookwireless.skyhook.pageObjects.resourcePages;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import com.skyhookwireless.skyhook.pageObjects.*;

public class CertifiedForWeb_OverviewPage extends AbstractPageObject {

	public CertifiedForWeb_OverviewPage(WebDriver driver) {
		
		super(driver);
		
		// Assert URL.
		Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("resources.certified.overviewLink")));
		log.info("URL is correct");
	}
}
