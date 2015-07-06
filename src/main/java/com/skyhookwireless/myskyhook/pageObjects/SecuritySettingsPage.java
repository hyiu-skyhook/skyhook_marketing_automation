package com.skyhookwireless.myskyhook.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * SecuritySettingsPage is a page object that represents the edit security section
 * of the my.skyhook web portal
 * 
 * @author heiyiu
 * 
 */
public class SecuritySettingsPage extends AbstractPageObject {
	/** Web Elements **/

	
	private By enableMultiAuth= By.id("enable_multiauth");
	
	@FindBy(id = "confirm_multiauth")
	private WebElement confirmMultiAuthbtn;
	
	@FindBy(id = "cancel_multiauth")
	private WebElement cancelMultiAuthbtn;
	
	@FindBy(id= "email_value")
	private WebElement emailAddressField;
	
	@FindBy(id = "mobile_phone_value")
	private WebElement mobilePhoneField;
	
	@FindBy(id = "verify_enable_code")
	private WebElement verificationCodeField;
	
	@FindBy(id = "submit_form")
	private WebElement submitMultiAuthbtn;
	
	@FindBy(id = "cancel")
	private WebElement cancelSubmitMultiAuthbtn;
	
	@FindBy(id = "disable_multiauth")
	private WebElement disablebtn;
	
	@FindBy(id = "security_disable")
	private WebElement securityCodeDisable;
	
	@FindBy(id = "multiauth_options_email")
	private WebElement emailRadiobtn;
	
	@FindBy(id = "multiauth_options_mobile_phone")
	private WebElement mobileRadiobtn;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutbtn;
	
    public SecuritySettingsPage(WebDriver driver) {
	super(driver);

	// Assert title.
	Assert.assertEquals(driver.getTitle(), properties.getProperty("securitysettings.title"));

	log.info("Title is correct");
    }
    
    // Enable
    /**
     * Allows the user to click the enable multi auth. button
     * 
     * @return the current page
     */
    public SecuritySettingsPage clickEnableMultiAuth() {
    	driver.findElement(enableMultiAuth).click();
    	log.info("Enable multi-step authetication button clicked");
    	return this;
        }
    
    /**
     * Allows the user to click the confirm multi auth. button
     * 
     * @return the current page
     */
    public SecuritySettingsPage confirmEnableMultiAuth() {
    	confirmMultiAuthbtn.click();
    	log.info("Confirm multi-step authetication button clicked");
    	return this;
        }
    
    /**
     * Allows the user to click the cancel multi auth. button
     * 
     * @return the current page
     */
    public SecuritySettingsPage cancelEnableMultiAuth() {
    	cancelMultiAuthbtn.click();
    	log.info("Cancel multi-step authetication button clicked");
    	return this;
        }
    
    /**
     * Allows the user to select the email radio button
     * 
     * @return the current page
     */
    public SecuritySettingsPage clickSelectEmail() {
    	emailRadiobtn.click();
    	return this;
    }
    
    /**
     * Allows the user to enter an email address
     * 
     * @return the current page
     */
    public SecuritySettingsPage typeEmail(String emailAddress) {
    	emailAddressField.clear();
    	emailAddressField.sendKeys(emailAddress);
    	return this;
    }
    
    /**
     * Allows the user to select the mobile phone num radio button
     * 
     * @return the current page
     */
    public SecuritySettingsPage clickSelectMobilePhoneNum() {
    	mobileRadiobtn.click();
    	return this;
    }
    
    /**
     * Allows the user to select the email radio button
     * 
     * @return the current page
     */
    public SecuritySettingsPage typeMobilePhoneNum(String phonenum){
    	mobilePhoneField.clear();
    	mobilePhoneField.sendKeys(phonenum);
    	return this;
    }
    
    /**
     * Allows the user to submit request to enable multi-authentication
     * 
     * @return the current page
     */
    public SecuritySettingsPage submitEnableMultiAuth() {
    	submitMultiAuthbtn.click();
    	log.info("Submit multi-step authetication button clicked");
    	return this;
        }
    
    /**
     * Allows the user to cancel request to enable multi-authentication
     * 
     * @return the current page
     */
    public SecuritySettingsPage cancelSubmitEnableMultiAuth() {
    	cancelSubmitMultiAuthbtn.click();
    	log.info("Cancel submit multi-step authetication button clicked");
    	return this;
        }
    
    /**
     * Allows the user to enter a verification code
     * 
     * @return the current page
     */
    public SecuritySettingsPage typeVerificationCode(String code) {
    	verificationCodeField.clear();
    	verificationCodeField.sendKeys(code);
    	log.info("Verification code entered");
    	return this;
    }
    
