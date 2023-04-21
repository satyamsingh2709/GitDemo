package automationlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import automationlearning.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;

	public CartPage(WebDriver driver) {	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class=\"cartSection\"]")
	List<WebElement>cartProductList;
	
	@FindBy(xpath="//button[text()=\"Checkout\"]")
	WebElement checkout;
	
//	List <WebElement> cartProductList = driver.findElements(By.xpath("//div[@class=\"cartSection\"]"));
//	String productName = "IPHONE 13 PRO";
//	boolean match = cartProductList.stream().anyMatch((cartProduct->cartProduct.findElement(By.xpath("h3")).getText().equalsIgnoreCase(productName)));
//	Assert.assertTrue(match);
	
	public List<WebElement> getCartProductList(){
		return cartProductList;
	}

	public boolean validateProductInCart(String productName) {
		boolean match = getCartProductList().stream().anyMatch((cartProduct->cartProduct.findElement(By.xpath("h3")).getText().equalsIgnoreCase(productName)));
		return match;
	}
	
	public PlaceOrderPage checkout() {
		checkout.click();
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}
}
