package com.w2a.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

public class  Page {
	public static WebDriver driver;// static as all child pages should use the same driver reference.
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") 
			+"\\src\\test\\resources\\com\\w2a\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports extentRep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser; 
	public static TopMenu menu;
	
	public static void quit() {
		driver.quit();		
	}	
	/*
	 * Logging
	 * Properties
	 * Excel
	 * Extent Reports
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	public Page() {
		if(driver==null) {
						
			try {
				fis = new 	FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\com\\w2a\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Conf File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new 	FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			//Browser config from Jenkins or use from program itself 
			if(System.getenv("browser") != null && !System.getenv("browser").isEmpty())//Check id browser selection is done in jenkins
			{
				browser = System.getenv("browser"); // set browser selection from Jenkkins
			}else {
				browser=config.getProperty("browser");// else set from config.properties file.
			}
			config.setProperty("browser",browser);	
			
			
			//select browser to launch
			if(config.getProperty("browser").contentEquals("firefox")){
				System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\executables\\geckodriver.exe"); // We need to use this is latest firefox driver is being used
				driver = new FirefoxDriver();	
			}else if(config.getProperty("browser").contentEquals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\executables\\chromedriver.exe");					
				log.debug("Chrome Launched");
				Map<String,Object> prefs= new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications",2);
				prefs.put("credentials_enable_service",false);
				prefs.put("profile.password_manager_enabled",false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs",prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");				
				driver = new ChromeDriver(options);			
			}
			else if(config.getProperty("browser").contentEquals("ie")) {
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")
						+"\\src\\test\\resources\\com\\w2a\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to "+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
			menu= new TopMenu(driver);			
		}
	}
	//Common functions to reduce locator code
public static void click(String locator) {
		
		if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			}else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).click();
			}
		log.debug("Clicking on element" + locator); 
		test.log(LogStatus.INFO, "Clicking on " + locator);
	}
	public static void type(String locator,String value) {
		if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);		
		}else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);	
			}else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);	
			}	
		log.debug("Typing in element:" + locator + " Value:" + value); 
		test.log(LogStatus.INFO, "Typing in " + locator + " Entered : "+ value);
	}
	WebElement dropdown;
	public void select(String locator,String value) {
		if(locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));		
		}else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));	
			}else if (locator.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(OR.getProperty(locator)));	
			}		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.debug("Selecting :" + locator + " Value Selected:" + value); 
		test.log(LogStatus.INFO, "Selecting from dropdown " + locator + " Value is  : "+ value);
	}
	
	public boolean IsElementPresent(By by) {
	try {
	driver.findElement(by);
	return true;
	}catch(NoSuchElementException e) {
		return false;
	}
}
	public static void verifyEquals(String expected, String actual) throws IOException {
		try {	
			Assert.assertEquals(expected,actual);
		}catch(Throwable t) {
			Utilities.captureScreenShot();
			//Report NG
			Reporter.log("<BR> Verification Failure" + t.getMessage() +"<br>");
			Reporter.log("<a target = '_blank' href=" + Utilities.ScreenShotName +">"
					+ "<img src =" + Utilities.ScreenShotName +" height=100 width=100 </a><br><br>");			
			//Extent Reports
			test.log(LogStatus.FAIL,"Verification failed : "+ t.getMessage());
			test.log(LogStatus.FAIL,test.addScreenCapture(Utilities.ScreenShotName));
	 	}
	}
}
