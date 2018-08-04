package Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import BaseTest.TestBaseSetup;
import Pages.HomePage;
import Pages.ResultsPage;

public class SearchBooksTest extends TestBaseSetup {

	private WebDriver driver;
	private HomePage homepage;
	private ResultsPage resultpage;
	
	
	
	@BeforeClass
	public void setUp()
	{
		driver=getDriver();
		
	}
	
	@Test
	public void SearchBooksTest() throws InterruptedException {
		homepage = new HomePage(driver);
		homepage.SelectBook();
		Thread.sleep(500);
		homepage._enterbookname("Data Catalog");
		resultpage = homepage.Search(driver);
		resultpage.getproperties();
	}
	
	
}
