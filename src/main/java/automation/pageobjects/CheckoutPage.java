package automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	//Page for landing page 
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		//sending driver to the abstract class
		super(driver);
		//initialization of driver from the stand alone test
		this.driver = driver;
		//initializes all the web elements to driver
		PageFactory.initElements(driver, this);
	}
	//PageFactoryElements
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	WebElement countryName;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		this.countryName.click();
		
	}
	
	public ConfirmationPage SubmitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
