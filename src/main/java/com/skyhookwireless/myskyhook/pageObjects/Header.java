package com.skyhookwireless.myskyhook.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Header class used as a persistent object to model the header of a page object
 * 
 * @author Skyhook Wireless
 * 
 */
public class Header {

    // Logging
    protected final Logger log = LogManager.getLogger(getClass().toString());

    private WebDriver driver;

    /* Web Elements */
    // Header block
    @FindBy(id = "header")
    private WebElement headerBlock;  
	// Limits element searching to exclusively the header.

    // Resend email wrap
    @FindBy(id = "email_not_confirmed")
    private WebElement emailWrap;
    
    // Gear circle
    @FindBy(xpath= "//a[@href='/profile/edit']")
    private WebElement settingsGear;
    
    By popupAlert = By.className("popup-content");

    /**
     * Constructor for Header object
     * 
     * @param driver
     *            The webdriver used to navigate the page
     */
    public Header(WebDriver driver) {
	this.driver = driver;
    }

    /**
     * Allows the user to click the 'Dashboard' link in the header of the page
     * 
     * @return Dashboard page
     */
    public DashboardPage clickDashboard() {

	headerBlock.findElement(By.linkText("DASHBOARD")).click();
	log.info("Dashboard link clicked");
	return PageFactory.initElements(this.driver, DashboardPage.class);
    }

    /**
     * Allows the user to click the 'Resources' link in the header of the page
     * 
     * @return Resources page
     */
    public ResourcesPage clickResources() {

	headerBlock.findElement(By.linkText("RESOURCES")).click();
	log.info("Resources link clicked");
	return PageFactory.initElements(this.driver, ResourcesPage.class);
    }

    /**
     * Allows the user to click the 'Support' link in the header of the page Do
     * not use this method if you are the login page, instead use
     * <code>{@link Header#clickPublicSupport()}</code>
     * 
     * @return Support page
     * @see #clickPublicSupport()
     */
    public SupportPage clickSupport() {

	headerBlock.findElement(By.linkText("SUPPORT")).click();
	log.info("Support link clicked");
	return PageFactory.initElements(this.driver, SupportPage.class);
    }

    /**
     * Allows the user to click the 'Support' link in the header of the page Use
     * this method instead of <code>{@link Header#clickSupport()}</code> if you
     * are at the login page
     * 
     * @return Public support page
     * @see #clickSupport()
     */
    public PublicSupportPage clickPublicSupport() {

	headerBlock.findElement(By.linkText("SUPPORT")).click();
	log.info("Public Support link clicked");
	return PageFactory.initElements(this.driver, PublicSupportPage.class);
    }

    /**
     * Allows the user to click the 'Analytics' link in the header of the page
     * 
     * @return Analytics page
     */
    public AnalyticsPage clickAnalytics() {

	headerBlock.findElement(By.linkText("ANALYTICS")).click();
	log.info("Analytics link clicked");
	return PageFactory.initElements(this.driver, AnalyticsPage.class);
    }

    /**
     * Allows the user to click the 'Geofences' link in the header of the page
     * 
     * @return Geofences page
     */
    public GeofencesPage clickGeofences() {

	headerBlock.findElement(By.linkText("GEOFENCES")).click();

	return PageFactory.initElements(this.driver, GeofencesPage.class);
    }

    /**
     * Allows the user to click the 'Usage Reports' link in the header of the
     * page
     * 
     * @return Usage reports page
     */
    public UsageReportsPage clickUsageReports() {

	headerBlock.findElement(By.linkText("USAGE REPORTS")).click();
	log.info("Usage reports link clicked");
	return PageFactory.initElements(this.driver, UsageReportsPage.class);
    }

    /**
     * Allows the user to click the 'Account/Profile settings' link in the menu utility
     * section of the page
     * 
     * @return Account settings page
     */
    public AccountSettingsPage clickAccountSettings() {

    WebDriverWait wait = new WebDriverWait(driver, 5);
    WebElement headerAct= 
    		wait.until(ExpectedConditions.visibilityOf(settingsGear));
	headerAct.click();
	driver.findElement(By.linkText("Profile")).click();
	return PageFactory.initElements(this.driver, AccountSettingsPage.class);
    }

    /**
     * Allows the user to click the 'User settings' link in the menu utility of
     * the page
     * 
     * @return User settings page
     */
    public UserSettingsPage clickUserSettings() {
    settingsGear.click();
	driver.findElement(By.linkText("Permissions")).click();
	log.info("User settings link clicked");
	return PageFactory.initElements(this.driver, UserSettingsPage.class);
    }
    
    /**
     * Allows the user to click the 'User settings' link in the menu utility of
     * the page
     * 
     * @return Security settings page
     */
    public SecuritySettingsPage clickSecuritySettings() {
    settingsGear.click();
	driver.findElement(By.linkText("Security")).click();
	log.info("Security tab clicked");
	return PageFactory.initElements(driver, SecuritySettingsPage.class);
    }

    /**
     * Allows the user to click the 'Logout' link in the menu utility section of
     * the page
     * 
     * @return Login page
     */
    public LoginPage clickLogout() {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(popupAlert));
	headerBlock.findElement(By.linkText("Logout")).click();
	log.info("Logout link clicked");
	return PageFactory.initElements(this.driver, LoginPage.class);
    }

    /**
     * Allows the user to click the 'Login' link in the menu utility section of
     * the page
     * 
     * @return Login page
     */
    public LoginPage clickLogin() {

	headerBlock.findElement(By.linkText("Log In")).click();
	log.info("Log In link clicked");
	return PageFactory.initElements(this.driver, LoginPage.class);
    }

    /**
     * Allows the user to click the My.Skyhook logo
     * 
     * @return Login page
     */
    public LoginPage clickLogo() {
	driver.findElement(By.linkText("")).click();
	log.info("Logo clicked");
	return PageFactory.initElements(this.driver, LoginPage.class);
    }

    public String getWelcomeText() {
	WebElement menuUtility = headerBlock.findElement(By.className("header__menu__utility"));
	return menuUtility.findElement(By.xpath("//p[1]")).getText();
    }

    /**
     * @return Returns true if resend button is present on the page
     */
    public boolean isResendButtonPresent() {
	return isElementPresent(By.cssSelector("#top_alert > div > button"));
    }

    /**
     * @return	Returns true if usage reports is present in the header
     */
    public boolean isUsageReportsPresent() {
	try {
	    headerBlock.findElement(By.linkText("USAGE REPORTS"));
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }
    
    /**
     * @return	Returns true if geofences is present in the header
     */
    public boolean isGeofencesPresent() {
	try {
	    headerBlock.findElement(By.linkText("GEOFENCES"));
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    protected boolean isElementPresent(By by) {
	try {
	    driver.findElement(by);
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }
}
