package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

public class AlertsPage extends AbstractPageObject {
	/* Web Elements */
    @FindBy(id = "cancel")
    private WebElement notSaveNewAlert;
    
    @FindBy(id = "title")
    private WebElement alertTitle;
    
    @FindBy(className = "alert-icon-check")
    private WebElement greenCheckIcon;
    
    @FindBy(className = "alert-icon-smile")
    private WebElement greenSmileIcon;
    
    @FindBy(className = "alert-icon-info")
    private WebElement blueInfoIcon;
    
    @FindBy(className = "alert-icon-sad")
    private WebElement redSadIcon;
    
    @FindBy(className = "alert-icon-exclamation")
    private WebElement redAlertIcon;
    
    @FindBy(id = "text_body")
    private WebElement alertTextBody;
    
    @FindBy(id = "cta_link")
    private WebElement alertLinkWebsite;
    
    @FindBy(id = "cta_text")
    private WebElement alertLinkText;
    
    @FindBy( id = "product-hyperlocalip")
    private WebElement hyperlocalipbtn;
    
    @FindBy( id = "product-precisionlocation")
    private WebElement precisionlocationbtn;
    
    @FindBy( id = "product-accelerator")
    private WebElement acceleratorbtn;
    
    @FindBy( id = "period-once")
    private WebElement periodonce;
    
    @FindBy( id = "period-daily")
    private WebElement perioddaily;
    
    @FindBy( id = "description")
    private WebElement alertDescription;
    
    @FindBy( id = "submit_form")
    private WebElement saveNewAlert;
    
    @FindBy( id = "top_alert")
    private WebElement alertPopup;
    
    @FindBy( id = "top_alert")
    private WebElement headerAlert;
    
    @FindBy( linkText = "USERS")
    private WebElement usersSettings;
    
    @FindBy (linkText = "CREATE ALERT")
    private WebElement createAlert;
    
    @FindBy ( xpath = "//div[@id='div_end_date']/div/div[@class='inline-block date']/input")
    private WebElement endDateYMD;
    
    @FindBy ( xpath = "//div[@id='div_end_date']/div/div[@class='inline-block time']/input")
    private WebElement endDateHM;
    
    @FindBy( xpath = "//div[@class='modal-footer']/a[@class='btn btn-primary']")
    private WebElement confirmDate;
    

    
	// By xpath
    private By alertDuration = By.xpath("//input[@name='duration']");
	private By popupPanel = By.xpath("//div[@class='popup-content']");
	private By popupPanel2 = By.xpath("//div[@id='popup-overlay']");
	private By alertBanner = By.xpath("//div[contains(@class,'a-close-default')]");
	private By confirmBtn = By.xpath("//a[@class='confirm-yes btn btn-orange']");
	private By cancelBtn = By.xpath("//a[@class='confirm-no btn btn-gray']");
	private By bannerIcon = By.xpath("//div/div/div[contains(@class,'a-icon')]");
	private By bannerText = By.xpath("//div/div/span");
	private By bannerLink = By.xpath("//div/div/a");
	private By footerModal = By.xpath("//div[@class='modal-footer']");
	
	// By css 
	private By startDate = By.cssSelector("div.inline-block.date > input[type=\"text\"]");
	private By emailConfirmationAlert = By.cssSelector("body > div.container-fluid.container > div > ul.sortable-list.alert-list.body.position-relative.ui-sortable > li");
	private By highlightedDate = By.cssSelector("body > div:nth-child(10) > div.datepicker-days > table > tbody > tr > td.day.active");
	
	// Variables
	private String allActiveAlerts = "//ul[@class='sortable-list alert-list body position-relative ui-sortable']";
	private String alert1Path = "//ul[@class='sortable-list alert-list body position-relative ui-sortable']/li[@class='alert-row alert-bg-check']";
	private String alert2Path = "//ul[@class='sortable-list alert-list body position-relative ui-sortable']/li[@class='alert-row alert-bg-smile']";
	private String alert3Path = "//ul[@class='sortable-list alert-list body position-relative ui-sortable']/li[@class='alert-row alert-bg-info']";
	// Correspond to Alert Test Case #5, but Case #4 has been removed
	private String alert4Path = "//ul[@class='sortable-list alert-list body position-relative ui-sortable']/li[@class='alert-row alert-bg-sad']";
	
