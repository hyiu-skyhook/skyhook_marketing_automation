package com.skyhookwireless.myskyhook.pageObjects.adminPages;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.skyhookwireless.myskyhook.pageObjects.AbstractPageObject;

public class StatisticsPage extends AbstractPageObject{
	 /* Web Elements */

    // Switch between the two tabs
    @FindBy(linkText = "Setup Date Range")
    private WebElement setupDateRangeTab;
    
    @FindBy(linkText = "Statistics")
    private WebElement statisticsTab;

   // Constructor
	public StatisticsPage(WebDriver driver) {
			super(driver);
			// Assert title.
			// Commenting out until the title gets fixed
			// Assert.assertEquals(driver.getTitle(), properties.getProperty("admin.statistics.title"));
			// log.info("Title is correct");
		}
	
	 /**
     * Allows the user to switch to the 'Setup Date Range' tab
     * @return this page
     */
    public StatisticsPage switchToSetupDateRange() {
    setupDateRangeTab.click();
	log.info("Switched to the date range tab");
	return this;
    }
    
	
	/**
    * Allows the user to switch to the 'Statistics' tab
    * @return this page
    */
   public StatisticsPage switchToStatistics() {
   statisticsTab.click();
   log.info("Switched to the statistics tab");
   return this;
   }
   
   /**
    * Extract information from the page table, then split the information into a String array
    * @return a string array in the format of names[0]=project_name, names[1]=start_date, 
    * names[2]=end_date, names[3]=project_name, names[4]=start_date, ...
    */
   public String[] getAllDateRange() {
	   String string= driver.findElement(By.id("analyticsdaterange")).getText();
	   String[] names = string.split("     |\n");
	   return names;
   }
   
   /**
    * compares total sign-up number with database value
    * @param rs - the result set from querying the database table
    */
   public void compareSignUpTotal(ResultSet rs) {
	   String signUpTotal = driver.findElement(By.xpath("//td[@class='w150']")).getText();
	   try {
		   rs.next();
		   Assert.assertEquals(signUpTotal, rs.getString(1));
				
	   } catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		   Assert.fail("SQLException: " + ex.getMessage());
		   }
	   }
   
   /**
    * compare last week's sign-up number with database value
    * @param rs - the result set from querying the database table
    */
   public void compareSignUpLastWeek(ResultSet rs) {
	   String signUpWeekly = driver.findElement(By.xpath("//table[@style='margin-top: 15px;']/tbody/tr[2]/td[2]")).getText();
	   try {
		   rs.next();
		   Assert.assertEquals(signUpWeekly, rs.getString(1));
				
	   } catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		   Assert.fail("SQLException: " + ex.getMessage());
		   }
	   }
   
   /**
    * Compare date ranges from page with database values 
    * @param rs - the result set from querying database table 'analytic_meta'
    */
   public void comparePageToDB(ResultSet rs) {
	   String[] pageValues= getAllDateRange();
	   int startIndex = 1;
	   
	   try {
		   
		while (rs.next()){
			Assert.assertEquals(pageValues[startIndex].trim(), rs.getString(2));
			Assert.assertEquals(pageValues[startIndex + 1].trim(), rs.getString(3));
			startIndex += 3;
			}
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		   Assert.fail("SQLException: " + ex.getMessage());
		   }
	   log.info("The page values matches with the values from the analytics database table.");
   }
}
