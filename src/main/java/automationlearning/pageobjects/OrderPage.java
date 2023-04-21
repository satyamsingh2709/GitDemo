package automationlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import automationlearning.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;

	public OrderPage(WebDriver driver) {	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr[@class=\"ng-star-inserted\"]//td[2]")
	List<WebElement>orderList;
	
	public List<WebElement> getOrderList(){
		return orderList;
	}

	public boolean validateProductInOrders(String productName) {
		boolean match = getOrderList().stream().anyMatch((order->order.getText().equalsIgnoreCase(productName)));
		return match;
	}
	

}
