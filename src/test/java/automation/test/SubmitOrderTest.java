package automation.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTest;
import automation.pageobjects.CartPage;
import automation.pageobjects.CheckoutPage;
import automation.pageobjects.ConfirmationPage;
import automation.pageobjects.OrderPage;
import automation.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws Exception {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goTocart();
		
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmPage = checkoutPage.SubmitOrder();
		
		Assert.assertTrue(confirmPage.verifyConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("test.sakura@gmail.com", "Sakura@23");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +  "\\src\\test\\java\\automation\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}

//HashMap<String, String> hashmap = new HashMap<String, String> ();
//hashmap.put("email","test.sakura@gmail.com");
//hashmap.put("password","Sakura@23");
//hashmap.put("productName", "ZARA COAT 3");
//
//HashMap<String, String> hashmap2 = new HashMap<String, String> ();
//hashmap2.put("email","taro@gmail.com");
//hashmap2.put("password","Testtaro23");
//hashmap2.put("productName",  "ADIDAS ORIGINAL");
