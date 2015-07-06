package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ViewAllActionsPage extends AbstractAdminPageObject {

    public ViewAllActionsPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.allactions.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to click the All Actions button
     * 
     * @return View account users page
     */
    public ViewAllActionsPage clickAllActions() {
	driver.findElement(By.linkText("All Actions")).click();
	log.info("All Actions button clicked");
	return PageFactory.initElements(driver, ViewAllActionsPage.class);
    }

    /**
     * Allows the user to click the Back to Account button
     * 
     * @return View back to account page
     */
    public ViewAllActionsPage clickBackToAccount() {
	driver.findElement(By.linkText("Back to Account")).click();
	log.info("Back to Account button clicked");
	return PageFactory.initElements(driver, ViewAllActionsPage.class);
    }

}
