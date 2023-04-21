package automationlearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent{

	WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "(//span[@class=\"ng-star-inserted\"])[2]")
	WebElement clickOnCountry;
	
	@FindBy (xpath = "//a[text()=\"Place Order \"]")
	WebElement placeOrderButton;
	
	By country = By.xpath( "//input[@placeholder=\"Select Country\"]");

	public void selectCountry(String countryName) throws InterruptedException {
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(country),countryName).build().perform();	
		clickOnCountry.click();
		Thread.sleep(2000);
	}
	
	
	public ConfirmationPage placeOrder() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,3000)");
		waitForElementToClickable(placeOrderButton);
		Thread.sleep(2000);
		placeOrderButton.click();	
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
