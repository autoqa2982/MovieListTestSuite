package tests.ui;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import library.TestBase;
import tests.ui.pages.CreateMoviePage;
import tests.ui.pages.HomeNavigationPage;
import tests.ui.pages.ListMoviesPage;

public class CreateMovies extends TestBase{
	
	@Test(groups={"ui"},dataProvider="getData")
	public void verifyMoviesCreated(Map<String,String> data) {
		HomeNavigationPage home = new HomeNavigationPage(driver());
		home.lanuchApp();
		CreateMoviePage createMovie = home.navigateToCreateMovie();
		createMovie.setCreateMovieDetails(data.get("setname"),data.get("setrating"),data.get("settime"));
		Assert.assertEquals(createMovie.createMovie(),"Movie inserted successfully");
		ListMoviesPage listMovie = home.navigateToListMovies();
		listMovie.verifyMovieInTheList(data.get("expname"),data.get("exprating"),data.get("exptime"));
	}
	

}
