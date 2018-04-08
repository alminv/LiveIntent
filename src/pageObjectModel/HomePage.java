package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "a[href = '/campaign-manager/conversion-trackers']") WebElement conversionTrackersLink;

	public WebElement getConversionTrackersLink() {
		return conversionTrackersLink;
	}
}
