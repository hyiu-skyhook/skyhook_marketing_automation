package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * PublicSupportPage is a page object used to model the support section of the
 * my.skyhook web portal for users that are not logged in.
 * 
 * @author evansapienza
 * 
 */
public class PublicSupportPage extends AbstractPageObject {

    /* Web Elements */

    // Container
    @FindBy(className = "page-container")
    private WebElement pageContainer;

    // Email field
    @FindBy(id = "supplied_email")
    @CacheLookup
    private WebElement emailTextBox;

    // First Name field
    @FindBy(id = "supplied_first_name")
    @CacheLookup
    private WebElement firstNameTextBox;
    
    // Last Name field
    @FindBy(id = "supplied_last_name")
    @CacheLookup
    private WebElement lastNameTextBox;

    // Submit login form
    @FindBy(id = "submit")
    @CacheLookup
    private WebElement continueButton;

    /**
     * Constructor for the public support page
     * 
     * @param driver	The webdriver of the page
     */
    public PublicSupportPage(WebDriver driver) {

	super(driver);

	// Assert title.
	try {
	Assert.assertEquals(driver.getTitle(), properties.getProperty("publicsupport.title"));
	} catch (AssertionError ae){
	    Assert.assertEquals(driver.getTitle(), properties.getProperty("publicsupport.successTitle"));
	}

	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the email textbox
     * 
     * @param email
     *            String representing an email address
     * @return The current page
     */
    public PublicSupportPage typeEmail(String email) {
	emailTextBox.sendKeys(email);
	log.info("Email entered");
	return this;
    }

    /**
     * Allows the user to clear the email textbox
     * 
     * @return The current page
     */
    public PublicSupportPage clearEmail() {
	emailTextBox.clear();
	log.info("Email field cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the name textbox
     * 
     * @param name
     *            String representing a name
     * @return The current page
     */
    public PublicSupportPage typeFirstName(String name) {
	firstNameTextBox.sendKeys(name);
	log.info("First Name entered");
	return this;
    }
    
    /**
     * Allows the user to enter a string in the name textbox
     * 
     * @param name
     *            String representing a name
     * @return The current page
     */
    public PublicSupportPage typeLastName(String name) {
	lastNameTextBox.sendKeys(name);
	log.info("Last Name entered");
	return this;
    }

    /**
     * Allows the user to clear the first name textbox
     * 
     * @return The current page
     */
    public PublicSupportPage clearFirstName() {
	firstNameTextBox.clear();
	log.info("First Name field cleared");
	return this;
    }
    
    /**
     * Allows the user to clear the last name textbox
     * 
     * @return The current page
     */
    public PublicSupportPage clearLastName() {
	lastNameTextBox.clear();
	log.info("Last Name field cleared");
	return this;
    }
    
    
    
    /**
     * Allows the user to click the continue button. If
     * expecting failure use {@link #clickContinueExpectingFailure()}
     * @return Case details form page
     * @see #clickContinueExpectingFailure()
     */
    public CaseFormPage clickContinue() {
	continueButton.click();
	log.info("Continue button clicked");
	return PageFactory.initElements(driver, CaseFormPage.class);
    }
    
    /**
     * Allows the user to click the continue button with the
     * expectation of failure
     * @return	Current page
     */
    public PublicSupportPage clickContinueExpectingFailure() {
	continueButton.click();
	log.info("Continue button clicked [expecting failure]");
	return this;
    }

    /**
     * Allows the user to enter an email and name and to start writing a case.
     * If you expect a failure use
     * {@link #startCaseExpectingFailure(String, String)}.
     * 
     * @param email
     *            String representing an email address
     * @param name
     *            String representing a name
     * @return Case details form page
     * @see #startCaseExpectingFailure(String, String)
     */
    public CaseFormPage startCase(String email, String firstName, String lastName) {
	typeEmail(email);
	typeFirstName(firstName);
	typeLastName(lastName);
	continueButton.click();
	log.info(String.format("Case started, email: %s name: %s %s", email, firstName, lastName));
	return PageFactory.initElements(driver, CaseFormPage.class);
    }

    /**
     * Allows the user to enter an email and name that should fail. This method
     * should only be used when expecting a failure, in this case the failure
     * should be that the email is already associated with an account.
     * 
     * @param email
     *            String representing an email address
     * @param name
     *            String representing a name
     * @return The current page
     */
    public PublicSupportPage startCaseExpectingFailure(String email, String firstName, String lastName) {
	typeEmail(email);
	typeFirstName(firstName);
	typeLastName(lastName);
	continueButton.click();
	log.info(String.format("Case failed, email: %s name: %s %s", email, firstName, lastName));
	return this;
    }

    /**
     * Allows the user to click the log in link on the public support page
     * 
     * @return Login Page
     */
    public LoginPage clickLogin() {
	pageContainer.findElement(By.linkText("log in")).click();
	log.info("Log in link clicked");
	return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Allows the user to click the 'Create new account' link after they have
     * successfully submitted a support case
     * 
     * @return Signup Page
     */
    public SignUpPage clickCreateNewAccount() {
	pageContainer.findElement(By.linkText("Create a new account")).click();
	log.info("Create new account link clicked");
	return PageFactory.initElements(driver, SignUpPage.class);
    }
    
    /**
     * Allows the user to click the 'Open a new support case' page
     * @return	New instance of the public support page
     */
    public PublicSupportPage clickOpenNewSupportCase() {
	pageContainer.findElement(By.linkText("Open a new support case")).click();
	log.info("Open a new support case link clicked");
	return PageFactory.initElements(driver, PublicSupportPage.class);
    }

}
