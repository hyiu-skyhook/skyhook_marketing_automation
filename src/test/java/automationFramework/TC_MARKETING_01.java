package automationFramework;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.skyhookwireless.skyhook.pageObjects.DevelopersPage;
import com.skyhookwireless.skyhook.pageObjects.LandingPage;
import com.skyhookwireless.skyhook.pageObjects.ProductsPage;
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

   
    
    }