	private String alert1PopupPath= "//div[@class='alert-bg-check']";
	private String alert2PopupPath= "//div[@class='alert-bg-smile']";
	private String alert3PopupPath= "//div[@class='alert-bg-info']";
	private String alert4PopupPath= "//div[@class='alert-bg-sad']";
	
	private String emailPopupPath = "//div[@id='top_alert']/div[@class='wrap']/button[@data-url='/register/resendConfirmation']";
	private String emailPopupClosePath = "//div[@id='top_alert']/div[@class='wrap']/div[@data-url='/services/alertclose/1']";
	
	// Extensions
	private String deleteBtn = "/div[@class='floatleft actions']/i[@class='fa fa-times']";
	private String editBtn = "/div[@class='floatleft actions']/a[@class='fa fa-pencil-square-o']";
	private String showBtn = "/div[@class='floatleft actions']/i[@class='js-show-alert fa fa-share-square-o']";

	
    public AlertsPage(WebDriver driver) {
		super(driver);
		
		// Assert title.
		Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.alerts.title"));
		log.info("Title is correct");
	}
	
	/**
	 * Allows the user to click the 'Create Alert' button
	 * @return this page
	 */
	public AlertsPage createAlert(){
		createAlert.click();
		log.info("Create new alert button clicked");
		return this;
	}
	
