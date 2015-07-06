package com.skyhookwireless.myskyhook.pageObjects;

import java.util.ArrayList;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skyhookwireless.myskyhook.pageObjects.adminPages.AccountsPage;

/**
 * LoginPage is a page object used to model the user interface of the login page
 * of the my.skyhook web portal.
 * 
 * @author evansapienza
 */
public class LoginPage extends AbstractPageObject {

    /* Web Elements */

    // Email field
    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailTextBox;

    // Submit login form
    @FindBy(id = "submit")
    private WebElement submitLogin;
    
    @FindBy(id = "code")
    private WebElement verificationCodeTextBox;

    // Xpath Paths.
    private By passwordTextBox = By.xpath(properties.getProperty("login.passwordPath"));

    // CSS Paths.
    private By signUpPath = By.cssSelector(properties.getProperty("login.signUpPath"));
    private By browseAnonymouslyPath = By.cssSelector(properties.getProperty("login.browseAnonymouslyPath"));
    private By forgotPasswordpath = By.cssSelector(properties.getProperty("login.forgotPasswordPath"));
    private By errorMessageDiv = By.cssSelector(properties.getProperty("login.errorMessageDiv"));

    /**
     * Constructor for LoginPage object
     * 
     * @param driver
     *            The webdriver used to navigate the page
     */
    public LoginPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("login.title"));

	log.info("Title is correct");
    }

    /**
     * Allows for the user to type a string (email address) into the email text
     * field located on the home page
     * 
     * @param email
     *            Email address of user
     * @return Current page
     */
    public LoginPage typeEmail(String email) {
	emailTextBox.sendKeys(email);
	log.info("Email successfully entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the email text field
     * 
     * @return Current page
     */
    public LoginPage clearEmail() {
	emailTextBox.clear();
	return this;
    }

    /**
     * Allows for the user to type a string (password) into the password text
     * field
     * 
     * @param password
     *            Password of user
     * @return Current page
     */
    public LoginPage typePassword(String password) {
	driver.findElement(passwordTextBox).sendKeys(password);
	log.info("Password successfully entered");
	return this;
    }

    /**
     * Allows for the user to type alpha-numeric string
     * into the code verification field
     * 
     * @param code
     *           verification code
     * @return Current Page
     */
    public LoginPage typeVerificationCode(String code) {
    verificationCodeTextBox.clear();
    verificationCodeTextBox.sendKeys(code);
    log.info("Verification code successfully entered");
    return this;
    }
    
    /**
     * Allows the user to clear any text in the password text field
     * 
     * @return Current page
     */
    public LoginPage clearPassword() {
	driver.findElement(passwordTextBox).clear();
	log.info("Password texbox cleared");
	return this;
    }

    /**
     * Allows the user to click the login button with the expectation that the
     * new project page will be loaded
     * 
     * @return New project page
     */
    public NewProjectPage clickLoginNewProject() {
	submitLogin.click();
	log.info("Login button clicked [loading new project page]");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }

    /**
     * Allows the user to click the login button with the expectation that the
     * dashboard page will be loaded
     * 
     * @return Dashboard page
     */
    public DashboardPage clickLoginDashboard() {
	submitLogin.click();
	log.info("Login button clicked [loading dashboard page]");
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the login button with the expectation that the
     * accounts page from the admin dashboard will be loaded
     * 
     * @return Accounts page
     */
    public AccountsPage clickLoginAdmin() {
	submitLogin.click();
	log.info("Login button clicked [loading admin accounts page]");
	return PageFactory.initElements(driver, AccountsPage.class);
    }

    /**
     * Allows the user to submit the login form knowing that an error will
     * occur. This method will allow for testing without loading a new page.
     * 
     * @return Current page
     */
    public LoginPage clickLoginExpectingFailure() {
	submitLogin.click();
	log.info("Submit button clicked [Expecting Failure]");
	return this;
    }
    
    /**
     * Allows the user to submit the login form knowing that the multi-factor
     * authentication page will appear next.
     * @return
     */
    public LoginPage clickLoginMultiAuth() {
    submitLogin.click();
	log.info("Submit button clicked [Expecting Multi Auth]");
    return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Allows the user to sign in to a valid account that already has at least
     * one project.
     * <p>
     * The method returns a {@link DashboardPage} object. This object represents
     * the page that has been loaded. Only use this method for valid
     * credentials, if you are testing incorrect credentials use the
     * <code>{@link #signInUserExpectingFailure(String, String)}</code> method
     * to test for errors since a new page object will not be loaded.
     * 
     * @param userName
     *            The username of the user (email address)
     * @param password
     *            The password of the user
     * @return Dashboard page
     * @see #signInUserExpectingFailure(String, String)
     */
    public DashboardPage signInUserWithProject(String userName, String password) {
	this.typeEmail(userName);
	this.typePassword(password);
	return this.clickLoginDashboard();
    }

    /**
     * Allows the user to sign in to a valid account that has no projects
     * created.
     * <p>
     * The method returns a {@link NewProjectPage} object. This object
     * represents the page that has been loaded. Only use this method for valid
     * credentials, if you are testing incorrect credentials use the
     * <code>{@link #signInUserExpectingFailure(String, String)}</code> method
     * to test for errors since a new page object will not be loaded.
     * 
     * @param userName
     *            The username of the user (email address)
     * @param password
     *            The password of the user
     * @return New project page
     * @see #signInUserExpectingFailure(String, String)
     */
    public NewProjectPage signInUserNoProject(String userName, String password) {
	this.typeEmail(userName);
	this.typePassword(password);
	return this.clickLoginNewProject();
    }

    /**
     * Allows the user to sign in to the portal admin dashboard
     * 
     * @param userName
     *            The username of the admin
     * @param password
     *            The password of the admin
     * @return New accounts page on the admin dashboard
     */
    public AccountsPage signInAdmin(String userName, String password) {
	this.typeEmail(userName);
	this.typePassword(password);
	return this.clickLoginAdmin();
    }
    
    /**
     * Allows the user to sign in expecting multi auth
     * 
     * @param userName
     *            The username of the admin
     * @param password
     *            The password of the admin
     * @return New accounts page on the admin dashboard
     */
    public LoginPage signInUserMultiAuth(String userName, String password) {
    this.typeEmail(userName);
    this.typePassword(password);
    return this.clickLoginMultiAuth();
    }

    /**
     * Allows the user to submit invalid credentials without a new page loading
     * 
     * @param userName
     *            String representing the username
     * @param password
     *            String representing the password
     * @return Current page
     */
    public LoginPage signInUserExpectingFailure(String userName, String password) {
	this.typeEmail(userName);
	this.typePassword(password);
	clickLoginExpectingFailure();
	log.info("Credentials submitted [Expecting Failure]");
	return this;
    }

    /**
     * Allows the user to click the 'I forgot my password' link
     * 
     * @return Forgot password page
     */
    public ForgotPasswordPage clickForgotMyPassword() {
	driver.findElement(forgotPasswordpath).click();
	log.info("Forgot password element found");
	return PageFactory.initElements(this.driver, ForgotPasswordPage.class);
    }

    /**
     * Allows the user to click the sign up button, bringing them to the sign up
     * page
     * 
     * @return Sign up page
     */
    public SignUpPage clickSignUp() {
	driver.findElement(signUpPath).click();
	log.info("Signup page loading");
	return PageFactory.initElements(this.driver, SignUpPage.class);
    }

    /**
     * Allows the user to click the developer resources link to load the
     * developer resources page
     * 
     * @return Resources page
     */
    public void clickBrowseDeveloperResources() {
	driver.findElement(browseAnonymouslyPath).click();
	log.info("Resources page loading");
	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	try {
	    Thread.sleep(3000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Assert.assertEquals(driver.getCurrentUrl(), properties.getProperty("header.resources"));
	driver.close();
	driver.switchTo().window(tabs.get(0));
    }

    /**
     * Gets the text entered in the email textbox
     * 
     * @return Text located in email textbox
     */
    public String getEmail() {
	return emailTextBox.getAttribute("value");
    }

    /**
     * Gets the text entered in the password textbox
     * 
     * @return Text located in password textbox
     */
    public String getPassword() {
	return driver.findElement(passwordTextBox).getAttribute("value");
    }

    /**
     * @return Error message on the login page if present
     */
    public String getErrorMessage() {
	if (this.isElementPresent(errorMessageDiv))
	    return driver.findElement(errorMessageDiv).getText();
	else
	    return null;
    }

    /**
     * @return Returns true if the login button is present
     */
    public boolean isLoginButtonDisplayed() {
	return this.submitLogin.isDisplayed();
    }

    /**
     * @return Returns true if the forgot password link is present
     */
    public boolean isForgotPasswordLinkDisplayed() {
	return driver.findElement(forgotPasswordpath).isDisplayed();
    }
}
