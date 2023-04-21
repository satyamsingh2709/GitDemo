package automationlearning.testcomponents;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(option);
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("userEmail")).sendKeys("automationlearning@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Automation@123");
		driver.findElement(By.id("login")).click();
		
		//LandingPage landingPage = new LandingPage(driver);
		
		String productName = "IPHONE 13 PRO";
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();
		
		List <WebElement> cartProductList = driver.findElements(By.xpath("//div[@class=\"cartSection\"]"));
		
		boolean match = cartProductList.stream().anyMatch((cartProduct->cartProduct.findElement(By.xpath("h3")).getText().equalsIgnoreCase(productName)));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//button[text()=\"Checkout\"]")).click();
		
		Actions action = new Actions(driver);
		
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//span[@class=\"ng-star-inserted\"])[2]")).click();
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2600)");

		Thread.sleep(2000);
	//	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()=\"Place Order \"]"))));
		driver.findElement(By.xpath("//a[text()=\"Place Order \"]")).click();
		
		String confirmMessage = driver.findElement(By.xpath("//h1[@class=\"hero-primary\"]")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		System.out.println("Passed");
	}

}
