package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

public class SVNPage extends AbstractPageObject {
    
	// SVN Build number
    @FindBy(xpath = "//table/tbody/tr[1]/td[2]")
    private WebElement buildNum;

	
	// Constructor
	public SVNPage(WebDriver driver) {
			super(driver);
			// Assert title.
			Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.svn.title"));
			log.info("Title is correct");
		}
		
	/**
	 * Compare the revision number with the one on the web-site
	 * 
	 * @return this page
	 */
	public void verifyRevisionNumber(int number) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(Integer.parseInt(buildNum.getText().trim() ) >= number );
	}
	
	
	/**
	 * Compare the revision number with the one on the web-site
	 * 
	 * @return this page
	 */
	public void verifySvnUrl(String path) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(
				By.xpath("//table/tbody/tr[2]/td[2]")).getText().contains(path) );
	}
}
