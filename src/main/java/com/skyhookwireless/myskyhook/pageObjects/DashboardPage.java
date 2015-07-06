package com.skyhookwireless.myskyhook.pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationFramework.TC_PORTAL_06;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.skyhookwireless.myskyhook.enums.ProjectType;
import com.skyhookwireless.myskyhook.pageObjects.adminPages.UsersPage;

public class DashboardPage extends AbstractPageObject {

    /* Web Elements */

    // Project Container
    @FindBy(id = "s2id_app_dropdown")
    private WebElement projectContainer;

    // Project Drop down
    @FindBy(id = "app_dropdown")
    private WebElement projectDropdown;

    // API Key textbox
    @FindBy(id = "js-api-key")
    private WebElement apiKeyTextbox;

    // API Key textbox
    @FindBy(id = "js-api-key")
    private WebElement domTextbox;
    
    // Container
    @FindBy(className = "page-container")
    private WebElement pageContainer;

    // Pencil Edit Button
    @FindBy(className = "icon-pencil")
    @CacheLookup
    private WebElement editButton;

    
    //Project Contain searchbox
    @FindBy(xpath = "//div[@id='select2-drop']/div[@class='select2-search']/input[contains(@class,'select2-input')]")
    private WebElement projectSearchBox;
    
    
    // CSS Paths
    private By projectNameTextbox = By.cssSelector(properties.getProperty("dashboard.projectNameTextbox"));
    private By createNewProjectButton = By.cssSelector(properties.getProperty("dashboard.createNewProjectButtonPath"));
    private By geofencesButton = By.xpath(properties.getProperty("dashboard.geofencesButtonPath"));
    private By analyticsButton = By.xpath(properties.getProperty("dashboard.analyticsButtonPath"));
    private By domainTextbox = By.cssSelector(properties.getProperty("dashboard.domainTextbox"));
    private By resourceLinks = By.cssSelector(properties.getProperty("dashboard.resourcesLinks"));
    private By downloadLinks = By.cssSelector(properties.getProperty("dashboard.downloadLinks"));
    private By demoAppYesButton = By.cssSelector(properties.getProperty("dashboard.demoapp.yesButton"));
    private By demoAppNoButton = By.cssSelector(properties.getProperty("dashboard.demoapp.noButton"));
    private By demoAppCancelButton = By.cssSelector(properties.getProperty("dashboard.demoapp.cancelButton"));
    private By backToDashboardButton = By.cssSelector(properties.getProperty("newproject.backToDashboard"));
    private By projTitle = By.cssSelector(properties.getProperty("dashboard.activeproject.name"));
    
    private String settingsGear = "//a[@href='/profile/edit']";
    
