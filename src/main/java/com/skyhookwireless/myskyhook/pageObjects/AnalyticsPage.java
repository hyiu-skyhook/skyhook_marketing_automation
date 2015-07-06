package com.skyhookwireless.myskyhook.pageObjects;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AnalyticsPage extends AbstractPageObject {
	

    
    @FindBy(xpath = "//div[@class='cell']/div[@class='floatleft iconbutton active']/div")
    private WebElement activeTimeScale;
    
    @FindBy(xpath = "//div[@class='cell']/div[@data-value='day']")
    private WebElement weeklyTimeScale;
    
    @FindBy(xpath = "//div[@class='cell']/div[@data-value='year']")
    private WebElement yearlyTimeScale;
   
    @FindBy(xpath = "//div[@class='analytics-label']/label[@class='analytics-info-date']")
    private WebElement chartDates;
    
    @FindBy(id = "radio-unique-users")
    private WebElement uniqueUsersRadioBtn;
    
    @FindBy(id = "radio-unique-requests")
    private WebElement uniqueRequestsRadioBtn;
    
    @FindBy(linkText = "Back to the world view")
    private WebElement zoomToWorldMap;
 
    By projectBox = By.xpath("//div[@id='s2id_app_dropdown']/a/span");
    By projectSearchBox = By.xpath("//div[@class='select2-search']/input");
    By csvExport = By.linkText("CSV Export");
    By chartInfo = By.xpath("//svg[@id='chart1']/text");
    By countryChart = By.xpath("//svg[@id='chart3']/g");
    By mapScale = By.cssSelector(properties.getProperty("analytics.mapScale"));
    By mapColor = By.cssSelector("#map > div > div:nth-child(1) > div > svg > g > g:nth-child(2) > g:nth-child(1) > g > path[fill='#169fda']");
    By mapUS = By.cssSelector("#map > div > div:nth-child(1) > div > svg > g > g:nth-child(2) > g:nth-child(1) > g > path:nth-child(728)");
    By graphYAxisMax = By.cssSelector(properties.getProperty("analytics.yAxisMax"));
    By backToWorldView = By.linkText("Back to the world view");
    
    public AnalyticsPage(WebDriver driver) {
    	
	super(driver);
	// Assert title.
	Assert.assertTrue(driver.getTitle().contains(properties.getProperty("analytics.title")));
	log.info("Title is correct");
    }
    

    /**
     * Switch view to unique users
     * @return
     */
    public AnalyticsPage clickUniqueUsersBtn() {
    uniqueUsersRadioBtn.click();
    log.info("Switched to the unique users view");
    return this;
    }
    
    /**
     * Switch view to location requests
     * @return
     */
    public AnalyticsPage clickLocationRequestsBtn() {
    uniqueRequestsRadioBtn.click();
    log.info("Switched to the unique requests view");
    return this;
    }
    
    /**
     * Switch view to location requests
     * @return
     */
    public AnalyticsPage clickCSVExport() {
    try {
        driver.findElement(csvExport).click();
        log.info("Clicked CSV Export");
    	Thread.sleep(10000);
    } catch (Exception e) {
    	log.error("CSV Export not found");
    }
    return this;
    }
    
    /**
     * Allows the user to click the weekly time tab
     * 
     */
    public AnalyticsPage selectWeekly() {
	weeklyTimeScale.click();
	log.info("Switched to weekly time scale for graphs");
	return this;
    }
    
    /**
     * Allows the user to click the weekly time tab
     * 
     */
    public AnalyticsPage selectYearly() {
	yearlyTimeScale.click();
	log.info("Switched to yearly time scale for graphs");
	return this;
    }
    
    /**
     * Switch to precision project if not currently on one
     * @return
     */
    public AnalyticsPage selectPrecisionProject() {
    if ( !this.getCurrentProject().contains("PRE") ) {
    	driver.findElement(projectBox).click();
    	driver.findElement(projectSearchBox).sendKeys("PRE" + Keys.ENTER);
    } 
    return this;
    }
    
    /**
     * Switch to accelerator project if not currently on one
     * @return
     */
    public AnalyticsPage selectAcceleratorProject() {
    if ( !this.getCurrentProject().contains("ACC") ) {
    	driver.findElement(projectBox).click();
    	driver.findElement(projectSearchBox).sendKeys("ACC" + Keys.ENTER);
    } 
    return this;
    }
    
    /**
     * Switch to hyperlocal project if not currently on one
     * @return
     */
    public AnalyticsPage selectHyperlocalProject() {
    if ( !this.getCurrentProject().contains("HYP") ) {
    	driver.findElement(projectBox).click();
    	driver.findElement(projectSearchBox).sendKeys("HYP" + Keys.ENTER);
    } 
    return this;
    }
    
    /**
     * Returns the current project
     * @return
     */
    public String getCurrentProject() {
    return driver.findElement(projectBox).getText();
    }
    
    /**
     * Return the current highlighted time period
     * Output can be weekly, monthly, or yearly
     */
    public String getTimePeriod() {
	return activeTimeScale.getText();
    }
    
    /**
     * Return the current highlighted time period
     * Output can be weekly, monthly, or yearly
     */
    public String getYAxisMax() {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    WebElement yaxis =wait.until(ExpectedConditions.visibilityOfElementLocated(graphYAxisMax)); 
	return yaxis.getText();
    }
    
    /**
     * Validate the graph range for monthly time scale 
     */
    public void verifyGraphMonthly() {
    String[] dates;
    dates= chartDates.getText().split("[(—)]");

    DateTime currentDateTime = new DateTime();
    DateTimeFormatter dateFormat = DateTimeFormat.forPattern("MMMM dd, yyyy");
    DateTime start_dt = dateFormat.parseDateTime(dates[1].trim());
    DateTime end_dt = dateFormat.parseDateTime(dates[2].trim());
    
    // Compare current date with chart end date
    Assert.assertEquals(end_dt.getYear(), currentDateTime.getYear());	
    Assert.assertEquals(end_dt.getMonthOfYear(), currentDateTime.getMonthOfYear());
    if (currentDateTime.getDayOfMonth() != 1)
    Assert.assertEquals(end_dt.getDayOfMonth(), currentDateTime.getDayOfMonth() - 1);	
    
    // Compare expected and actual start date
    Assert.assertEquals(start_dt.getMonthOfYear(), currentDateTime.getMonthOfYear() - 2);
    Assert.assertEquals(start_dt.getDayOfMonth(), 1);	
    
    // Range 
    Assert.assertTrue(Days.daysBetween(start_dt, end_dt).getDays() < 92 );
    Assert.assertTrue(Days.daysBetween(start_dt, end_dt).getDays() > 59);
    }
    
    /**
     * Validate the graph range for weekly time scale 
     */
    public void verifyGraphWeekly() {
    String[] dates;
    dates= chartDates.getText().split("[(—)]");

    DateTime currentDateTime = new DateTime();
    DateTimeFormatter dateFormat = DateTimeFormat.forPattern("MMMM dd, yyyy");
    DateTime start_dt = dateFormat.parseDateTime(dates[1].trim());
    DateTime end_dt = dateFormat.parseDateTime(dates[2].trim());
    
    // Compare current date with chart end date
    Assert.assertEquals(end_dt.getYear(), currentDateTime.getYear());	
    Assert.assertEquals(end_dt.getMonthOfYear(), currentDateTime.getMonthOfYear());
    if (currentDateTime.getDayOfMonth() != 1)
    Assert.assertEquals(end_dt.getDayOfMonth(), currentDateTime.getDayOfMonth() - 1);	
    
    // Compare expected and actual start date
    Assert.assertEquals(start_dt.getDayOfMonth(), currentDateTime.minusDays(7).getDayOfMonth());	
    
    // Range 
    Assert.assertEquals(Days.daysBetween(start_dt,  end_dt).getDays(), 6 );
    }

    /**
     * Validate the graph range for yearly time scale 
     */
    public void verifyGraphYearly() {
    String[] dates;
    dates= chartDates.getText().split("[(—)]");

    DateTime currentDateTime = new DateTime();
    DateTimeFormatter dateFormat = DateTimeFormat.forPattern("MMMM dd, yyyy");
    DateTime start_dt = dateFormat.parseDateTime(dates[1].trim());
    DateTime end_dt = dateFormat.parseDateTime(dates[2].trim());
    DateTime startofYear = dateFormat.parseDateTime("January 1, 2015");
    
    // Compare current date with chart end date
    Assert.assertEquals(end_dt.getYear(), currentDateTime.getYear());	
    Assert.assertEquals(end_dt.getMonthOfYear(), currentDateTime.getMonthOfYear());
    if (currentDateTime.getDayOfMonth() != 1)
    Assert.assertEquals(end_dt.getDayOfMonth(), currentDateTime.minusDays(1).getDayOfMonth() );	
    
    // Compare expected and actual start date
    Assert.assertEquals(start_dt.getYear(), 2015);
    Assert.assertEquals(start_dt.getMonthOfYear(), 1);
    Assert.assertEquals(start_dt.getDayOfMonth(), 1);	
    
    // Range 
    Assert.assertEquals(Days.daysBetween(start_dt, end_dt).getDays(),  Days.daysBetween(startofYear, currentDateTime.minusDays(1)).getDays());
    
    }
    
    /**
     * Verify if the max makes sense
     */
    public void verifyYAxis() {
    Assert.assertFalse(getYAxisMax().equals("0"));
    Assert.assertTrue(getYAxisMax().contains("K") || getYAxisMax().contains("M"));
    }
    
    
    /**
     * Verify if the csv export file is good
     */
    public void verifyCSVExportLR() {
    File dataExport = new File(properties.getProperty("csv.downloadDirectory"));
    FileFilter nameFilter = new WildcardFileFilter("*-Location-Requests*.csv");
    File[] csvFile = dataExport.listFiles(nameFilter);
    if (csvFile[0].exists()) {
    	log.info(csvFile[0]);
    	Assert.assertTrue(csvFile[0].getTotalSpace() > 0);
    	deleteFile(csvFile[0]);
    	}
    }
    
    /**
     * Verify if the csv export file is good
     */
    public void verifyCSVExportUU() {
    File dataExport = new File(properties.getProperty("csv.downloadDirectory"));
    FileFilter nameFilter = new WildcardFileFilter("*-Unique-Users*.csv");
    File[] csvFile = dataExport.listFiles(nameFilter);
    log.info(csvFile[0]);
    Assert.assertTrue(csvFile[0].exists());
    Assert.assertTrue(csvFile[0].getTotalSpace() > 0);
    deleteFile(csvFile[0]);
    }
    
    /**
     * Verify if there is data to populate the graph
     * @return
     */
    public boolean isDataAvailable() {
    
    if (this.isElementPresent(chartInfo) ) {
    	Assert.assertTrue(driver.findElement(chartInfo).getText().equals("No Data Available."));
    	log.error("Please load data"); 
    	return false;
    }
    else
    	return true;		
    }
    
    /**
     * Verify if there is map scale
     * @return
     */
    public boolean isMapAvailable() {
    if ( this.isElementPresent(mapScale)  ) {
    	Assert.assertTrue(this.isElementPresent(mapColor));
    	// Try clicking the most populous location and then exiting out
    	driver.findElement(mapUS).click();
    	if (this.isElementPresent(mapUS))
    		driver.findElement(mapUS).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(backToWorldView));
     	zoomToWorldMap.click();
    	return true;
    }
    else
    	return false;		
    }

    /**
     * Verify if there is bars in the chart
     * For precision location
     * @return
     */
    public boolean isChartAvailable(String chartName) {
    if ( this.isElementPresent(By.xpath("//svg[@id='" + chartName +"']/g")) ) {
    	// Verify that 'No Data available is not visible 
    	Assert.assertFalse(isElementPresent(By.xpath("//svg[@id='" + chartName +"']/text")));
    	// Verify there are 5 bars in the graph
    	Assert.assertEquals(driver.findElements(By.xpath("//*[@id='" + chartName + "']/g/g/g[3]/g/g/g/g/g")).size(), 5);
    	return true;
    }
    else
    	return false;		
    }
    
    /**
     * Verify if there is bars in the chart
     * For accelerator
     * @return
     */
    public boolean isDemographicsAvailable(String chartName) {
    if ( !this.isElementPresent(By.xpath("//svg[@id='" + chartName +"']/text")) ) {
    	// Number of split sections in the pie chart
    	int numOfArcs= driver.findElements(By.cssSelector("#" + chartName + " > svg > g > g.arc")).size();
    	// Only female and male for gender
    	if (chartName.equals("gender"))
    		Assert.assertTrue( numOfArcs >= 2);
    	else
    		Assert.assertTrue( numOfArcs >= 5);;
    	// Number of cross-sections should equal the number of legends
    	Assert.assertEquals( numOfArcs, 
    			driver.findElements(By.cssSelector("#" + chartName + " > svg > g > g.legend")).size() );
    	return true;
    }
    else
    	return false;		
    }
    
    /**
     * See if persona data is available
     * @return
     */
    public boolean isPersonaAvailable() {
    	 if ( !this.isElementPresent(By.xpath("//svg[@id='leftPersonas']/text")) ) 
    		 return true;
    	 else
    		 return false;
    }
    
    /**
     * The method will delete the file 
     * specified by the path, then verify 
     * that the delete is successful
     * @param input - name of the file to be deleted
     */
    public void deleteFile(File inputFile) {
    boolean result;
    result = FileUtils.deleteQuietly(inputFile);
    Assert.assertTrue(result);
    }
}
