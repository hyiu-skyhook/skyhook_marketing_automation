package automationFramework;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Abstract class to handle the setup/tear down of a test
 * 
 * @author Evan Sapienza
 */
public abstract class AbstractTestCase {

    public WebDriver driver = null;
    public String root = null;

    // Properties Files
    public static Properties properties = new Properties();

    // Logging
    protected final Logger log = LogManager.getLogger(getClass().toString());
    
    // Database Connection
    protected Connection conn = null;

    // Mail Inbox
    protected Inbox mailInbox = null;

    private String browser = null;

    @BeforeClass
    @Parameters({ "browser" })
    public void setUpTest(@Optional String browser) {
	
	ThreadContext.push(UUID.randomUUID().toString());

	// Load properties files
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
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

	if (properties.containsKey("root.secure"))
	    root = properties.getProperty("root.secure");
	else
	    root = properties.getProperty(("root"));

	// Not set.
	if (browser == null)
	    this.browser = properties.getProperty("browser.default"); // Default
								      // browser.
	if (this.browser.equalsIgnoreCase("firefox")) {
	    driver = new FirefoxDriver();
	} else if (this.browser.equalsIgnoreCase("chrome")) {
	    if (System.getProperty("user.dir").contains("target")) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").replace("target", "") + properties.getProperty("chrome.path"));
	    } else {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + properties.getProperty("chrome.path"));
	    }
	    driver = new ChromeDriver();
	    Dimension winSize = new Dimension(1201, 768);
	    driver.manage().window().setSize(winSize);
	}
	
	String db_url = properties.getProperty("db.url");
	String user = properties.getProperty("db.user");
	String password = properties.getProperty("db.password");
	
	// Connect to database
	this.conn = this.connectDB(db_url, user, password);
	
	// Setup inbox
	String host = properties.getProperty("inbox.host");
	String inbox_user = properties.getProperty("inbox.user");
	String inbox_password = properties.getProperty("inbox.password");
	this.mailInbox = new Inbox(host, inbox_user, inbox_password);
    }
        
    @AfterClass
    public void tearDownTest() {
	ThreadContext.pop();
	driver.quit();
    }
    
    @BeforeMethod
    public void setUpMethod() {
	driver.manage().deleteAllCookies();
    }
    
    private Connection connectDB(String db_url, String user, String password) {
	try {
	    
	    Class.forName("com.mysql.jdbc.Driver");
	    
	    return DriverManager.getConnection(db_url, user, password);

	} catch (SQLException ex) {
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    Assert.fail("SQLException: " + ex.getMessage());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return null;
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
    
    /**
     * Takes a full screenshot of the current page
     * @param imageName - name to be provided for the image
     */
    public void getFullScreenshot(String imageName) {
    	File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	String fileSavePath = "seleniumScreenshots"+File.separator+imageName+".png";
    	File newFileLocation = new File(fileSavePath);
    	newFileLocation.getParentFile().mkdirs();
    try {
		FileUtils.copyFile(imageFile, newFileLocation);
		log.info("The image is saved at this location: " + imageFile);
	} catch (IOException e) {
		log.error("Screenshot copy failed");
	}
    }
}
