package tests.ui.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateMoviePage extends BasePage{

	@FindBy(xpath="(.//label[text()='Name: ']/following-sibling::input)[1]")
	private WebElement textMovieName;
	
	@FindBy(xpath="(.//label[text()='Rating: ']/following-sibling::input)[1]")
	private WebElement textRating;
	
	@FindBy(xpath="(.//label[text()='Time: ']/following-sibling::input)[1]")
	private WebElement textTime;
	
	@FindBy(xpath=".//button[text()='Add Movie']")
	private WebElement btnAddMovie;
	
	@FindBy(xpath=".//button[text()='Update Movie']")
	private WebElement btnUpdateMovie;
	
	@FindBy(xpath=".//a[text()='Cancel']")
	private WebElement btnCancel;
	
	public CreateMoviePage(WebDriver driver) {
		super(driver);
	}
	
	public void setCreateMovieDetails(String name,String rating,String time) {
		textMovieName.clear();
		textMovieName.sendKeys(name);
		textRating.clear();
		textRating.sendKeys(rating);
		textTime.clear();
		textTime.sendKeys(time);		
	}
	
	public String createMovie() {
		btnAddMovie.click();
		if(waitForAlert(5)) {
			Alert alert = driver.switchTo().alert();
			String movieInsert = alert.getText();
			alert.accept();
			return movieInsert;
		}
		return null;
	}
	
	public String updateMovie() {
		btnUpdateMovie.click();
		if(waitForAlert(5)) {
			Alert alert = driver.switchTo().alert();
			String movieUpdate = alert.getText();
			alert.accept();
			return movieUpdate;
		}
		return null;
	}
	
	public void cancelAddMovie() {
		btnCancel.click();		
	}
	
	
	
	
	
	

}
