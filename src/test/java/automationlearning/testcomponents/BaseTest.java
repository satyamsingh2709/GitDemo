package automationlearning.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automationlearning.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	LandingPage landingPage;
	
	public WebDriver initializeBrowser() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//automationlearning//resources//config.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(option);	

		}
		
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.out.println("in firefox");
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("Edge")) {
			System.out.println("in edge");
			driver = new EdgeDriver();
		}
		else {
			System.out.println("wrong browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeBrowser();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to string
		//jackson binding dependency required and for FileUtil commons.io dependency required
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

		//string to hashmap conversion
		ObjectMapper objectMap = new ObjectMapper();
		List<HashMap<String,String>> data = objectMap.readValue(jsonContent,new TypeReference <List<HashMap<String,String>>>(){
		});
		return data;
	}
	
	public String screenshot(String testCaseName, WebDriver driver) throws IOException {
		System.out.println(3);
		TakesScreenshot ts = (TakesScreenshot)driver;
		System.out.println(4);
		File source = ts.getScreenshotAs(OutputType.FILE);
		System.out.println(5);
		File file = new File(System.getProperty("user.dir")+"//Reports//"+testCaseName+".png");
		System.out.println(6);
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//Reports//"+testCaseName+".png";
		
	}

}
