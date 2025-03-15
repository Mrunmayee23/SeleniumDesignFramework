package automation.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTest;
import automation.pageobjects.CartPage;
import automation.pageobjects.CheckoutPage;
import automation.pageobjects.ConfirmationPage;
import automation.pageobjects.LandingPage;
import automation.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test
	public void submitOrder() throws Exception {

		ProductCatalogue productCatalogue = landingPage.loginApplication("test.sakura@gmail.com", "Sakura@23");
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goTocart();
		
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmPage = checkoutPage.SubmitOrder();
		
		Assert.assertTrue(confirmPage.verifyConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
	}
}
