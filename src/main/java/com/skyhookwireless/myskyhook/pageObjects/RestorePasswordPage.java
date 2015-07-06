package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * RestorePasswordPage is a page object that models the restore password page on
 * the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class RestorePasswordPage extends AbstractPageObject {

    /* Web Elements */

    // Password field
    @FindBy(id = "password")
    @CacheLookup
    private WebElement passwordTextbox;

    // Repeat Password field
    @FindBy(id = "password2")
    @CacheLookup
    private WebElement password2Textbox;

    // Set Password Button
    @FindBy(id = "submit")
    @CacheLookup
    private WebElement setPasswordButton;

    public RestorePasswordPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("restorepassword.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the password textbox
     * 
     * @param password
     *            The password to be entered
     * @return The current page
     */
    public RestorePasswordPage typePassword(String password) {
	passwordTextbox.sendKeys(password);
	log.info("Password enetered");
	return this;
    }

    /**
     * Allows the user to clear any text in the password textbox
     * 
     * @return The current page
     */
    public RestorePasswordPage clearPassword() {
	passwordTextbox.clear();
	log.info("Password textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the repeat password textbox
     * 
     * @param password
     *            The password to be entered
     * @return The current page
     */
    public RestorePasswordPage typeRepeatPassword(String password) {
	password2Textbox.sendKeys(password);
	log.info("Repeated password enetered");
	return this;
    }

    /**
     * Allows the user to clear any text in the repeat password textbox
     * 
     * @return The current page
     */
    public RestorePasswordPage clearRepeatPassword() {
	password2Textbox.clear();
	log.info("Repeat password textbox cleared");
	return this;
    }

    /**
     * Allows the user to click the set password button when expecting a failure
     * 
     * @return The current page
     */
    public RestorePasswordPage clickSetPasswordExpectingFailure() {
	setPasswordButton.click();
	log.info("Set password button pressed [Expecting failure]");
	return this;
    }

    /**
     * Allows the user to click the set password button with the expectation
     * that the dashboard page should be loaded. Use when a user already has
     * projects, otherwise use {@link #clickSetPasswordLoadNewProject()}. If
     * expecting a failure use {@link #clickSetPasswordExpectingFailure()}.
     * 
     * @return Dashboard page
     * @see #clickSetPasswordLoadNewProject()
     * @see #clickSetPasswordExpectingFailure()
     */
    public DashboardPage clickSetPasswordLoadDashboard() {
	setPasswordButton.click();
	log.info("Set password button pressed");
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the set password button with the expectation
     * that the new project page should be loaded. Use when a user does not
     * already have projects, otherwise use
     * {@link #clickSetPasswordLoadDashboard()}. If expecting a failure use
     * {@link #clickSetPasswordExpectingFailure()}.
     * 
     * @return Dashboard page
     * @see #clickSetPasswordLoadDashboard()
     * @see #clickSetPasswordExpectingFailure()
     */
    public NewProjectPage clickSetPasswordLoadNewProject() {
	setPasswordButton.click();
	log.info("Set password button pressed");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }
}
