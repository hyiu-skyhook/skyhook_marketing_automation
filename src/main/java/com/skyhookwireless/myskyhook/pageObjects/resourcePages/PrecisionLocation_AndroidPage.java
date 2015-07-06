package com.skyhookwireless.myskyhook.pageObjects.resourcePages;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.skyhookwireless.myskyhook.enums.Platform;
import com.skyhookwireless.myskyhook.pageObjects.*;

public class PrecisionLocation_AndroidPage extends AbstractPageObject {

    // Resources drop down
    @FindBy(id = "resources-select")
    @CacheLookup
    private WebElement resourcesDropDown;

    public PrecisionLocation_AndroidPage(WebDriver driver) {

	super(driver);

	// Assert URL.
	Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("resources.precision.androidLink")));
	log.info("URL is correct");

    }

    /**
     * Allows the user to click the resources home button
     * 
     * @return Resources Page
     */
    public ResourcesPage clickResourcesHome() {
	driver.findElement(By.linkText("RESOURCES HOME")).click();
	log.info("Resources home link clicked");
	return PageFactory.initElements(driver, ResourcesPage.class);
    }

    /**
     * Allows the user to select a platform specific resource from the resources
     * drop down
     * 
     * @param platform
     *            Platform to be selected
     * @return Current Page
     */
    public AbstractPageObject selectResource(Platform platform) {
	Select droplist = new Select(resourcesDropDown);

	if (platform != null) {

	    if (platform.equals(Platform.ANDROID)) {
		droplist.selectByVisibleText("Precision Location for Android");
		log.info("Android resource selected");
		return PageFactory.initElements(driver, PrecisionLocation_AndroidPage.class);
	    } else if (platform.equals(Platform.APPLE) || platform.equals(Platform.IOS)) {
		droplist.selectByVisibleText("Precision Location for MacOS");
		log.info("MacOS resource selected");
		return PageFactory.initElements(driver, PrecisionLocation_MacOSPage.class);
	    } else if (platform.equals(Platform.WINDOWS)) {
		droplist.selectByVisibleText("Precision Location for Windows");
		log.info("Windows resource selected");
		return PageFactory.initElements(driver, PrecisionLocation_WindowsPage.class);
	    } else if (platform.equals(Platform.LINUX) || platform.equals(Platform.RASPBERRYPI)) {
		droplist.selectByVisibleText("Precision Location for Linux");
		log.info("Linux resource selected");
		return PageFactory.initElements(driver, PrecisionLocation_LinuxPage.class);
	    }
	}

	log.warn("No resource could be selected");
	return this;
    }
}
