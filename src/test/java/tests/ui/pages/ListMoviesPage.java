package tests.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListMoviesPage extends BasePage{

	@FindBy(xpath=".//div[@class='rt-tbody']//div[@role='row']")
	private List<WebElement> movieRows;
		
	private By col = By.xpath("//div[@role='gridcell'][not(.//span/div)]");
	
	@FindBy(xpath="(.//div[@class='rt-thead -filters']//input)[2]")
	private WebElement nameSearch;
	
	@FindBy(xpath="(.//div[@class='rt-thead -filters']//input)[3]")
	private WebElement ratingSearch;
	
	@FindBy(xpath=".//select[@aria-label='rows per page']")
	private WebElement selectRowsPerPage;
	
	@FindBy(xpath=".//button[text()='Next']")
	private WebElement buttonNext;
	
	@FindBy(xpath=".//button[text()='Previous']")
	private WebElement buttonPrev;
	

	public ListMoviesPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean verifyMovieInTheList(String name, String rating, String time) {
		nameSearch.sendKeys(name.trim());
		ratingSearch.sendKeys(rating.trim());
		
		boolean resultFound = false;
		
		for(WebElement row : movieRows) {
			for(WebElement col : row.findElements(col)) {
				System.out.print(col.getText()+" -- ");
			}
			System.out.println();
		}
		
		return false;
	}
	

}
