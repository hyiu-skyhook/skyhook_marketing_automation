package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ViewAccountPage extends AbstractAdminPageObject {

    public ViewAccountPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.accounts.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to click the All Actions button
     * 
     * @return View account users page
     */
    public ViewAccountUsersPage clickAllActions() {
	driver.findElement(By.linkText("ALL ACTIONS")).click();
	log.info("All Actions button clicked");
	return PageFactory.initElements(driver, ViewAccountUsersPage.class);
    }

    /**
     * Allows the user to click the Back to Account button
     * 
     * @return View back to account page
     */
    public ViewAccountUsersPage clickBackToAccount() {
	driver.findElement(By.linkText("BACK TO ACCOUNT")).click();
	log.info("Back to Account button clicked");
	return PageFactory.initElements(driver, ViewAccountUsersPage.class);
    }

    /**
     * Allows the user to click the Account Users button
     * 
     * @return View account users page
     */
    public ViewAccountUsersPage clickAccountUsers() {
	driver.findElement(By.linkText("ACCOUNT USERS")).click();
	log.info("Account Users button clicked");
	return PageFactory.initElements(driver, ViewAccountUsersPage.class);
    }

}
