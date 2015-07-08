package automationFramework;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.skyhookwireless.skyhook.pageObjects.CoverageMapPage;
import com.skyhookwireless.skyhook.pageObjects.DevelopersPage;
import com.skyhookwireless.skyhook.pageObjects.DocumentationPage;
import com.skyhookwireless.skyhook.pageObjects.JapanCoverageMapPage;
import com.skyhookwireless.skyhook.pageObjects.LandingPage;
import com.skyhookwireless.skyhook.pageObjects.PatentsPage;
import com.skyhookwireless.skyhook.pageObjects.PrivacyPage;
import com.skyhookwireless.skyhook.pageObjects.ProductsPage;
import com.skyhookwireless.skyhook.pageObjects.SubmitAPPage;
import com.skyhookwireless.skyhook.pageObjects.SupportPage;
import com.skyhookwireless.skyhook.pageObjects.solutionsPages.*;
import com.skyhookwireless.skyhook.pageObjects.aboutPages.*;
import com.skyhookwireless.skyhook.pageObjects.getStartedPages.*;

public class TC_MARKETING_01 extends AbstractTestCase {
	  // Page fields.

    
    private LandingPage landingPage;
    private ProductsPage productsPage;
    private DevelopersPage developersPage;
    private AppsPage appsPage;
    private AdvertisingPage advertisingPage;
    private DevicesPage devicesPage;
    private CompanyPage companyPage;
    private CareersPage careersPage;
    private BlogPage blogPage;
    private GetStartedPage getStartedPage;
    private CustomerLoginPage customerLoginPage;
    private SpeakWithUsPage swuPage;
    private PatentsPage patentsPage;
    private DocumentationPage documentationPage;
    private CoverageMapPage coverageMapPage;
    private JapanCoverageMapPage japanCoverageMapPage;
    private PrivacyPage privacyPage;
    private SupportPage supportPage;
	private SubmitAPPage submitAPPage;
    
    @Test(description = "Test the header of landing page for skyhookwireless.com", enabled = true)
    public void testLandingPage_Header() throws IOException {

	log.entry();

	// Open the login page
	driver.get(root);

	// Initialize Page.
	this.landingPage = PageFactory.initElements(driver, LandingPage.class);

	// Click the logo in the header
	landingPage = landingPage.header.clickLogo();
	
	// Click on products link
	productsPage = landingPage.header.clickProducts();
	landingPage = productsPage.header.clickLogo();
	
	// Click on products - CA link
	productsPage = landingPage.header.clickProducts("CA");
	Assert.assertTrue(productsPage.verifyProducts_ContextAccelerator());
	landingPage = productsPage.header.clickLogo();
	
	// Click on products - PL link
	productsPage = landingPage.header.clickProducts("PL");
	Assert.assertTrue(productsPage.verifyProducts_PrecisionLocation());
	landingPage = productsPage.header.clickLogo();
	
	// Click on solution - Apps
	appsPage = landingPage.header.clickSolutions_Apps();
	landingPage = appsPage.header.clickLogo();
	
	// Click on solution - advertising
	advertisingPage = landingPage.header.clickSolutions_Advertising();
	landingPage = advertisingPage.header.clickLogo();
	
	// Click on solution - devices
	devicesPage = landingPage.header.clickSolutions_Devices();
	landingPage = devicesPage.header.clickLogo();
	
	// Click on about- company
	companyPage = landingPage.header.clickAbout_Company();
	landingPage = companyPage.header.clickLogo();
	
	// Click on about - careers
	careersPage = landingPage.header.clickAbout_Careers();
	landingPage = careersPage.header.clickLogo();
	
	// Click on about - blogs
	blogPage = landingPage.header.clickAbout_Blog();
	landingPage = blogPage.header.clickLogo();
	
	// Click on developers link
	developersPage = landingPage.header.clickDevelopers();
	landingPage = developersPage.header.clickLogo();
	
	// Click on get started
	getStartedPage = landingPage.header.clickGetStarted();
	driver.navigate().back();
	landingPage = developersPage.header.clickLogo();
	
	// Click on get started- customer login
	customerLoginPage = landingPage.header.clickGetStarted_CustomerLogin();
	driver.navigate().back();
	landingPage = developersPage.header.clickLogo();
	
	// Click on get started- speak with us
	swuPage = landingPage.header.clickGetStarted_SpeakWithUs();
	driver.navigate().back();
	landingPage = developersPage.header.clickLogo();
	
    }

    @Test(description = "Test the side icons of landing page for skyhookwireless.com", enabled = true)
    public void testLandingPage_SideIcons() throws IOException {

	log.entry();

	// Open the login page
	driver.get(root);

	// Initialize Page.
	this.landingPage = PageFactory.initElements(driver, LandingPage.class);
	
	// Click the logo in the header
	landingPage = landingPage.header.clickLogo();
	
	String[] iconsLink = landingPage.getRibbonIcons();
	
	Assert.assertEquals("https://twitter.com/skyhook", iconsLink[0]);
	Assert.assertEquals("https://www.facebook.com/Skyhook", iconsLink[1]);
	Assert.assertEquals("https://www.linkedin.com/company/skyhook-wireless", iconsLink[2]);
	Assert.assertEquals("http://www.skyhookwireless.com/#", iconsLink[3]);
    }
    
    @Test(description = "Test the side icons of landing page for skyhookwireless.com", enabled = true)
    public void testLandingPage_Footer() throws IOException {

	log.entry();

	// Open the login page
	driver.get(root);

	// Initialize Page.
	this.landingPage = PageFactory.initElements(driver, LandingPage.class);
	
	// Click the logo in the header
	landingPage = landingPage.header.clickLogo();
	
	// Click on about link
	companyPage = landingPage.footer.clickAbout();
	landingPage = companyPage.header.clickLogo();
	
	// Click on careers link
	careersPage = landingPage.footer.clickCareers();
	landingPage = careersPage.header.clickLogo();
	
	// Click on products link
	productsPage = landingPage.footer.clickProducts();
	landingPage = productsPage.header.clickLogo();
	
	// Click on patents link
	patentsPage = landingPage.footer.clickPatents();
	landingPage = patentsPage.header.clickLogo();
	
	// Click on documentation link
	documentationPage = landingPage.footer.clickDocumentation();
	driver.navigate().back();
	landingPage = patentsPage.header.clickLogo();
	
	// Click on coverage map link
	coverageMapPage = landingPage.footer.clickCoverageMap();
	landingPage = coverageMapPage.header.clickLogo();

	// Click on japn coverage map link
	japanCoverageMapPage = landingPage.footer.clickJapanCoverageMap();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	landingPage = japanCoverageMapPage.header.clickLogo();
	
	// Click on privacy link
	privacyPage = landingPage.footer.clickPrivacy();
	landingPage = privacyPage.header.clickLogo();	
	
	// Click on support link
	supportPage = landingPage.footer.clickSupport();
	landingPage = supportPage.header.clickLogo();	
	
	// Click on submit access point link
	submitAPPage = landingPage.footer.clickSubmitAP();
	landingPage = supportPage.header.clickLogo();
	
	// Click on speak with us link
	swuPage = landingPage.footer.clickSpeakWithUs();
	driver.navigate().back();
	landingPage = swuPage.header.clickLogo();
	
    }
    
    }
