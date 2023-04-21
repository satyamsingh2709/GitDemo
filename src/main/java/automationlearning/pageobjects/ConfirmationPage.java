package automationlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath = "//h1[@class=\"hero-primary\"]")
	WebElement messageLocation;
	public String validateConfirmationMessage() {
		String confirmMessage = messageLocation.getText();
		return confirmMessage;
		
	}
}
