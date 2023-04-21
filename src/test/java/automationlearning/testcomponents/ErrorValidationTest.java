package automationlearning.testcomponents;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorValidation"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		landingPage.loginApplication("automationleang@gmail.com","Automation@123");	
	//	Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password");
		
		Assert.assertTrue(false);	
		
	}
	@Test(groups= {"ErrorValidation"})
	public void productValidation() throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("cba@gmail.com","123@gmail.coM");		
		
		String productName = "IPHONE 13 PRO";
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.clickOnCart();
		boolean match = cartPage.validateProductInCart("IPHONE 13 PROO");
		Assert.assertFalse(match);
	}

}
