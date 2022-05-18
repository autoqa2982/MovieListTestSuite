package tests.ui;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.APIMethods;
import core.RestSession;
import io.restassured.response.Response;
import library.TestBase;
import tests.ui.pages.CreateMoviePage;
import tests.ui.pages.HomeNavigationPage;
import tests.ui.pages.ListMoviesPage;

public class CreateMovies extends TestBase{
	
	
	
	@Test(groups={"ui"},dataProvider="getData",priority=1)
	public void verifyMoviesCreated(Map<String,String> data) {
		HomeNavigationPage home = new HomeNavigationPage(driver());
		home.lauchApp();
		CreateMoviePage createMovie = home.navigateToCreateMovie();
		createMovie.setCreateMovieDetails(data.get("setname"),data.get("setrating"),data.get("settime"));
		Assert.assertEquals(createMovie.createMovie(),"Movie inserted successfully");
		ListMoviesPage listMovie = home.navigateToListMovies();
		Assert.assertEquals(listMovie.verifyMovieInTheList(data.get("expname"),data.get("exprating"),data.get("exptime")), true);
	}
	
	@Test(groups={"ui"}, dependsOnMethods= "verifyMoviesCreated",priority=2)
	public void verifyMovieUpdated() {
		HomeNavigationPage home = new HomeNavigationPage(driver());
		home.lauchApp();
		ListMoviesPage listMovie = home.navigateToListMovies();
		List<String> movieUpdateInfo = listMovie.performUpdate();
		CreateMoviePage updateMovie = new CreateMoviePage(driver());
		updateMovie.setCreateMovieDetails(movieUpdateInfo.get(0), movieUpdateInfo.get(1),"Updated Time");
		Assert.assertEquals(updateMovie.updateMovie(),"Movie updated successfully");
		listMovie = home.navigateToListMovies();
		Assert.assertEquals(listMovie.verifyMovieInTheList(movieUpdateInfo.get(0), movieUpdateInfo.get(1),"Updated Time"), true);		
	}
	
	@Test(groups={"ui"}, dependsOnMethods= {"verifyMoviesCreated"},priority=3)
	public void verifyMovieDeleted() throws InterruptedException {
		HomeNavigationPage home = new HomeNavigationPage(driver());
		home.lauchApp();
		ListMoviesPage listMovie = home.navigateToListMovies();
		List<String> movieDeleteInfo = listMovie.performDelete();		
		listMovie = home.navigateToListMovies();
		Assert.assertEquals(listMovie.verifyMovieInTheList(movieDeleteInfo.get(0), movieDeleteInfo.get(1),movieDeleteInfo.get(2)), false);		
	}
	
	
	
	
	
	
	
	
	

}
