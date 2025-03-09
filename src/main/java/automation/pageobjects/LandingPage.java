package automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	//Page for landing page 
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		//sending driver to the abstract class
		super(driver);
		//initialization of driver from the stand alone test
		this.driver = driver;
		//initializes all the web elements to driver
		PageFactory.initElements(driver, this);
	}
	//PageFactoryElements
	//driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	//driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement login;
	
	//Login method
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		//this will land you in the product catalogue page, so let us create the object in this method itself. 
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
