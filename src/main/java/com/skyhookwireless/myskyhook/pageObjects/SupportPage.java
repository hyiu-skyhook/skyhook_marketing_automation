package com.skyhookwireless.myskyhook.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * SupportPage is a page object representing the support page on the my.syhook
 * web portal
 * 
 * @author evansapienza
 * 
 */
public class SupportPage extends AbstractPageObject {

    /* Web Elements */

    // Container
    @FindBy(className = "page-container")
    private WebElement pageContainer;

    /**
     * Constructor for the SupportPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public SupportPage(WebDriver driver) {

	super(driver);

	Assert.assertEquals(driver.getTitle(), properties.getProperty("support.title"));
	log.info("title is correct");
    }

    /**
     * Allows the user to click the 'Log a new Case' button
     * 
     * @return Case form
     */
    public CaseFormPage clickLogNewCase() {
	pageContainer.findElement(By.linkText("LOG A NEW CASE")).click();
	log.info("Log new case button clicked");
	return PageFactory.initElements(driver, CaseFormPage.class);
    }

    /**
     * Allows the user to select an already made ticket
     * 
     * @param subject
     *            The subject title of the support ticket the user wishes to
     *            select
     * @return Case details page
     */
    public CaseDetailsPage selectTicket(String subject) {
	pageContainer.findElement(By.linkText(subject)).click();
	log.info(String.format("Ticket: %s clicked", subject));
	return PageFactory.initElements(driver, CaseDetailsPage.class);
    }

    /**
     * @param subject
     *            Subject line of the support ticket
     * @return True if the support ticket exists
     */
    public boolean isTicketPresent(String subject) {
	return this.isElementPresent(By.partialLinkText(subject));
    }

}
