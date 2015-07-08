package com.skyhookwireless.skyhook.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.skyhookwireless.skyhook.pageObjects.LandingPage;
import com.skyhookwireless.skyhook.pageObjects.ProductsPage;
import com.skyhookwireless.skyhook.pageObjects.solutionsPages.*;
import com.skyhookwireless.skyhook.pageObjects.aboutPages.*;
import com.skyhookwireless.skyhook.pageObjects.getStartedPages.*;

/**
 * Footer class used as a persistent object to model the header of a page object
 * @author Skyhook Wireless
 *
 */
public class Header {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	
	private WebDriver driver;
	
	/* Web Elements */
	// Header block
	@FindBy(className="header")
	private WebElement headerBlock; // Limits element searching to exclusively the header.
	
	@FindBy(linkText="Logo")
	private WebElement headerLogo;
	
	@FindBy(css = "#hs_cos_wrapper_header_v4 > div > nav > li.subnav-dropdown-products > ul > a:nth-child(1)")
    private WebElement contextAcceleratorElement; 
	
	@FindBy(css = "#hs_cos_wrapper_header_v4 > div > nav > li.subnav-dropdown-products > ul > a:nth-child(2)")
    private WebElement precisionLocationElement; 

	@FindBy(css = "#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(2)")
	private WebElement productsTab;
	
	@FindBy(css = "#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(3)")
	private WebElement aboutTab;
	
	@FindBy(css = "#hs_cos_wrapper_header_v4 > div > nav > li.subnav-dropdown-getstart")
	private WebElement getStartedTab;
	
	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public Header(WebDriver driver) {
		this.driver = driver;
	}
	
	  /**
     * Allows the user to click the My.Skyhook logo
     * 
     * @return Landing page
     */
    public LandingPage clickLogo() {
	driver.findElement(By.linkText("Logo")).click();
	log.info("Logo clicked");
	return PageFactory.initElements(this.driver, LandingPage.class);
    }

    /**
     * @return Returns true if resend button is present on the page
     */
   // public boolean isResendButtonPresent() {
//	return isElementPresent(By.cssSelector("#top_alert > div > button"));
  //  }

    /**
     * Allows the user to click the 'Products' link in the header
     * 
     * @return Products page
     */
    public ProductsPage clickProducts() {
    driver.findElement(By.linkText("PRODUCTS")).click();
	log.info("Products link clicked");
	return PageFactory.initElements(this.driver, ProductsPage.class);
    }
    
