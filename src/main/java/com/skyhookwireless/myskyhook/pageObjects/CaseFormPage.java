package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.enums.ProjectType;
import com.skyhookwireless.myskyhook.enums.Severity;

/**
 * Represents the case form located on the publicsupport and support pages
 * 
 * @author evansapienza
 * 
 */
public class CaseFormPage extends AbstractPageObject {

    /* Web Elements */

    // Subject field
    @FindBy(id = "subject")
    @CacheLookup
    private WebElement subjectTextBox;

    // Prouct field
    @FindBy(id = "product")
    @CacheLookup
    private WebElement productDropdown;

    // Severity field
    @FindBy(id = "severity")
    @CacheLookup
    private WebElement severityDropDown;

    // Description field
    @FindBy(id = "description")
    @CacheLookup
    private WebElement descriptionTextBox;

    // Steps to reproduce field
    @FindBy(id = "steps")
    @CacheLookup
    private WebElement stepsTextBox;

    // Choose file
    @FindBy(id = "attachment-0")
    private WebElement chooseFile;

    // Submit button
    @FindBy(id = "submit")
    @CacheLookup
    private WebElement submitButton;

    // Cancel button
    @FindBy(id = "cancel")
    @CacheLookup
    private WebElement cancelButton;

    /**
     * Constructor for the CaseFormPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public CaseFormPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("publicsupport.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the Subject field
     * 
     * @param subject
     *            String representing the subject title of the case form
     * @return Current Page
     */
    public CaseFormPage typeSubject(String subject) {
	subjectTextBox.sendKeys(subject);
	log.info("Subject entered");
	return this;
    }

    /**
     * Allows the user to clear the Subject field
     * 
     * @return Current Page
     */
    public CaseFormPage clearSubject() {
	subjectTextBox.clear();
	log.info("Subject field cleared");
	return this;
    }

    /**
     * Allows the user to select a product from the dropdown list. If you do not
     * wish to enter a product type enter null for the projectType.
     * 
     * @param projectType
     *            The project type (Precision Location, Hyperlocal IP, etc.)
     * @return Current Page
     */
    public CaseFormPage selectProduct(ProjectType projectType) {
	Select droplist = new Select(productDropdown);
	if (projectType != null)
	    if (projectType == ProjectType.HYP)
		droplist.selectByVisibleText("Context Accelerator — Hyperlocal IP");
	    else
		droplist.selectByVisibleText(projectType.toString());
	else
	    droplist.selectByVisibleText("Unknown or Other");
	log.info("Product selected");
	return this;
    }

    /**
     * Allows the user to select a level severity from the severity drop down
     * 
     * @param severity
     *            The level of severity of the case
     * @return Current Page
     */
    public CaseFormPage selectSeverity(Severity severity) {
	Select droplist = new Select(severityDropDown);

	if (severity != null)
	    droplist.selectByVisibleText(severity.toString());

	log.info("Severity selected");
	return this;
    }

    /**
     * Allows the user to enter a description in the Description field
     * 
     * @param description
     *            The description of the case
     * @return Current Page
     */
    public CaseFormPage typeDescription(String description) {
	descriptionTextBox.sendKeys(description);
	log.info("Description entered");
	return this;
    }

    /**
     * Allows the user to clear the description field
     * 
     * @return Current Page
     */
    public CaseFormPage clearDescription() {
	descriptionTextBox.clear();
	log.info("Description field cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the Steps to reproduce field
     * 
     * @param steps
     *            The steps needed to replicate an error found
     * @return Current Page
     */
    public CaseFormPage typeSteps(String steps) {
	stepsTextBox.sendKeys(steps);
	log.info("Steps entered");
	return this;
    }

    /**
     * Allows the user to clear the steps field
     * 
     * @return Current Page
     */
    public CaseFormPage clearSteps() {
	stepsTextBox.clear();
	log.info("Steps field cleared");
	return this;
    }

    /**
     * Allows the user to upload a file to the support case
     * 
     * @param filePath
     *            The filepath to the file the user wishes to attach
     * @return Current page
     */
    public CaseFormPage uploadFile(String filePath) {
	if (filePath != null) {
	    chooseFile.sendKeys(filePath);
	    log.info("File uploaded");
	}
	return this;
    }

    /**
     * Allows the user to click the Submit button
     * 
     * @return Success Page on Public Support
     */
    public PublicSupportPage clickSubmitPublic() {
	submitButton.click();
	log.info("Submit button clicked");
	return PageFactory.initElements(driver, PublicSupportPage.class);
    }

    /**
     * Allows the user to click the Submit button
     * 
     * @return The support page
     */
    public SupportPage clickSubmit() {
	submitButton.click();
	log.info("Submit button clicked");
	return PageFactory.initElements(driver, SupportPage.class);
    }

    /**
     * Allows the user to click submit with the expectation that the form will
     * not be submitted due to an error
     * 
     * @return Current Page
     */
    public CaseFormPage clickSubmitExpectingFailure() {
	submitButton.click();
	log.info("Submit button clicked [expecting failure]");
	return this;
    }

    /**
     * Allows the user to fill out and submit a public support case
     * 
     * @param subject
     *            The subject title of the case
     * @param project
     *            The type of project the case pertains to (use null for
     *            unknown/other)
     * @param description
     *            The description of the case
     * @param steps
     *            The steps needed to replicate an error found
     * @return Success Page
     */
    public PublicSupportPage createPublicSupportCase(String subject, ProjectType project, String description,
	    String steps) {
	typeSubject(subject);
	selectProduct(project);
	typeDescription(description);
	typeSteps(steps);
	clickSubmitPublic();
	log.info("Case created");
	return PageFactory.initElements(driver, PublicSupportPage.class);
    }

    /**
     * Allows the user to fill out and submit a support case
     * 
     * @param subject
     *            The subject title of the case
     * @param project
     *            The type of project the case pertains to (use null for
     *            unknown/other)
     * @param severity
     *            The severity for the test case (use null to default to low)
     * @param description
     *            The description of the case
     * @param steps
     *            The steps needed to replicate an error found
     * @param filePath
     *            The filepath to the file to be attachment (use null if no file
     *            is to be attched
     * @return The support page
     */
    public SupportPage createSupportCase(String subject, ProjectType project, Severity severity, String description,
	    String steps, String filePath) {
	typeSubject(subject);
	selectProduct(project);
	selectSeverity(severity);
	typeDescription(description);
	typeSteps(steps);
	uploadFile(filePath);
	clickSubmit();
	log.info("Case created");
	return PageFactory.initElements(driver, SupportPage.class);
    }

    /**
     * Allows the user to click the cancel button which will return them to the
     * login page
     * 
     * @return The login page
     */
    public LoginPage clickCancelPublic() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * Allows the user to click the cancel button which will return them to the
     * support page
     * 
     * @return The support page
     */
    public SupportPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, SupportPage.class);
    }

}