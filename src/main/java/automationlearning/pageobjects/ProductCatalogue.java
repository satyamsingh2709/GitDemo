package automationlearning.pageobjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	


	By findBy = By.cssSelector(".mb-3");
	By productLocator = By.cssSelector(".card-body button:last-of-type");
	By elementToBeLocated = By.cssSelector("#toast-container");


	
	public List<WebElement> getProductList(){
		waitForElementToAppear(findBy);
		return products;
	}
	
	public WebElement getProductName(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductName(productName);
		prod.findElement(productLocator).click();
		waitForElementToAppear(elementToBeLocated);
		waitForElementToDisappear(driver.findElement(By.cssSelector(".ng-animating")));
		
	}
	


}
