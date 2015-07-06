package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ViewAccountUsersPage extends AbstractAdminPageObject {

    // Accounts Table
    @FindBy(className = "table")
    private WebElement usersTable;

    public ViewAccountUsersPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.accounts.title"));

	log.info("Title is correct");
    }

    /**
     * Allows the user to click the back to account button
     * 
     * @return View account page
     */
    public ViewAccountPage clickBackToAccount() {
	driver.findElement(By.linkText("Back to Account")).click();
	log.info("Back to Account button clicked");
	return PageFactory.initElements(driver, ViewAccountPage.class);
    }

    /**
     * @return Returns a map with key = user_id and a value containing a list of
     *         all data from the relevant row of the account users table
     */
    public Map<String, List<String>> getUsersMap() {
	HashMap<String, List<String>> usersMap = new HashMap<String, List<String>>();
	WebElement tableBody = this.usersTable.findElement(By.tagName("tbody"));
	List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
	for (WebElement row : tableRows) {
	    List<WebElement> data = row.findElements(By.tagName("td"));
	    List<String> userList = new ArrayList<String>();
	    if (data.size() > 1 ) {
	    	for (int i = 0; i < 5; i++)
	    	userList.add(data.get(i).getText());
		    usersMap.put(userList.get(0), userList);
	    }
	    
	}
	return usersMap;
    }
}
