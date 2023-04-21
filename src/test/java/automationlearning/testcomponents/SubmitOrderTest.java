package automationlearning.testcomponents;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.ConfirmationPage;
import automationlearning.pageobjects.OrderPage;
import automationlearning.pageobjects.PlaceOrderPage;
import automationlearning.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{

//	String productName = "IPHONE 13 PRO";
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));		
		
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.clickOnCart();
		boolean match = cartPage.validateProductInCart(input.get("productName"));
		Assert.assertTrue(match);
		
		
		PlaceOrderPage placeOrderPage = cartPage.checkout();
		placeOrderPage.selectCountry("India");
		
		
		ConfirmationPage confirmationPage = placeOrderPage.placeOrder();
		String confirmMessage = confirmationPage.validateConfirmationMessage();		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test()
	public void validateOrderTest() {
		String productName = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication("automationlearning@gmail.com","Automation@123");		
		OrderPage orderPage = productCatalogue.clickOnOrder();
		
		boolean match = orderPage.validateProductInOrders(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException{
//		HashMap <String, String> map = new HashMap <String,String>();
//		map.put("email", "automationlearning@gmail.com");
//		map.put("password", "Automation@123");
//		map.put("productName", "IPHONE 13 PRO");
//		
//		HashMap <String, String> map1 = new HashMap <String,String>();
//		map1.put("email", "cba@gmail.com");
//		map1.put("password", "123@gmail.coM");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Personal\\Learning\\SeleniumFrameworkDesign\\src\\main\\java\\automationlearning\\data\\dataProvider.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	

}
