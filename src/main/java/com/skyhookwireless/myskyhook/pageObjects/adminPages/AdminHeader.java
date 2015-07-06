package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.LoginPage;

public class AdminHeader {

    // Logging
    protected final Logger log = LogManager.getLogger(getClass().toString());
    
    private WebDriver driver;
    
    /* Web Elements */
    // Header block
    @FindBy(id = "header")
    private WebElement headerBlock; // Limits element searching to exclusively
				    // the header.
    
	String headerPath = "#header > div > div > div.header__nav-bar__main-menu > ul > li";
    
    /**
     * Constructor for AdminHeader object
     * 
     * @param driver
     *            The webdriver used to navigate the page
     */
    public AdminHeader(WebDriver driver) {
	this.driver = driver;
    }
    
    /**
     * Allows the user to click the 'Accounts' link in the header of the page
     * 
     * @return Accounts page
     */
    public AccountsPage clickAccounts() {

	headerBlock.findElement(By.linkText("ACCOUNTS")).click();
	log.info("ACCOUNTS link clicked");
	return PageFactory.initElements(this.driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to click the 'Users' link in the header of the page
     * 
     * @return Users page
     */
    public UsersPage clickUsers() {

	headerBlock.findElement(By.linkText("USERS")).click();
	log.info("USERS link clicked");
	return PageFactory.initElements(this.driver, UsersPage.class);
    }
    
    /**
     * Allows the user to click the 'Statistics' link in the header of the page
     * 
     * @return Statistics page
     */
    public StatisticsPage clickStatistics() {

	headerBlock.findElement(By.linkText("STATISTICS")).click();
	log.info("STATISTICS link clicked");
	return PageFactory.initElements(this.driver, StatisticsPage.class);
    }
    
    /**
     * Allows the user to click the 'Files' link in the header of the page
     * 
     * @return Files page
     */
    public FilesPage clickFiles() {

	headerBlock.findElement(By.linkText("FILES")).click();
	log.info("FILES link clicked");
	return PageFactory.initElements(this.driver, FilesPage.class);
    }
    
    /**
     * Allows the user to click the 'Batch' link in the header of the page
     * 
     * @return Batch page
     */
    public BatchPage clickBatch() {

	headerBlock.findElement(By.linkText("BATCH")).click();
	log.info("BATCH link clicked");
	return PageFactory.initElements(this.driver, BatchPage.class);
    }
    
    /**
     * Allows the user to click the 'SVN' link in the header of the page
     * 
     * @return SVN page
     */
    public SVNPage clickSVN() {

	headerBlock.findElement(By.linkText("STATISTICS")).click();
	log.info("STATISTICS link clicked");
	return PageFactory.initElements(this.driver, SVNPage.class);
    }
    
    /**
     * Allows the user to click the 'Alerts' link in the header of the page
     * 
     * @return Alerts page
     */
    public AlertsPage clickAlerts() {

	headerBlock.findElement(By.linkText("ALERTS")).click();
	log.info("ALERTS link clicked");
	return PageFactory.initElements(this.driver, AlertsPage.class);
    }
    
    /**
     * Allows the user to click the 'Logout' link in the menu utility section of
     * the page
     * 
     * @return Login page
     */
    public LoginPage clickLogout() {

	headerBlock.findElement(By.linkText("Logout")).click();
	log.info("Logout link clicked");
	return PageFactory.initElements(this.driver, LoginPage.class);
    }
    
    /**
     * Verify the metrics
     * 
     * @return Metric page
     */
    public void verifyMetrics() {
    
	WebElement metricElement = headerBlock.findElement(By.linkText("METRICS"));
	Assert.assertEquals(metricElement.getAttribute("href"), "http://metrics.skyhookwireless.com/");
	metricElement.click();
	log.info("METRICS link clicked");
    }
    
    /**
	 * Returns the list of elements found in the header
	 * @return
	 */
	public String[] getHeaderElements() {
		List<WebElement> headerList = driver.findElements(By.cssSelector(headerPath));
		String[] headerTextArray = new String[headerList.size()];
		for (int i = 0 ; i < headerList.size(); i++) {
			headerTextArray[i] = headerList.get(i).getText();
		}
		return headerTextArray;	
	}
    
}
