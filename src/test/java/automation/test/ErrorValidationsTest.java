package automation.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTest;
import automation.pageobjects.CartPage;
import automation.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{ 
	
	@Test
	public void submitOrder() throws Exception {
		landingPage.loginApplication("taro@gmail.com", "Testttaro23");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws Exception {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("test.sakura@gmail.com", "Sakura@23");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goTocart();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