    /**
     * Allows the user to click the 'Products' 
     * then 'Accelerator' link in the header
     * 
     * @return Products page
     */
    public ProductsPage clickProducts(String productType) {
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		"#hs_cos_wrapper_header_v4 > div > nav > li.subnav-dropdown-products"))).perform();
    if (productType.equals("CA"))
    	new Actions(driver).moveToElement(contextAcceleratorElement).click().perform();
    else if ( productType.equals("PL") )
    	new Actions(driver).moveToElement(precisionLocationElement).click().perform();
    else
    	new Actions(driver).click().perform();
	log.info("Products link clicked");
	// Wait 2 seconds to allow scrolling to finish
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	return PageFactory.initElements(this.driver, ProductsPage.class);
    }
    
    
    /**
     * Allows the user to click the 'Apps' sublink
     * under the 'Solutions' link in the header
     * 
     * @return Apps page
     */
    public AppsPage clickSolutions_Apps() {
    productsTab.click();
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		    		"#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(2) > ul > a:nth-child(1)"))).click().perform();
	log.info("Solutions link - Apps clicked");
	return PageFactory.initElements(this.driver, AppsPage.class);
    }
    
    /**
     * Allows the user to click the 'ad' sublink
     * under the 'Solutions' link in the header
     * 
     * @return Advertising page
     */
    public AdvertisingPage clickSolutions_Advertising() {
    productsTab.click();
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		    		"#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(2) > ul > a:nth-child(2)"))).click().perform();
	log.info("Solutions link - Advertising clicked");
	return PageFactory.initElements(this.driver, AdvertisingPage.class);
    }
    
    /**
     * Allows the user to click the 'ad' sublink
     * under the 'Solutions' link in the header
     * 
     * @return Devices page
     */
    public DevicesPage clickSolutions_Devices() {
    productsTab.click();
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		    		"#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(2) > ul > a:nth-child(3)"))).click().perform();
	log.info("Solutions link - Devices clicked");
	return PageFactory.initElements(this.driver, DevicesPage.class);
    }
    
    /**
     * Allows the user to click the 'company' sublink
     * under the 'about' link in the header
     * 
     * @return Company page
     */
    public CompanyPage clickAbout_Company() {
    aboutTab.click();
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		    		"#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(3) > ul > a:nth-child(1)"))).click().perform();
	log.info("About link - Company clicked");
	return PageFactory.initElements(this.driver, CompanyPage.class);
    }
    
    /**
     * Allows the user to click the 'careers' sublink
     * under the 'about' link in the header
     * 
     * @return Careers page
     */
    public CareersPage clickAbout_Careers() {
    aboutTab.click();
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		    		"#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(3) > ul > a:nth-child(2)"))).click().perform();
	log.info("About link - Careers clicked");
	return PageFactory.initElements(this.driver, CareersPage.class);
    }
    
    /**
     * Allows the user to click the 'blog' sublink
     * under the 'about' link in the header
     * 
     * @return Blog page
     */
    public BlogPage clickAbout_Blog() {
    aboutTab.click();
    new Actions(driver).moveToElement(driver.findElement(By.cssSelector(
    		    		"#hs_cos_wrapper_header_v4 > div > nav > li:nth-child(3) > ul > a:nth-child(3)"))).click().perform();
	log.info("About link - Blog clicked");
	return PageFactory.initElements(this.driver, BlogPage.class);
    }
    
    /**
     * Allows the user to click the 'Developers' link in the header
     * 
     * @return Developers page
     */
    public DevelopersPage clickDevelopers() {
    driver.findElement(By.linkText("DEVELOPERS")).click();
	log.info("Developers link clicked");
	return PageFactory.initElements(this.driver, DevelopersPage.class);
    }
    
    
    /**
     * Allows the user to click the 'Get started' link in the header
     * 
     * @return Get Started page
     */
    public GetStartedPage clickGetStarted() {
    // Wait 2 seconds for button to load correctly
    try {
    	Thread.sleep(2000);
    } catch (InterruptedException e) {
    	e.printStackTrace();
    }
    getStartedTab.click();
	log.info("Get Started link clicked");

	return PageFactory.initElements(this.driver, GetStartedPage.class);
    }
    
    
    /**
     * Allows the user to click the 'Get started' link in the header
     * 
     * @return Get Started CL page
     */
    public CustomerLoginPage clickGetStarted_CustomerLogin() {
    // Wait 2 seconds for button to load correctly
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    new Actions(driver).moveToElement(getStartedTab).perform();
    new Actions(driver).moveToElement(driver.findElement(By.xpath(
    		"//*[contains(@id,'hs-cta-wrapper')]/ul[@class='header-nav-getstart']/a[1]"))).click().perform();
	log.info("Get Started CL link clicked");
	return PageFactory.initElements(this.driver, CustomerLoginPage.class);
    }
    
    /**
     * Allows the user to click the 'Get started' link in the header
     * 
     * @return Get Started CL page
     */
    public SpeakWithUsPage clickGetStarted_SpeakWithUs() {
    // Wait 2 seconds for button to load correctly
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    new Actions(driver).moveToElement(getStartedTab).perform();
    new Actions(driver).moveToElement(driver.findElement(By.xpath(
    		"//*[contains(@id,'hs-cta-wrapper')]/ul[@class='header-nav-getstart']/a[2]"))).click().perform();
	log.info("Get Started CL link clicked");
	return PageFactory.initElements(this.driver, SpeakWithUsPage.class);
    }
    
    
}