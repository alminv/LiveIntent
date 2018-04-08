package pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConversionTrackersView {

	WebDriver driver;
	
	public ConversionTrackersView(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//h1[text() = 'Conversion Trackers']") WebElement conversionTrackersLabel;
	@FindBy(xpath = "//h3[text() = 'View Conversion Trackers for  ']") WebElement viewLabel;
	@FindBy(css = "div.select--dropdown") WebElement viewTrackersDropdown;
	@FindBy(css = "input[placeholder = 'Search']") WebElement searchBox;
	@FindBy(xpath = "(//div[@class = 'select--dropdown'])[2]") WebElement rowsDropdown;
	@FindBy(xpath = "//button[text() = 'First']") WebElement firstButton;
	@FindBy(xpath = "//button[text() = 'Previous']") WebElement previousButton;
	@FindBy(xpath = "//button[text() = 'Next']") WebElement nextButton;
	@FindBy(xpath = "//button[text() = 'Last']") WebElement lastButton;	
	@FindBy(xpath = "//button[text() = 'Create Conversion Tracker']") WebElement createButton;
	@FindBy(css = "table.ng-star-inserted") WebElement trackersTable;
	@FindBy(xpath = "//span[contains(text(), 'Showing')]") WebElement trackersNumber;
	
	public WebElement getConversionTrackersLabel() {
		return conversionTrackersLabel;
	}
	
	public WebElement getViewLabel() {
		return viewLabel;
	}
	
	public WebElement getViewTrackersDropdown() {
		return viewTrackersDropdown;
	}
	
	public WebElement getSearchBox() {
		return searchBox;
	}
	
	public WebElement getRowsDropdown() {
		return rowsDropdown;
	}
	
	public WebElement getFirstButton() {
		return firstButton;
	}
	
	public WebElement getPreviousButton() {
		return previousButton;
	}
	
	public WebElement getNextButton() {
		return nextButton;
	}
	
	public WebElement getLastButton() {
		return lastButton;
	}
	
	public WebElement getCreateButton() {
		return createButton;
	}
	
	public WebElement getTrackersTable() {
		return trackersTable;
	}
	
	public WebElement getTrackersNumber() {
		return trackersNumber;
	}
	
	public int getDisplayedNumberOfTrackers() {
		String numberOfTrackers = trackersNumber.getText();
		int displayedNumberOfTrackers = Integer.parseInt(numberOfTrackers.substring(numberOfTrackers.indexOf("- ")+2, numberOfTrackers.indexOf(" (")));
		return displayedNumberOfTrackers;
	}
	
	public int getTotalNumberOfTrackers() {
		String numberOfTrackers = trackersNumber.getText();
		int totalNumberOfTrackers = Integer.parseInt(numberOfTrackers.substring(numberOfTrackers.indexOf("(")+1, numberOfTrackers.indexOf(")")));
		return totalNumberOfTrackers;
	}
	
}
