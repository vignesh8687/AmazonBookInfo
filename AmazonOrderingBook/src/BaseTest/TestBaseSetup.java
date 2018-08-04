package BaseTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseSetup {

	private WebDriver driver;
	public static WebDriverWait wait;
	
	//Driver Path
	static String driverPath = "D:\\Softwares\\Drivers\\";
	

	public  WebDriver getDriver() {
		return driver;
	}
	
	//Setting Browser Type
	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
			
		case "IE":
			driver = initIEDriver(appURL);
			break;			
			
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}
	
	//Chrome driver initiation
	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath
				+ "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(appURL);
		return driver;
	}

	//Firefox driver initiation
	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", driverPath +"geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(appURL);
		return driver;
		
		
	}
	
	//InternetExplore driver initiation
		private static WebDriver initIEDriver(String appURL) {
			System.out.println("Launching internet explorer browser..");
			System.setProperty("webdriver.ie.driver", driverPath+ "IEDriverServer.exe");
			WebDriver driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(appURL);
			return driver;
		}
	
	
	//Specifying browser type and application url from testng parameter file
	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	} 
	
	//Taking Screenshot after test case failure
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			 WebDriver augmentedDriver = new Augmenter().augment(driver);
		        File screenshot = ((TakesScreenshot)augmentedDriver).
		                            getScreenshotAs(OutputType.FILE);
		        FileUtils.copyFile(screenshot, new File("ErrorScreenshots\\" + testResult.getName() + "-" 
					+ Arrays.toString(testResult.getParameters()) +  ".jpg"));
			//OutputStream output = new FileOutputStream("c:\\data\\output-text.txt", true); --> To Append File
			
			//Overwrite existing file
			OutputStream output = new FileOutputStream(screenshot, false); 
			output.close();
	   }        
	}
	
	// Closing the current driver instance
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}