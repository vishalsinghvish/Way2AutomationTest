package com.pack.base;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseSetup {
	
	private WebDriver driver;
	static String driverPath = "D:\\AMCAT\\STS_Practice_workSpace\\Selenium_practice\\drivers\\"; 
	
	public WebDriver getDriver(){
		return driver;
	}
	
	private void setDriver(String browserType, String appURL){
		switch(browserType){
		case "chrome" : 
			driver = initChromeDriver(appURL);
			break;
			
		case "firefox" : 
			driver = initFirefoxDriver(appURL);
			break;
			
			default:
			System.out.println("browser : "+ browserType +"is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		
		}
	}

	private WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching to FIREFOX ...");
		System.setProperty("webdriver.chrome.driver","D:\\AMCAT\\STS_Practice_workSpace\\Selenium_practice\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching to CHROME ...");
		System.setProperty("webdriver.chrome.driver","D:\\AMCAT\\STS_Practice_workSpace\\Selenium_practice\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	@Parameters({"browserType","appURL"})
	
	@BeforeClass
	public void TestBaseSetupStart(String browserType,String appURL){
		try{
			String log4jConfigPath = "D:\\AMCAT\\Git_Repo\\Way2AutomationTest\\src\\com\\pack\\log4j.properties";
			PropertyConfigurator.configure(log4jConfigPath);
			setDriver(browserType,appURL);
		}
		catch(Exception e){
			System.out.println("Error...." +e.getStackTrace());
		}
	}
//	
//	@AfterClass
//	public void TearDown(){
//		driver.quit();
//	}

	public static void main(String[] args) {

	}

}
