package com.skyhookwireless.skyhook.pageObjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractPageObject {

    // Properties Files
    public static Properties properties = new Properties();

    // Logging
    protected final Logger log = LogManager.getLogger(getClass().getName().toString());

    // Header and footer
    public Header header;
    public Footer footer;

    // Web driver
    public WebDriver driver;

    // Class loader.
    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    public AbstractPageObject(WebDriver driver) {
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

	// Instantiate header and footer.
	this.header = PageFactory.initElements(driver, Header.class);
	this.footer = PageFactory.initElements(driver, Footer.class);
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
    
    /**
     * Captures a partial screenshot consisting of the input WebElement
     * @param inputElement the element to be captured in the screenshot
     * @throws IOException
     */
    public void getScreenshot(WebElement inputElement, String ssName) throws IOException  {
    File fullScreenshotFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
    Point p = inputElement.getLocation();
    int width = inputElement.getSize().getWidth();
    int height = inputElement.getSize().getHeight();
    BufferedImage bfImage = ImageIO.read(fullScreenshotFile);
    log.info("width: " + width + " height: " + height + " X:"+ p.getX() + " Y:" + p.getY());
    BufferedImage partialbfImage = bfImage.getSubimage(p.getX(), p.getY(), 
    		width, height);
    ImageIO.write(partialbfImage, "png", fullScreenshotFile);
    File newSaveLocation = new File("seleniumScreenshots"+File.separator+ssName+".png");
    FileUtils.copyFile(fullScreenshotFile, newSaveLocation);

    }
    
    /**
     * Run a javascript to move the scroll bar
     * by the dimension provided
     * @param width - horizontal units
     * @param length - vertical units
     */
    public void scrollBy(int width, int length) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy("+
    	Integer.toString(width)+","+Integer.toString(length)+")");
    }

}
