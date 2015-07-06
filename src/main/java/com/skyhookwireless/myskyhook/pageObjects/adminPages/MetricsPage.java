package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

public class MetricsPage extends AbstractPageObject {
	String metricsHeaderPath = "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div.nav-collapse.collapse > ul > li:nth-child(rowNumber) > a";
	String[] metricsHeader = new String[]{"Access Points", "IPs", "IP HPE", "Devices", "Realm Activity", "SDK Version Metrics"};

	
	
	// Constructor
	public MetricsPage(WebDriver driver) {
		super(driver);
		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.metrics.title"));
		log.info("Title is correct");
	}
	
	public void verifyHeader() {
	String headerPath;
	for (int i=1; i < 7; i++) {
		headerPath = metricsHeaderPath.replace("rowNumber", Integer.toString(i));
		Assert.assertEquals(metricsHeader[i-1] ,driver.findElement(By.cssSelector(headerPath)).getText());
	}
	}

}