package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractAdminPageObject {

    // Properties Files
    public static Properties properties = new Properties();

    // Logging
    protected final Logger log = LogManager.getLogger(getClass().getName().toString());

    // Header
    public AdminHeader header;

    // Web driver
    public WebDriver driver;

    // Class loader.
    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    public AbstractAdminPageObject(WebDriver driver) {
	// Load properties files
	try (InputStream resourceStream = loader.getResourceAsStream("prop.properties")) {
	    properties.load(resourceStream);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	Properties properties2 = new Properties();

	// Load properties files
	try (InputStream resourceStream = loader.getResourceAsStream("locators.properties")) {
	    properties2.load(resourceStream);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	properties.putAll(properties2);

	this.driver = driver;

	// Warn if the words 'error' or 'warning' show up on the page.
	String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();
	if (bodyText.contains("error"))
	    log.warn(String.format("The word 'error' is on the page: %s.java", this.getClass().getSimpleName()));
	if (bodyText.contains("warn"))
	    log.warn(String.format("The word 'warn' is on the page: %s.java", this.getClass().getSimpleName()));

	// Instantiate header.
	this.header = PageFactory.initElements(driver, AdminHeader.class);
    }

    protected boolean isElementPresent(By by) {
	try {
	    driver.findElement(by);
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }
    
    /**
     * @param link
     *            The link to be checked
     * @return Returns true if the link is present on the page
     */
    public boolean isLinkTextPresent(String link) {
	return this.isElementPresent(By.linkText(link));
    }
    
    /**
     * @param url
     *            The url to be checked
     * @return Returns true if the link url is present on the page
     */
    public boolean isLinkURLPresent(String url) {
	List<WebElement> links = driver.findElements(By.tagName("a"));
	for (WebElement link : links) {
	    String webURL = link.getAttribute("href");
	    
	    if (webURL.matches("(.*).skyhookwireless.com/" + Pattern.quote(url)))
		return true;
	}
	return false;
    }

}