    public DashboardPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("myskyhook.title"));
	log.info("Title is correct");
    }

    /**
     * Allows the user to select a project from the project drop down
     * 
     * @param projectName
     *            Project Name
     * @param projectType
     *            Project Type
     * @return The current page
     */
    public DashboardPage selectProject(String projectName, ProjectType projectType) {
	Select droplist = new Select(projectDropdown);

	String projectTypeName = null;
	switch (projectType) {
	case ACC:
	    projectTypeName = "ACC";
	    break;
	case CRT:
	    projectTypeName = "CRT";
	    break;
	case HYP:
	    projectTypeName = "HYP";
	    break;
	case PRE:
	    projectTypeName = "PRE";
	    break;
	}

	if (projectName != null && projectTypeName != null) {

	    if (projectType == ProjectType.CRT) {
		droplist.selectByVisibleText(projectName + " (" + projectTypeName + ") ");
		return PageFactory.initElements(driver, DashboardPage.class);
	    }

	    List<WebElement> options = droplist.getOptions();
	    for (WebElement option : options) {
		String optionText = option.getText();
		String regExp;
		if (projectName == "Demo App")
		regExp = projectName + " " + "[0-9]{1,2}" + " [(]" + projectTypeName + "[)] " + "[0-9A-Za-z_-]{4}?";
		else
		regExp = projectName + " [(]" + projectTypeName + "[)] " + "[0-9A-Za-z_-]{4}?";
		
		if (optionText.matches(regExp)) {
		    droplist.selectByVisibleText(option.getText());
		    log.info("Selected: " + optionText);
		    return PageFactory.initElements(driver, DashboardPage.class);
		}
	    }
	    log.error("Project: " + projectName + " (" + projectTypeName + ")" + " could not be found.");
	    throw new NoSuchElementException("Project: " + projectName + " (" + projectTypeName + ")"
		    + " could not be found.");
	} else {
	    log.error("Could not select a project. Invalid project name or type");
	}
	return PageFactory.initElements(driver, DashboardPage.class);
    }
    
    /**
     * Allows the user to search for a project using the search box after expanding projects
     * 
     * @param projectName
     *            Project Name
     * @return The current page
     */
    public DashboardPage searchProject(String projectName) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	projectContainer.click(); 
	projectSearchBox.sendKeys(projectName + Keys.ENTER);
	
	return PageFactory.initElements(driver, DashboardPage.class);
    }


    /**
     * Allows the user to identify what the currently selected project on the
     * dashboard is
     * 
     * @return ProjectType enumeration representing the currently selected
     *         project
     * @throws Exception
     *             Unknown project selected
     */
    public ProjectType getSelectedProjectType() throws Exception {
	String projectType = projectContainer.getText().substring(projectContainer.getText().length() - 5);

	switch (projectType) {
	case "(ACC)":
	    return ProjectType.ACC;
	case "(CRT)":
	    return ProjectType.CRT;
	case "(HYP)":
	    return ProjectType.HYP;
	case "(PRE)":
	    return ProjectType.PRE;
	default:
	    throw new Exception("Unknown project selected.");
	}
    }
    
    /**
     * Return the project name of the current active project
     * @return
     */
    public String getActiveProjectTitle() {
    return driver.findElement(projTitle).getText();
    }

    /**
     * Allows the user to click the 'Create New Project' button
     * 
     * @return New project page
     */
    public NewProjectPage clickCreateNewProjectButton() {
	driver.findElement(createNewProjectButton).click();
	log.info("Create new project button clicked");
	return PageFactory.initElements(driver, NewProjectPage.class);
    }

    /**
     * Allows the user to click the deactivate key link
     * 
     * @return The current page
     */
    public DashboardPage clickDeactivateProject() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.linkText("Deactivate Project")).click();
	log.info("Deactivate key link clicked");
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.visibilityOf(
			driver.findElement(By.xpath("//div[@class='popup-content']"))));
	return this;
    }

    /**
     * Allows the user to enter a string in the deactivate key textbox
     * 
     * @param sentence
     *            The sentence to be entered
     * @return The current page
     */
    public DashboardPage typeDeactivateSentence(String sentence) {
	driver.findElement(By.id("word")).sendKeys(sentence);
	log.info("Deactivate sentence entered");
	return this;
    }

    /**
     * Allows the user to click the cancel button on the deactivate key modal
     * 
     * @return The current page
     */
    public DashboardPage clickDeactivateCancel() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id("cancel")).click();
	log.info("Cancel button clicked");
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
			By.xpath("//div[contains(@class|@id,'popup')]")));
	return this;
    }

    /**
     * Allows the user to deactivate a project
     * 
     * @return The dashboard page
     */
    public DashboardPage deactivateProject() {
	this.clickDeactivateProject();
	this.typeDeactivateSentence("Deactivate This Project");
	this.clickDeactivateButton();
	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the deactivate key button
     * 
     * @return The current project
     */
    public DashboardPage clickDeactivateButton() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id("Confirm")).click();
	log.info("Deactivate button clicked");
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
			By.className("popup")));
	
	return this;
    }
    
    /**
     * Allows the user to download the file tied to the current project
     * @param linkText - string representation of the link text
     * @return this page
     */
    public DashboardPage clickDownload(String linkText) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.linkText(linkText)).click();
    return this;
    }
    
    /**
     * Close the "project creation successful" message and return back to the dashboard page
     * @return this page
     */
    public DashboardPage clickBackToDashboard() {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    driver.findElement(backToDashboardButton).click();
	WebDriverWait wait = new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(backToDashboardButton));
    return this;
    }
    
    /**
     * @return Returns the API Key found in the API key textbox
     */
    public String getAPIKey() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return apiKeyTextbox.getAttribute("value").toString();
    }

    /**
     * @return Returns true if the deactivate button is disabled
     */
    public boolean isDeactivateButtonDisabled() {
	try {
	    String disabled = driver.findElement(By.id("Confirm")).getAttribute("disabled").toString();

	    if (disabled.equals("true"))
		return true;
	    else
		return false;
	} catch (NullPointerException e) {
	    return false;
	}

    }

    /**
     * Allows the user to change the project name of a project
     * 
     * @param projectName
     *            New name of the project
     * @return Current page
     */
    public DashboardPage changeProjectName(String projectName) {

	editButton.click();

	driver.findElement(projectNameTextbox).clear();
	driver.findElement(projectNameTextbox).sendKeys(projectName);

	driver.findElement(projectNameTextbox).sendKeys(Keys.RETURN);

	log.info("Project name changed.");

	return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the analytics button
     * 
     * @return Analytics Page
     */
    public AnalyticsPage clickAnalyticsButton() {
    WebDriverWait wait = new WebDriverWait(driver, 3);
    wait.until(ExpectedConditions.visibilityOfElementLocated(analyticsButton));    	
	driver.findElement(analyticsButton).click();
	log.info("Analytics button clicked");
	return PageFactory.initElements(driver, AnalyticsPage.class);
    }

    /**
     * Allows the user to click the geofences button
     * 
     * @return Geofences Page
     */
    public GeofencesPage clickGeofencesButton() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(geofencesButton));   
	driver.findElement(geofencesButton).click();
	log.info("Geofences button clicked");
	return PageFactory.initElements(driver, GeofencesPage.class);
    }

    /**
     * Allows the user to click the android demo app link
     * 
     * @return The current page
     */
    public DashboardPage clickAndroidDemoApp() {
	driver.findElement(By.linkText("Android Demo App")).click();
	log.info("Android demo app link clicked");
	WebDriverWait wait = new WebDriverWait(driver, 3);
	wait.until(ExpectedConditions.visibilityOfElementLocated(
			By.xpath("//div[@class='popup-content']")));
	return this;
    }

    /**
     * Allows the user to click the yes button the generate demo modal
     * 
     * @return The current page
     */
    public DashboardPage clickAndroidDemoAppYes() {
	driver.findElement(demoAppYesButton).click();
	log.info("Yes button in demo app modal clicked");
	return this;
    }

    /**
     * Allows the user to click the no button the generate demo modal
     * 
     * @return The current page
     */
    public DashboardPage clickAndroidDemoAppNo() {
	driver.findElement(demoAppNoButton).click();
	log.info("No button in demo app modal clicked");
	return this;
    }

    /**
     * Allows the user to click the cancel button the generate demo modal
     * 
     * @return The current page
     */
    public DashboardPage clickAndroidDemoAppCancel() {
	driver.findElement(demoAppCancelButton).click();
	log.info("Cancel button in demo app modal clicked");
	WebDriverWait wait = new WebDriverWait(driver, 3);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(
			By.xpath("//div[@class='popup-content']")));
	return this;
    }
    
    /**
     * Allows the user to stop emulating as the user and return as the admin
     * 
     * @return The Users page
     */
    public UsersPage clickBackToAdmin() {
    driver.findElement(By.linkText("Logout")).click();;
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@class='btn btn-gray']")));
    return PageFactory.initElements(driver, UsersPage.class);
    }
    
    /**
     * Clicks the logout button
     * @return the login page
     */
	public LoginPage clickLogout() {
	driver.findElement(By.linkText("Logout")).click();
    log.info("Logout Button Clicked");
    return PageFactory.initElements(driver, LoginPage.class);
	}
	

    /**
     * @return Returns the string of the selected project
     */
    public String getSelectedProject() {
	Select droplist = new Select(projectDropdown);
	WebElement selectedProject = droplist.getFirstSelectedOption();
	return selectedProject.getText();
    }

    /**
     * @return Returns the domain of a certified project
     */
    public String getDomain() {
	String domain = driver.findElement(domainTextbox).getAttribute("value");
	log.info("Domain: " + domain);
	return domain;
    }
    
    public boolean isSettingsGearPresent() {
    return this.isElementPresent(By.xpath(settingsGear));
    }

    /**
     * @return Returns true if the API button is present on the page
     */
    public boolean isAPIKeyPresent() {

	if (this.apiKeyTextbox.isDisplayed()) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * @return Returns true if the geofence button is present on the page
     */
    public boolean isGeofenceButtonPresent() {
	return this.isElementPresent(this.geofencesButton);
    }

    /**
     * @return Returns true if the analytics button is present on the page
     */
    public boolean isAnalyticsButtonPresent() {

    return this.isElementPresent(this.analyticsButton);
    }

    /**
     * @return Returns true if resource links are present
     */
    public boolean isResourcesPresent() {
	return this.isElementPresent(resourceLinks);
    }

    /**
     * @return Returns true if download links are present
     */
    public boolean isDownloadsPresent() {
	WebElement we = driver.findElement(this.downloadLinks);
	if (we.getAttribute("class").equals("hidden")) {
	    return false;
	}
	return this.isElementPresent(this.downloadLinks);
    }

    /**
     * @return Returns true if create demo app modal is present
     */
    public boolean isDemoAppModalPresent() {
	String attribute = driver.findElement(By.className("popup")).getAttribute("style");

	if (attribute == null)
	    return false;

	if (attribute.contains("opacity: 1"))
	    return true;
	else
	    return false;
    }
    
    

    /**
     * @param projectName
     *            Name of the project to be checked
     * @param projectType
     *            The type of the project
     * @return Returns true if the project exists in the drop down
     */
    public boolean isProjectPresent(String projectName, ProjectType projectType) {
	try {
	    this.selectProject(projectName, projectType);
	    return true;
	} catch (Throwable T) {
	    return false;
	}
    }

    /**
     * @return Returns true if the API Key Texbox is read only
     */
    public boolean isAPIKeyReadOnly() {
	if (this.apiKeyTextbox.getAttribute("readonly").equals("true"))
	    return true;
	else
	    return false;
    }

    /**
     * @return Returns true if the Domain Texbox is read only
     */
    public boolean isDomainReadOnly() {
	if (driver.findElement(domainTextbox).getAttribute("readonly").equals("true"))
	    return true;
	else
	    return false;
    }
    
    
    /**

     * First, click the download link specified,
     * then wait for the download to finish based on the timeout provided,
     * finally compare the SHA hash code on the website with the hash code of the
     * downloaded file
     * @param downloadLinkName - the link text for the download
     * @param timeOut - period to wait for the download to finish in milliseconds
     * @param hashNum - specify the row in the download table on the website
     * @return true if the both hashes match 
     */
    public boolean verifyDownloadHash(String downloadLinkName, int timeOut, int hashNum) {
    File androidSDKFile;
    HashCode hc=null;
    String hc_string=null;
    driver.findElement(By.linkText(downloadLinkName)).click();
    androidSDKFile = new File(TC_PORTAL_06.properties.getProperty("project.downloadDirectory") +
  			 downloadLinkName.replace("version", "wpsapi"));
	try {
    	Thread.sleep(timeOut);
    	hc= Files.hash(androidSDKFile, Hashing.sha1());
    	hc_string = hc.toString();
    } catch (IOException | InterruptedException e) {
    	log.error("Encountered problem while trying to get hash code");
    }
    deleteFile(androidSDKFile);
    
    if (hc_string.equals( driver.findElement(By.xpath("//tr[" + hashNum + "]" + "/td[@class='hash']")).getText() ) )
    	return true;
    else
    	return false;
    
    }

    /**
     * The method will delete the file 
     * specified by the path, then verify 
     * that the delete is successful
     * @param input - name of the file to be deleted
     */
    public void deleteFile(File inputFile) {
    boolean result;
    result = FileUtils.deleteQuietly(inputFile);
    Assert.assertTrue(result);
    }

	
}