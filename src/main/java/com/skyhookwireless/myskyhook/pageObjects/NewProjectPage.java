package com.skyhookwireless.myskyhook.pageObjects;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skyhookwireless.myskyhook.enums.*;
import com.skyhookwireless.myskyhook.pageObjects.resourcePages.CertifiedForWeb_OverviewPage;

/**
 * NewProjectPage is a page object that models the create project flow of the
 * my.skyhook web portal
 * 
 * @author evansapienza
 * 
 */
public class NewProjectPage extends AbstractPageObject {

    /* Web Elements */
    @FindBy(id = "submit-step1")
    private WebElement continueStep1;
    
    // ID
    By domainTextbox = By.id("domain");
    By continueStep2 = By.id("submit-step2");
    By continueStep3 = By.id("submit-step3");
    By continueStep4 = By.id("submit-step4");
    By apiKeyTextbox = By.id("input-api-key-text");
    By returnToDashboardButton = By.id("return_to_dashboard");
    By inviteNewUsersButton = By.id("send-invitation");
    

    // CSS Paths
    By downloadSDK = By.cssSelector(properties.getProperty("newproject.downloadSDK"));
    By integrationGuide = By.cssSelector(properties.getProperty("newproject.integrationGuide"));
    By projectSuccessModal = By.cssSelector(properties.getProperty("newproject.successModal"));


    // XPaths
    By acceleratorButton = By.xpath(properties.getProperty("newproject.selectAccelerator"));
    By hyperlocalButton = By.xpath(properties.getProperty("newproject.selectHyperlocal"));
    By precisionButton = By.xpath(properties.getProperty("newproject.selectPrecision"));
    
    
    By androidPlatformButton = By.xpath(properties.getProperty("newproject.selectAndroidPath"));
    By applePlatformButton = By.xpath(properties.getProperty("newproject.selectApplePath"));
    By iosPlatformButton = By.xpath(properties.getProperty("newproject.selectIOSPath"));
    By linuxPlatformButton = By.xpath(properties.getProperty("newproject.selectLinuxPath"));
    By rpiPlatformButton = By.xpath(properties.getProperty("newproject.selectRaspberryPiPath"));
    By windowsPlatformButton = By.xpath(properties.getProperty("newproject.selectWindowsPath"));

    By projectNameTextbox = By.xpath(properties.getProperty("newproject.projectNamePath2"));
    By descriptionTextbox= By.xpath(properties.getProperty("newproject.projectDescPath"));
    By continueButton = By.xpath(properties.getProperty("newproject.continueButton"));
    By createButton = By.xpath(properties.getProperty("newproject.createProject"));
    
