package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * CaseDetailsPage is a page object that represents a case details page on the
 * my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class CaseDetailsPage extends AbstractPageObject {

    /* Web Elements */

    // Container
    @FindBy(className = "page-container")
    private WebElement pageContainer;

    // Choose file
    @FindBy(id = "attachment-0")
    private WebElement chooseFile;

    // Submit attachment button
    @FindBy(id = "submit_attachment")
    private WebElement submitAttachmentButton;

    // Comments field
    @FindBy(id = "comment_body")
    private WebElement commentsTextBox;

    // Submit button
    @FindBy(id = "submit")
    private WebElement submitButton;

    // Cancel button
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    
    // Product div
    @FindBy(id = "div_product")
    private WebElement productDiv;

    // Severity div
    @FindBy(id = "div_severity")
    private WebElement severityDiv;

    // Subject div
    @FindBy(id = "div_subject")
    private WebElement subjectDiv;
    
    // Description div
    @FindBy(id = "div_description")
    private WebElement descriptionDiv;

    // Steps div
    @FindBy(id = "div_steps")
    private WebElement stepsDiv;

    /**
     * Constructor for the CaseDetailsPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public CaseDetailsPage(WebDriver driver) {
	super(driver);

	Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("support.casedetails.url")));
	log.info("Title is correct");
    }

    /**
     * Allows the user to upload a file to the support case
     * 
     * @param filePath
     *            The file path to the file to be uploade to the case
     * @return Current page
     */
    public CaseDetailsPage uploadFile(String filePath) {
	chooseFile.sendKeys(filePath);
	log.info("File uploaded");
	return this;
    }

    /**
     * Allows the user to click the submit attachment button
     * 
     * @return Current page
     */
    public CaseDetailsPage clickSubmitAttachment() {
	submitAttachmentButton.click();
	log.info("Submit Attachment button pressed");
	return this;
    }

    /**
     * Allows the user to click the submit attachment button with the
     * expectation of failing
     * 
     * @return Current page
     */
    public CaseDetailsPage clickSubmitAttachmentExpectingFailure() {
	submitAttachmentButton.click();
	log.info("Submit Attachment button pressed [expecting failure]");
	return this;
    }

    /**
     * Allows the user to enter a string in the 'Add comment' text box
     * 
     * @param comment
     *            The comment to be added to the case
     * @return Current page
     */
    public CaseDetailsPage typeComment(String comment) {
	commentsTextBox.sendKeys(comment);
	log.info("Comment entered");
	return this;
    }

    /**
     * Allows the user to clear the comment text box
     * 
     * @return Current page
     */
    public CaseDetailsPage clearComment() {
	commentsTextBox.clear();
	log.info("Comment entered");
	return this;
    }

    /**
     * Allows the user to click the submit button. If expecting failure use
     * {@link #clickSubmitExpectingFailure()}
     * 
     * @return Current page
     * @see #clickSubmitExpectingFailure()
     */
    public CaseDetailsPage clickSubmit() {
	submitButton.click();
	log.info("Submit button clicked");
	return PageFactory.initElements(driver, CaseDetailsPage.class);
    }

    /**
     * Allows the user to click the submit button. Should only be used when
     * expecting failure
     * 
     * @return Current page
     */
    public CaseDetailsPage clickSubmitExpectingFailure() {
	submitButton.click();
	log.info("Submit button clicked [expecting failure]");
	return this;
    }

    /**
     * Allows the user to click the cancel button
     * 
     * @return Current page
     */
    public SupportPage clickCancel() {
	cancelButton.click();
	log.info("Cancel button clicked");
	return PageFactory.initElements(driver, SupportPage.class);
    }
    
    /**
     * @return Returns the text from the product field
     */
    public String getProduct() {
	return productDiv.findElement(By.className("note-text")).getText();
    }
    
    /**
     * @return Returns the text from the severity field
     */
    public String getSeverity() {
	return severityDiv.findElement(By.className("note-text")).getText();
    }
    
    /**
     * @return Returns the text from the subject field
     */
    public String getSubject() {
	return subjectDiv.findElement(By.className("note-text")).getText();
    }
    
    /**
     * @return Returns the text from the description field
     */
    public String getDescription() {
	return descriptionDiv.findElement(By.className("note-text")).getText();
    }
    
    /**
     * @return Returns the text from the steps field
     */
    public String getSteps() {
	return stepsDiv.findElement(By.className("note-text")).getText();
    }

    /**
     * @return Returns true if the case comments section is empty
     */
    public boolean isCommentsEmpty() {
	if (this.isElementPresent(By.className("case-comments"))) {
	    if (driver.findElement(By.className("case-comments")).getText().contains("No comments for this case."))
		;
	    return true;
	} else
	    return false;
    }

    /**
     * @param comment
     *            Comment to be verified
     * @return Returns true if the comment is located in the case comments section
     */
    public boolean isCommentPresent(String comment) {
	if (this.isElementPresent(By.className("case-comments"))) {
	    if (driver.findElement(By.className("case-comments")).getText().contains(comment))
		;
	    return true;
	} else
	    return false;
    }
}
