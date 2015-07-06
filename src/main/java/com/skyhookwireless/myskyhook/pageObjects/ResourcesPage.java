package com.skyhookwireless.myskyhook.pageObjects;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.skyhookwireless.myskyhook.pageObjects.resourcePages.*;

/**
 * ResourcesPage is a page object used to model the user interface of the
 * resources page of the my.skyhook web portal.
 * 
 * @author Skyhook Wireless
 */
public class ResourcesPage extends AbstractPageObject {

    // CSS Paths.
    private By contextAcceleratorLinks = By.cssSelector(properties.getProperty("resources.context.linksTray"));
    private By precisionLocationLinks = By.cssSelector(properties.getProperty("resources.precision.linksTray"));
    private By hyperlocalLinks = By.cssSelector(properties.getProperty("resources.hyperlocal.linksTray"));
    private By certifiedForWebLinks = By.cssSelector(properties.getProperty("resources.certified.linksTray"));

    /**
     * Constructor for ResourcesPage object
     * 
     * @param driver
     *            The webdriver used to navigate the page
     */
    public ResourcesPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("resources.title"));

    }

    /**
     * Allows the user to click the iOS link for certified location
     * 
     * @return iOS page for context accelerator
     */
    public ContextAccelerator_IOSPage clickContextIOS() {

	driver.findElement(contextAcceleratorLinks).findElement(
		By.cssSelector(properties.getProperty("resources.context.linksTray")
			+ properties.getProperty("resources.context.ios"))).click();
	log.info("Context Accelerator iOS link clicked");
	return PageFactory.initElements(driver, ContextAccelerator_IOSPage.class);
    }
    
    /**
     * Allows the user to click the Android link for certified location
     * 
     * @return Current Page
     */
    public ContextAccelerator_AndroidPage clickContextAndroid() {

	driver.findElement(contextAcceleratorLinks).findElement(
		By.cssSelector(properties.getProperty("resources.context.linksTray")
			+ properties.getProperty("resources.context.android"))).click();
	log.info("Context Accelerator Android link clicked");
	return PageFactory.initElements(driver, ContextAccelerator_AndroidPage.class);
    }

    /**
     * Allows the user to click the MacOS quickstart guide link for precision
     * location
     * 
     * @return MacOS quickstart page for precision location
     */
    public PrecisionLocation_MacOSPage clickPrecisionMacOS() {

	driver.findElement(precisionLocationLinks).findElement(
		By.cssSelector(properties.getProperty("resources.precision.linksTray")
			+ properties.getProperty("resources.precision.macOS"))).click();
	log.info("Precision MacOS link clicked");
	return PageFactory.initElements(driver, PrecisionLocation_MacOSPage.class);
    }

    /**
     * Allows the user to click the Windows quickstart guide link for precision
     * location
     * 
     * @return Windows quickstart page for precision location
     */
    public PrecisionLocation_WindowsPage clickPrecisionWindows() {

	driver.findElement(precisionLocationLinks).findElement(
		By.cssSelector(properties.getProperty("resources.precision.linksTray")
			+ properties.getProperty("resources.precision.windows"))).click();
	log.info("Precision Windows link clicked");
	return PageFactory.initElements(driver, PrecisionLocation_WindowsPage.class);
    }

    /**
     * Allows the user to click the Android quickstart guide link for precision
     * location
     * 
     * @return Android quickstart page for precision location
     */
    public PrecisionLocation_AndroidPage clickPrecisionAndroid() {

	driver.findElement(precisionLocationLinks).findElement(
		By.cssSelector(properties.getProperty("resources.precision.linksTray")
			+ properties.getProperty("resources.precision.android"))).click();
	log.info("Precision Android link clicked");
	return PageFactory.initElements(driver, PrecisionLocation_AndroidPage.class);
    }

    /**
     * Allows the user to click the Linux quickstart guide link for precision
     * location
     * 
     * @return Linux quickstart page for precision location
     */
    public PrecisionLocation_LinuxPage clickPrecisionLinux() {

	driver.findElement(precisionLocationLinks).findElement(
		By.cssSelector(properties.getProperty("resources.precision.linksTray")
			+ properties.getProperty("resources.precision.linux"))).click();
	log.info("Precision Linux link clicked");
	return PageFactory.initElements(driver, PrecisionLocation_LinuxPage.class);
    }

    /**
     * Allows the user to click the RaspberryPi quickstart guide link for
     * precision location
     * 
     * @return Linux quickstart page for precision location
     */
    public PrecisionLocation_LinuxPage clickPrecisionRPI() {

	driver.findElement(precisionLocationLinks).findElement(
		By.cssSelector(properties.getProperty("resources.precision.linksTray")
			+ properties.getProperty("resources.precision.rpi"))).click();
	log.info("Precision RPI link clicked");
	return PageFactory.initElements(driver, PrecisionLocation_LinuxPage.class);
    }

    /**
     * Allows the user to click the MacOS api link for hyperlocal IP
     * 
     * @return Hyperlocal IP API page
     */
    public HyperlocalIP_APIPage clickHyperlocalMacOS() {

	driver.findElement(hyperlocalLinks).findElement(
		By.cssSelector(properties.getProperty("resources.hyperlocal.linksTray")
			+ properties.getProperty("resources.hyperlocal.macOS"))).click();
	log.info("Hyperlocal MacOS link clicked");
	return PageFactory.initElements(driver, HyperlocalIP_APIPage.class);
    }

    /**
     * Allows the user to click the Windows api link for hyperlocal IP
     * 
     * @return Hyperlocal IP API page
     */
    public HyperlocalIP_APIPage clickHyperlocalWindows() {

	driver.findElement(hyperlocalLinks).findElement(
		By.cssSelector(properties.getProperty("resources.hyperlocal.linksTray")
			+ properties.getProperty("resources.hyperlocal.windows"))).click();
	log.info("Hyperlocal Windows link clicked");
	return PageFactory.initElements(driver, HyperlocalIP_APIPage.class);
    }

    /**
     * Allows the user to click the Android quickstart guide link for hyperlocal IP
     * 
     * @return Hyperlocal IP API page
     */
    public HyperlocalIP_APIPage clickHyperlocalAndroid() {

	driver.findElement(hyperlocalLinks).findElement(
		By.cssSelector(properties.getProperty("resources.hyperlocal.linksTray")
			+ properties.getProperty("resources.hyperlocal.android"))).click();
	log.info("Hyperlocal Android link clicked");
	return PageFactory.initElements(driver, HyperlocalIP_APIPage.class);
    }

    /**
     * Allows the user to click the Linux api link for hyperlocal IP
     * 
     * @return Hyperlocal IP API page
     */
    public HyperlocalIP_APIPage clickHyperlocalLinux() {

	driver.findElement(hyperlocalLinks).findElement(
		By.cssSelector(properties.getProperty("resources.hyperlocal.linksTray")
			+ properties.getProperty("resources.hyperlocal.linux"))).click();
	log.info("Hyperlocal Linux link clicked");
	return PageFactory.initElements(driver, HyperlocalIP_APIPage.class);
    }

    /**
     * Allows the user to click the Raspberry PI api link for hyperlocal IP
     * 
     * @return Hyperlocal IP API page
     */
    public HyperlocalIP_APIPage clickHyperlocalRPI() {

	driver.findElement(hyperlocalLinks).findElement(
		By.cssSelector(properties.getProperty("resources.hyperlocal.linksTray")
			+ properties.getProperty("resources.hyperlocal.rpi"))).click();
	log.info("Hyperlocal Raspberry Pi link clicked");
	return PageFactory.initElements(driver, HyperlocalIP_APIPage.class);
    }
    
    /**
     * Allows the user to click the Certified for Web documentation link (zip file)
     * 
     * @return Current Page
     */
    public ResourcesPage clickCertifiedDocumentation() {

	driver.findElement(certifiedForWebLinks).findElement(
		By.cssSelector(properties.getProperty("resources.certified.linksTray")
			+ properties.getProperty("resources.certified.link"))).click();
	log.info("Certified for Web documentation link clicked");
	return this;
    }
    
    /**
     * @return	Returns true if the context accelerator links tray is visible
     */
    public boolean isContextAcceleratorLinksPresent() {
	return driver.findElement(contextAcceleratorLinks).isDisplayed();
    }
    
    /**
     * @return	Returns true if the certified links tray is visible
     */
    public boolean isCertifiedLinksPresent() {
	return driver.findElement(certifiedForWebLinks).isDisplayed();
    }
    
    /**
     * @return	Returns true if the hyperlocal links tray is visible
     */
    public boolean isHyperlocalLinksPresent() {
	return driver.findElement(hyperlocalLinks).isDisplayed();
    }
    
    /**
     * @return	Returns true if the context accelerator links tray is visible
     */
    public boolean isPrecisionLinksPresent() {
	return driver.findElement(precisionLocationLinks).isDisplayed();
    }
}
