package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseTest.TestBaseSetup;

public class HomePage extends TestBaseSetup{
	
	private WebDriver driver;
	
	//CSS Selector for clicking on Category selection button
	private By Category = By.xpath("//*[@id=\"searchDropdownBox\"]");
	
	//CSS Selector of search button in the home page 
	private By SearchButton = By.cssSelector("#nav-search > form > div.nav-right > div > input");
	
	//CSS Selector of input search field
	private By EnterSearchText = By.cssSelector("#twotabsearchtextbox");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Getting title of the current page
	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	//Getting title of the home page
	public boolean verifyBasePageTitle() {
		String expectedPageTitle="Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
		return getPageTitle().contains(expectedPageTitle);
	}
	
	//Selecting book from the dropdown menu	
	public void SelectBook()
	{
		WebElement all = driver.findElement(Category);
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.presenceOfElementLocated(Category));
		all.click();
		Select books = new Select (driver.findElement(Category));
		books.selectByVisibleText("Books");
		
	}	
	
	//Entering the book name in the search field
	public void _enterbookname(String bookname) {
		
		WebElement searchtext = driver.findElement(EnterSearchText);
		
		if(searchtext.isDisplayed())
			
			searchtext.sendKeys(bookname);
	}
	
	//Click on the search button
	public ResultsPage Search(WebDriver driver)
	{
		WebElement SearchBtn = driver.findElement(SearchButton);
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.presenceOfElementLocated(SearchButton));
		SearchBtn.click();
		return new ResultsPage(driver);
	
	}
	
}
