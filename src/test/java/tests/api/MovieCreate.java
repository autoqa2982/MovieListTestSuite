package tests.api;

import library.TestBase;
import core.APIMethods;
import core.Config;
import core.RestSession;
import io.restassured.response.Response;
import library.MovieUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class MovieCreate extends TestBase {

    @Test(dataProvider = "getAPIData",groups={"createMovie","api"})
    public void verifyMovieCreated(Map<String,String> data){
        try{
            //Create
            RestSession session = new RestSession();
            Response resp = session.sendRequest(APIMethods.CREATE_MOVIE, MovieUtils.getMovieBody(data));
            String _id = resp.body().jsonPath().get("id");
            
            Assert.assertEquals(resp.getStatusCode(),208);

            //Verify           
            resp = session.sendRequest(APIMethods.GET_MOVIE);
            List<Map<String,String>> movieList = resp.body().jsonPath().getList("data");
            boolean movieCreated = false;
            for(Map<String,String> movie : movieList) {
            	if(movie.get("_id").equals(_id)) {
            		movieCreated = true;break;
            	}
            }             
            Assert.assertEquals(movieCreated, true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
