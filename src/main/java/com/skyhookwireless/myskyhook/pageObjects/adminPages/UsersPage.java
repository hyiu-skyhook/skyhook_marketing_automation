package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.DashboardPage;

public class UsersPage extends AbstractAdminPageObject {
	// WebElements
	@FindBy(id = "userName")
	private WebElement userName;
	
	@FindBy(id = "email")
	private WebElement emailTextbox;
	
	@FindBy(id = "first_name")
	private WebElement firstNameTextbox;
	
	@FindBy(id = "last_name")
	private WebElement lastNameTextbox;
	
	@FindBy(id = "role_id")
	private WebElement roleSelect;
	
	@FindBy(id = "password")
	private WebElement passwordTextbox;
	
	@FindBy(id = "password2")
	private WebElement repeatPasswordTextbox;
	
	@FindBy(id = "phone")
	private WebElement phoneTextbox;
	
	@FindBy(id = "defer")
	private WebElement deferCheckbox;
	
	@FindBy(id = "cancel")
	private WebElement cancelbtn;
	
	@FindBy(id = "submit_form")
	private WebElement savebtn;
	
	@FindBy(linkText = "ADD USER")
	private WebElement addUserbtn;
	
	// CSS
	String usersTableRow= "body > div.container-fluid.container > div > form > div.admin-table > div.clearfix > table > tbody > tr:nth-child(rowNumber)";
	String usersTableRowRole = usersTableRow + " > td:nth-child(6)";
	String usersTableRowEdit = usersTableRow + " > td:nth-child(7) > a.action-edit.mr10";
	String usersTableRowEmulate = usersTableRow + " > td:nth-child(7) > a.impersonate-icon";
	// Xpath
	String editUserRoleSelector = "//select[@name='role_id']/option";
	
	// Constructor
	public UsersPage(WebDriver driver) {
		super(driver);

		Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.users.title"));
		log.info("Title is correct");
	}
	
	/**
	 * Allow the user to click the field box for entering username
	 * @return this page
	 */
	public UsersPage clickUsernameTextbox(){
		userName.click();
		log.info("username textbox clicked");
		return this;
	}
	
	/**
	 * Allows the user to input text into the username field box
	 * @param inputName
	 * @return this page
	 */
	public UsersPage searchByUsername(String inputName){
		userName.clear();
		userName.sendKeys(inputName + Keys.ENTER);
		return this;
	}
	
	/**
	 * Allows the user to click the add user button
	 * @return this page
	 */
	public UsersPage clickAddUser(){
		addUserbtn.click();;
		return this;
	}
	
	/**
	 * Allows the user to type in a first name
	 * @param input- first name in String format
	 * @return this page
	 */
	public UsersPage typeFirstName(String input){
		firstNameTextbox.clear();
		firstNameTextbox.sendKeys(input);
		return this;
	}
	
	/**
	 * Allows the user to type in a last name
	 * @param input- last name in String format
	 * @return this page
	 */
	public UsersPage typeLastName(String input){
		lastNameTextbox.clear();
		lastNameTextbox.sendKeys(input);
		return this;
	}
	
	/**
	 * Allows the user to type in an email
	 * @param input- email in String format
	 * @return this page
	 */
	public UsersPage typeEmail(String input){
		emailTextbox.clear();
		emailTextbox.sendKeys(input);
		return this;
	}
	
	/**
	 * Allows the user to type in a password
	 * @param input- password in String format
	 * @return this page
	 */
	public UsersPage typePassword(String input){
		passwordTextbox.clear();
		passwordTextbox.sendKeys(input);
		return this;
	}
	
	/**
	 * Allows the user to type in a repeat password/password2
	 * @param input- repeat password in String format
	 * @return this page
	 */
	public UsersPage typeRepeatPassword(String input){
		repeatPasswordTextbox.clear();
		repeatPasswordTextbox.sendKeys(input);
		return this;
	}
	
	/**
	 * Allows the user to type in a phone number
	 * @param input- phone number in String format
	 * @return this page
	 */
	public UsersPage typePhoneNum(String input){
		phoneTextbox.clear();
		phoneTextbox.sendKeys(input);
		return this;
	}
	
	/**
	 * Allows the user to check the 'defer email' check-box
	 * @return this page
	 */
	public UsersPage clickDeferUserEmail() {
		deferCheckbox.click();
		return this;
	}
	
