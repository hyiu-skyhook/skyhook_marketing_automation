package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * ForgotPasswordPage is a page object representing the restor password page of
 * the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class ForgotPasswordPage extends AbstractPageObject {

    /* Web Elements */

    // Page container
    @FindBy(className = "page-container")
    @CacheLookup
    private WebElement pageContainer;

    // Email field
    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailTextbox;

    // Change Password Button
    @FindBy(id = "submit")
    @CacheLookup
    private WebElement changePasswordButton;

    // Email field
    @FindBy(id = "cancel")
    @CacheLookup
    private WebElement cancelButton;

    // CSS Path
    private By resendButton = By.cssSelector(properties.getProperty("forgotpassword.resendButtonPath"));
    private By errorMessageDiv = By.cssSelector(properties.getProperty("forgotpassword.errorMessageDiv"));

    /**
     * Constructor for a ForgotPasswordPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public ForgotPasswordPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("forgotpassword.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to click the sign in link
     * 
     * @return The login page
     */
    public LoginPage clickSignIn() {
	pageContainer.findElement(By.linkText("sign in")).click();;
	log.info("Sign in link clicked");
	return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Allows the user to type a string in the email field
     * 
     * @param email
     *            Email address of a user
     * @return The current page
     */
    public ForgotPasswordPage typeEmail(String email) {
	emailTextbox.sendKeys(email);
	log.info("email entered");
	return this;
    }

    /**
     * Allows the user to clear the email field
     * 
     * @return The current page
     */
    public ForgotPasswordPage clearEmail() {
	emailTextbox.clear();
	log.info("email field cleared");
	return this;
    }

    /**
     * Allows the user to submit an email address
     * 
     * @return The current page
     */
    public ForgotPasswordPage clickChangePassword() {
	changePasswordButton.click();
	log.info("Change password button clicked");
	return this;
    }

    /**
     * Allows the user to click the cancel button
     * 
     * @return The login page
     */
    public LoginPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Allows the user to click the resend button
     * 
     * @return The current page
     */
    public ForgotPasswordPage clickResend() {
	driver.findElement(resendButton).click();
	log.info("Resend button clicked");
	return this;
    }
    
    /**
     * @return	Error message on the login page if present
     */
    public String getErrorMessage() {
	if (this.isElementPresent(errorMessageDiv))
	    return driver.findElement(errorMessageDiv).getText();
	else
	    return null;
    }
}
