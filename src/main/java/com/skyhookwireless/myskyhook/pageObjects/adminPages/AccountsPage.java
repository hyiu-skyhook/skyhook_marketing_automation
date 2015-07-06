package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AccountsPage extends AbstractAdminPageObject {

    /* Web Elements */

    // Account search field
    @FindBy(id = "accountName")
    private WebElement accountTextBox;
    
    // Company search field
    @FindBy(id = "company_name")
    private WebElement companyName;
    
    // Project name
    @FindBy(id = "project_name")
    private WebElement projectNameTextbox;
    
    // Product type
    @FindBy(id = "product_type")
    private WebElement productSelector;
    
    // Platform type
    @FindBy(id = "platform")
    private WebElement platformSelector;
    
    // Realm Name
    @FindBy(id = "realm_name")
    private WebElement realmTextbox;
    
    // Submit button
    @FindBy (id = "submit")
    private WebElement submitButton;

    // Cancel button
    @FindBy (id = "cancel")
    private WebElement cancelButton;
    
    // Accounts Table
    @FindBy(className = "admin-table")
    private WebElement accountsTable;
    
    // Accounts Table
    @FindBy(className = "js-pagination")
    @CacheLookup
    private WebElement accountsPageNumber;
    
    // The Users Tab
    @FindBy( linkText = "USERS")
    private WebElement usersSettings;
    
    // The Statistics Tab
    @FindBy( linkText = "STATISTICS")
    private WebElement statisticsSettings;
    
    // The Files tab
    @FindBy( linkText = "FILES")
    private WebElement downloadFiles;
    
    // The SVN tab
    @FindBy( linkText = "SVN")
    private WebElement adminSVN;
    
    // The Batch tab
    @FindBy( linkText = "BATCH")
    private WebElement adminBatch;
    
    // The Alerts tab
    @FindBy( linkText = "ALERTS")
    private WebElement adminAlerts;
    
    // Yes Button
    @FindBy( linkText = "YES")
    private WebElement yesButton;
    
    // No Button
    @FindBy( linkText = "NO")
    private WebElement noButton;
    
    // Add Account Button
    @FindBy( linkText = "ADD ACCOUNT")
    private WebElement addAccountButton;
    
    // Acount Users Button
    @FindBy( linkText = "ACCOUNT USERS")
    private WebElement accountUsersButton;
    
    // Merge Account Button
    @FindBy( linkText = "MERGE ACCOUNT")
    private WebElement mergeAccountButton;
    
    // Add project Button
    @FindBy( linkText = "ADD PROJECT")
    private WebElement addProjectButton;
    
    // Add user to account button
    @FindBy( linkText = "ADD USER TO ACCOUNT")
    private WebElement addUserToAccountButton;
    
    // Back to account
    @FindBy( linkText = "BACK TO ACCOUNT")
    private WebElement backToAccountButton;
    
    // All Actions button
    @FindBy( linkText = "ALL ACTIONS")
    private WebElement allActionsButton;
    
    // Email
    @FindBy( name = "email")
    private WebElement email;

    // CSS Paths.


    // XPaths
    By pageRightLink = By.xpath(properties.getProperty("admin.accounts.pageRightPath"));
    By rightEndLink  = By.xpath(properties.getProperty("admin.accounts.rightEndPath"));
    By pageLeftLink  = By.xpath(properties.getProperty("admin.accounts.pageLeftPath"));
    By leftEndLink   = By.xpath(properties.getProperty("admin.accounts.leftEndPath"));
    By pageNavBar = By.xpath("//div[@id='paginator']");
    By logEntriesCount = By.xpath("//div[@class='pagination-info']/i[2]");
    By createProjectButton = By.xpath("//div[@class='form-styled-actions']/input[@id='submit']");
    By accountEditButton = By.xpath("//a[@class='action-edit mr10']");

    
    // String
    String accountListTable = "//div[@class='admin-table']/div[@class='clearfix']/table/tbody";
    String accountName = accountListTable + "/tr[rowNumber]/td[2]/a";
    String accountUsersIcon = accountListTable + "/tr[rowNumber]" + "/td[4]/a[@class='action-users mr10']";
    String accountEditIcon = accountListTable + "/tr[rowNumber]" + "/td[4]/a[@class='action-edit mr10']";
    String accountUsersTableRow = "//div[@class='admin-table']/form/div[@class='clearfix']/table/tbody/tr[rowNumber]";
    String accountUsersTableRowID = accountUsersTableRow + "/td[1]";
    String accountUsersTableRowEmail = accountUsersTableRow + "/td[2]/a";
    String accountUsersTableRowRole = accountUsersTableRow + "/td[5]";
    String accountUsersTableRowSetMaster = accountUsersTableRow + 
    		"/td[6]/a[@class='js-accountmaster-action action-setaccountmaster mr10']";
    String accountUsersTableRowRemoveMaster = accountUsersTableRow + 
    		"/td[6]/a[@class='js-accountmaster-action action-removeaccountmaster mr10']";
    String accountUsersTableRowRemoveUser = accountUsersTableRow + "/td[6]/a[@class='delete-user']";
    String accountUsersTableRowUnAuthUser = accountUsersTableRow + "/td[6]/a[@class='multiauth-icon']";
    String accountLogTable = "//div[@class='admin-table']/table/tbody";
    String mergeAccountTableRow = "//*[@id='accountlist']/table/tbody/tr[5]/td/table/tbody/tr[1]/td[rowNumber]/a";
    String accountProjectTableRow = "//table[@class='table table-no-bg']/tbody/tr";
    String accountProjectTableRowProject = accountProjectTableRow + "[@class='device']/td[@class='tdfirst app_name']/a";
    String keyDeactivationField = "//div[@id='deactivation_date']/input";
  

    public AccountsPage(WebDriver driver) {

	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.accounts.title"));

	log.info("Title is correct");

    }

    /**
     * Allows the user to click the add account button
     * 
     * @return Add Account Page
     */
    public AccountsPage clickAddAccount() {
	addAccountButton.click();
	log.info("Add account button clicked");
	return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to click the add project button
     * 
     * @return Add Project Page
     */
    public AccountsPage clickAddProject() {
	addProjectButton.click();
	log.info("Add project button clicked");
	return PageFactory.initElements(driver, AccountsPage.class);
    }
    

    /**
     * Allows the user to click the page right
     * 
     * @return View Account Page
     */
    public AccountsPage clickPageRight() {
	driver.findElement(pageRightLink).click();
	log.info("Page right button clicked");
	return PageFactory.initElements(driver, AccountsPage.class);
    }

    /**
     * Allows the user to click the end of pages
     * 
     * @return View Account Page
     */
    public AccountsPage clickRightEnd() {
	driver.findElement(rightEndLink).click();
	log.info("Right End button clicked");
	return PageFactory.initElements(driver, AccountsPage.class);
    }

    /**
     * Allows the user to click the page left
     * 
     * @return View Account Page
     */
    public AccountsPage clickPageLeft() {
	driver.findElement(pageLeftLink).click();
	log.info("Page left button clicked");
	return PageFactory.initElements(driver, AccountsPage.class);
    }

    /**
     * Allows the user to click the start of pages
     * 
     * @return View Account Page
     */
    public AccountsPage clickLeftEnd() {
	driver.findElement(leftEndLink).click();
	log.info("Left end button clicked");
	return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows to user to confirm adding a new account
     * @return
     */
    public AccountsPage clickSaveChanges() {
    submitButton.click();
    log.info("New account added");
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows to user to confirm cancel adding a new account
     * @return
     */
    public AccountsPage clickCancel() {
    cancelButton.click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows to user to click the yes button
     * @return
     */
    public AccountsPage clickYes() {
    yesButton.click();
    waitForPopupToClose();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows to user to click the yes button
     * @return
     */
    public AccountsPage clickNo() {
    noButton.click();
    waitForPopupToClose();
    return PageFactory.initElements(driver, AccountsPage.class); 
    }
    
    /**
     * Allows the user to click on the account user icon based on the row
     * number given
     * @param rowNum - row's number after excluding the header row
     * @return
     */
    public AccountsPage clickAccountUsersIcon(String rowNum) {
    String accountUsers = accountUsersIcon.replace("rowNumber", rowNum);
    driver.findElement(By.xpath(accountUsers)).click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to click on the account edit icon based on the row
     * number given
     * @param rowNum - row's number after excluding the header row
     * @return
     */
    public AccountsPage clickAccountEditIcon(String rowNum) {
    String accountEdit = accountEditIcon.replace("rowNumber", rowNum);
    driver.findElement(By.xpath(accountEdit)).click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to click the account user button
     * @return
     */
    public AccountsPage clickAccountUsers() {
    accountUsersButton.click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to add user to account
     */
    public AccountsPage clickAddUserToAccount() {
    addUserToAccountButton.click();
    return PageFactory.initElements(driver,  AccountsPage.class);
    }
    
    /**
     * Allows the user to click on the back to account button
     * @return
     */
    public AccountsPage clickBackToAccount() {
    backToAccountButton.click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to click on the "All Actions" button
     * @return
     */
    public AccountsPage clickAllActions() {
    allActionsButton.click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows the user to click on "Merge Account" button
     * @return
     */
    public AccountsPage clickMergeAccount() {
    mergeAccountButton.click();
    return PageFactory.initElements(driver, AccountsPage.class);
    }
    
    /**
     * Allows to user to click the set account master button 
     * for the row specified
     */
    public AccountsPage clickSetAccountMaster(String rowNum) {
    String accountMaster = accountUsersTableRowSetMaster.replace("rowNumber", rowNum);
    waitForPopupToClose();
    driver.findElement(By.xpath(accountMaster)).click();
    return PageFactory.initElements(driver, AccountsPage.class);	
    }
    
    /**
     * Allows to user to click the un-set account master button 
     * for the row specified
     */
    public AccountsPage clickRemoveAccountMaster(String rowNum) {
    String accountMaster = accountUsersTableRowRemoveMaster.replace("rowNumber", rowNum);
    waitForPopupToClose();
    driver.findElement(By.xpath(accountMaster)).click();
    return PageFactory.initElements(driver, AccountsPage.class);	
    }

    /**
     * Allows to user to click the remove account user button 
     * for the row specified
     */
    public AccountsPage clickRemoveUser(String rowNum) {
    String accountUser = accountUsersTableRowRemoveUser.replace("rowNumber", rowNum);
    driver.findElement(By.xpath(accountUser)).click();
    return PageFactory.initElements(driver, AccountsPage.class);	
    }
    
	/**
	 * Allows the user to click the account name based on the row specified
	 * in the accounts listed for account merging
	 */
	public AccountsPage clickMergedAccountName(String rowNum) {
	String accountName = mergeAccountTableRow.replace("rowNumber", rowNum);
	driver.findElement(By.xpath(accountName)).click();
	return this;
	}
	
	/**
	 * Allows the user to click on a random project that will return
	 * the key de-activation screen
	 * @return
	 */
	public AccountsPage clickRandomAccountProject() {
	driver.findElement(By.xpath(accountProjectTableRowProject)).click();
	return PageFactory.initElements(driver, AccountsPage.class);
	}
   
    /**
     * Allows the user to search for particular page
     * 
     * @param pageNumber
     *            The page number to search for 
     * @return View account list page
     */
    public AccountsPage searchPage(String pageNumber) {
	accountsPageNumber.sendKeys(Keys.BACK_SPACE);
	accountsPageNumber.sendKeys(pageNumber);
	accountsPageNumber.sendKeys(Keys.RETURN);
	log.info("Searching for page number " + pageNumber);
	return PageFactory.initElements(driver, AccountsPage.class);
    }

    /**
     * Allows the user to search for an account
     * 
     * @param accountName
     *            The account name to search for (realm id may also be used)
     * @return View account page
     */
    public AccountsPage searchAccount(String accountName) {
	accountTextBox.sendKeys(accountName);
	accountTextBox.sendKeys(Keys.RETURN);
	log.info("Searching for account " + accountName);
	return PageFactory.initElements(driver, AccountsPage.class);
    }

    /**
     * Allows the user to view an account that is present in the accounts table
     * 
     * @param name
     *            The name of the account
     * @param realmId
     *            One of the realm id's of the account
     * @param accountId
     *            The id of the account
     * @return View account page
     */
    public ViewAccountPage viewAccount(String name, String realmId, String accountId) {
	WebElement row = this.getRowElement(name, realmId, accountId);
	row.findElement(accountEditButton).click();
	log.info("Loading view account page for account name: " + name);
	return PageFactory.initElements(driver, ViewAccountPage.class);
    }
    
    /**
	 * Allows the user to go the 'Users' section under the admin account
	 * @return the Users Page
	 */
	public UsersPage viewUsersSettings() {
	usersSettings.click();
	return PageFactory.initElements(driver, UsersPage.class);
	}
	
	/**
	 * Allows the user to go the 'Alerts' section under the admin account
	 * @return the Alerts Page
	 */
    public AlertsPage viewAdminAlerts() {
    adminAlerts.click();
    log.info("Alerts button clicked");
    return PageFactory.initElements(driver, AlertsPage.class);
    }
	
    /**
	 * Allows the user to go the 'Statistics' section under the admin account
	 * @return the Statistics Page
	 */
	public StatisticsPage viewAdminStatistics() {
	statisticsSettings.click();
	return PageFactory.initElements(driver, StatisticsPage.class);
	}
	
	 /**
	  * Allows the user to go the 'Statistics' section under the admin account
	  * @return the Statistics Page
	  */
	public FilesPage viewAdminFiles() {
	downloadFiles.click();
	return PageFactory.initElements(driver, FilesPage.class);
	}

	/**
	 * Allows the user to go the 'SVN' section under the admin account
	 * 
	 * @return the Statistics Page
	 */
	public SVNPage viewAdminSVN() {
	adminSVN.click();
	return PageFactory.initElements(driver, SVNPage.class);
	}
	
	/**
	 * Allows the user to go the 'Batch' section under the admin account
	 * 
	 * @return the Batch Page
	 */
	public BatchPage viewAdminBatch() {
	adminBatch.click();
	return PageFactory.initElements(driver, BatchPage.class);
	}
	
	/**
	 * Allows the user to type in an account name
	 * 
	 * @return the current page
	 */
	public AccountsPage typeAccountName(String inputAccount) {
	companyName.sendKeys(inputAccount);
	return PageFactory.initElements(driver, AccountsPage.class);
	}
	
	/**
	 * Allows the user to type in an email address
	 * @param inputEmail
	 * @return the accounts page
	 */
	public AccountsPage typeEmail(String inputEmail) {
	email.clear();
	email.sendKeys(inputEmail);
	return PageFactory.initElements(driver, AccountsPage.class);
	}
	
	/**
	 * Gets the account name based on the row number provided
	 * 
	 * @param rowNum - row that the account is on (exclude the header)
	 * @return
	 */
	public String getAccountName(String rowNum) {
	String rowAccountName = accountName.replace("rowNumber", rowNum);
	return driver.findElement(By.xpath(rowAccountName)).getText();
	}
	
	/**
	 * Get the account user id based on the row number
	 * @param rowNum- row that the account is on (exclude the header)
	 * @return
	 */
	public String getAccountID(String rowNum) {
	String accountID = accountUsersTableRowID.replace("rowNumber", rowNum);
	return driver.findElement(By.xpath(accountID)).getText();
	}
	
	/**
	 * Get the account user information based on the row number
	 * @param rowNum- row that the account is on (exclude the header)
	 * @return
	 */
	public String getAccountEmail(String rowNum) {
	String accountEmail = accountUsersTableRowEmail.replace("rowNumber", rowNum);
	return driver.findElement(By.xpath(accountEmail)).getText();
	}
	
	/**
	 * Get the account user role based on the row number
	 * @param rowNum- row that the account is on (exclude the header)
	 * @return
	 */
	public String getAccountRole(String rowNum) {
	String accountRole = accountUsersTableRowRole.replace("rowNumber", rowNum);
	return driver.findElement(By.xpath(accountRole)).getText();
	}
	
	/**
	 * Get the number of log entries
	 * @return
	 */
	public int getAccountLogEntryCount() {
	int logEntriesNum = Integer.parseInt(driver.findElement(logEntriesCount).getText());
	return logEntriesNum;
	}
	
	/**
	 * Get the number of project counts for the account
	 * @return
	 */
	public int getAccountProjectCount() {
	int accountTotal = driver.findElements(By.xpath(accountProjectTableRow)).size();
	return accountTotal;
	}
	
	/**
	 * Verify if the button indicated by the input linkName
	 * is present
	 * @param inputText
	 * @return
	 */
	public boolean isAccountButtonPresent(String inputText) {
	if (this.isElementPresent(By.linkText(inputText)))
		return true;
	else
		return false;
	}
	
	/**
	 * Verify if the icon to remove multi-auth is present
	 * for user who has the feature enabled
	 * @param rowNum
	 * @return
	 */
	public boolean isRemoveAuthIconPresent(String rowNum) {
	String pathToUnAuth = accountUsersTableRowUnAuthUser.replace("rowNumber", rowNum);
	if (this.isElementPresent(By.xpath(pathToUnAuth)))
		return true;
	else
		return false;	
	}
	
	/**
	 * Verify if the delete icon is present
	 * in the row specified
	 * @param rowNum
	 * @return
	 */
	public boolean isDeleteIconPresent(String rowNum) {
	String pathToDelete = accountUsersTableRowRemoveUser.replace("rowNumber", rowNum);
	if (this.isElementPresent(By.xpath(pathToDelete)))
		return true;
	else
		return false;
	}
	
	/**
	 * Verify if the deactivation date field is present
	 * @return
	 */
	public boolean isDeactivationDatePresent() {
	return this.isElementPresent(By.xpath(keyDeactivationField));
	}
	
	/**
	 * Verify if all the buttons expected to appear for the admin account page
	 * are visible/present
	 * @param inputClassname
	 * @return
	 */
	public boolean areAccountButtonsPresent() {
	if (this.isElementPresent(By.linkText("ACCOUNT USERS")) && this.isElementPresent(By.linkText("MERGE ACCOUNT"))
			&& this.isElementPresent(By.linkText("ADD PROJECT")) && this.isElementPresent(By.linkText("ALL ACTIONS")))
		return true;
	else
		return false;
	}
	
	/**
	 * Create a project based on the parameters
	 * @param projectName - the name of the project
	 * @param product - which product line
	 * @param platform - which operating system
	 * @param realm
	 * @return
	 */
	public AccountsPage createProject(String projectName, String product, String platform) {
	projectNameTextbox.sendKeys(projectName);
	productSelector.sendKeys(product);
	platformSelector.sendKeys(platform);
	driver.findElement(createProjectButton).click();
	return PageFactory.initElements(driver, AccountsPage.class);
	}
	
	/**
	 * Get the header of the page
	 * @return
	 */
	public void verifyActionLog(int prevCount) {
	Assert.assertTrue(driver.findElement(By.xpath("//h3")).getText().contains("Logged actions"));
	int logEntriesNum = Integer.parseInt(driver.findElement(logEntriesCount).getText());
	Assert.assertTrue( logEntriesNum - prevCount == 5);
	if (logEntriesNum >= 10 ) {
		Assert.assertTrue(this.isElementPresent(pageNavBar));
		Assert.assertTrue(driver.findElements(By.xpath(accountLogTable + "/tr")).size() == 10);
	}
	}
	
	
	/**
	 * Wait for any popup element to close
	 * with a default wait time of 5 seconds
	 */
	public void waitForPopupToClose() {
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='popup-overlay']")));
	WebDriverWait wait2 = new WebDriverWait(driver, 5);
	wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='popup']")));	
	}
	
	/**
	 * Wait for any popup element to close based 
	 * on the user-inputted waitTime
	 */
	public void waitForPopupToClose(int waitTime) {
	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='popup-overlay']")));
	WebDriverWait wait2 = new WebDriverWait(driver, waitTime);
	wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='popup']")));
	}
	
    /**
     * Gets the relevant row from the accounts table
     * 
     * @param name
     *            The name of the account
     * @param realmId
     *            The realm id of the account
     * @param acconutId
     *            The account id of the account
     * @return
     */
    private WebElement getRowElement(String name, String realmId, String accountId) {
	WebElement tableBody = this.accountsTable.findElement(By.tagName("tbody"));
	List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
	for (WebElement row : tableRows) {
	    List<WebElement> data = row.findElements(By.tagName("td"));
	    String row_accountId = data.get(0).getText();
	    String row_accountName = data.get(1).getText();
	    String row_realms = data.get(2).getText();

	    if (row_accountId.equals(accountId) && row_accountName.equals(name) && row_realms.contains(realmId)) {
		return row;
	    }
	}
	return null;
    }
    
}