    /**
     * Constructor for a NewProjectPage object
     * 
     * @param driver
     *            The webdriver of the page
     */
    public NewProjectPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("newproject.url")));
	log.info("Title is correct");
    }

    /**
     * Allows the user to enter a string in the project name field
     * 
     * @param projectName
     *            The name of the project
     * @return The current page
     */
    public NewProjectPage typeProjectName(String projectName) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(projectNameTextbox).clear();
	driver.findElement(projectNameTextbox).sendKeys(projectName + Keys.ENTER);
	log.info("Project name entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the project name field
     * 
     * @return The current page
     */
    public NewProjectPage clearProjectName() {
	driver.findElement(projectNameTextbox).clear();
	log.info("Project name field cleared");
	return this;
    }

    /**
     * Allows the user to enter a string in the description textbox
     * 
     * @param projectDescription
     *            The description of the project
     * @return The current page
     */
    public NewProjectPage typeProjectDescription(String projectDescription) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(descriptionTextbox).clear();
	driver.findElement(descriptionTextbox).sendKeys(projectDescription);
	log.info("Project description entered");
	return this;
    }

    /**
     * Allows the user to clear any text in the description textbox
     * 
     * @return The current page
     */
    public NewProjectPage clearProjectDescription() {
	driver.findElement(descriptionTextbox).clear();
	log.info("Project description cleared");
	return this;
    }

    /**
     * Allows the user to select a product for their project. Note: Certified
     * projects can no longer be created.
     * 
     * @param projectType
     *            The type of project (accelerator, precision, etc.)
     * @return The current page
     */
    public NewProjectPage selectProjectType(ProjectType projectType) {
	switch (projectType) {
	case ACC:
	    driver.findElement(acceleratorButton).click();
	    break;

	case HYP:
	    driver.findElement(hyperlocalButton).click();
	    break;

	case PRE:
	    driver.findElement(precisionButton).click();
	    break;
	case CRT:
	    Assert.fail("Certified project are no longer able to be created.");
	    break;
	default:
	    break;
	}
	log.info("Project Type: " + projectType.toString() + " selected");
	return this;
    }

 
    /**
     * Description Page got pulled out
     * Sometimes, the continue button will appear in the final page
     */
    public NewProjectPage clickCreateButton() {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.elementToBeClickable(createButton));
    driver.findElement(createButton).click();
    log.info("Create/Continue button clicked");
    return this;
    }

    
    /**
     * Allows the user to select a platform for step 2
     * 
     * @param platform
     *            The platform to be selected
     * @return The current page
     */
    public NewProjectPage selectPlatform(Platform platform) {
    
    // Need to pause for a few seconds for the icon to load
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		log.error("sleep failed");
	}
    
	switch (platform) {
	case ANDROID:
	    driver.findElement(androidPlatformButton).click();
	    break;

	case APPLE:
	    driver.findElement(applePlatformButton).click();
	    break;

	case IOS:
	    driver.findElement(iosPlatformButton).click();
	    break;

	case LINUX:
	    driver.findElement(linuxPlatformButton).click();
	    break;

	case RASPBERRYPI:
	    driver.findElement(rpiPlatformButton).click();
	    break;

	case WINDOWS:
	    driver.findElement(windowsPlatformButton).click();
	    break;
	}
	driver.findElement(continueButton).click();
	log.info("Platform selected");
	return this;
    }

    /**
     * Allows the user to enter a string in the define domain textbox
     * 
     * @param domain
     *            The domain to be entered
     * @return The current page
     */
    public NewProjectPage typeDomain(String domain) {
	driver.findElement(domainTextbox).sendKeys(domain);
	log.info("Domain entered");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }

    /**
     * Allows the user to click the download sdk icon in step 3 of a certified
     * for web project (downloads a zip)
     * 
     * @return Current page
     */
    public NewProjectPage clickDownloadSDKIcon() {
	driver.findElement(downloadSDK).click();
	log.info("Download SDK icon clicked");
	return this;
    }

    public CertifiedForWeb_OverviewPage clickIntegrationIcon() {
	driver.findElement(integrationGuide).click();
	log.info("Integration icon clicked");
	return PageFactory.initElements(driver, CertifiedForWeb_OverviewPage.class);
    }

    /**
     * Allows the user to click the return to dashboard button
     * 
     * @return Dashboard page
     */
    public DashboardPage clickReturnToDashboardButton() {
	WebElement successModal = driver.findElement(projectSuccessModal);
	WebElement backToDashboard = successModal.findElement(returnToDashboardButton);
	
	WebDriverWait wait = new WebDriverWait(driver, 3);
    wait.until(ExpectedConditions.elementToBeClickable(backToDashboard));
	backToDashboard.click();
	
	// Wait for fade out.
	try {
	    Thread.sleep(3000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	log.info("Return to dashboard button clicked");
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to read the API key
     * 
     * @return Returns the API key of the created project
     */
    public String getAPIKey() {
	return driver.findElement(apiKeyTextbox).getText();
    }

    /**
     * Allows the user to click the invite new users button
     * 
     * @return User settings page
     */
    public UserSettingsPage clickInviteNewUsersButton() {
	driver.findElement(inviteNewUsersButton).click();
	log.info("Invite new users button clicked");
	return PageFactory.initElements(driver, UserSettingsPage.class);
    }

    /**
     * Allows the user to create a precision location project
     * 
     * @param projectName
     *            The name of the project
     * @param description
     *            The description of the project
     * @param platform
     *            The platform of the project
     * @return The current page
     */
    public NewProjectPage createPrecisionProject(String projectName, String description, Platform platform) {
	this.selectProjectType(ProjectType.PRE);
	this.typeProjectName(projectName);
	this.selectPlatform(platform);
	this.clickCreateButton();
	log.info("Precision project " + projectName + " created");
	return this;
    }

    /**
     * Allows the user to create a context accelerator project
     * 
     * @param projectName
     *            The name of the project
     * @param description
     *            The description of the project
     * @param platform
     *            The platform of the project
     * @return The current page
     */
    public NewProjectPage createAcceleratorProject(String projectName, String description, Platform platform) {
	this.selectProjectType(ProjectType.ACC);
	this.typeProjectName(projectName);
	this.selectPlatform(platform);
	this.clickCreateButton();
	log.info("Accelerator project " + projectName + " created");
	return this;
    }

    /**
     * Allows the user to create a hyperlocal IP project
     * 
     * @param projectName
     *            The name of the project
     * @param description
     *            The description of the project
     * @return The current page
     */
    public NewProjectPage createHyperlocalProject(String projectName, String description) {
    this.selectProjectType(ProjectType.HYP);
    this.typeProjectName(projectName);
    this.clickCreateButton();
	log.info("Hyperlocal project " + projectName + " created");
	return this;
    }
}
