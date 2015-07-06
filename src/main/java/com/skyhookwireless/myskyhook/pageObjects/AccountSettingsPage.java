package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * AccountSettingsPage is a page object that represents the edit profile section
 * of the my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class AccountSettingsPage extends AbstractPageObject {

    /* Web Elements */

    // First name field
    @FindBy(id = "first_name")
    @CacheLookup
    private WebElement firstNameTextbox;

    // Last name field
    @FindBy(id = "last_name")
    @CacheLookup
    private WebElement lastNameTextbox;

    // Email field
    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailTextbox;

    // Password field
    @FindBy(id = "password")
    @CacheLookup
    private WebElement passwordTextbox;

    // Confirm Password field
    @FindBy(id = "password2")
    @CacheLookup
    private WebElement confirmPasswordTextbox;

    // Company field
    @FindBy(id = "name")
    @CacheLookup
    private WebElement companyTextbox;

    // Phone field
    @FindBy(id = "phone")
    @CacheLookup
    private WebElement phoneTextbox;

    // Save Button
    @FindBy(id = "submit_form")
    @CacheLookup
    private WebElement saveButton;

    // Cancel Button
    @FindBy(id = "cancel")
    @CacheLookup
    private WebElement cancelButton;

    /**
     * Constructor for an AccountSettingsPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public AccountSettingsPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("accountsettings.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the first name textbox
     * 
     * @param firstName
     *            First name of user being created
     * @return The current page
     */
    public AccountSettingsPage typeFirstName(String firstName) {
	firstNameTextbox.clear();
	firstNameTextbox.sendKeys(firstName);
	log.info("First name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the first name textbox
     * 
     * @return The current page
     */
    public AccountSettingsPage clearFirstName() {
	firstNameTextbox.clear();
	log.info("First name textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the last name textbox
     * 
     * @param lastName
     *            Last name of user being created
     * @return The current page
     */
    public AccountSettingsPage typeLastName(String lastName) {
	lastNameTextbox.clear();
	lastNameTextbox.sendKeys(lastName);
	log.info("Last name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the last name textbox
     * 
     * @return The current page
     */
    public AccountSettingsPage clearLastName() {
	lastNameTextbox.clear();
	log.info("Last name textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the password textbox
     * 
     * @param password
     *            Password of user being created
     * @return The current page
     */
    public AccountSettingsPage typePassword(String password) {
	passwordTextbox.clear();
	passwordTextbox.sendKeys(password);
	log.info("Password entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the password textbox
     * 
     * @return The current page
     */
    public AccountSettingsPage clearPassword() {
	passwordTextbox.clear();
	log.info("Password textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the confirm password textbox
     * 
     * @param password
     *            Confirmed password of user being created
     * @return The current page
     */
    public AccountSettingsPage typeConfirmPassword(String password) {
	confirmPasswordTextbox.clear();
	confirmPasswordTextbox.sendKeys(password);
	log.info("Confirm password entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the confirm password textbox
     * 
     * @return The current page
     */
    public AccountSettingsPage clearConfirmPassword() {
	confirmPasswordTextbox.clear();
	log.info("Confirm password textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the phone textbox
     * 
     * @param phone
     *            Phone number of user being created
     * @return The current page
     */
    public AccountSettingsPage typePhone(String phone) {
	phoneTextbox.clear();
	phoneTextbox.sendKeys(phone);
	log.info("Phone number entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the phone textbox
     * 
     * @return The current page
     */
    public AccountSettingsPage clearPhone() {
	phoneTextbox.clear();
	log.info("Phone textbox cleared");
	return this;
    }

    /**
     * Allows the user to click the save button and brings them back to the new
     * project page (should only be used for users that do not have any
     * projects). Other wise use {@link #clickSave()}
     * 
     * @return New project page
     */
    public NewProjectPage clickSaveNewUser() {
	saveButton.click();
	log.info("Save button clicked");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }

    /**
     * Allows the user to click the save button and brings them to the dashboard
     * page
     * 
     * @return Dashboard page
     */
    public DashboardPage clickSave() {
	saveButton.click();
	log.info("Save button clicked");
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the cancel button and brings them back to the
     * new project page (should only be used for users that do not have any
     * projects). Other wise use {@link #clickCancel()}
     * 
     * @return New project page
     */
    public NewProjectPage clickCancelNewUser() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }

    /**
     * Allows the user to click the cancel button and brings them to the
     * dashboard page
     * 
     * @return Dashboard page
     */
    public DashboardPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the permissions tab
     * 
     * @return User settings page
     */
    public UserSettingsPage clickPermissionsTab() {
	driver.findElement(By.linkText("Permissions")).click();
	log.info("Permissions tab clicked");
	return PageFactory.initElements(driver, UserSettingsPage.class);
    }

    
    /**
     * Allows the user to click the security tab
     * 
     * @return Security settings page
     */
    public SecuritySettingsPage clickSecurityTab() {
	driver.findElement(By.linkText("Security")).click();
	log.info("Security tab clicked");
	return PageFactory.initElements(driver, SecuritySettingsPage.class);
    }
    
    /**
     * @return Returns true if the email textbox is read only
     */
    public boolean isEmailReadOnly() {
	if (this.emailTextbox.getAttribute("readonly").toString().equals("true"))
	    return true;
	else
	    return false;
    }

    /**
     * @return Returns true if the company textbox is read only
     */
    public boolean isCompanyReadOnly() {
	if (this.companyTextbox.getAttribute("readonly").toString().equals("true"))
	    return true;
	else
	    return false;
    }

    /**
     * @return Returns the text from the first name textbox
     */
    public String getFirstName() {
	return this.firstNameTextbox.getAttribute("value");
    }
    
    /**
     * @return Returns the text from the last name textbox
     */
    public String getLastName() {
	return this.lastNameTextbox.getAttribute("value");
    }
    
    /**
     * @return Returns the text from the phone textbox
     */
    public String getPhone() {
	return this.phoneTextbox.getAttribute("value");
    }
    
    /**
     * @return Returns the text from the company textbox
     */
    public String getCompany() {
    return this.companyTextbox.getAttribute("value");
    }
}
