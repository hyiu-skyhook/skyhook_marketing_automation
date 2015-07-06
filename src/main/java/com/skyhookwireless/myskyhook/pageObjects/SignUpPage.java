package com.skyhookwireless.myskyhook.pageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.enums.Audience;
import com.skyhookwireless.myskyhook.enums.JobFunction;
import com.skyhookwireless.myskyhook.enums.JobLevel;
import com.skyhookwireless.myskyhook.enums.Sector;

/**
 * SignUpPage is a page object that represents the sign up flow for the
 * my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class SignUpPage extends AbstractPageObject {

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

    // Audience Drop down
    @FindBy(id = "audience")
    @CacheLookup
    private WebElement audienceDropdown;
    
    @FindBy(id = "sub_segment")
    @CacheLookup
    private WebElement sectorDropdown;
    
    // Job function field
    @FindBy(id = "job_function")
    private WebElement jobFunctionDropdown;
    
    // Job level field
    @FindBy(id = "job_level")
    private WebElement jobLevelDropdown;

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

    // Accept License Checkbox
    @FindBy(id = "accepted_license_id")
    @CacheLookup
    private WebElement acceptLicenseCheckbox;

    // Finish Button
    @FindBy(id = "submit_form")
    @CacheLookup
    private WebElement finishButton;

    /**
     * Constructor for a SignUpPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public SignUpPage(WebDriver driver) {
	super(driver);

	// Assert title.
	try {
	    Assert.assertEquals(driver.getTitle(), properties.getProperty("signup.title"));
	}
	catch (AssertionError ae ){
	    Assert.assertEquals(driver.getTitle(), properties.getProperty("inviteSignup.title"));
	}

	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the first name textbox
     * 
     * @param firstName
     *            First name of user being created
     * @return The current page
     */
    public SignUpPage typeFirstName(String firstName) {
	firstNameTextbox.sendKeys(firstName);
	log.info("First name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the first name textbox
     * 
     * @return The current page
     */
    public SignUpPage clearFirstName() {
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
    public SignUpPage typeLastName(String lastName) {
	lastNameTextbox.sendKeys(lastName);
	log.info("Last name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the last name textbox
     * 
     * @return The current page
     */
    public SignUpPage clearLastName() {
	lastNameTextbox.clear();
	log.info("Last name textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the email textbox
     * 
     * @param email
     *            Email of user being created
     * @return The current page
     */
    public SignUpPage typeEmail(String email) {
	emailTextbox.sendKeys(email);
	log.info("Email entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the email textbox
     * 
     * @return The current page
     */
    public SignUpPage clearEmail() {
	emailTextbox.clear();
	log.info("Email textbox cleared");
	return this;
    }

    /**
     * Allows the user to select an audience from the audience drop down. If the
     * audience parameter is null 'Other' will be selected.
     * 
     * @param audience
     *            The audience the user wishes to selected
     * @return The current page
     */
    public SignUpPage selectAudience(Audience audience) {
	Select droplist = new Select(audienceDropdown);
	if (audience != null)
	    droplist.selectByVisibleText(audience.toString());
	else
	    droplist.selectByVisibleText(Audience.OTHER.toString());
	log.info("Audience selected");
	return this;
    }
    
    /**
     * Allows the user to select a sector from the sector drop down. If the
     * sector parameter is null 'Other' will be selected.
     * 
     * @param sector
     *            The sector the user wishes to selected
     * @return The current page
     */
    public SignUpPage selectSector(Sector sector) {
	Select droplist = new Select(sectorDropdown);
	if (sector != null)
	    droplist.selectByVisibleText(sector.toString());
	log.info("Sector selected");
	return this;
    }

    /**
     * Allows the user to select a job function from the sector drop down. If the
     * sector parameter is null 'Other' will be selected.
     * 
     * @param jf
     *            The job function the user wishes to selected
     * @return The current page
     */
    public SignUpPage selectJobFunction(JobFunction jf) {
    Select droplist = new Select(jobFunctionDropdown);
    if (jf != null)
    	droplist.selectByVisibleText(jf.toString());
    else
	    droplist.selectByVisibleText(Audience.OTHER.toString());
    log.info("Sector selected");
    return this;
    }
    
    /**
     * Allows the user to select a job function from the sector drop down. If the
     * sector parameter is null 'Other' will be selected.
     * 
     * @param jf
     *            The job function the user wishes to selected
     * @return The current page
     */
    public SignUpPage selectJobLevel(JobLevel jl) {
    Select droplist = new Select(jobLevelDropdown);
    if (jl != null)
    	droplist.selectByVisibleText(jl.toString());
    else
	    droplist.selectByVisibleText(Audience.OTHER.toString());
    log.info("Sector selected");
    return this;
    }
    
    /**
     * Allows the user to enter a string in the password textbox
     * 
     * @param password
     *            Password of user being created
     * @return The current page
     */
    public SignUpPage typePassword(String password) {
	passwordTextbox.sendKeys(password);
	log.info("Password entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the password textbox
     * 
     * @return The current page
     */
    public SignUpPage clearPassword() {
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
    public SignUpPage typeConfirmPassword(String password) {
	confirmPasswordTextbox.sendKeys(password);
	log.info("Confirm password entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the confirm password textbox
     * 
     * @return The current page
     */
    public SignUpPage clearConfirmPassword() {
	confirmPasswordTextbox.clear();
	log.info("Confirm password textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the company textbox
     * 
     * @param company
     *            Company of user being created
     * @return The current page
     */
    public SignUpPage typeCompany(String company) {
	companyTextbox.sendKeys(company);
	log.info("Company entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the company textbox
     * 
     * @return The current page
     */
    public SignUpPage clearCompany() {
	companyTextbox.clear();
	log.info("Company textbox cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the phone textbox
     * 
     * @param phone
     *            Phone number of user being created
     * @return The current page
     */
    public SignUpPage typePhone(String phone) {
	phoneTextbox.sendKeys(phone);
	log.info("Phone number entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the phone textbox
     * 
     * @return The current page
     */
    public SignUpPage clearPhone() {
	phoneTextbox.clear();
	log.info("Phone textbox cleared");
	return this;
    }

    /**
     * Allows the user to click the accept license checkbox
     * 
     * @return The current page
     */
    public SignUpPage clickAcceptLicenseCheckbox() {
	acceptLicenseCheckbox.click();
	log.info("Accept license checkbox clicked");
	return this;
    }

    /**
     * Allows the user to click the finish button. If expecting a failure use
     * {@link #clickFinishExpectingFailure()}
     * 
     * @return New project page
     * @see #clickFinishExpectingFailure()
     */
    public NewProjectPage clickFinish() {
	finishButton.click();
	log.info("Finish button clicked");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }
    
    /**
     * Allows the user to click the finish button and loads the dashboard. If expecting a failure use
     * {@link #clickFinishExpectingFailure()}
     * 
     * @return Dashboard page
     * @see #clickFinishExpectingFailure()
     */
    public DashboardPage clickFinishDashboard() {
	finishButton.click();
	log.info("Finish button clicked");
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the finish button when expecting a failure
     * 
     * @return The current page
     */
    public SignUpPage clickFinishExpectingFailure() {
	finishButton.click();
	log.info("Finish button clicked [Expecting Failure]");
	return this;
    }

    /**
     * Allows the user to register a new user on my.skyhook
     * 
     * @param firstName
     *            The first name of the new user
     * @param lastName
     *            The last name of the new user
     * @param email
     *            The email of the new user
     * @param audience
     *            The audience selected by the new user
     * @param password
     *            The password of the new user
     * @param confirmPassword
     *            Password confirmation
     * @param company
     *            The company of the new user
     * @param phone
     *            The phone number of the new user
     * @return New project page
     */
    public NewProjectPage registerUser(String firstName, String lastName, String email, Audience audience, Sector sector, String password,
	    String confirmPassword, String company, JobFunction jobFunction, JobLevel jobLevel, String phone) {
	typeFirstName(firstName);
	typeLastName(lastName);
	typeEmail(email);
	selectAudience(audience);
	selectSector(sector);
	typePassword(password);
	typeConfirmPassword(confirmPassword);
	typeCompany(company);
	selectJobFunction(jobFunction);
	selectJobLevel(jobLevel);
	typePhone(phone);
	clickAcceptLicenseCheckbox();
	log.info("New user created: " + email + "password: " + password);
	return clickFinish();
    }
    
    /**
     * Allows the user to register a new user on my.skyhook with
     * the expectation of failure
     * 
     * @param firstName
     *            The first name of the new user
     * @param lastName
     *            The last name of the new user
     * @param email
     *            The email of the new user
     * @param audience
     *            The audience selected by the new user
     * @param password
     *            The password of the new user
     * @param confirmPassword
     *            Password confirmation
     * @param company
     *            The company of the new user
     * @param phone
     *            The phone number of the new user
     * @return The current page
     */
    public SignUpPage registerUserExpectingFailure(String firstName, String lastName, String email, Audience audience, String password,
	    String confirmPassword, String company, String phone) {
	typeFirstName(firstName);
	typeLastName(lastName);
	typeEmail(email);
	selectAudience(audience);
	typePassword(password);
	typeConfirmPassword(confirmPassword);
	typeCompany(company);
	typePhone(phone);
	clickAcceptLicenseCheckbox();
	clickFinishExpectingFailure();
	return this;
    }
    
    /**
     * @return	Returns true if the TOS link is present
     */
    public boolean isTOSPresent() {
	return this.isElementPresent(By.linkText("Skyhook terms and service"));
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
    
   
    public void verifyHotlink(String fullLink) {
    
    // Compare first name in link with text box value
    Pattern FirstName = 	Pattern.compile("(first_name=)(\\w+)(&)");
    Matcher firstNameMatch = FirstName.matcher(fullLink);
    if (firstNameMatch.find()) 
    	Assert.assertEquals(firstNameTextbox.getAttribute("value"), firstNameMatch.group(2));
    else 
    	log.info("first name not found in link");
    
    // Compare last name in link with text box value
    Pattern LastName = 	Pattern.compile("(last_name=)(\\w+)(&)");
    Matcher lastNameMatch = LastName.matcher(fullLink);
    if (lastNameMatch.find()) 
    	Assert.assertEquals(lastNameTextbox.getAttribute("value"), lastNameMatch.group(2));
    else 
    	log.info("last name not found in link");
    
    // Compare email in link with text box value
    Pattern Email = 	Pattern.compile("(email=)([\\w@.]+)(&)");
    Matcher emailMatch = Email.matcher(fullLink);
    if (emailMatch.find()) 
    	Assert.assertEquals(emailTextbox.getAttribute("value"), emailMatch.group(2));
    else 
    	log.info("email not found in link");
    
    }

}
