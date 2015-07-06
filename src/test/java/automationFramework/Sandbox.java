package automationFramework;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.skyhookwireless.myskyhook.pageObjects.LoginPage;
import com.skyhookwireless.myskyhook.pageObjects.adminPages.AccountsPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Sandbox to test framework
 * 
 * @author Evan Sapienza
 * @see AbstractTestCase
 */
public class Sandbox extends AbstractTestCase {

    private String validUsername = "portaladmin@skyhookwirelessqa.com";
    private String validPassword = "password";

    // Page fields.
    private LoginPage loginPage;
    private AccountsPage admin_AccountsPage;

    @Test(description = "Test database connection and accounts search on portal admin", enabled = true)
    public void portlAdminTest() {	

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
	admin_AccountsPage = loginPage.clickLoginAdmin();
	
	// Get random account row
	ResultSet rs = this.getRandomAccount();
	
	String accountId = null;
	String realmId = null;
	String accountName = null;
	
	try {
	    rs.first();
	    accountId = String.valueOf(rs.getInt(1));
	    realmId =  String.valueOf(rs.getInt(2));
	    accountName = rs.getString(3);
	} catch (SQLException ex) {
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}

	admin_AccountsPage = admin_AccountsPage.searchAccount(realmId);
	admin_AccountsPage.viewAccount(accountName, realmId, accountId);

    }

    private ResultSet getRandomAccount() {
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery("SELECT account_id, realmid, display_name "
	    	+ "FROM application "
	    	+ "WHERE realmid > 5000 "
	    	+ "ORDER BY RAND() "
	    	+ "LIMIT 100;");
	    
	    return rs;
	} catch (SQLException ex){
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    Assert.fail("SQLException: " + ex.getMessage());
	}
	
	return null;
    }
}