	/**
	 * Allows the user to select a role from the drop-down menu
	 * @param input - the role of the user (user, lead, or admin)
	 * @return this page
	 */
	public UsersPage selectRole(String input) {
		roleSelect.click();
		driver.findElement(By.xpath("//option[@label=\'" + input +"\']")).click();
		return this;
	}
	
	/**
	 * Allows the user to click the cancel button
	 * @return this page
	 */
	public UsersPage clickCancel() {
		cancelbtn.click();
		return this;
	}
	
	/**
	 * Allows the user to click the save/save changes button
	 * @return this page
	 */
	public UsersPage clickSave() {
		savebtn.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.info("Sleeping one second to allow the page to save");
		}
		return this;
	}

	/**
	 * Allows the admin account to emulate another user
	 * based on the row number provided
	 * @return dashboard page of the emulated user
	 */
	public DashboardPage emulateUser(String rowNum) {
		String emulatePath = usersTableRowEmulate.replace("rowNumber", rowNum);
		driver.findElement(By.cssSelector(emulatePath)).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(emulatePath)));
		return PageFactory.initElements(driver, DashboardPage.class);
	}
	
	
	/** 
	 * Allows the admin account to edit an existing user
	 * based on the row number given
	 * @return edit users page 
	 */
	public UsersPage editUser(String rowNum) {
		String editPath = usersTableRowEdit.replace("rowNumber", rowNum);
		driver.findElement(By.cssSelector(editPath)).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(editPath)));
		return PageFactory.initElements(driver, UsersPage.class);
	}

	/**
	 * Allows the admin account to disable multi-auth for an user with the 
	 * functionality enabled
	 * @return this page
	 */
	public UsersPage disableMultiAuth() {
		try{
			driver.findElement(By.xpath("//a[@class='multiauth-icon']")).click();
			log.info("Disable multi auth button clicked");
			Thread.sleep(2000);
		} catch(Exception e){
			log.error("Cannot find the 'multi-auth' icon. Search query returned no result");
		}
		return this;
	}
	
	/**
	 * Returns the row based on the row number given
	 */
	public String getRole(String inputRow) {
		String rolePath = usersTableRowRole.replace("rowNumber", inputRow);
		return driver.findElement(By.cssSelector(rolePath)).getText();
	}
	
	/**
	 * Return the number of different roles in the role selector box
	 * @return
	 */
	public int getNumberOfRoles() {
		return driver.findElements(By.xpath(editUserRoleSelector)).size();
	}
	
	/**
	 * Verify if the multi-auth icon is still available for the user
	 * @return
	 */
	public boolean isMultiAuthPresent() {
		return this.isElementPresent(By.xpath("//a[@class='multiauth-icon']")); 
	}
	
	/**
	 * Verify if the defer mail icon is available for the user
	 */
	public boolean isDeferPresent() {
		return this.isElementPresent(By.xpath("//a[@class='action-invite']"));
	}
	
	/**
	 * Verify if the text box specified by the input ID
	 * is visible on the page
	 * @return
	 */
	public boolean isUsersTextboxPresent(String inputText) {
		return (this.isElementPresent(By.id(inputText)));
	}
	
	/**
	 * Verify if a button with the link text that is the same
	 * as the input is present
	 * @param inputText
	 * @return
	 */
	public boolean isButtonPresent(String inputText) {
		return (this.isElementPresent(By.linkText(inputText)));
	}
	
	/**
	 * Compare the previously inputed values with the current values
	 * @param firstNameIn 
	 * @param lastNameIn
	 * @param roleIn
	 * @param phoneIn
	 */
	public void compareCurrentWithPreviouslyUpdatedFields(String firstNameIn, String lastNameIn,
			 String roleIn, String phoneIn) {
		
		Assert.assertEquals(firstNameTextbox.getAttribute("value"), firstNameIn);
		Assert.assertEquals(lastNameTextbox.getAttribute("value"), lastNameIn);
		Assert.assertEquals(
				driver.findElement(By.xpath("//select[@id='role_id']/option[@selected='selected']")).getAttribute("label"), roleIn);
		Assert.assertEquals(phoneTextbox.getAttribute("value"), phoneIn);
		log.info("The current user settings match with the previously inputed values");
	}
}
