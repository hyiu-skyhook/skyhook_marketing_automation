package automationFramework;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import com.skyhookwireless.myskyhook.pageObjects.*;

/**
 * An example of a my.skyhook automated test case.
 * 
 * @author Evan Sapienza
 * @see AbstractTestCase
 */
public class TestExample extends AbstractTestCase {

    // Setup
    private String validUsername = "bot_tc01@skyhookwirelessqa.com";
    private String validPassword = "password";

    // Page fields.
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    @Test(description = "Example Test", enabled = true)
    public void basicLoginTest() {

	log.entry();

	// Open the login page
	driver.get(root);

	// Initialize Page.
	this.loginPage = PageFactory.initElements(driver, LoginPage.class);
	
	// Enter a valid username and password
	loginPage.typeEmail(this.validUsername);
	loginPage.typePassword(this.validPassword);

	// Verify that username can be entered
	Assert.assertEquals(loginPage.getEmail(), this.validUsername);

	// Verify that the password is masked and can be entered
	Assert.assertEquals(loginPage.getPassword(), this.validPassword);

	// Verify that the login button is displayed
	Assert.assertTrue(loginPage.isLoginButtonDisplayed());

	// Verify that the “I forgot my password” link is displayed
	Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed());

	// Click on the login button. Verify that you are logged in successfully
	dashboardPage = loginPage.clickLoginDashboard();

	// Click on the logout button in the upper left corner. Verify that you
	// are successfully logged out.
	dashboardPage.header.clickLogout();

	log.exit();
    }
}
