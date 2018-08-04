package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {
	
	private WebDriver driver;
	//Xpath of title of the book
	private By Title = By.xpath("//*[@id=\"result_0\"]/div/div/div/div[2]/div[1]/div[1]/a/h2");
	
	//Xpath of price of the book
	private By Price = By.xpath("//*[@id=\"result_0\"]/div/div/div/div[2]/div[2]/div[1]/div[2]/a/span[2]/span");
	
	//Xpath of Authors of the book
	private By Authors = By.xpath("//*[@id=\"result_0\"]/div/div/div/div[2]/div[1]/div[2]");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getproperties()
	{
		//Getting title of the book
		WebElement getTitle = driver.findElement(Title);
		System.out.println("Title of the book is : " + getTitle.getText());
		
		//Getting price of the book
		WebElement getPrice = driver.findElement(Price);
		System.out.println("Price of the book is :" + getPrice.getText());
		
		//Getting author of the book
		WebElement getAuthors = driver.findElement(Authors);
		System.out.println("The Book was written :" + getAuthors.getText());
	
	}

}
