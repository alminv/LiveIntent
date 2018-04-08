package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateConversionTrackerModal {
	
	private WebDriver driver;
	
	public CreateConversionTrackerModal(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h4[text() = 'Create Conversion Tracker']") WebElement conversionTrackerLabel;
	@FindBy(xpath = "//label[text() = 'Advertiser']") WebElement advertiserLabel;
	@FindBy(xpath = "//label[text() = 'Tracker Name']") WebElement trackerNameLabel;
	@FindBy(xpath = "//label[text() = 'Type']") WebElement typeLabel;
	@FindBy(xpath = "//label[text() = 'Attribution Window']") WebElement attributionWindowLabel;
	@FindBy(css = "input[placeholder = 'Type an advertiser name']") WebElement advertiserTextField;
	@FindBy(css = "input[name = 'name']") WebElement nameTextField;
	@FindBy(xpath = "(//button[@class = 'dropdown regular'])[3]") WebElement typeDropdown;
	@FindBy(xpath = "(//button[@class = 'dropdown regular'])[4]") WebElement attributionWindowDropdown;
	@FindBy(xpath = "//button[text() = 'Create Tracker']") WebElement createTrackerButton;
	
	public WebElement getConversionTrackerLabel() {
		return conversionTrackerLabel;
	}
	
	public WebElement getAdvertiserLabel() {
		return advertiserLabel;
	}
	
	public WebElement getTrackerNameLabel() {
		return trackerNameLabel;
	}
	
	public WebElement getTypeLabel() {
		return typeLabel;
	}
	
	public WebElement getAttributionWindowLabel() {
		return attributionWindowLabel;
	}
	
	public WebElement getAdvertiserTextField() {
		return advertiserTextField;
	}
	
	public WebElement getNameTextField() {
		return nameTextField;
	}
	
	public WebElement getTypeDropdown() {
		return typeDropdown;
	}
	
	public WebElement getAttributionWindowDropdown() {
		return attributionWindowDropdown;
	}
	
	public WebElement getCreateTrackerButton() {
		return createTrackerButton;
	}
}
