package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * InviteUserPage is a page object that model the invite user section of the
 * my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class InviteUserPage extends AbstractPageObject {

    /* Web Elements */

    // Email field
    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailTextbox;

    // Invite User button
    @FindBy(id = "formsubmit")
    @CacheLookup
    private WebElement inviteUserButton;
    
    // Billing Div
    @FindBy(id = "div_billing")
    @CacheLookup
    private WebElement billingDiv;
    
    // Database Download Div
    @FindBy(id = "div_ip_database")
    @CacheLookup
    private WebElement databaseDiv;

    /**
     * Constructor for an InviteUserPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public InviteUserPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("inviteuser.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the email textbox
     * 
     * @param email
     *            Email address of the invited user
     * @return The current page
     */
    public InviteUserPage typeEmail(String email) {
	emailTextbox.sendKeys(email);
	log.info("Email entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the email textbox
     * 
     * @return The current page
     */
    public InviteUserPage clearEmail() {
	emailTextbox.clear();
	log.info("Email textbox cleared");
	return this;
    }

    /**
     * Allows the user to click the invite user button. If expecting
     * a failure see {@link #clickInviteExpectingFailure()}
     * 
     * @return User settings page
     */
    public UserSettingsPage clickInvite() {
	inviteUserButton.click();
	log.info("Invite user button clicked");
	return PageFactory.initElements(driver, UserSettingsPage.class);
    }
    
    /**
     * Allows the user to click the invite user button
     * 
     * @return User settings page
     */
    public InviteUserPage clickInviteExpectingFailure() {
	inviteUserButton.click();
	log.info("Invite user button clicked [Expecting Failure]");
	return this;
    }
    
    /**
     * Allows the user to click the view billing checkbox
     * @return The current page
     */
    public InviteUserPage clickViewBillingCheckbox() {
	billingDiv.findElement(By.className("row-label")).click();
	log.info("View billing checkbox clicked");
	return this;
    }
    
    /**
     * Allows the user to click the IP database download checkbox
     * @return The current page
     */
    public InviteUserPage clickDatabaseDownloadCheckbox() {
	databaseDiv.findElement(By.className("row-label")).click();
	log.info("IP database download checkbox clicked");
	return this;
    }
    
    /**
     * Allows the user to click the profile tab
     * @return	Account settings page
     */
    public AccountSettingsPage clickProfileTab() {
	driver.findElement(By.linkText("Profile")).click();
	log.info("Profile tab clicked");
	return PageFactory.initElements(driver, AccountSettingsPage.class);
    }

}
