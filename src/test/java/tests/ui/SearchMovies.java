package tests.ui;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import core.APIMethods;
import core.RestSession;
import io.restassured.response.Response;
import library.MovieUtils;
import library.TestBase;
import tests.ui.pages.HomeNavigationPage;
import tests.ui.pages.ListMoviesPage;

public class SearchMovies extends TestBase{
	
	@BeforeClass(groups= {"ui"})
	public void paginationSetupData() throws Exception {
		Object[][] data = getData(this.getClass().getMethod("paginationSetupData"));
		RestSession session = new RestSession();
		
		for(int i=0; i < data.length; i++) {
			@SuppressWarnings("unchecked")
			Response resp = session.sendRequest(APIMethods.CREATE_MOVIE, MovieUtils.getMovieBody((Map<String,String>) data[i][0]));
            String _id = resp.body().jsonPath().get("id");            
            Assert.assertEquals(resp.getStatusCode(),208);
		}				
	}
	
	@Test(groups= {"ui"})
	public void verifyPaginationEntrycount() {
		HomeNavigationPage home = new HomeNavigationPage(driver());
		home.lauchApp();
		ListMoviesPage listMovie = home.navigateToListMovies();
		listMovie.selectRowCount(5);
		Assert.assertEquals(listMovie.verifyPaginationRowCount(10), true);
	}
	
	@Test(groups = {"ui"})
	public void verifyDataSorting() {
		HomeNavigationPage home = new HomeNavigationPage(driver());
		home.lauchApp();
		ListMoviesPage listMovie = home.navigateToListMovies();
		Assert.assertEquals(listMovie.sortMovieColumn("name"), true);   
		Assert.assertEquals(listMovie.sortMovieColumn("rating"), true);   
	}
}
