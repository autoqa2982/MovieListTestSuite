package tests.ui.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ListMoviesPage extends BasePage{

	@FindBy(xpath=".//div[@class='rt-tbody']//div[@role='row']")
	private List<WebElement> movieRows;
		
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
	
	@FindBy(xpath=".//div[text()='Rating']")
	private WebElement ratingCol;
	
	@FindBy(xpath=".//div[text()='Name']")
	private WebElement nameCol;
	
    private By movieCols = By.xpath("div[@role='gridcell'][not(.//span/div)]");
	
	private By updateLink = By.xpath("//div[text()='Upadate']");
	
	private By deleteLink = By.xpath("//div[text()='Delete']");
	
	private By nameColumn = By.xpath("div[@role='gridcell'][2]");
	
	private By ratingColumn = By.xpath("div[@role='gridcell'][3]");
	
	
	public ListMoviesPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean verifyMovieInTheList(String name, String rating, String time) {
		nameSearch.sendKeys(name.trim());
		ratingSearch.sendKeys(rating.trim());
		
		//boolean resultFound = false;
		List<String> params = new ArrayList<>();
		params.add(name);params.add(rating);params.add(time);
		
		
		if(movieRows.size() == 0) {return false;}
		
		for(WebElement row : movieRows) {
			for(int i=1; i< row.findElements(movieCols).size(); i++ ) {
				if(!params.contains(row.findElements(movieCols).get(i).getText())) {
					return false;
				}	
			}		
		}
		
		return true;
	}
	
	public List<String> performUpdate(){
		List<String> extractInfo = new ArrayList<>();
		for(WebElement row : movieRows) {
			for(int i=1; i< row.findElements(movieCols).size(); i++ ) {
				extractInfo.add(row.findElements(movieCols).get(i).getText());
			}
			row.findElement(updateLink).click();
			break;
		}
		return extractInfo;		
	}
	
	public List<String> performDelete(){
		List<String> extractInfo = new ArrayList<>();
		for(WebElement row : movieRows) {
			for(int i=1; i< row.findElements(movieCols).size(); i++ ) {
				extractInfo.add(row.findElements(movieCols).get(i).getText());
			}
			row.findElement(deleteLink).click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
			break;
		}
		return extractInfo;		
	}
	
	public void selectRowCount(int num) {
		Select sel = new Select(selectRowsPerPage);
		sel.selectByVisibleText(String.valueOf(num)+" rows");
	}
	
	public boolean verifyPaginationRowCount(int expectedRowCount) {
		int actualRowCount = 0;
		actualRowCount = movieRows.size();
		buttonNext.click();
		actualRowCount = actualRowCount+ movieRows.size();
		return(actualRowCount == expectedRowCount);		
	}
	
	public boolean sortMovieColumn(String colName) {
		List<String> unsortedList =  new ArrayList<>();
		List<String> sortedList =  new ArrayList<>();
		if(colName.trim().equalsIgnoreCase("name")) {
			movieRows.forEach((ele)->  unsortedList.add(ele.findElement(nameColumn).getText()));
			nameCol.click();
			movieRows.forEach((ele)->  sortedList.add(ele.findElement(nameColumn).getText()));
		}else if(colName.trim().equalsIgnoreCase("rating")) {
			movieRows.forEach((ele)->  unsortedList.add(ele.findElement(ratingColumn).getText()));
			ratingCol.click();
			movieRows.forEach((ele)->  sortedList.add(ele.findElement(ratingColumn).getText()));
		}
		Collections.sort(unsortedList);
		return(sortedList.equals(unsortedList));		
	}
	
	

}
