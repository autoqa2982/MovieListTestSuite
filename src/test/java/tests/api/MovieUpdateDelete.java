package tests.api;

import core.APIMethods;
import core.Config;
import core.RestSession;
import io.restassured.response.Response;
import library.TestBase;
import library.ApiLibrary;
import library.MovieUtils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovieUpdateDelete extends TestBase {
 
    @Test(dependsOnGroups = {"createMovie"},groups= {"api"},priority=1)
    public void verifyMovieUpdate(){
        try{
            RestSession session = new RestSession();
            Response resp = session.sendRequest(APIMethods.GET_MOVIE);
            
            List<Map<String,String>> movieList = resp.body().jsonPath().getList("data");
            for(Map<String,String> movie : movieList) {
            	if(movie.get("name").equalsIgnoreCase("titanic")) {
            		Map<String,String> data = new HashMap<>();
            		data.put("time","1h44m");
            		data.put("name", movie.get("name"));
            		data.put("rating", String.valueOf(movie.get("rating")));
            		APIMethods.UPDATE_MOVIE.addMethodParam(movie.get("_id"));
            		resp = session.sendRequest(APIMethods.UPDATE_MOVIE, MovieUtils.getMovieBody(data));
            		break;
            	}
            }  
            Assert.assertEquals(resp.body().jsonPath().get("message"),"Movie updated!");
        }catch(Exception e){
           e.printStackTrace();
        }
    }

    @Test(dependsOnGroups = {"createMovie"},groups= {"api"},priority=2)
    public void deleteMovie(){
        try{
        	RestSession session = new RestSession();
        	ApiLibrary.deleteMovies();
            Response resp = session.sendRequest(APIMethods.GET_MOVIE);
            Assert.assertEquals(resp.body().jsonPath().getList("data"),null);           
        }catch(Exception e){
          e.printStackTrace();
        }
    }

}
