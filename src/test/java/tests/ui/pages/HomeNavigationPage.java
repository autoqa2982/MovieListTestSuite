package tests.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Config;

public class HomeNavigationPage extends BasePage{
	
	
	@FindBy(xpath=".//a[text()='List Movies']")
	private WebElement linkMoviesList;
	
	@FindBy(xpath=".//a[text()='Create Movie']")
	private WebElement linkCreateMovie;
	

	public HomeNavigationPage(WebDriver driver) {
		super(driver);		
	}

	public void lanuchApp() {
		driver.get(Config.getProperty("appurl"));
	}
	
	public ListMoviesPage navigateToListMovies() {
		linkMoviesList.click();
		return new ListMoviesPage(driver);
	}
	
	public CreateMoviePage navigateToCreateMovie() {
		linkCreateMovie.click();
		return new CreateMoviePage(driver);
	}
	
	
	
}
