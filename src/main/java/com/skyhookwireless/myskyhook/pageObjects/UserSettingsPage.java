package com.skyhookwireless.myskyhook.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * UserSettingsPage is a page object that represents the user settings section
 * of the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class UserSettingsPage extends AbstractPageObject {

    /* Web Elements */

    // Table rows
    @FindBy(tagName = "tr")
    @CacheLookup
    private List<WebElement> tableRows;

    /**
     * Constructor for a UserSettingsPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public UserSettingsPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("usersettings.title"));

	log.info("Title is correct");
    }
    
    

    /**
     * Allows the user to click the invite user button
     * 
     * @return Invite user page
     */
    public InviteUserPage clickInviteUser() {
	driver.findElement(By.linkText("INVITE USER")).click();
	log.info("Invite user button clicked");
	return PageFactory.initElements(driver, InviteUserPage.class);
    }

    /**
     * Allows the user to edit the permissions for a particular user
     * 
     * @param name
     *            The name of the account user to have their permissions edited
     * @return An edit permissions modal
     */
    public SetPermissionsModal clickSetPermissions(String name) {
	boolean hit = false;
	for (WebElement row : tableRows) {
	    List<WebElement> data = row.findElements(By.tagName("td"));
	    for (WebElement cell : data) {
		cell.getText();

		if (hit) {
		    cell.findElement(By.linkText("Set permissions")).click();
		    log.info("Set permissions link clicked");
		    return PageFactory.initElements(driver, SetPermissionsModal.class);
		}

		if (cell.getText().contains(name)) {
		    hit = true;
		}
	    }
	}

	log.error("Name: " + name + " could not be found");
	throw new AssertionError("Name: " + name + " could not be found");
    }

    /**
     * @param name
     *            Full name of account user
     * @return Returns true if the user is the account master
     */
    public boolean isAccountMaster(String name) {
	String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();
	if (bodyText.contains(name.toLowerCase() + " (account master)"))
	    return true;
	else
	    return false;
    }

}
