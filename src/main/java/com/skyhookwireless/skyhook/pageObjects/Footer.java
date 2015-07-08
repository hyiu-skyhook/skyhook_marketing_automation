package com.skyhookwireless.skyhook.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skyhookwireless.skyhook.pageObjects.aboutPages.CareersPage;
import com.skyhookwireless.skyhook.pageObjects.aboutPages.CompanyPage;
import com.skyhookwireless.skyhook.pageObjects.getStartedPages.SpeakWithUsPage;

/**
 * Footer class used as a persistent object to model the header of a page object
 * @author Skyhook Wireless
 *
 */
public class Footer {

	// Logging
	protected final Logger log = LogManager.getLogger(getClass().toString());
	
	private WebDriver driver;
	
	/* Web Elements */
	// Footer block
	@FindBy(className="footer")
	private WebElement footerBlock; // Limits element searching to exclusively the footer.

	/**
	 * Constructor for Footer object
	 * 
	 * @param driver	The webdriver used to navigate the page
	 */
	public Footer(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Allows the user to click the 'Resources' link in the footer of the page
	 * @return	Resources page
	 */
	public ResourcesPage clickResources() {
		footerBlock.findElement(By.linkText("Resources")).click();
		log.info("Resources link clicked");
		return PageFactory.initElements(this.driver, ResourcesPage.class);
	}
	
    /**
     * Allows the user to click the 'company' link in the footer
     * 
     * @return Company page
     */
    public CompanyPage clickAbout() {
    	footerBlock.findElement(By.linkText("About")).click();
    	log.info("About link clicked");
    	return PageFactory.initElements(this.driver, CompanyPage.class);
    }
    
    /**
     * Allows the user to click the 'careers' link in the footer
     * 
     * @return Career page
     */
    public CareersPage clickCareers() {
    	footerBlock.findElement(By.linkText("Careers")).click();
    	log.info("Career link clicked");
    	return PageFactory.initElements(this.driver, CareersPage.class);
    }
    
    /**
     * Allows the user to click the 'products' link in the footer
     * 
     * @return Company page
     */
    public ProductsPage clickProducts() {
    	footerBlock.findElement(By.linkText("Products")).click();
    	log.info("Products link clicked");
    	return PageFactory.initElements(this.driver, ProductsPage.class);
    }
    
    /**
     * Allows the user to click the 'patents' link in the footer
     * 
     * @return Patents page
     */
    public PatentsPage clickPatents() {
    	footerBlock.findElement(By.linkText("Patents")).click();
    	log.info("Patents link clicked");
    	return PageFactory.initElements(this.driver, PatentsPage.class);
    }
    
    /**
     * Allows the user to click the 'press' link in the footer
     * 
     * @return Press page
     */
    public PressPage clickPress() {
    	footerBlock.findElement(By.linkText("Press")).click();
    	log.info("Press link clicked");
    	return PageFactory.initElements(this.driver, PressPage.class);
    }
	
    
    /**
     * Allows the user to click the 'documentation' link in the footer
     * 
     * @return Documentation page
     */
    public DocumentationPage clickDocumentation() {
    	footerBlock.findElement(By.linkText("Documentation")).click();
    	log.info("Documentation link clicked");
    	return PageFactory.initElements(this.driver, DocumentationPage.class);
    }
    
    /**
     * Allows the user to click the 'coverage map' link in the footer
     * 
     * @return Coverage Map page
     */
    public CoverageMapPage clickCoverageMap() {
    	footerBlock.findElement(By.linkText("Coverage Map")).click();
    	log.info("Coverage Map link clicked");
    	return PageFactory.initElements(this.driver, CoverageMapPage.class);
    }
    
    
    /**
     * Allows the user to click the 'japan coverage map' link in the footer
     * 
     * @return Japan Coverage Map page
     */
    public JapanCoverageMapPage clickJapanCoverageMap() {
    	footerBlock.findElement(By.linkText("日本")).click();
    	log.info("Japan Coverage Map link clicked");
    	return PageFactory.initElements(this.driver, JapanCoverageMapPage.class);
    }
    
    /**
     * Allows the user to click the 'privacy' link in the footer
     * 
     * @return Privacy page
     */
    public PrivacyPage clickPrivacy() {
    	footerBlock.findElement(By.linkText("Privacy Policies")).click();
    	log.info("Privacy link clicked");
    	return PageFactory.initElements(this.driver, PrivacyPage.class);
    }
    
    /**
     * Allows the user to click the 'support' link in the footer
     * 
     * @return Support page
     */
    public SupportPage clickSupport() {
    	footerBlock.findElement(By.linkText("Support")).click();
    	log.info("Support clicked");
    	return PageFactory.initElements(this.driver, SupportPage.class);
    }
    
    /**
     * Allows the user to click the 'submit AP' link in the footer
     * 
     * @return Submit AP page
     */
    public SubmitAPPage clickSubmitAP() {
    	footerBlock.findElement(By.linkText("Submit an Access Point")).click();
    	log.info("Submit an Access Point clicked");
    	return PageFactory.initElements(this.driver, SubmitAPPage.class);
    }
    
    /**
     * Allows the user to click the 'speak with us' link in the footer
     * 
     * @return Speak with us page
     */
    public SpeakWithUsPage clickSpeakWithUs() {
    	footerBlock.findElement(By.linkText("Speak with us")).click();
    	log.info("Speak with us clicked");
    	return PageFactory.initElements(this.driver, SpeakWithUsPage.class);
    }
	
	public String[] getSocialIcons() {
		String[] iconArray = new String[4];
		List<WebElement> wbList = new ArrayList<WebElement>();
		wbList = footerBlock.findElements(By.xpath(
				"//div[@class='footer-social']/div/a"));
		for (int i=0; i < 4; i++) {
			iconArray[i] = wbList.get(i).getAttribute("href");
		}
		return iconArray;
	}
	
	public String getLinkText_WebsitePrivacy() {
		return footerBlock.findElement(By.cssSelector(
				"#hs_cos_wrapper_footer_v4 > div > div.footer-copyright.clearfix > div > p > a")).getAttribute("href");
	}
	
	public String getLinkText_Truste() {
		return footerBlock.findElement(By.cssSelector(
				"#hs_cos_wrapper_footer_v4 > div > div.footer-copyright.clearfix > div > div > a")).getAttribute("href");
	}
	
	
}