    /**
     * Allows the user to request verification code to be resent
     * 
     * @return the current page
     */
    public SecuritySettingsPage clickResendVerificationCode() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.findElement(By.linkText("click here")).click();
    	return this;
    }
    
    /**
     * Allows the user to exit out the verification code step
     * and to restart the multi-authentication process
     * 
     * @return the current page
     */
    public SecuritySettingsPage clickDisableVerificationCode() {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	WebElement disablelink = wait.until(ExpectedConditions.elementToBeClickable(securityCodeDisable));
    	// Double-click is required, this is intended duplication
    	disablelink.click();
    	disablelink.click();
    	return this;
    }
    
    // Disable
    /**
     * Allows the user to disable the multi-authentication
     * 
     * @return the current page
     */
    public SecuritySettingsPage clickDisableMultiAuth() {
    	WebDriverWait wait = new WebDriverWait(driver, 5);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id|@class,'popup')]")));
    	disablebtn.click();
    	log.info("Disable Button Clicked");
    	return this;
    }
    
    /**
     * Allows the user to confirm disabling the multi-authentication
     * 
     * @return the current page
     */
    public SecuritySettingsPage confirmDisableMultiAuth() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.findElement(By.xpath("(//a[contains(text(),'Yes')])[2]")).click();
    	return this;
    }
    
    /**
     * Allows the user to resend the verification code email
     * 
     * @return the current page
     */
    public SecuritySettingsPage resendEmail() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.findElement(By.linkText("click here to resend")).click();
    	return this;
    }
    
    /**
     * Allows the current user to log out
     * 
     * @return the current page
     */
    public LoginPage clickLogout() {
    	logoutbtn.click();
    	log.info("Logout Button Clicked");
    	return PageFactory.initElements(driver, LoginPage.class);
    }
    
    /**
     * Allows the user to cancel disabling the multi-authentication
     * 
     * @return the current page
     */
    public SecuritySettingsPage cancelDisableMultiAuth() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.findElement(By.xpath("(//a[contains(text(),'No')])[2]")).click();
    	return this;
    }
    
    /**
     * Allows the user to cancel disabling the multi-authentication
     * 
     * @return the current page
     */
    public SecuritySettingsPage exitMultiAuthConfirm() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	this.cancelSubmitEnableMultiAuth();
    	return PageFactory.initElements(driver, SecuritySettingsPage.class);
    
    }
    
    
    /**
     * Tests whether the enable multi authentication button is visible
     * 
     * @return true or false
     */
    public boolean isEnableMultiAuthPresent() {
    	if ( this.isElementPresent(enableMultiAuth) ){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Tests whether the confirmation button is visible
     * 
     * @return true or false
     */
    public boolean isConfirmEnableMultiAuthPresent() {
    	if (confirmMultiAuthbtn.isDisplayed() ) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Tests whether the confirmation button is visible
     * 
     * @return true if the confirmation button is detected
     */
    public boolean isCancelEnableMultiAuthPresent() {
    	if (cancelMultiAuthbtn.isDisplayed() ) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Tests whether the user-inputed email matches with what appears in the email box
     * 
     * @return true if email matches
     */
    public boolean isEmailMatching(String emailAddress){
    	if (emailAddress.matches(emailAddressField.getAttribute("value")) ) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * Tests whether the user-inputed phone matches with what appears in the mobile number box
     * 
     * @return true if phone number matches
     */
    public boolean isPhoneNumberMatching(String phonenumber){
    	if (phonenumber.matches(mobilePhoneField.getAttribute("value"))){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Tests whether the verification code box is present
     * 
     * @return true if the verification box is visible
     */
    public boolean isVerificationCodeBoxPresent() {
    	if (verificationCodeField.isDisplayed()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    /**
     * Tests whether the verification error is present
     * 
     * @return true if the error is disabled
     */
    public boolean isVerificationErrorPresent(String error) {
    	if (driver.findElement(By.xpath("//div[@class='form-element ']/div[@class='form-errors']")).getText().trim()
    			.equals(error))
    		return true;
    	else
    		return false;
    }
   
    
    /**
     * Tests whether the verification message is present
     * 
     * @return true if the error is disabled
     */
    public boolean isVerificationMessagePresent(String msg) {
    	if (driver.findElement(By.xpath("//div[@id='div_verify_enable_code']/label/p[@class='html-secure']"))
    			.getText().contains(msg))
    		return true;
    	else
    		return false;
    }
    
    
    /**
     * Tests whether the verification error is present
     * 
     * @return true if the error is disabled
     */
    public boolean isBadPhoneNumMessagePresent(String msg) {
    	if (driver.findElement(By.xpath("//div[@class='form-element ']/div[@class='form-errors']"))
    			.getText().contains(msg))
    		return true;
    	else
    		return false;
    }
}