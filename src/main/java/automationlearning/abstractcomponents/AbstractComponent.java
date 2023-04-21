package automationlearning.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@routerlink=\"/dashboard/cart\"]")
	WebElement cartButton;
	
	@FindBy(xpath = "//button[@routerlink=\"/dashboard/myorders\"]")
	WebElement orderPageButton;
	
	
	
	public void waitForElementToAppear(By findBy) {		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForEleToAppear(WebElement element) {		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToDisappear(WebElement elementToDisappear) {	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(elementToDisappear));
	}
	
	public void waitForElementToClickable(WebElement elementToClickable) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(elementToClickable));
	}
	
	public CartPage clickOnCart() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage clickOnOrder() {
		orderPageButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
