package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjectModel.ConversionTrackersView;
import pageObjectModel.CreateConversionTrackerModal;
import pageObjectModel.HomePage;
import pageObjectModel.LoginPage;

import org.apache.commons.io.FileUtils;


public class TestCases {

	private WebDriver driver;	
	private WebDriverWait wait;
	private LoginPage loginPage;
	private HomePage homePage;
	private ConversionTrackersView conversionTrackersView;
	private CreateConversionTrackerModal createConversionTrackerModal;
	private int totalNumberOfTrackers;

	@BeforeClass
	public void setUpEnvironment() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Almin\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://platform.liveintent.com/login");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		conversionTrackersView = new ConversionTrackersView(driver);
		createConversionTrackerModal = new CreateConversionTrackerModal(driver);
	}
	
	@Parameters({"email", "password"})
	@Test
	public void loginTest(String email, String password) {
		loginPage.getEmailTextField().sendKeys(email);
		loginPage.getPasswordTextField().sendKeys(password);
		loginPage.getLogInButton().click();		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[text() = 'testAutomation testAutomation']"), 1));
		Assert.assertEquals(driver.getCurrentUrl(), "https://platform.liveintent.com/campaign-manager");
	}

	@Test(dependsOnMethods = {"loginTest"})
	public void openConversionTrackersTest() {
		homePage.getConversionTrackersLink().click();
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//button[text() = 'Create Conversion Tracker']"), 1));
		Assert.assertEquals(driver.getCurrentUrl(), "https://platform.liveintent.com/campaign-manager/conversion-trackers");
	}

	@Test(dependsOnMethods = {"openConversionTrackersTest"})
	public void verifyConversionTrackersViewContent() {
		SoftAssert softAssert = new SoftAssert();

		//Verify labels		
		softAssert.assertEquals(conversionTrackersView.getConversionTrackersLabel().getText(), "Conversion Trackers");
		softAssert.assertEquals(conversionTrackersView.getViewLabel().getText(), "View Conversion Trackers for");

		//Verify dropdowns
		conversionTrackersView.getViewTrackersDropdown().click();
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = 'All Accounts']")).size(), 2);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = 'Selected Accounts']")).size(), 1);
		conversionTrackersView.getRowsDropdown().click();
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '10 Rows']")).size(), 2);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '5 Rows']")).size(), 1);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '25 Rows']")).size(), 1);

		//Verify buttons
		softAssert.assertNotNull(conversionTrackersView.getFirstButton());
		softAssert.assertNotNull(conversionTrackersView.getPreviousButton());
		softAssert.assertNotNull(conversionTrackersView.getNextButton());
		softAssert.assertNotNull(conversionTrackersView.getLastButton());

		//Verify displayed number of trackers
		wait.until(ExpectedConditions.elementToBeClickable(conversionTrackersView.getTrackersTable()));
		int trackersInTable = conversionTrackersView.getTrackersTable().findElements(By.cssSelector("tr")).size()-1;
		totalNumberOfTrackers = conversionTrackersView.getTotalNumberOfTrackers();
		softAssert.assertEquals(trackersInTable, conversionTrackersView.getDisplayedNumberOfTrackers());		
		
		softAssert.assertAll();
	}

	@Test(dependsOnMethods = {"verifyConversionTrackersViewContent"})
	public void openCreateConversionTrackerModal() {
		conversionTrackersView.getCreateButton().click();
		createConversionTrackerModal = new CreateConversionTrackerModal(driver);
		wait.until(ExpectedConditions.elementToBeClickable(createConversionTrackerModal.getAdvertiserTextField()));
		Assert.assertEquals(createConversionTrackerModal.getConversionTrackerLabel().getText(), "Create Conversion Tracker");
	}

	@Test(dependsOnMethods = {"openCreateConversionTrackerModal"})
	public void verifyConversionTrackerModal() {
		SoftAssert softAssert = new SoftAssert();
		
		// Verify labels
		softAssert.assertEquals(createConversionTrackerModal.getAdvertiserLabel().getText(), "Advertiser");
		softAssert.assertEquals(createConversionTrackerModal.getTrackerNameLabel().getText(), "Tracker Name");
		softAssert.assertEquals(createConversionTrackerModal.getTypeLabel().getText(), "Type");
		softAssert.assertEquals(createConversionTrackerModal.getAttributionWindowLabel().getText(), "Attribution Window");
		
		// Verify type dropdown
		createConversionTrackerModal.getTypeDropdown().click();
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = 'LiveConnect']")).size(), 3);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = 'Image Pixel']")).size(), 1);

		// Verify attribution window dropdown
		createConversionTrackerModal.getNameTextField().click();
		createConversionTrackerModal.getAttributionWindowDropdown().click();
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '30 Days']")).size(), 2);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '14 Days']")).size(), 1);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '7 Days']")).size(), 1);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '3 Days']")).size(), 1);
		softAssert.assertEquals(driver.findElements(By.xpath("//span[text() = '1 Day']")).size(), 1);
		
		// Verify create tracker button
		softAssert.assertNotNull(createConversionTrackerModal.getCreateTrackerButton());
		softAssert.assertFalse(createConversionTrackerModal.getCreateTrackerButton().isEnabled());

		softAssert.assertAll();
	}

	@Test(dependsOnMethods = {"openCreateConversionTrackerModal"})
	public void verifyMandatoryFields() {
		SoftAssert softAssert = new SoftAssert();
				
		softAssert.assertFalse(createConversionTrackerModal.getCreateTrackerButton().isEnabled());
		
		// Verify advertiser search field
		createConversionTrackerModal.getAdvertiserTextField().click();		
		createConversionTrackerModal.getAdvertiserTextField().sendKeys("t");
		// Without a pause search is not working properly
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		createConversionTrackerModal.getAdvertiserTextField().sendKeys("e");
		createConversionTrackerModal.getAdvertiserTextField().sendKeys("s");
		createConversionTrackerModal.getAdvertiserTextField().sendKeys("t");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		createConversionTrackerModal.getAdvertiserTextField().sendKeys(Keys.DOWN);
		createConversionTrackerModal.getAdvertiserTextField().sendKeys(Keys.ENTER);
		softAssert.assertFalse(createConversionTrackerModal.getCreateTrackerButton().isEnabled());
		
		// Verify name text field
		createConversionTrackerModal.getNameTextField().sendKeys("Tracker Name");
		softAssert.assertTrue(createConversionTrackerModal.getCreateTrackerButton().isEnabled());

		softAssert.assertAll();		
	}

	@Test(dependsOnMethods = {"verifyMandatoryFields"}) 
	public void addNewConversionTracker() {
		createConversionTrackerModal.getCreateTrackerButton().click();
		wait.until(ExpectedConditions.invisibilityOf(createConversionTrackerModal.getTypeDropdown()));
		wait.until(ExpectedConditions.elementToBeClickable(conversionTrackersView.getSearchBox()));
		int newNumberOfTrackers = conversionTrackersView.getTotalNumberOfTrackers();
		Assert.assertEquals(newNumberOfTrackers, totalNumberOfTrackers+1);
	}

	@AfterClass
	public void finishTests() {
			driver.close();
	}

	/**
	 * Make screenshot when test fails.
	 */
	@AfterMethod(alwaysRun=true)
	public void catchExceptions(ITestResult result){
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	    String methodName = result.getName();
	    if(!result.isSuccess()){
	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(scrFile, new File("failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png"));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
}
