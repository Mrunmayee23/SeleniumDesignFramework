package automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class ProductCatalogue  extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		//sending driver to the abstract class
		super(driver);
		//initialization of driver from the stand alone test
		this.driver = driver;
		//initializes all the web elements to driver
		PageFactory.initElements(driver, this);
	}
	//PageFactoryElements
	@FindBy(css = ".mb-3")  //List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	List <WebElement> products;
	
	@FindBy(css = ".ng-animating") //driver.findElement(By.cssSelector(".ng-animating"))
	WebElement animation;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	
	//gets the list of all products visible on the page
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod =  getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws Exception {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(animation);
	}
	
}
