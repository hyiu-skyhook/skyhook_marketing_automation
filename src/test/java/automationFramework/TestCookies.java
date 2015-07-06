package automationFramework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.PageFactory;

import com.skyhookwireless.myskyhook.pageObjects.*;

/**
 * * Basic test to show how to use TestNG, Page Objects, and Page Factory
 * 
 * @author Evan Sapienza
 * @see AbstractTestCase
 */
public class TestCookies extends AbstractTestCase {

    private String userName;
    private String password;

    // Page fields.
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeClass
    public void setUp() {
	userName = properties.getProperty("user.userName");
	password = properties.getProperty("user.password");
    }

    // Check the checkbox, log in, and validate that the expiration date on the
    // cookie is correct (1 week away).
    @Parameters({ "browser" })
    @Test(description = "Validating that if 'remember me' is checked that the cookie expires in 7 days")
    public void testCookies1() {

	log.info("Start: Selenium_PortalTesting_TestCookies1");

	// Open the login page
	driver.get(root);

	log.info("Login page loaded");

	// Initialize Page.
	this.loginPage = PageFactory.initElements(driver, LoginPage.class);

	log.info("LoginPage object instantiated.");

	// Sign into the dashboard.
	this.dashboardPage = loginPage.signInUserWithProject(userName, password);

	log.info("Dashboard page loaded");

	// Get the session cookie.
	Cookie myCookie = driver.manage().getCookieNamed("PHPSESSID");

	// Get the expiration date from the cookie.
	Date expirationDate = myCookie.getExpiry();

	// Set todays date to a week in the future.
	Date currentDate = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(currentDate);
	cal.add(Calendar.DAY_OF_MONTH, 7);

	// Format the dates to ignore the time.
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	// Assert that the two date strings are the same.
	try {
	    Assert.assertEquals(formatter.parseObject(formatter.format(cal.getTime())).toString(), formatter
		    .parseObject(formatter.format(expirationDate)).toString());
	} catch (ParseException e) {
	    Assert.fail(e.getMessage());
	}

	dashboardPage.header.clickLogout();

	log.info("Successfully logged out");

	log.info("End: Selenium_PortalTesting_TestCookies1");

    }

    // Log in and validate that the expiration date on the cookie is correct
    // (today's date).
    @Parameters({ "browser" })
    @Test(description = "validating that if a user logs in without checking the 'remember me' checkbox that the cookie expires the same day")
    public void testCookies2() {

	log.info("Start: Selenium_PortalTesting_TestCookies2");

	// Open the login page
	driver.get(root);

	log.info("Login page loaded");

	// Initialize Page.
	this.loginPage = PageFactory.initElements(driver, LoginPage.class);

	log.info("LoginPage object instantiated.");

	log.info("Checkbox checked");

	// Sign into the dashboard.
	this.dashboardPage = loginPage.signInUserWithProject(userName, password);

	log.info("Dashboard page loaded");

	// Get the session cookie.
	Cookie myCookie = driver.manage().getCookieNamed("PHPSESSID");

	// Get the expiration date from the cookie.
	Date expirationDate = myCookie.getExpiry();

	// Set todays date to a week in the future.
	Date currentDate = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(currentDate);

	// Format the dates to ignore the time.
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	// Assert that the two date strings are the same.
	try {
	    Assert.assertEquals(formatter.parseObject(formatter.format(cal.getTime())).toString(), formatter
		    .parseObject(formatter.format(expirationDate)).toString());
	} catch (ParseException e) {
	    Assert.fail(e.getMessage());
	}

	log.info("End: Selenium_PortalTesting_TestCookies2");
    }
}
