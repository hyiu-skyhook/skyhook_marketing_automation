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

public class ContextAccelerator_AndroidPage extends AbstractPageObject {

    // Resources drop down
    @FindBy(id = "resources-select")
    @CacheLookup
    private WebElement resourcesDropDown;

    public ContextAccelerator_AndroidPage(WebDriver driver) {

	super(driver);

	// Assert URL.
	Assert.assertTrue(driver.getCurrentUrl().contains(properties.getProperty("resources.context.androidLink")));
	log.info("URL is correct");
    }

    /**
     * Allows the user to click the developer resources link on the page
     * 
     * @return The resources page
     */
    public ResourcesPage clickDeveloperResources() {
	driver.findElement(By.linkText("Developer Resources")).click();
	log.info("Developer resources link clicked");
	return PageFactory.initElements(driver, ResourcesPage.class);
    }

    /**
     * Allows the user to select a platform from the project drop down
     * 
     * @param platform
     *            Platform to be selected
     * @return Current Page
     */
    public AbstractPageObject selectResource(Platform platform) {
	Select droplist = new Select(resourcesDropDown);

	if (platform != null) {

	    if (platform.equals(Platform.ANDROID)) {
		droplist.selectByVisibleText("Android");
		log.info("Android resource selected");
		return PageFactory.initElements(driver, ContextAccelerator_AndroidPage.class);
	    } else if (platform.equals(Platform.IOS)) {
		droplist.selectByVisibleText("iOS");
		log.info("iOS resource selected");
		return PageFactory.initElements(driver, ContextAccelerator_IOSPage.class);
	    }
	}

	log.warn("No resource could be selected");
	return this;
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
}