	/**
	 * Allows the user to click cancel saving the new alert
	 * @return this page
	 */
	public AlertsPage clickNotSaveAlert(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupPanel));
        WebDriverWait wait2 = new WebDriverWait(driver, 15);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(popupPanel2));
		notSaveNewAlert.click();
		log.info("Cancel/Do not save changes clicked");
		return this;
	}
	
	/**
	 * Allows the user to click save the new alert
	 * @return this page
	 */
	public AlertsPage clickSaveAlert() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		saveNewAlert.click();
		log.info("Save alert clicked");
		return this;
	}
	
	/**
	 * Allows the user to enter an alert name
	 * @return this page
	 */
	public AlertsPage typeAlertTitle(String alertName){
		alertTitle.clear();
		alertTitle.sendKeys(alertName);
		return this;
	}
	
	/**
	 * Allows the user to enter text for the body of the alert
	 * @return this page
	 */
	public AlertsPage typeAlertTextBody(String text){
		alertTextBody.clear();
		alertTextBody.sendKeys(text);
		return this;
	}
	
	/**
	 * Allows the user to enter a link to an external website
	 * @return this page
	 */
	public AlertsPage typeAlertLinkToWebsite(String website){
		alertLinkWebsite.clear();
		alertLinkWebsite.sendKeys(website);
		return this;
	}
	
	/**
	 * Allows the user to enter text for the link button of the alert
	 * @return this page
	 */
	public AlertsPage typeAlertLinkText(String text){
		alertLinkText.clear();
		alertLinkText.sendKeys(text);
		return this;
	}
	
	/**
	 * Allows the user to enter the start date of the alert
	 * @param date month, day and year
	 * @param time hour and minute
	 * @return this page
	 */
	public AlertsPage typeAlertStartDate(String date, String time){
		driver.findElement(startDate).clear();
		driver.findElement(startDate).sendKeys(date + Keys.TAB + time );
		confirmDate.click();
		return this;
	}
	
	/**
	 * Allows the user to enter the end date of the alert
	 * @param date month, day and year
	 * @param time hour and minute
	 * @return this page
	 */
	public AlertsPage typeAlertEndDate(String date, String time){
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(endDateYMD));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(footerModal));
        // For Day
        endDateYMD.click();
		endDateYMD.clear();
		endDateYMD.sendKeys(date);
		driver.findElement(highlightedDate).click();
		// For Hour and minute
		endDateHM.click();
		endDateHM.clear();
		endDateHM.sendKeys(time); 
		confirmDate.click();
		return this;
	}
	
	/**
	 * Allows the user to enter the duration for the alert
	 * @return this page
	 */
	public AlertsPage typeDuration(String period){
		WebElement alertDurElement = driver.findElement(alertDuration);
		alertDurElement.clear();
		alertDurElement.sendKeys(period);
		return this;
	}
	
	/**
	 * 
	 * Allows the user to enter description for the alert 
	 * (different from text-body)
	 * @return this page
	 */
	public AlertsPage typeAlertDescription(String text){
		alertDescription.clear();
		alertDescription.sendKeys(text);
		return this;
	}

	/**
	 * Based on user-input, the method will click one of the alert icon
	 * @param input - the classname of the alert icon
	 * @return this page
	 */
	public AlertsPage selectIcon(String input){
		if (input.equals("alert-icon-smile") )
			greenSmileIcon.click();
		else if (input.equals("alert-icon-info") )
			blueInfoIcon.click();
		else if (input.equals("alert-icon-sad") )
			redSadIcon.click();
		else if (input.equals("alert-icon-exclamation") )
			redAlertIcon.click();
		else 
			greenCheckIcon.click();
		
		return this;
	}
	
	/**
	 * Allows the user to click the Hyperlocal IP checkbox
	 * @return this page
	 */
	public AlertsPage selectHyperlocalip() {
		hyperlocalipbtn.click();
		return this;
	}
	
	/**
	 * Allows the user to click the precision location checkbox
	 * @return this page
	 */
	public AlertsPage selectPreciseLocation() {
		precisionlocationbtn.click();
		return this;
	}
	
	/**
	 * Allows the user to click the accelerator checkbox
	 * @return this page
	 */
	public AlertsPage selectAccelerator() {
		acceleratorbtn.click();
		return this;
	}
	
	/**
	 * Allows the user to click the repeat once checkbox
	 * @return this page
	 */
	public AlertsPage selectPeriodOnce() {
		periodonce.click();
		return this;
	}
	
	/**
	 * Allows the user to click the repeat daily checkbox
	 * @return this page
	 */
	public AlertsPage selectPeriodDaily() {
		perioddaily.click();
		return this;
	}
	
	/**
	 * Return the xpath to the alert based on test case/alert icon number
	 * @param number- test case number/alert icon number
	 * @return
	 */
	public String selectAlertPath(int number) {
		switch(number){
		case 1:
			return alert1Path;
		case 2:
			return alert2Path;
		case 3:
			return alert3Path;
		case 4:
			return alert4Path;
		default:
			return null;
		}
		
	}
	
	/**
	 * Return the xpath to the alert popup based on test case/alert icon number
	 * @param number- test case/alert icon number
	 * @return
	 */
	public String selectAlertPopupPath(int number) {
		switch(number){
		case 1:
			return alert1PopupPath;
		case 2:
			return alert2PopupPath;
		case 3:
			return alert3PopupPath;
		case 4:
			return alert4PopupPath;
		default:
			return null;
		}
		
	}

	/**
	 * Allow the user to click the 'Show alert at the top' button based on test case/alert icon number
	 * @param inputAlertNumber - test case/alert icon number
	 * @return
	 */
	public AlertsPage showTopAlertBanner(int inputAlertNumber) {
		String alertPath= selectAlertPath(inputAlertNumber);
		driver.findElement(By.xpath(alertPath + showBtn)).click();
		return this;
	}
	
	/**
	 * Allow the user to close the alert at the top specified by the test case/alert icon number
	 * @param inputAlertNumber - test case/alert icon number
	 * @return
	 */
	public AlertsPage closeTopAlertBanner(int inputAlertNumber) {
		String alertPopupPath = selectAlertPopupPath(inputAlertNumber);
		driver.findElement(alertBanner ).click();
		
		// Verify the top banner is closed successfully
		Assert.assertFalse(isElementPresent(alertPopupPath));
		return this;
	}
	
	/**
	 * Allow the user to edit the alert specified by the test case/alert icon number
	 * @param inputAlertNumber - test case/alert icon number
	 * @return
	 */
	public AlertsPage editAlert(int inputAlertNumber) {
		String alertPath = selectAlertPath(inputAlertNumber);
		driver.findElement(By.xpath(alertPath + editBtn)).click();
		return this;
	}
	
	/**
	 * Allow the user to edit the alert specified by the test case/alert icon number
	 * @param inputAlertNumber - test case/alert icon number
	 * @return this page
	 */
	public AlertsPage deleteAlert(int inputAlertNumber) {
		String alertPath = selectAlertPath(inputAlertNumber);
		driver.findElement(By.xpath(alertPath + deleteBtn)).click();
		return this;
	}
	
	/**
	 * Allow the user to click the Confirm/OK button
	 * @return this page
	 */
	public AlertsPage clickConfirmChanges() {
		driver.findElement(confirmBtn).click();
		return this;
	}
	
	/**
	 * Allow the user to click the cancel button
	 * @return this page
	 */
	public AlertsPage clickCancelChanges() {
		driver.findElement(cancelBtn).click();
		return this;
	}
	
	/**
	 * Allows the user to go the 'Users' section under the admin account
	 * @return the Users Page
	 */
	public UsersPage viewUsersSettings() {
		usersSettings.click();
		return PageFactory.initElements(driver, UsersPage.class);
	}
	
	/**
	 * The method verifies the newly created alert entry under the Active Alerts category
	 * @param inputAlertNumber -  test case/alert icon number
	 * @param inputAlertName - alert name
	 * @param inputAlertStart - alert start time
	 * @param inputAlertEnd - alert end time
	 * @param inputAlertIcon - alert icon
	 */
	public void verifyAlert(int inputAlertNumber, String inputAlertName, 
			String inputAlertStart, String inputAlertEnd, String inputAlertIcon) {
		
		// Select alter path based on user-inputed number
		String alertPath = selectAlertPath(inputAlertNumber);
		
		// Verify that the alert is created and placed into the active alert category
		Assert.assertTrue( driver.findElement(By.xpath(alertPath)).isDisplayed() );
		
		// Verify the alert name
		String latestAlertName = driver.findElement(By.xpath(alertPath + "/div[@class='floatleft title']")).getText();
		Assert.assertEquals(latestAlertName, inputAlertName);
		
		// Verify the alert start time
		String latestAlertStartTime = driver.findElement(By.xpath(alertPath + "/div[@class='floatleft start_date']")).getText();
		Assert.assertEquals(latestAlertStartTime, inputAlertStart);
		
		// Verify the alert end date
		String latestAlertEndTime = driver.findElement(By.xpath(alertPath + "/div[@class='floatleft end_date']")).getText();
		Assert.assertEquals(latestAlertEndTime, inputAlertEnd);
		
		// Verify the alert icon
		String latestAlertIcon = driver.findElement(By.xpath(alertPath + "/div[@class='floatleft icon']/div")).getAttribute("class");
		Assert.assertEquals(latestAlertIcon, inputAlertIcon);
		
	}
	
	 
	/**
	 * The method cross-verifies the newly created alert with its correspond pop-up alert banner at the top
	 * @param inputAlertNumber -  test case/alert icon number
	 * @param inputAlertName - alert name
	 * @param inputAlertStart - alert start time
	 * @param inputAlertEnd - alert end time
	 * @param inputAlertIcon - alert icon
	 */
	public void compareAlertBannerToAlert(int inputAlertNumber, String inputAlertName, String inputAlertIcon, 
			String inputBodyText, String inputLinkText, String inputLinkTo) {
		
		String alertPath= selectAlertPath(inputAlertNumber);
		String alertPopupPath = selectAlertPopupPath(inputAlertNumber);
		
		// Verify that the classname of the alert banner matches the classname of the alert
		// Since the top/pop-up alert's classname is shorter, the longer classname is truncated accordingly 
		Assert.assertEquals(alertPopup.getAttribute("class"), driver.findElement(By.xpath(alertPath)).getAttribute("class").substring(10));
		
		// Verifying that the icon matches
		
		String alertPopupIcon = headerAlert.findElement(bannerIcon).getAttribute("class");
		Assert.assertEquals(alertPopupIcon.substring(7), inputAlertIcon);
		
		
		// Verify the text body
		String alertPopupText = headerAlert.findElement(bannerText).getText();
		Assert.assertEquals(alertPopupText, inputBodyText);
		
		// Verify the link Text
		String alertPopupLinkText = headerAlert.findElement(bannerLink).getText();
		Assert.assertEquals(alertPopupLinkText, inputLinkText);
		
		// Verify on the link To
		String alertPopupLinkTo = driver.findElement(bannerLink).getAttribute("href");
		Assert.assertEquals(alertPopupLinkTo, inputLinkTo);
	}

	/**
	 * The method verifies the alert pop-up at the top of the page
	 * @param inputAlertNumber -  test case/alert icon number
	 * @param inputAlertName - alert name
	 * @param inputAlertIcon - alert icon
	 * @param inputBodyText - alert body text
	 * @param inputLinkText - alert link name
	 * @param inputLinkTo - the website that the alert link points to
	 */
	public void verifyAlertBanner(int inputAlertNumber, String inputAlertName, String inputAlertIcon, 
			String inputBodyText, String inputLinkText, String inputLinkTo) {
	
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String alertPopupPath = selectAlertPopupPath(inputAlertNumber);
		
		removeEmailConfirmationBanner();
		WebDriverWait wait = new WebDriverWait(driver, 15);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(alertPopupPath)));
		
		// Verifying that the icon matches
	    String alertPopupIcon = headerAlert.findElement(bannerIcon).getAttribute("class");
		Assert.assertEquals(alertPopupIcon.substring(7), inputAlertIcon);
		
		// Verify the text body
		String alertPopupText = headerAlert.findElement(bannerText).getText();
		Assert.assertEquals(alertPopupText, inputBodyText);
		
		// Verify the link Text
		String alertPopupLinkText = headerAlert.findElement(bannerLink ).getText();
		Assert.assertEquals(alertPopupLinkText, inputLinkText);
		
		// Click on the link
		String alertPopupLinkTo = driver.findElement(bannerLink).getAttribute("href");
		Assert.assertEquals(alertPopupLinkTo, inputLinkTo);
	}
	
	/**
	 * Remove the alert banner at the top
	 */
	public void removeEmailConfirmationBanner() {
		try {
			driver.findElement(By.xpath(emailPopupPath));
			driver.findElement(By.xpath(emailPopupClosePath)).click();
		} catch (Exception e){
			log.info("No email confirmation banner was found");
		}
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath(emailPopupPath)));
	}
	
	/**
	 * Remove all alerts with the name "Automated" under the active alerts section
	 */
	public void removeOldAlerts() {
	String alertPath;
	int numOfAlerts = driver.findElements(By.xpath(allActiveAlerts + "/li")).size();
	int count = 2;
	while (count <= numOfAlerts ) {
		alertPath = allActiveAlerts + "/li[" + count + "]";
		driver.findElement(By.xpath(alertPath + deleteBtn)).click();
		this.clickConfirmChanges();
		count ++;
	}
	}
	
	/**
	 * Confirm that the fixed email alert is on the page
	 * @return true or false
	 */
	public boolean isEmailConfirmationAlertPresent() {
		if (driver.findElement(emailConfirmationAlert).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	
	}
	
	/**
	 * Confirm whether an element is on the page by xpath
	 * @return true or false
	 */
	public boolean isElementPresent(String element) {
		try {
			driver.findElement(By.xpath(element));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * Determine whether an alert is under the active category
	 * @param inputAlertNumber -test case/alert icon number
	 * @return true or false
	 */
	public boolean isAlertActive(int inputAlertNumber) {
		String alertPath = selectAlertPath(inputAlertNumber);
		try{
			driver.findElement(By.xpath(alertPath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
